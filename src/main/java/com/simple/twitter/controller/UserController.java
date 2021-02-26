package com.simple.twitter.controller;

import com.simple.twitter.model.Repost;
import com.simple.twitter.model.dto.user.UserDto;
import com.simple.twitter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController("users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("repost-twit")
    public UserDto repostTwitInDatabase(@Valid @RequestBody Repost repost,
                                     HttpServletRequest request){
        log.info("Repost twit : {}",repost);
        return userService.repostTwit(request.getUserPrincipal().getName(),repost);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("subscribe-to-user")
    public UserDto subscribeToUserInDatabase(@NotNull @RequestParam("userForSubscribe") String userForSubscribe,
                                          HttpServletRequest request){
        log.info("User for subscribe : {}",userForSubscribe);
        return userService.subscribeToUser(userForSubscribe,request.getUserPrincipal().getName());
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("get-user")
    public List<UserDto> getUser(@NotNull @RequestParam("username") String username){
        log.info("Find user by username : {}",username);
        return userService.getOneUserInfo(username);
    }

}
