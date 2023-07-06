package com.bikkadit.ectronic_store.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userId;

    private String name;

    private String email;
    private String gender;
    private String about;
    private String password;
    private String imageName;
}
