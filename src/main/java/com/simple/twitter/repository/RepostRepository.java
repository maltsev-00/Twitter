package com.simple.twitter.repository;

import com.simple.twitter.model.Repost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepostRepository extends JpaRepository<Repost, UUID> {

    void deleteRepostById(UUID twitId);

    Repost findRepostById(UUID id);

}
