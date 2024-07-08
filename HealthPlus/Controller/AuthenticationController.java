package com.Api.HealthPlus.Controller;

import com.Api.HealthPlus.Entity.User;
import com.Api.HealthPlus.Payload.JwtResponse;
import com.Api.HealthPlus.Payload.Request;
import com.Api.HealthPlus.Payload.UserClientResponse;
import com.Api.HealthPlus.Payload.UserDto;
import com.Api.HealthPlus.Repositary.UserRepositary;
import com.Api.HealthPlus.Security.JWTAuthentication.JwtHelper;
import com.Api.HealthPlus.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Request request){
        this.doAuthenticate(request.getEmail(),request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwtHelper.generateToken(userDetails);
        UserDto user = userService.getUserByEmail(request.getEmail());

        UserClientResponse userClientResponse = new UserClientResponse(
                user.getId(),user.getName(),user.getEmail(),user.getPhone(),user.getAddress(), user.getCity(), user.getState(), user.getPin(),user.getRole()
        );

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .userClientResponse(userClientResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email,String password){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,password);
        try {
            manager.authenticate(authentication);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid username and password");
        }
    }
}
