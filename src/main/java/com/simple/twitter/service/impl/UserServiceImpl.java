package com.simple.twitter.service.impl;

import com.simple.twitter.exception.ResourceNotFoundException;
import com.simple.twitter.mappers.UserMapper;
import com.simple.twitter.model.Repost;
import com.simple.twitter.model.Subscriber;
import com.simple.twitter.model.User;
import com.simple.twitter.model.dto.user.UserDto;
import com.simple.twitter.repository.UserRepository;
import com.simple.twitter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto repostTwit(String username,Repost repost) {

        User user = userRepository.findUserByUsername(username);

        if(user!=null){

            user.setReposts(new HashSet<>(Arrays.asList(repost)));
            return userMapper.toUserDto(userRepository.save(user));

        }
        throw new ResourceNotFoundException("Username : "+username+" not founded in database");
    }

    @Override
    public UserDto subscribeToUser(String userForSubscribe, String currentUserUsername) {

        User userFind = userRepository.findUserByUsername(userForSubscribe);

        User currentUser = userRepository.findUserByUsername(currentUserUsername);

        if(userFind!=null){

            userFind.setSubscribers(new HashSet<>(Arrays.asList(new Subscriber(UUID.randomUUID(),currentUser.getUsername()))));
            log.info("IN subscribeToUser user save : {}",userFind);

            return userMapper.toUserDto(userRepository.save(userFind));
        }

        throw new ResourceNotFoundException("Username : "+currentUserUsername+" not founded in database");
    }

    @Override
    public List<UserDto> getOneUserInfo(String username) {
        return userRepository.findUserByUsernameContainingIgnoreCase(username).stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

}
