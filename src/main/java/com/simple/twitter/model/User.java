package com.simple.twitter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;

    @NotEmpty
    @Length(max = 20)
    @Column(name = "username")
    private String username;

    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "user_role")
    private String userRole;

    @NotEmpty
    @Column(length = 90,name = "password")
    @Length(max = 10)
    private String password;

    @Column(name = "delete_check")
    private Boolean deleteCheck;

    @Column(name = "date_delete")
    private LocalDate dateDelete;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private Set<Repost> reposts;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private Set<Subscriber> subscribers;

}
