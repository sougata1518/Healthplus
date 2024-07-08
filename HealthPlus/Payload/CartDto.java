package com.Api.HealthPlus.Payload;

import com.Api.HealthPlus.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private int id;
    private String img;
    private String name;
    private String main_price;
    private String quantity;
    private String total_price;
    private String cartType;
    private UserClientResponse user;
}
