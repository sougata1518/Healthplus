package com.Api.HealthPlus.Service;

import com.Api.HealthPlus.Payload.ProductDto;

import java.util.List;

public interface ProductService {

    public ProductDto createProducts(ProductDto productDto,int prodCat_Id);
    public  ProductDto getProductById(int prodId);
    public List<ProductDto> getProductByProductCategory(int prodCat_Id);
    public ProductDto updateProduct(ProductDto productDto,int prod_Id);
    public void deleteProductData(int prod_Id);
}
