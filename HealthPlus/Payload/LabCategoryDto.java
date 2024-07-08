package com.Api.HealthPlus.Payload;

import jakarta.persistence.Column;
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
public class LabCategoryDto {

    private int id;
    @Column(name = "LabCate_img")
    private String img;
    @NotBlank(message = "Please enter the Lab Category")
    private String name;
    private List<LabTestDto> labTest = new ArrayList<>();
}
