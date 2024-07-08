package com.Api.HealthPlus.Service.ServiceImple;

import com.Api.HealthPlus.Entity.LabCategory;
import com.Api.HealthPlus.Entity.LabTest;
import com.Api.HealthPlus.Exception.ResourseNotFoundException;
import com.Api.HealthPlus.Payload.LabTestDto;
import com.Api.HealthPlus.Repositary.LabCategoryRepositary;
import com.Api.HealthPlus.Repositary.LabTestRespositary;
import com.Api.HealthPlus.Service.LabTestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabTestServiceImple implements LabTestService {

    @Autowired
    private LabTestRespositary labTestRespositary;
    @Autowired
    private LabCategoryRepositary labCategoryRepositary;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LabTestDto createLabTest(LabTestDto labTestDto, int labCate_Id) {
        LabCategory labCategory = labCategoryRepositary.findById(labCate_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Lab Category", "labCat_Id", labCate_Id));
        LabTest map = modelMapper.map(labTestDto, LabTest.class);
        map.setLabCategory(labCategory);
        LabTest save = labTestRespositary.save(map);
        LabTestDto lab = modelMapper.map(save, LabTestDto.class);
        return lab;
    }

    @Override
    public LabTestDto getLabTestById(int labTestId) {
        LabTest labTest = labTestRespositary.findById(labTestId)
                .orElseThrow(() -> new ResourseNotFoundException("Lab Test", "labTest_Id", labTestId));
        LabTestDto labTestById = modelMapper.map(labTest, LabTestDto.class);
        return labTestById;
    }

    @Override
    public List<LabTestDto> getLabTestByLabCategory(int labCateId) {
        LabCategory labCategory = labCategoryRepositary.findById(labCateId)
                .orElseThrow(() -> new ResourseNotFoundException("Lab Category", "labCat_Id", labCateId));
        List<LabTest> byLabCategory = labTestRespositary.findByLabCategory(labCategory);
        List<LabTestDto> collect = byLabCategory
                .stream()
                .map((labTest) -> modelMapper.map(labTest, LabTestDto.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public LabTestDto getLabTestDataById(int labId) {
        LabTest labTest = labTestRespositary.findById(labId)
                .orElseThrow(() -> new ResourseNotFoundException("Lab Test", "labId", labId));
        LabTestDto map = modelMapper.map(labTest, LabTestDto.class);
        return map;
    }

    @Override
    public LabTestDto updateLabTestData(LabTestDto labTestDto, int labTest_Id) {
        LabTest labTest = labTestRespositary.findById(labTest_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Lab Test", "labTest_Id", labTest_Id));
        LabTest map = modelMapper.map(labTestDto, LabTest.class);

        labTest.setName(map.getName());
        labTest.setTest_include(map.getTest_include());
        labTest.setMainPrice(map.getMainPrice());
        labTest.setDistPer(map.getDistPer());
        labTest.setDistPrice(map.getDistPrice());

        LabTest save = labTestRespositary.save(labTest);
        LabTestDto labTestData = modelMapper.map(save, LabTestDto.class);
        return labTestData;
    }

    @Override
    public void deleteLabTestData(int labTest_Id) {
        LabTest labTest = labTestRespositary.findById(labTest_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Lab Test", "labTest_Id", labTest_Id));
        labTestRespositary.delete(labTest);
    }
}
