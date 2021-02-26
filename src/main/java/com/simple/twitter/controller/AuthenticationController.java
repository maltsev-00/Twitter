package com.simple.twitter.controller;

import com.simple.twitter.model.User;
import com.simple.twitter.model.dto.user.AuthenticationToken;
import com.simple.twitter.model.dto.user.AuthenticationUser;
import com.simple.twitter.model.dto.user.RegistrationUserDto;
import com.simple.twitter.model.dto.user.UserDto;
import com.simple.twitter.service.UserServiceSecurity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final UserServiceSecurity userServiceSecurity;

    @PostMapping("registration")
    public UserDto registrationUser(@RequestBody @Valid RegistrationUserDto registrationUserDto) {
        log.info("New user {}", registrationUserDto);
        return userServiceSecurity.saveUser(registrationUserDto);
    }

    @PostMapping("authentication")
    public AuthenticationToken authentication(@Valid @RequestBody AuthenticationUser user) {
        log.info("Authentication user : {}",user.toString());
        return userServiceSecurity.authorization(user);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("delete-user")
    public UserDto deleteUserInDatabase(@Valid @RequestBody UserDto user){
        log.info("Delete user : {}",user);
        return userServiceSecurity.deleteUser(user);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("reestablish-user")
    public UserDto reestablishUser(@RequestBody UserDto userDto){
        log.info("User for reestablish : {}",userDto);
        return userServiceSecurity.reestablishUser(userDto);
    }

    @PreAuthorize("hasRole('USER')")
    public UserDto deleteCurrentUserInDatabase(HttpServletRequest request){
        String username = request.getUserPrincipal().getName();
        log.info("Delete user by username : {}",username);
        return userServiceSecurity.deleteCurrentUser(username);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("registration")
    public UserDto registrationManager(@RequestBody @Valid RegistrationUserDto registrationUserDto) {
        log.info("New user {}", registrationUserDto);
        return userServiceSecurity.saveManager(registrationUserDto);
    }


}
