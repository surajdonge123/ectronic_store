package com.bikkadit.ectronic_store.controller;
import com.bikkadit.ectronic_store.constant.AppConstant;
import com.bikkadit.ectronic_store.dto.PageableResponse;
import com.bikkadit.ectronic_store.dto.UserDto;
import com.bikkadit.ectronic_store.helper.ApiResponse;
import com.bikkadit.ectronic_store.helper.FileResponse;
import com.bikkadit.ectronic_store.service.FileService;
import com.bikkadit.ectronic_store.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    public static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;


    /**
     * @param userDto
     * @return
     * @auther Suraj
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
     * @param userId
     * @param userDto
     * @return
     * @auther suraj
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
    public ResponseEntity<PageableResponse<UserDto>> getAllUser(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        logger.info("initiating request for getting all users");
        PageableResponse<UserDto> allUsers = userService.getAllUsers(pageNumber, pageSize, sortBy, sortDir);
        logger.info("request complete for getting all users");
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }


    /**
     * @param userId
     * @return
     * @apiNote getting single user
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> singleUser(@PathVariable String userId) {

        logger.info("initiating request for getting single user using userId " + userId);
        UserDto userById = userService.getUserById(userId);
        logger.info("request complete for getting user using userId " + userId);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }


    /**
     * @param email
     * @return
     * @apiNote getting user by email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
        logger.info("initiating request for getting user by using email " + email);
        UserDto userByEmail = userService.getUserByEmail(email);
        logger.info("request complete for getting user by using email " + email);
        return new ResponseEntity<>(userByEmail, HttpStatus.OK);
    }

    /**
     * @param keyword
     * @return
     * @apiNote search user using keyword
     */
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchByKeyword(@PathVariable String keyword) {

        logger.info("initiating request for search user using keyword " + keyword);
        List<UserDto> dtoList = userService.searchUser(keyword);
        logger.info("request complete for search user using keyword " + keyword);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    /**
     * @author Suraj
     * @param file
     * @param userId
     * @return
     * @apiNote Api for uploading user image
     * @throws IOException
     */

    @PostMapping("/image/{userId}")
    public ResponseEntity<FileResponse> uploadImage(@RequestParam ("userImage")MultipartFile file,@PathVariable String userId) throws IOException {
        logger.info("initialising request using fileService with the help of {},{}"+file,imageUploadPath);
        String imageName = fileService.uploadFile(file, imageUploadPath);
        logger.info("initialising request for getting user by user Id {} "+userId);
        UserDto user = userService.getUserById(userId);
        user.setImageName(imageName);
        UserDto userDto = userService.updateUser(user, userId);
        logger.info("request complete for getting users using user Id {} "+userId);
        FileResponse fileUpload = FileResponse.builder().imageName(imageName).status(HttpStatus.OK).success(true).message(AppConstant.FILE_UPLOAD).build();
        logger.info("request complete for uploading image {}"+fileUpload);
        return new ResponseEntity<>(fileUpload,HttpStatus.CREATED);
    }

    /**
     * @Author Suraj
     * @param userId
     * @param response
     * @apiNote Api for serveUserImage
     * @throws IOException
     */
    @GetMapping("/images/{userId}")
    public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
        logger.info("Initialising request for getting user by using userId {}"+userId);
        UserDto user = userService.getUserById(userId);
        logger.info("request complete for getting users using userId {]"+userId);
        logger.info("User image name {}"+user.getImageName());
        InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }

}