package com.simple.twitter.mappers;

import com.simple.twitter.model.Twitter;
import com.simple.twitter.model.dto.twit.TwitterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TwitterMapper {

    TwitterDto toTwitterDto(Twitter town);

}
