package com.simple.twitter.service;

import com.simple.twitter.model.User;
import com.simple.twitter.model.dto.user.AuthenticationUser;
import com.simple.twitter.model.dto.user.AuthenticationToken;
import com.simple.twitter.model.dto.user.RegistrationUserDto;
import com.simple.twitter.model.dto.user.UserDto;

public interface UserServiceSecurity {

    UserDto saveUser(RegistrationUserDto newUser);

    User findByUsername(String username);

    AuthenticationToken authorization(AuthenticationUser request);

    UserDto deleteUser(UserDto user);

    UserDto reestablishUser(UserDto userDto);

    UserDto deleteCurrentUser(String username);

    UserDto saveManager(RegistrationUserDto registrationUserDto);
}
