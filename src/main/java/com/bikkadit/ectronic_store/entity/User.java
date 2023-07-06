package com.bikkadit.ectronic_store.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    private String userId;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email",unique = true)
    private String email;

    @Column(name = "user_gender")
    private String gender;

    @Column(name = "user_about")
    private String about;

    @Column(name = "user_password")
    private String password;

    private String imageName;

}
