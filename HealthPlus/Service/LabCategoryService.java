package com.Api.HealthPlus.Service;

import com.Api.HealthPlus.Payload.LabCategoryDto;

import java.util.List;

public interface LabCategoryService {

    public LabCategoryDto createLabCategory(LabCategoryDto labCategoryDto);
    public LabCategoryDto getLabCategoryById(int labCat_Id);
    public List<LabCategoryDto> getAllLabCategory();
    public LabCategoryDto updateLabCategory(LabCategoryDto labCategoryDto,int labCate_Id);
    public void deleteLabCategory(int labCate_Id);
}
