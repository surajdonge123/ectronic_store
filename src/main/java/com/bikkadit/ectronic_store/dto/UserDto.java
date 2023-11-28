package com.bikkadit.ectronic_store.dto;
import com.bikkadit.ectronic_store.validation.ImageNameValid;
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

    @NotBlank(message = "Password should not be null")
   // @Pattern(regexp = "^.(?=.{8,})(?=..[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=]).$",
          //   message = "Password should contains minimum one uppercase,lowercase,digit and one special characters")
    private String password;

    @ImageNameValid
    private String imageName;

}
