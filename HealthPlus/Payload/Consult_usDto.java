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
public class Consult_usDto {

    private int id;
    private String name;
    private String email;
    private String number;
    private String gender;
    private String dob;
    private String address;
    @NotBlank(message = "Please describe your medical condition")
    private String medical_conditions;
    @NotBlank(message = "Please describe medication")
    private String current_medications;
    @NotBlank(message = "Please enter your reason")
    private String reason;
    @NotBlank(message = "Please select the date")
    private String app_date;
    @NotBlank(message = "Please select the time")
    private String consultation_time;
}
