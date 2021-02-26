package com.simple.twitter.repository;

import com.simple.twitter.model.Twitter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TwitterRepository extends JpaRepository<Twitter, UUID> {

    Twitter findTwitterById(UUID id);

    Twitter findTwitterByTwitId(UUID id);

    Page<Twitter> findTwitterByUsernameTwitter(String username, Pageable pageRequest);


}
