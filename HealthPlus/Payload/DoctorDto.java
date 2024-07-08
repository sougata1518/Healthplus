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
public class DoctorDto {

    private int id;
    @NotBlank(message = "Please enter the doctor name")
    private String name;
    @NotBlank(message = "Please enter the doctor degree")
    private String degree;
    @NotBlank(message = "Please enter the doctor medical experience")
    private String med_exp;
    @NotBlank(message = "Please enter the time")
    private String avail_time;
    @NotBlank(message = "Please enter the doctor fee")
    private String fee;
}
