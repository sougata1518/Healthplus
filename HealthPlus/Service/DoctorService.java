package com.Api.HealthPlus.Service;

import com.Api.HealthPlus.Payload.DoctorDto;

import java.util.List;

public interface DoctorService {
    public DoctorDto saveAllDocData(DoctorDto doctorDto , int docCatId);
    public List<DoctorDto> getDoctorsByDoctorCategory(int docCateId);
    public DoctorDto getDoctorById(int docId);
    public DoctorDto updateDoctorData(DoctorDto doctorDto , int docId);
    public void deleteDoctor(int docId);
}
