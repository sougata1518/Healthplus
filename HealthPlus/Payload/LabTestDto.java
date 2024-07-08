package com.Api.HealthPlus.Payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LabTestDto {

    private int id;
    private String img;
    @NotBlank(message = "Please enter the name of lab test")
    private String name;
    @NotBlank(message = "Please enter the test_include")
    private String test_include;
    @NotBlank(message = "Please enter the main price")
    private String mainPrice;
    @NotBlank(message = "Please enter the discounted percentage")
    private String distPer;
    @NotBlank(message = "Please enter the discounted price")
    private String distPrice;

}
