package com.bikkadit.ectronic_store.service;
import com.bikkadit.ectronic_store.dto.UserDto;
import java.util.List;
public interface UserService {


    //create user
    UserDto createUser(UserDto userDto);

    //Update User
    UserDto updateUser(UserDto userDto,String userId);

    //Delete User
    void deleteUser(String userId);

    //GetAll User
    List<UserDto> getAllUsers();

    //Get User By id
    UserDto getUserById(String userId);

    //Get User By email

    UserDto getUserByEmail(String email);

    List<UserDto> searchUser(String keyword);
}
