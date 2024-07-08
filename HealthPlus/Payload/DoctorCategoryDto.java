package com.Api.HealthPlus.Payload;

import com.Api.HealthPlus.Entity.Doctors;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
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
public class DoctorCategoryDto {

    private int id;
    private String img;
    @NotBlank(message = "Please select the doctor category")
    private String name;
    private List<DoctorDto> doctor = new ArrayList<>();

}
