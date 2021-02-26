package com.simple.twitter.service;

import com.simple.twitter.model.Twit;

import java.util.UUID;

public interface RepostService {

    void deleteRepost(UUID twitId);

    Twit getTwitByRepost(UUID id);


}
