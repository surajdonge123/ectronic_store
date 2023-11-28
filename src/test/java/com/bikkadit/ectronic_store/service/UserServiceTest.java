package com.bikkadit.ectronic_store.service;

import com.bikkadit.ectronic_store.dto.UserDto;
import com.bikkadit.ectronic_store.entity.User;
import com.bikkadit.ectronic_store.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    User user;

    @BeforeEach
    public void init(){
   user = User.builder().name("suraj").email("surajdonge@1gmail.com")
                .gender("male").password("suraj132").imageName("suraj.png")
                .about("i am java developer ").userId("abcd1").build();


    }
    @Test
    public void createUserTest(){
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        UserDto user1 = userService.createUser(modelMapper.map(user, UserDto.class));
        System.out.println(user1);
        Assertions.assertNotNull(user1);
        Assertions.assertEquals("suraj",user1.getName());

    }

    @Test
    public void updateUserTest(){

        String userId="sdfhsjdfjs";
       UserDto userDto= UserDto.builder().name("Suraj Donge")
                .email("surajdonge16@gmail.com")
                .gender("male")
                .about("I'm jr java developer")
                .imageName("abc.png").build();

        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        UserDto updatedUser = userService.updateUser(userDto, userId);

        System.out.println(updatedUser.getName());
        Assertions.assertNotNull(updatedUser);
        System.out.println(updatedUser.getEmail());
        Assertions.assertEquals(userDto.getName(),updatedUser.getName());


    }


}
