package com.Api.HealthPlus.Service.ServiceImple;

import com.Api.HealthPlus.Entity.LabCategory;
import com.Api.HealthPlus.Exception.ResourseNotFoundException;
import com.Api.HealthPlus.Payload.LabCategoryDto;
import com.Api.HealthPlus.Repositary.LabCategoryRepositary;
import com.Api.HealthPlus.Service.LabCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabCategoryServiceImple implements LabCategoryService {

    @Autowired
    private LabCategoryRepositary labCategoryRepositary;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LabCategoryDto createLabCategory(LabCategoryDto labCategoryDto) {
        LabCategory labCategory = modelMapper.map(labCategoryDto, LabCategory.class);
        LabCategory save = labCategoryRepositary.save(labCategory);
        LabCategoryDto dto = modelMapper.map(save, LabCategoryDto.class);
        return dto;
    }

    @Override
    public LabCategoryDto getLabCategoryById(int labCat_Id) {
        LabCategory labCategory = labCategoryRepositary.findById(labCat_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Lab Category", "labCate_Id", labCat_Id));
        LabCategoryDto map = modelMapper.map(labCategory, LabCategoryDto.class);
        return map;
    }

    @Override
    public List<LabCategoryDto> getAllLabCategory() {
        List<LabCategory> allLabCategory = labCategoryRepositary.findAll();
        List<LabCategoryDto> labCategoryDto = allLabCategory.stream()
                .map((category) -> modelMapper.map(category, LabCategoryDto.class))
                .collect(Collectors.toList());
        return labCategoryDto;
    }

    @Override
    public LabCategoryDto updateLabCategory(LabCategoryDto labCategoryDto, int labCate_Id) {
        LabCategory labCategory = labCategoryRepositary.findById(labCate_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Lab Category", "labCate_Id", labCate_Id));
        LabCategoryDto map = modelMapper.map(labCategoryDto, LabCategoryDto.class);
        labCategory.setName(map.getName());
        LabCategory save = labCategoryRepositary.save(labCategory);
        LabCategoryDto saveData = modelMapper.map(save, LabCategoryDto.class);
        return saveData;
    }

    @Override
    public void deleteLabCategory(int labCate_Id) {
        LabCategory labCategory = labCategoryRepositary.findById(labCate_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Lab Category", "labCate_Id", labCate_Id));
        labCategoryRepositary.delete(labCategory);

    }
}
