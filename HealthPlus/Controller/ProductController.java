package com.Api.HealthPlus.Controller;

import com.Api.HealthPlus.Payload.DeleteResponse;
import com.Api.HealthPlus.Payload.ProductDto;
import com.Api.HealthPlus.Service.FileService;
import com.Api.HealthPlus.Service.ProductService;
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
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private FileService fileService;
    @Autowired
    private ProductService productService;

    @Value("${project.image}")
    private String path;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/ProductCate/{prodCate_Id}")
    public ResponseEntity<ProductDto> createProductData(
            @PathVariable("prodCate_Id") int id,
            @RequestBody @Valid ProductDto productDto
    ){
        ProductDto products = productService.createProducts(productDto, id);
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/image/upload/{product_id}/{prod_cat_id}")
    public ResponseEntity<ProductDto> createDoctorCategory(
            @RequestParam("image") MultipartFile image,
            @PathVariable("product_id") int id,
            @PathVariable("prod_cat_id") int prodId
    )throws IOException {
        System.out.println("image = "+image);
        ProductDto productById = productService.getProductById(id);
        String fileName = fileService.uploadImage(path, image);
        productById.setImg(fileName);
        ProductDto products = productService.createProducts(productById, prodId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/get/prodCat/{prodCat_Id}")
    public ResponseEntity<List<ProductDto>> getProductByProductCategory(
            @PathVariable("prodCat_Id") int id
    ){
        List<ProductDto> productByProductCategory = productService.getProductByProductCategory(id);
        return ResponseEntity.ok(productByProductCategory);
    }

    @GetMapping("/getProductData/byId/{prodId}")
    public ResponseEntity<ProductDto> getProductById(
            @PathVariable("prodId") int id
    ){
        ProductDto productById = productService.getProductById(id);
        return ResponseEntity.ok(productById);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{prod_Id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable("prod_Id") int id,
            @RequestBody ProductDto productDto
    ){
        ProductDto product = productService.updateProduct(productDto, id);
        return ResponseEntity.ok(product);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{prod_Id}")
    public ResponseEntity<DeleteResponse> deleteProduct(
            @PathVariable("prod_Id") int id
    ){
        productService.deleteProductData(id);
        return new ResponseEntity<>(new DeleteResponse
                ("Product deleted successfully", HttpStatus.OK.value(), true)
        ,HttpStatus.OK);
    }
}
