package com.Api.HealthPlus.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllPaymentDto {

    private int id;
    private String detail;
    private String price;
    private String status;
    private String cartType;
    private UserClientResponse user;
}
