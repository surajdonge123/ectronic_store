package com.bikkadit.ectronic_store.controller;
import com.bikkadit.ectronic_store.constant.AppConstant;
import com.bikkadit.ectronic_store.dto.UserDto;
import com.bikkadit.ectronic_store.helper.ApiResponse;
import com.bikkadit.ectronic_store.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    public static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;


    /**
     * @auther Suraj
     * @param userDto
     * @return
     * @apiNote Create User
     */
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Initiating request for create User ");
        UserDto userCreated = userService.createUser(userDto);
        logger.info("completed request for save user data ");
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }


    /**
     * @auther suraj
     * @param userId
     * @param userDto
     * @return
     * @apiNote Update user data
     */
    @PostMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable String userId, @RequestBody UserDto userDto) {

        logger.info("initiating request for update user : " + userId);
        UserDto userDto1 = userService.updateUser(userDto, userId);
        logger.info("request completed for update user :" + userId);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }


    /**
     * @param userId
     * @return message
     * @apiNote Delete user
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        logger.info("Initiating request for delete user " + userId);
        userService.deleteUser(userId);
        ApiResponse message = ApiResponse.builder().message(AppConstant.USER_DELETE).success(true).status(HttpStatus.OK).build();
        logger.info("request complete for delete user " + userId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    /**
     * @return
     * @auther suraj
     * @apiNote Get all users
     */
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getAllUser(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
    ) {
        logger.info("initiating request for getting all users");
        List<UserDto> allUsers = userService.getAllUsers(pageNumber, pageSize,sortBy,sortDir);
        logger.info("request complete for getting all users");
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }


    /**
     * @param userId
     * @apiNote getting single user
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> singleUser(@PathVariable String userId) {

        logger.info("initiating request for getting single user using userId "+userId);
        UserDto userById = userService.getUserById(userId);
        logger.info("request complete for getting user using userId "+userId);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }


    /**
     *
     * @param email
     * @apiNote getting user by email
     * @return
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
        logger.info("initiating request for getting user by using email "+ email);
        UserDto userByEmail = userService.getUserByEmail(email);
        logger.info("request complete for getting user by using email " +email);
        return new ResponseEntity<>(userByEmail, HttpStatus.OK);
    }

    /**
     *
     * @param keyword
     * @apiNote search user using keyword
     * @return
     */
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchByKeyword(@PathVariable String keyword) {

        logger.info("initiating request for search user using keyword " +keyword);
        List<UserDto> dtoList = userService.searchUser(keyword);
        logger.info("request complete for search user using keyword "+keyword);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

}
