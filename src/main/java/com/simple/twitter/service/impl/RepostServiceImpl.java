package com.simple.twitter.service.impl;

import com.simple.twitter.exception.ResourceNotFoundException;
import com.simple.twitter.model.Repost;
import com.simple.twitter.model.Twit;
import com.simple.twitter.repository.RepostRepository;
import com.simple.twitter.service.RepostService;
import com.simple.twitter.service.TwitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RepostServiceImpl implements RepostService {

    private final RepostRepository repostRepository;
    private final TwitService twitService;

    @Override
    public void deleteRepost(UUID twitId) {
        repostRepository.deleteRepostById(twitId);
    }

    @Override
    public Twit getTwitByRepost(UUID id) {
        Repost repostFind = repostRepository.findRepostById(id);
        if(repostFind==null){
            throw new ResourceNotFoundException("Repost with id : "+id+" not founded ");
        }

        return twitService.getTwit(repostFind.getTwitId());
    }
}
