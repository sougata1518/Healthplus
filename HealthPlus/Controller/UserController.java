package com.Api.HealthPlus.Controller;

import com.Api.HealthPlus.Payload.*;
import com.Api.HealthPlus.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserClientResponse> saveUserData(
            @RequestBody @Valid UserDto userDto
    ){
        UserDto user = userService.createUser(userDto);
        UserClientResponse userClientResponse = new UserClientResponse(
                user.getId(),user.getName(), user.getEmail(), user.getPhone(),user.getAddress(), user.getCity(),user.getState(), user.getPin(), user.getRole()
        );

        return new ResponseEntity<>(userClientResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserClientResponse>> getAllUserList(){
        List<UserDto> listOfUsers = userService.getAllUser();
        List<UserDto> listOfUserDto = new ArrayList<>();
        for(UserDto user:listOfUsers){
            if(user.getRole().equals("USER")){
                listOfUserDto.add(user);
            }
        }
        List<UserClientResponse> userList = listOfUserDto.stream().map((user) ->
                        new UserClientResponse(
                                user.getId(),user.getName(), user.getEmail(), user.getPhone(),user.getAddress(), user.getCity(),user.getState(), user.getPin(), user.getRole()
                        ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userList);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getById/{userId}")
    public ResponseEntity<UserClientResponse> getUserById(
            @PathVariable("userId") int id
    ){
        UserDto user = userService.getUserById(id);
        UserClientResponse userClientResponse = new UserClientResponse(
                user.getId(),user.getName(), user.getEmail(), user.getPhone(),user.getAddress(), user.getCity(),user.getState(), user.getPin() , user.getRole()
        );
        return ResponseEntity.ok(userClientResponse);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/update/userData/{userId}/{token}")
    public ResponseEntity<JwtResponse> updateUserData(
            @PathVariable("token") String token,
            @RequestBody UserDto userDto,
            @PathVariable("userId") int id
    ){
        UserDto user = userService.updateUserData(userDto, id);
        UserClientResponse userClientResponse = new UserClientResponse(
                user.getId(),user.getName(), user.getEmail(), user.getPhone(),user.getAddress(), user.getCity(),user.getState(), user.getPin(), user.getRole()
        );
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .userClientResponse(userClientResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getByEmail/{getEmail}")
    public ResponseEntity<UserDto> getUserDataByEmail(
            @PathVariable String getEmail
            ){
        UserDto user = userService.getUserByEmail(getEmail);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/updatePass")
    public ResponseEntity<UserDto> updatePassword(
            @RequestBody NewPassword newPassword
            ){
        UserDto user = userService.updatePassword(newPassword);
        return ResponseEntity.ok(user);
    }
}
