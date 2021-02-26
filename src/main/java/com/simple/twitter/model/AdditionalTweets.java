package com.simple.twitter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "additionalTweets")
public class AdditionalTweets {

    @Id
    private UUID id;

    @Length(min = 10,max = 20)
    private String text;

}
