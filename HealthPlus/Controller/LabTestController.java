package com.Api.HealthPlus.Controller;

import com.Api.HealthPlus.Payload.DeleteResponse;
import com.Api.HealthPlus.Payload.LabTestDto;
import com.Api.HealthPlus.Service.FileService;
import com.Api.HealthPlus.Service.LabTestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/labTest")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/{labCate_Id}")
    public ResponseEntity<LabTestDto> createLabTest(
            @PathVariable("labCate_Id") int id,
            @RequestBody @Valid LabTestDto labTestDto
    ){
        LabTestDto labTest = labTestService.createLabTest(labTestDto, id);
        return new ResponseEntity<>(labTest, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/image/upload/{lab_test_id}/{lab_cat_id}")
    public ResponseEntity<LabTestDto> createDoctorCategory(
            @RequestParam("image") MultipartFile image,
            @PathVariable("lab_test_id") int id,
            @PathVariable("lab_cat_id") int catId
    )throws IOException {
        LabTestDto labTestById = labTestService.getLabTestById(id);
        String fileName = fileService.uploadImage(path, image);
        labTestById.setImg(fileName);
        LabTestDto labTest = labTestService.createLabTest(labTestById, catId);
        return ResponseEntity.ok(labTest);
    }

    @GetMapping("/getData/labCate/{labCat_Id}")
    public ResponseEntity<List<LabTestDto>> getLabTestByLabCategory(
            @PathVariable("labCat_Id") int id
    ){
        List<LabTestDto> labTestByLabCategory = labTestService.getLabTestByLabCategory(id);
        return ResponseEntity.ok(labTestByLabCategory);
    }
    @GetMapping("/getTestData/{labId}")
    public ResponseEntity<LabTestDto> getLabTestDataById(
            @PathVariable("labId") int id
    ){
        LabTestDto labTestDataById = labTestService.getLabTestDataById(id);
        return ResponseEntity.ok(labTestDataById);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{labTest_Id}")
    public ResponseEntity<LabTestDto> updateLabTest(
            @PathVariable("labTest_Id") int id,
            @RequestBody LabTestDto labTestDto
    ){
        LabTestDto labTest = labTestService.updateLabTestData(labTestDto, id);
        return ResponseEntity.ok(labTest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{labTest_Id}")
    public ResponseEntity<DeleteResponse> deleteLabTestData(
            @PathVariable("labTest_Id") int id
    ){
        labTestService.deleteLabTestData(id);
        return new ResponseEntity<>(new DeleteResponse
                ("Lab Test deleted successfully", HttpStatus.OK.value(), true)
        ,HttpStatus.OK);
    }
}
