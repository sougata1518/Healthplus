package com.Api.HealthPlus.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;
    @NotBlank(message = "Please enter your name.")
    private String name;
    @NotBlank(message = "Please enter your name.")
    @Email(message = "Please enter the correct email id")
    private String email;
    @NotBlank(message = "Please enter your phone number.")
    @Size(min = 0,max = 10)
    private String phone;
    private String address;
    private String city;
    private String state;
    private String pin;
    @NotBlank(message = "Please enter your password.")
    private String password;
    private String role;
}
