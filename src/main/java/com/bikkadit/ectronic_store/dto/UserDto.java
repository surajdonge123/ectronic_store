package com.bikkadit.ectronic_store.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userId;

    @NotBlank
    @Size(max = 50,min = 3,message = "Invalid user Name!! Name should contain minimum 3 letters")
    private String name;

    @Email
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",message = "Invalid Email")
    private String email;

    @NotBlank
    @Size(min = 4,max = 10,message = "Invalid details")
    private String gender;

    @NotBlank
    @Size(min = 10,max = 255,message = "Invalid details !! Describe more about yourself")
    private String about;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)[a-zA-Z\\\\d]{8,}$",message = "Invalid details !!.Password must contains minimum one Capital one Small letter and one Number ")
    private String password;


    private String imageName;
}
