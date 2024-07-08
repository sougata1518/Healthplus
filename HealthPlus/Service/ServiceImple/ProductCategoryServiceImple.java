package com.Api.HealthPlus.Service.ServiceImple;

import com.Api.HealthPlus.Entity.ProductCategory;
import com.Api.HealthPlus.Exception.ResourseNotFoundException;
import com.Api.HealthPlus.Payload.ProductCategoryDto;
import com.Api.HealthPlus.Repositary.ProductCategoryRepositary;
import com.Api.HealthPlus.Service.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImple implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepositary productCategoryRepositary;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto) {
        ProductCategory prodCate = modelMapper.map(productCategoryDto, ProductCategory.class);
        ProductCategory save = productCategoryRepositary.save(prodCate);
        ProductCategoryDto savedData = modelMapper.map(save, ProductCategoryDto.class);
        return savedData;
    }

    @Override
    public List<ProductCategoryDto> getAllProductCategory() {
        List<ProductCategory> list = productCategoryRepositary.findAll();
        List<ProductCategoryDto> listOfProductCategory = list
                .stream()
                .map((l) -> modelMapper.map(l, ProductCategoryDto.class))
                .collect(Collectors.toList());
        return listOfProductCategory;
    }

    @Override
    public ProductCategoryDto getProductById(int id) {
        ProductCategory productCategory = productCategoryRepositary.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Products Category", "prodId", id));
        ProductCategoryDto productCateDto = modelMapper.map(productCategory, ProductCategoryDto.class);
        return productCateDto;
    }

    @Override
    public ProductCategoryDto updateProductCategory(ProductCategoryDto productCategoryDto, int prodCate_Id) {
        ProductCategory productCategory = productCategoryRepositary.findById(prodCate_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Product Category", "prodCate_Id", prodCate_Id));
        ProductCategory map = modelMapper.map(productCategoryDto, ProductCategory.class);
        productCategory.setName(map.getName());
        ProductCategory save = productCategoryRepositary.save(productCategory);
        ProductCategoryDto updateData = modelMapper.map(save, ProductCategoryDto.class);
        return updateData;
    }

    @Override
    public void deleteProductByProductCategory(int prodCate_Id) {
        ProductCategory productCategory = productCategoryRepositary.findById(prodCate_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Product Category", "prodCate_Id", prodCate_Id));
        productCategoryRepositary.delete(productCategory);
    }
}
