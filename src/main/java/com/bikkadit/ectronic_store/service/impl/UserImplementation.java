package com.bikkadit.ectronic_store.service.impl;

import com.bikkadit.ectronic_store.constant.AppConstant;
import com.bikkadit.ectronic_store.dto.UserDto;
import com.bikkadit.ectronic_store.entity.User;
import com.bikkadit.ectronic_store.repository.UserRepository;
import com.bikkadit.ectronic_store.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        //to generate unique id in String Format
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);

        //Dto To Entity
        User user = dtoToEntity(userDto);
        User saveUser = this.userRepository.save(user);

        //Entity To Dto
        UserDto newDto = entityToDto(saveUser);

        return newDto;
    }


    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(AppConstant.USER_NOT_FOUND));

        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        User updatedUser = userRepository.save(user);
        UserDto userDto1 = entityToDto(updatedUser);
        return userDto1;
    }

    @Override
    public void deleteUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(AppConstant.USER_NOT_FOUND));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public UserDto getUserById(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(AppConstant.USER_NOT_FOUND));


        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email)
    {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(AppConstant.USER_NOT_FOUND));

        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {


        List<User> userList = userRepository.findByNameContaining(keyword);
        List<UserDto> dtolist = userList.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        return dtolist;
    }

    private User dtoToEntity(UserDto userDto) {

       /* User user = User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .gender(userDto.getGender())
                .about(userDto.getAbout())
                .imageName(userDto.getImageName())
                .build();
*/

        return this.modelMapper.map(userDto,User.class);
    }

    private UserDto entityToDto(User saveUser) {

        /*UserDto userDto = UserDto.builder()
                .userId(saveUser.getUserId())
                .name(saveUser.getName())
                .email(saveUser.getEmail())
                .password(saveUser.getPassword())
                .about(saveUser.getAbout())
                .gender(saveUser.getGender())
                .imageName(saveUser.getImageName())
                .build();
*/
        return this.modelMapper.map(saveUser,UserDto.class);
    }
}

