package com.simple.twitter.repository;

import com.simple.twitter.model.Twit;
import com.simple.twitter.model.Twitter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TwitRepository extends MongoRepository<Twit,UUID> {

    Twit findTwitById(UUID id);

    Twit deleteTwitById(UUID id);
}
