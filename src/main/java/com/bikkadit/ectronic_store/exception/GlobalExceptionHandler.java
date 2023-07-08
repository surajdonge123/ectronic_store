package com.bikkadit.ectronic_store.exception;

import com.bikkadit.ectronic_store.helper.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandle(ResourceNotFoundException ex){
        logger.info("Exception Handler Invoked !! ");
        ApiResponse apiResponse = ApiResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();


        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

}
