package com.Api.HealthPlus.Service.ServiceImple;

import com.Api.HealthPlus.Entity.DoctorCategory;
import com.Api.HealthPlus.Entity.Doctors;
import com.Api.HealthPlus.Exception.ResourseNotFoundException;
import com.Api.HealthPlus.Payload.DoctorDto;
import com.Api.HealthPlus.Repositary.DoctorCategoryRepositary;
import com.Api.HealthPlus.Repositary.DoctorRepositary;
import com.Api.HealthPlus.Service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImple implements DoctorService {

    @Autowired
    private DoctorRepositary doctorRepositary;
    @Autowired
    private DoctorCategoryRepositary doctorCategoryRepositary;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public DoctorDto saveAllDocData(DoctorDto doctorDto,int docCatId) {
        DoctorCategory doctorCategory = doctorCategoryRepositary.findById(docCatId)
                .orElseThrow(() -> new ResourseNotFoundException("Doctor category", "Doc_Id", docCatId));
        Doctors doctor = modelMapper.map(doctorDto, Doctors.class);
        doctor.setDoctorCategory(doctorCategory);
        Doctors save = doctorRepositary.save(doctor);
        DoctorDto savedData = modelMapper.map(save, DoctorDto.class);
        return savedData;
    }

    @Override
    public List<DoctorDto> getDoctorsByDoctorCategory(int docCateId) {
        DoctorCategory doctorCategory = doctorCategoryRepositary.findById(docCateId)
                .orElseThrow(() -> new ResourseNotFoundException("Doctor Category", "DocCate_Id", docCateId));
        List<Doctors> byDoctorCategory = doctorRepositary.findByDoctorCategory(doctorCategory);
        List<DoctorDto> collect = byDoctorCategory
                .stream()
                .map((doctor) -> modelMapper.map(doctor, DoctorDto.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public DoctorDto getDoctorById(int docId) {
        Doctors doctors = doctorRepositary.findById(docId)
                .orElseThrow(() -> new ResourseNotFoundException("Doctors", "docId", docId));
        DoctorDto docDto = modelMapper.map(doctors, DoctorDto.class);
        return docDto;
    }

    @Override
    public DoctorDto updateDoctorData(DoctorDto doctorDto, int docId) {
        Doctors doctors = doctorRepositary.findById(docId)
                .orElseThrow(() -> new ResourseNotFoundException("Doctor", "DocId", docId));
        DoctorDto map = modelMapper.map(doctorDto, DoctorDto.class);

        doctors.setName(map.getName());
        doctors.setDegree(map.getDegree());
        doctors.setMed_exp(map.getMed_exp());
        doctors.setAvail_time(map.getAvail_time());
        doctors.setFee(map.getFee());

        Doctors save = doctorRepositary.save(doctors);
        DoctorDto saveDoctorDto = modelMapper.map(save, DoctorDto.class);
        return saveDoctorDto;
    }

    @Override
    public void deleteDoctor(int docId) {
        Doctors doctors = doctorRepositary.findById(docId)
                .orElseThrow(() -> new ResourseNotFoundException("Doctor", "docId", docId));
        doctorRepositary.delete(doctors);
    }
}
