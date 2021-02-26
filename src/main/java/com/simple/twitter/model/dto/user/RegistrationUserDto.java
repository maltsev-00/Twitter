package com.simple.twitter.model.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationUserDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

}
