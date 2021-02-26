package com.simple.twitter.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationUser {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
