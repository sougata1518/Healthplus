package com.Api.HealthPlus.Service;

import com.Api.HealthPlus.Payload.Consult_usDto;

import java.util.List;

public interface ConsultUsService {

    public Consult_usDto saveUserConsultData(Consult_usDto consultUsDto);
    public List<Consult_usDto> getAllConsultData();
    public void deleteConsultDataById(int id);
}
