package com.Api.HealthPlus.Service.ServiceImple;

import com.Api.HealthPlus.Entity.DoctorCategory;
import com.Api.HealthPlus.Entity.Doctors;
import com.Api.HealthPlus.Exception.ResourseNotFoundException;
import com.Api.HealthPlus.Payload.DoctorCategoryDto;
import com.Api.HealthPlus.Repositary.DoctorCategoryRepositary;
import com.Api.HealthPlus.Repositary.DoctorRepositary;
import com.Api.HealthPlus.Service.DoctorCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorCategoryServiceImple implements DoctorCategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DoctorCategoryRepositary doctorCategoryRepositary;
    @Autowired
    private DoctorRepositary doctorRepositary;

    @Override
    public DoctorCategoryDto createDoctorCategory(DoctorCategoryDto doctorCategoryDto) {
        DoctorCategory doc_cat = modelMapper.map(doctorCategoryDto, DoctorCategory.class);
        DoctorCategory save = doctorCategoryRepositary.save(doc_cat);
        DoctorCategoryDto saveData = modelMapper.map(save, DoctorCategoryDto.class);
        return saveData;
    }

    @Override
    public DoctorCategoryDto getDocCateById(int id) {
        DoctorCategory doctorCategory = doctorCategoryRepositary.findById(id).orElseThrow(
                () -> new ResourseNotFoundException("Doctor Category", "doctorCategoryId", id)
        );
        DoctorCategoryDto doctorCate = modelMapper.map(doctorCategory, DoctorCategoryDto.class);
        return doctorCate;
    }

    @Override
    public List<DoctorCategoryDto> getAllCategory() {
        List<DoctorCategory> categories = doctorCategoryRepositary.findAll();
        List<DoctorCategoryDto> allDoctorCategories = categories
                .stream()
                .map((category) -> modelMapper.map(category, DoctorCategoryDto.class))
                .collect(Collectors.toList());
        return allDoctorCategories;
    }

    @Override
    public DoctorCategoryDto getDoctorCategoryById(int id) {
        DoctorCategory doctorCategory = doctorCategoryRepositary.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Doctor Category", "doc_Id", id));
        DoctorCategoryDto category = modelMapper.map(doctorCategory, DoctorCategoryDto.class);
        return category;
    }

    @Override
    public DoctorCategoryDto updateDoctorCategory(int id, DoctorCategoryDto doctorCategoryDto) {
        DoctorCategory doctorCategory = doctorCategoryRepositary.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Doctor Category", "doc_Id", id));
        DoctorCategory category = modelMapper.map(doctorCategoryDto, DoctorCategory.class);
        doctorCategory.setName(category.getName());
        DoctorCategory save = doctorCategoryRepositary.save(doctorCategory);
        DoctorCategoryDto categoryDto = modelMapper.map(save, DoctorCategoryDto.class);
        return categoryDto;
    }

    @Override
    public void deleteCategory(int id) {
        DoctorCategory doctorCategory = doctorCategoryRepositary.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Doctor Category", "doc_Id", id));
        doctorCategoryRepositary.delete(doctorCategory);
    }

    @Override
    public DoctorCategoryDto getDoctorCategoryByDoctorId(int doctor_id) {
        Doctors doctors = doctorRepositary.findById(doctor_id)
                .orElseThrow(() -> new ResourseNotFoundException("Doctor", "doc_id", doctor_id));
        DoctorCategory byDoctor = doctorCategoryRepositary.findByDoctor(doctors);
        DoctorCategoryDto doc = modelMapper.map(byDoctor, DoctorCategoryDto.class);
        return doc;
    }
}
