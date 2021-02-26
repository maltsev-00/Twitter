package com.simple.twitter.mappers;

import com.simple.twitter.model.Twit;
import com.simple.twitter.model.dto.twit.TwitDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TwitMapper {

    TwitDto toTwitDto(Twit twit);

}
