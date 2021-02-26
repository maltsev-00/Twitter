package com.simple.twitter.service.impl;

import com.simple.twitter.exception.BadRequestException;
import com.simple.twitter.exception.ResourceNotFoundException;
import com.simple.twitter.mappers.TwitMapper;
import com.simple.twitter.mappers.TwitterMapper;
import com.simple.twitter.model.AdditionalTweets;
import com.simple.twitter.model.Like;
import com.simple.twitter.model.Twit;
import com.simple.twitter.model.Twitter;
import com.simple.twitter.model.dto.twit.TwitDto;
import com.simple.twitter.model.dto.twit.TwitterDto;
import com.simple.twitter.repository.TwitterRepository;
import com.simple.twitter.service.RepostService;
import com.simple.twitter.service.TwitService;
import com.simple.twitter.service.TwitterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TwitterServiceImpl implements TwitterService {

    private final TwitterRepository twitterRepository;
    private final TwitService twitService;
    private final RepostService repostService;
    private final TwitterMapper twitterMapper;
    private final TwitMapper twitMapper;


    @Override
    public TwitterDto addNewTwitter(String username, Twit twit) {

     Twitter twitter = Twitter.builder()
               .id(UUID.randomUUID())
               .twitId(twit.getId())
               .usernameTwitter(username)
               .dateCreated(LocalDate.now())
               .build();

            twitService.addTwit(twit);

            return twitterMapper.toTwitterDto(twitterRepository.save(twitter));

    }

    @Override
    public TwitterDto addNewAdditionTwit(UUID id,AdditionalTweets tweets) {

        Twitter twitter = twitterRepository.findTwitterByTwitId(id);

        if(twitter!=null){
            twitter.setAdditionalTweets(Collections.singleton(tweets));
            return twitterMapper.toTwitterDto(twitter);
        }
        throw new BadRequestException("Twit with id : "+id+" not founded ");

    }

    @Override
    public TwitterDto saveLike(UUID idTwit,String username) {

        Twitter twitter = twitterRepository.findTwitterByTwitId(idTwit);

            if(twitter!=null){

                  twitter.setLikes(new HashSet<>(Arrays.asList(new Like(UUID.randomUUID(),username))));

                  twitterRepository.save(twitter);
                  log.info("IN saveLike save like {}",twitter);

                  return twitterMapper.toTwitterDto(twitter);

            }
        throw new ResourceNotFoundException("Twit with id : "+idTwit+" not founded ");
    }

    @Override
    public TwitterDto deleteTwitter(Twitter twitter) {

        twitService.deleteTwit(twitter.getId());
        twitterRepository.delete(twitter);
        repostService.deleteRepost(twitter.getTwitId());

        return twitterMapper.toTwitterDto(twitter);
    }

    @Override
    public Page<TwitDto> showAllTwittersByUsername(String username, Pageable pageRequest) {

       Page<Twitter> twitter = twitterRepository.findTwitterByUsernameTwitter(username,pageRequest);

       List<TwitDto> twits = new LinkedList<>();

       twitter.forEach(twitter1 -> {
           twits.add(twitMapper.toTwitDto(twitService.getTwit(twitter1.getTwitId())));
       });

        return new PageImpl<>(twits, pageRequest, twitter.getTotalElements());
    }

    @Override
    public TwitDto getOneTwit(UUID idTwit) {
        return twitMapper.toTwitDto(twitService.getTwit(idTwit));
    }

    @Override
    public List<TwitDto> getTwitsSubscribe(String name) {


        return null;
    }


}
