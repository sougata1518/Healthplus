package com.Api.HealthPlus.Service;

import com.Api.HealthPlus.Payload.DoctorCategoryDto;

import java.util.List;

public interface DoctorCategoryService {

    public DoctorCategoryDto createDoctorCategory(DoctorCategoryDto doctorCategoryDto);
    public DoctorCategoryDto getDocCateById(int id);
    public List<DoctorCategoryDto> getAllCategory();
    public DoctorCategoryDto getDoctorCategoryById(int id);
    public DoctorCategoryDto updateDoctorCategory(int id,DoctorCategoryDto doctorCategoryDto);
    public void deleteCategory(int id);
    public DoctorCategoryDto getDoctorCategoryByDoctorId(int doctor_id);
}
