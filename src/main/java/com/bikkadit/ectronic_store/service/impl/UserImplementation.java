package com.bikkadit.ectronic_store.service.impl;

import com.bikkadit.ectronic_store.constant.AppConstant;
import com.bikkadit.ectronic_store.dto.PageableResponse;
import com.bikkadit.ectronic_store.dto.UserDto;
import com.bikkadit.ectronic_store.entity.User;
import com.bikkadit.ectronic_store.exception.ResourceNotFoundException;
import com.bikkadit.ectronic_store.helper.Helper;
import com.bikkadit.ectronic_store.repository.UserRepository;
import com.bikkadit.ectronic_store.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserImplementation implements UserService {
    public static Logger logger = LoggerFactory.getLogger(UserImplementation.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Value("${user.profile.image.path}")
    private String imagePath;

    /**
     * @param userDto
     * @return
     * @auther Suraj
     * @apiNote Logic for Create User
     */
    @Override
    public UserDto createUser(UserDto userDto) {
        /**
         * @apiNote Logic for generate unique id in String Format
         */
        logger.info("Initiating request for generate unique id in String format ");
        String userId = UUID.randomUUID().toString();
        logger.info("Request complete for generating unique id in String format ");
        userDto.setUserId(userId);

        //Dto To Entity
        User user = dtoToEntity(userDto);
        logger.info("Sending request to userRepository for Creating user in database ");
        User saveUser = this.userRepository.save(user);
        logger.info("request Complete for the creating user in database");
        //Entity To Dto
        UserDto newDto = entityToDto(saveUser);

        return newDto;
    }


    /**
     * @param userDto
     * @param userId
     * @return
     * @auther suraj
     * @apiNote update user
     */
    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        logger.info("Sending request to UserRepository for update user data of {} " + userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        User updatedUser = userRepository.save(user);
        logger.info("Request complete for update user data of {}" + userId);
        UserDto userDto1 = entityToDto(updatedUser);
        return userDto1;
    }

    /**
     * @param userId
     * @apiNote Delete user
     */
    @Override
    public void deleteUser(String userId) {

        logger.info("Sending request to userRepository for delete user data of {}" + userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        userRepository.delete(user);
        logger.info("Request complete for delete user data from of {}" + userId);

        //for delete userImage
        String fullPath = imagePath + user.getImageName();
        try {
            Path path=Paths.get(fullPath);
            Files.delete(path);
        }catch (NoSuchFileException e){
            logger.info("No user image found in folder");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     * @author Suraj
     * @apiNote get all users
     */
    @Override
    public PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {

        logger.info("Sending Request to UserRepository for getting all the users ");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);
        Page<User> all = userRepository.findAll(pageable);
        PageableResponse<UserDto> pageableResponse = Helper.getPageableResponse(all, UserDto.class);


        logger.info("Request complete for getting all data");
        return pageableResponse;
    }

    /**
     * @param userId
     * @return
     * @auther Suraj
     * @apiNote get user by userId
     */
    @Override
    public UserDto getUserById(String userId) {

        logger.info("Sending request to userRepository for getting single user of {}" + userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        logger.info("request complete for getting single user of {}" + userId);
        return entityToDto(user);
    }

    /**
     * @param email
     * @return
     * @auther suraj
     * @apiNote get user by email
     */
    @Override
    public UserDto getUserByEmail(String email) {
        logger.info("Sending Request to userRepository for getting user by Email {} " + email);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        logger.info("request complete for getting user by Email {} " + email);
        return entityToDto(user);
    }

    /**
     * @param keyword
     * @return
     * @auther suraj
     * @apiNote getting user details by keyword
     */
    @Override
    public List<UserDto> searchUser(String keyword) {
        logger.info("Sending Request to userRepository for getting user details using keyword {}" + keyword);
        List<User> userList = userRepository.findByNameContaining(keyword);
        logger.info("request complete for getting details user using keyword");
        List<UserDto> dtolist = userList.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        return dtolist;
    }

    /**
     * @param userDto
     * @return
     * @apiNote Converting DTO to ENTITY class
     */
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

        return this.modelMapper.map(userDto, User.class);
    }

    /**
     * @param saveUser
     * @return
     * @apiNote converting Entity class to Dto
     */
    private UserDto entityToDto(User saveUser) {

        /*UserDto userDto = UserDto.builder()
                .userId(saveUser.getUserId())
                .name(saveUser.getName())
                .email(saveUser.getEmail())
                .password(saveUser.getPassword())
                .about(saveUser.getAbout())
                .gender(saveUser.getGender())
                .imageName(saveUser.getImageName())5555
                .build();
*/
        return this.modelMapper.map(saveUser, UserDto.class);
    }
}

