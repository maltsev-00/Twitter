package com.simple.twitter.service;

import com.simple.twitter.model.Twit;

import java.util.UUID;

public interface TwitService {

    Twit getTwit(UUID id);

    Twit deleteTwit(UUID id);

    Twit addTwit(Twit twit);
}
