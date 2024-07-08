package com.Api.HealthPlus.Controller;

import com.Api.HealthPlus.Payload.DeleteResponse;
import com.Api.HealthPlus.Payload.DoctorDto;
import com.Api.HealthPlus.Service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/doc/cat/{Doc_Id}")
    public ResponseEntity<DoctorDto> saveDoctorData(
            @RequestBody @Valid DoctorDto doctorDto,
            @PathVariable("Doc_Id") int id
    ){
        DoctorDto doctor = doctorService.saveAllDocData(doctorDto,id);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @GetMapping("/get/docCate/{doc_Id}")
    public ResponseEntity<List<DoctorDto>> getDoctorsByDoctorCategory(
            @PathVariable("doc_Id") int id
    ){
        List<DoctorDto> doctorsByDoctorCategory = doctorService.getDoctorsByDoctorCategory(id);
        return ResponseEntity.ok(doctorsByDoctorCategory);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{doc_Id}")
    public ResponseEntity<DoctorDto> updateDoctor(
            @PathVariable("doc_Id") int id,
            @RequestBody DoctorDto doctorDto
    ){
        DoctorDto doctor = doctorService.updateDoctorData(doctorDto, id);
        return ResponseEntity.ok(doctor);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteDoc/{docId}")
    public ResponseEntity<DeleteResponse> deleteDoctor(
            @PathVariable("docId") int id
    ){
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(new DeleteResponse
                ("Doctor deleted successfully", HttpStatus.OK.value(), true)
        ,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getDocById/{docId}")
    public ResponseEntity<DoctorDto> getDoctorById(
            @PathVariable("docId") int id
    ){
        DoctorDto doctor = doctorService.getDoctorById(id);
        System.out.println(doctor.getName());
        return ResponseEntity.ok(doctor);
    }
}
