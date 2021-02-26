package com.simple.twitter.repository;

import com.simple.twitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findUserById(UUID id);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findUserByUsername(String username);

    List<User> findUserByUsernameContainingIgnoreCase(String username);

    User deleteUserById(UUID id);
}
