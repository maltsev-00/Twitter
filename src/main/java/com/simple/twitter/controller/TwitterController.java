package com.simple.twitter.controller;

import com.simple.twitter.model.AdditionalTweets;
import com.simple.twitter.model.Like;
import com.simple.twitter.model.Twit;
import com.simple.twitter.model.Twitter;
import com.simple.twitter.model.dto.twit.TwitDto;
import com.simple.twitter.model.dto.twit.TwitterDto;
import com.simple.twitter.service.TwitterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController("twitters")
@RequiredArgsConstructor
@Slf4j
public class TwitterController {

    private final TwitterService twitterService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("add-new-twit")
    public TwitterDto addNewTwitInDatabase(HttpServletRequest request,
                                           @Valid @RequestBody Twit twit) {
        log.info("Save twit : {}",twit);
        log.info(request.getUserPrincipal().getName());
        return twitterService.addNewTwitter(request.getUserPrincipal().getName(),twit);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("add-new-addition-twit")
    public TwitterDto addNewAdditionalTwitInDatabase(@NotNull @RequestParam("idTwitter") UUID idTwitter,
                                        @Valid @RequestBody AdditionalTweets twit) {
        log.info("Save additional twit : {}",twit);
        return twitterService.addNewAdditionTwit(idTwitter,twit);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("like-twit")
    public TwitterDto likeTwit(@NotNull @RequestParam("idTwit") UUID idTwit, HttpServletRequest request) {

        String username = request.getUserPrincipal().getName();

        log.info("Like twit with username : {}",username);
        log.info("ID twit :{}",idTwit);
        return twitterService.saveLike(idTwit,username);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("delete-twit")
    public TwitterDto likeTwit(@Valid @RequestBody Twitter twitter) {
        log.info("Delete twit : {}",twitter);
        return twitterService.deleteTwitter(twitter);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("show-twits")
    public Page<TwitDto> showTwits(@PageableDefault(size = 2) Pageable pageRequest,
                                   HttpServletRequest request) {

        String username = request.getUserPrincipal().getName();
        log.info("Show twits by username : {}",username);

        return twitterService.showAllTwittersByUsername(username,pageRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("show-twit")
    public TwitDto getTwit(@NotNull @RequestParam("idTwit") UUID idTwit) {
        log.info("Show twit by id : {}",idTwit);
        return twitterService.getOneTwit(idTwit);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("show-twit-subscribe")
    public List<TwitDto> getTwitsSubscribeInDataBase(HttpServletRequest request){
        log.info("Show all twits subscribes ");
        return twitterService.getTwitsSubscribe(request.getUserPrincipal().getName());
    }


}
