package com.Api.HealthPlus.Controller;

import com.Api.HealthPlus.Payload.DeleteResponse;
import com.Api.HealthPlus.Payload.ProductCategoryDto;
import com.Api.HealthPlus.Service.ProductCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/cat")
@CrossOrigin("*")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<ProductCategoryDto> createProductCategory(
            @RequestBody @Valid ProductCategoryDto productCategoryDto
            ){
        ProductCategoryDto productCategory = productCategoryService.createProductCategory(productCategoryDto);
        return new ResponseEntity<>(productCategory, HttpStatus.CREATED);
    }

    @GetMapping("/get/allProductCate")
    public ResponseEntity<List<ProductCategoryDto>> getAllProductCategory(){
        List<ProductCategoryDto> allProductCategory = productCategoryService.getAllProductCategory();
        return ResponseEntity.ok(allProductCategory);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{prodCate_Id}")
    public ResponseEntity<ProductCategoryDto> updateProductCategory(
            @PathVariable("prodCate_Id") int id,
            @RequestBody ProductCategoryDto productCategoryDto
    ){
        ProductCategoryDto updateProductCategory = productCategoryService.updateProductCategory(productCategoryDto, id);
        return ResponseEntity.ok(updateProductCategory);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{prodCate_Id}")
    public ResponseEntity<DeleteResponse> deleteProductCategory(
            @PathVariable("prodCate_Id") int id
    ){
        productCategoryService.deleteProductByProductCategory(id);
        return new ResponseEntity<>(new DeleteResponse
                ("Product Category deleted successfully", HttpStatus.OK.value(), true)
        ,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getProdCateById/{prodCateId}")
    public ResponseEntity<ProductCategoryDto> getDoctorProductCateById(
            @PathVariable("prodCateId") int id
    ){
        ProductCategoryDto productCategory = productCategoryService.getProductById(id);
        return ResponseEntity.ok(productCategory);
    }
}
