package com.Api.HealthPlus.Service;

import com.Api.HealthPlus.Payload.ProductCategoryDto;

import java.util.List;

public interface ProductCategoryService {

    public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto);
    public List<ProductCategoryDto> getAllProductCategory();
    public ProductCategoryDto getProductById(int id);
    public ProductCategoryDto updateProductCategory(ProductCategoryDto productCategoryDto,int prodCate_Id);
    public void deleteProductByProductCategory(int prodCate_Id);
}
