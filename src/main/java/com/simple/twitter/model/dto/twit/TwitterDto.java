package com.simple.twitter.model.dto.twit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterDto {

    @NotNull
    private UUID id;

    @NotNull
    private String usernameTwitter;

    @NotNull
    private UUID twitId;

    @NotNull
    private LocalDate dateCreated;

}
