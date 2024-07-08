package com.Api.HealthPlus.Service.ServiceImple;

import com.Api.HealthPlus.Entity.Consult_us;
import com.Api.HealthPlus.Exception.ResourseNotFoundException;
import com.Api.HealthPlus.Payload.Consult_usDto;
import com.Api.HealthPlus.Repositary.ConsultUsRepositary;
import com.Api.HealthPlus.Service.ConsultUsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultUsServiceImple implements ConsultUsService {

    @Autowired
    private ConsultUsRepositary consultUsRepositary;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Consult_usDto saveUserConsultData(Consult_usDto consultUsDto) {
        Consult_us consult = modelMapper.map(consultUsDto, Consult_us.class);
        Consult_us consultData = consultUsRepositary.save(consult);
        Consult_usDto savedData = modelMapper.map(consultData, Consult_usDto.class);
        return savedData;
    }

    @Override
    public List<Consult_usDto> getAllConsultData() {
        List<Consult_us> listOfConsult = consultUsRepositary.findAll();
        List<Consult_usDto> consultList = listOfConsult
                .stream()
                .map((consult) -> modelMapper.map(consult, Consult_usDto.class))
                .collect(Collectors.toList());
        return consultList;
    }

    @Override
    public void deleteConsultDataById(int id) {
        Consult_us consultUs = consultUsRepositary.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Consult_Us", "consult_id", id));
        consultUsRepositary.delete(consultUs);
    }
}
