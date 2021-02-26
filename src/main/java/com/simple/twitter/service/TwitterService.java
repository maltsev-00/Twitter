package com.simple.twitter.service;

import com.simple.twitter.mappers.TwitterMapper;
import com.simple.twitter.model.AdditionalTweets;
import com.simple.twitter.model.Like;
import com.simple.twitter.model.Twit;
import com.simple.twitter.model.Twitter;
import com.simple.twitter.model.dto.twit.TwitDto;
import com.simple.twitter.model.dto.twit.TwitterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TwitterService {

    TwitterDto addNewTwitter(String username, Twit twit);

    TwitterDto addNewAdditionTwit(UUID id, AdditionalTweets tweets);

    TwitterDto saveLike(UUID idTwit,String username);

    TwitterDto deleteTwitter(Twitter twitter);

    Page<TwitDto> showAllTwittersByUsername(String username, Pageable pageRequest);

    TwitDto getOneTwit(UUID idTwit);

    List<TwitDto> getTwitsSubscribe(String name);
}
