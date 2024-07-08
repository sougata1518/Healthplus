package com.Api.HealthPlus.Payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private int id;
    private String img;
    @NotBlank(message = "Please enter the product name")
    private String name;
    @NotBlank(message = "Please enter the product price")
    private String price;
    @NotBlank(message = "Please enter the discounted percentage")
    private String dist_per;
    @NotBlank(message = "Please enter the discounted price")
    private String dist_price;
}
