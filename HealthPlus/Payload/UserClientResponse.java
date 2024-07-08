package com.Api.HealthPlus.Payload;

import com.Api.HealthPlus.Entity.AllPayment;
import com.Api.HealthPlus.Entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserClientResponse {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String pin;
    private String role;
}
