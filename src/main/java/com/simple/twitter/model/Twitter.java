package com.simple.twitter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "twitters")
public class Twitter {

    @Id
    private UUID id;

    @NotNull
    private String usernameTwitter;

    @NotNull
    private UUID twitId;

    @NotNull
    private LocalDate dateCreated;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "twitter_id",referencedColumnName = "id")
    private Set<AdditionalTweets> additionalTweets;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "twitter_id",referencedColumnName = "id")
    private Set<Like> likes;

}
