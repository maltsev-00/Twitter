package com.simple.twitter.mappers;

import com.simple.twitter.model.User;
import com.simple.twitter.model.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
}
