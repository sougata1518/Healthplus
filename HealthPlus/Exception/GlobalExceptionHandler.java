package com.Api.HealthPlus.Exception;

import com.Api.HealthPlus.Payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourseNotFoundException exception){
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value(),false);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourseAlreadyExit.class)
    public ResponseEntity<ApiResponse> resourceAlreadyExit(ResourseAlreadyExit exception){
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(),HttpStatus.ALREADY_REPORTED.value(),false);
        return new ResponseEntity<>(apiResponse,HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String> resp = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName,message);
        });
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserEmailNotFoundException.class)
    public ResponseEntity<ApiResponse> UserEmailNotValidException(UserEmailNotFoundException userEmailNotFoundException){
        ApiResponse apiResponse = new ApiResponse(userEmailNotFoundException.getMessage(),HttpStatus.NOT_FOUND.value(),false);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> BadCredentialsException(){
        return ResponseEntity.ok("Credentials Invalid !!");
    }
}
