package com.bikkadit.ectronic_store.exception;
import com.bikkadit.ectronic_store.helper.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @param ex
     * @return apiResponse
     * @auther Suraj
     * @apiNote Handle ResourceNotFoundException
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandle(ResourceNotFoundException ex) {
        logger.info("Exception Handler Invoked for ResourceNotFound Exception");
        ApiResponse apiResponse = ApiResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * @param exception
     * @return
     * @auther Suraj
     * @apiNote Handle MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodNotValidException(MethodArgumentNotValidException exception) {
        logger.info("Exception Handler Invoked For MethodArgumentNotValidException");
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        Map<String, Object> response = new HashMap<>();
        allErrors.stream().forEach((ObjectError) -> {
            String message = ObjectError.getDefaultMessage();
            String field1 = ((FieldError) ObjectError).getField();
            response.put(message, field1);
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * @apiNote Handling BadApiRequest
     * @param bad
     * @return
     */
    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResponse> handleBadApiRequest(BadApiRequest bad){
        logger.info("initialising request for Handling the Api Bad request");
        ApiResponse apiResponse = ApiResponse.builder().message(bad.getMessage()).status(HttpStatus.BAD_REQUEST).success(true).build();
        logger.info("complete request for Handling the Api Bad request");
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
}