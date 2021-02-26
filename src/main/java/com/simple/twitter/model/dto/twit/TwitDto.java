package com.simple.twitter.model.dto.twit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitDto {

    @NotEmpty
    @Length(max = 200)
    private String text;

}
