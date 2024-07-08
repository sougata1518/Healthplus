package com.Api.HealthPlus.Controller;

import com.Api.HealthPlus.Payload.DeleteResponse;
import com.Api.HealthPlus.Payload.LabCategoryDto;
import com.Api.HealthPlus.Service.FileService;
import com.Api.HealthPlus.Service.LabCategoryService;
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
@RequestMapping("/api/lab/cat")
@CrossOrigin("*")
public class LabCategoryController {

    @Autowired
    private LabCategoryService labCategoryService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createLabCate")
    public ResponseEntity<LabCategoryDto> createLabCategory(
            @RequestBody LabCategoryDto labCategoryDto
    ){
        LabCategoryDto labCategory = labCategoryService.createLabCategory(labCategoryDto);
        return new ResponseEntity<>(labCategory, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/image/upload/{lab_cate_id}")
    public ResponseEntity<LabCategoryDto> createDoctorCategory(
            @RequestParam("image") MultipartFile image,
            @PathVariable("lab_cate_id") int id
    )throws IOException {
        LabCategoryDto labCategoryById = labCategoryService.getLabCategoryById(id);
        String fileName = fileService.uploadImage(path, image);
        labCategoryById.setImg(fileName);
        LabCategoryDto labCategory = labCategoryService.createLabCategory(labCategoryById);
        return ResponseEntity.ok(labCategory);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<LabCategoryDto>> getAllLabCategory(){
        List<LabCategoryDto> allLabCategory = labCategoryService.getAllLabCategory();
        return ResponseEntity.ok(allLabCategory);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{labCate_Id}")
    public ResponseEntity<LabCategoryDto> updateLabCategory(
            @PathVariable("labCate_Id") int id,
            @RequestBody LabCategoryDto labCategoryDto
    ){
        LabCategoryDto labCategory = labCategoryService.updateLabCategory(labCategoryDto, id);
        return ResponseEntity.ok(labCategory);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{labCate_Id}")
    public ResponseEntity<DeleteResponse> deleteLabCategory(
            @PathVariable("labCate_Id") int id
    ){
        labCategoryService.deleteLabCategory(id);
        return new ResponseEntity<>(new DeleteResponse
                ("Lab Category deleted successfully", HttpStatus.OK.value(), true)
                ,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getLabCateById/{labCatId}")
    public ResponseEntity<LabCategoryDto> getLabCateById(
            @PathVariable("labCatId") int id
    ){
        LabCategoryDto labCategory = labCategoryService.getLabCategoryById(id);
        return ResponseEntity.ok(labCategory);
    }
}
