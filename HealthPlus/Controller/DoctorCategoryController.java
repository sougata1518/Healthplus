package com.Api.HealthPlus.Controller;

import com.Api.HealthPlus.Payload.DeleteResponse;
import com.Api.HealthPlus.Payload.DoctorCategoryDto;
import com.Api.HealthPlus.Service.DoctorCategoryService;
import com.Api.HealthPlus.Service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping("/api/doc/cate")
@CrossOrigin("*")
public class DoctorCategoryController {

    @Autowired
    private FileService fileService;

    @Autowired
    private DoctorCategoryService doctorCategoryService;

    @Value("${project.image}")
    private String path;


    //method to save image
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/image/upload/{doc_cate_id}")
    public ResponseEntity<DoctorCategoryDto> createDoctorCategory(
            @RequestParam("image") MultipartFile image,
            @PathVariable("doc_cate_id") int id
            )throws IOException{
        DoctorCategoryDto docCateById = doctorCategoryService.getDocCateById(id);
        String fileName = fileService.uploadImage(path, image);
        docCateById.setImg(fileName);
        DoctorCategoryDto doctorCategory = doctorCategoryService.createDoctorCategory(docCateById);
        return ResponseEntity.ok(doctorCategory);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<DoctorCategoryDto> createDocCategory(
            @RequestBody @Valid DoctorCategoryDto doctorCategoryDto
    ){
        DoctorCategoryDto doctorCategory = doctorCategoryService.createDoctorCategory(doctorCategoryDto);
        System.out.println(doctorCategory.getName());
        return ResponseEntity.ok(doctorCategory);
    }

    @GetMapping("/get/allCategories")
    public ResponseEntity<List<DoctorCategoryDto>> getAllDoctorCategories(){
        List<DoctorCategoryDto> categories = doctorCategoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getById/{doc_id}")
    public ResponseEntity<DoctorCategoryDto> getDoctorCategoryById(
            @PathVariable("doc_id") int id
    ){
        DoctorCategoryDto doctorCategoryById = doctorCategoryService.getDoctorCategoryById(id);
        return ResponseEntity.ok(doctorCategoryById);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{doc_id}")
    public ResponseEntity<DoctorCategoryDto> updateDoctorCategory(
            @RequestBody DoctorCategoryDto doctorCategoryDto,
            @PathVariable("doc_id") int id
    ){
        DoctorCategoryDto categoryDto = doctorCategoryService.updateDoctorCategory(id, doctorCategoryDto);
        return ResponseEntity.ok(categoryDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/cate/{doc_Id}")
    public ResponseEntity<DeleteResponse> deleteDoctorCategory(
            @PathVariable("doc_Id") int id
    ){
        doctorCategoryService.deleteCategory(id);
        return new ResponseEntity<>(new DeleteResponse
                ("Doctor Category deleted successfully", HttpStatus.OK.value(), true)
                ,HttpStatus.OK);
    }

    @GetMapping("/getDoc/{doc_id}")
    public ResponseEntity<DoctorCategoryDto> getDocCat(
            @PathVariable("doc_id") int id
    ){
        DoctorCategoryDto doctorCategoryByDoctorId = doctorCategoryService.getDoctorCategoryByDoctorId(id);
        return ResponseEntity.ok(doctorCategoryByDoctorId);
    }
}
