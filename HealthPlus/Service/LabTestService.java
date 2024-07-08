package com.Api.HealthPlus.Service;

import com.Api.HealthPlus.Payload.LabTestDto;

import java.util.List;

public interface LabTestService {

    public LabTestDto createLabTest(LabTestDto labTestDto,int labCate_Id);
    public LabTestDto getLabTestById(int labTestId);
    public List<LabTestDto> getLabTestByLabCategory(int labCateId);
    public LabTestDto getLabTestDataById(int labId);
    public LabTestDto updateLabTestData(LabTestDto labTestDto,int labTest_Id);
    public void deleteLabTestData(int labTest_Id);
}
