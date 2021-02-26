package com.simple.twitter.service.impl;

import com.simple.twitter.model.Twit;
import com.simple.twitter.repository.TwitRepository;
import com.simple.twitter.service.TwitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TwitServiceImpl implements TwitService {

    private final TwitRepository twitRepository;

    @Override
    public Twit getTwit(UUID id) {
        return twitRepository.findTwitById(id);
    }

    @Override
    public Twit deleteTwit(UUID id) {
        return twitRepository.deleteTwitById(id);
    }

    @Override
    public Twit addTwit(Twit twit) {
        return twitRepository.save(twit);
    }
}
