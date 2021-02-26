package com.simple.twitter.service;

import com.simple.twitter.model.Repost;
import com.simple.twitter.model.dto.user.UserDto;

import java.util.List;

public interface UserService {

    UserDto repostTwit(String username,Repost repost);

    UserDto subscribeToUser(String user, String currentUserUsername);

    List<UserDto> getOneUserInfo(String username);

}
