package com.Api.HealthPlus.Payload;

import jakarta.validation.constraints.NotBlank;
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
public class ProductCategoryDto {

    private int id;
    @NotBlank(message = "Please enter the product category name")
    private String name;
    private List<ProductDto> products = new ArrayList<>();
}
