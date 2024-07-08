package com.Api.HealthPlus.Controller;

import com.Api.HealthPlus.Payload.Consult_usDto;
import com.Api.HealthPlus.Payload.DeleteResponse;
import com.Api.HealthPlus.Service.ConsultUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/consult_us")
public class ConsultUsController {

    @Autowired
    private ConsultUsService consultUsService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/save")
    public ResponseEntity<Consult_usDto> saveConsultData(
            @RequestBody Consult_usDto consultUsDto
    ){
        Consult_usDto consult = consultUsService.saveUserConsultData(consultUsDto);
        return new ResponseEntity<>(consult, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllData")
    public ResponseEntity<List<Consult_usDto>> getAllData(){
        List<Consult_usDto> listConsultData = consultUsService.getAllConsultData();
        return ResponseEntity.ok(listConsultData);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/delete/{delId}")
    public ResponseEntity<DeleteResponse> deleteConsultData(
            @PathVariable("docId") int id
    ){
        consultUsService.deleteConsultDataById(id);
        return new ResponseEntity<>(new DeleteResponse
                ("Consult data deleted successfully", HttpStatus.OK.value(), true)
                ,HttpStatus.OK);
    }
}
