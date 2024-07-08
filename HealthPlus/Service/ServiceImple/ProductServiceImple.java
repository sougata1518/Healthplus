package com.Api.HealthPlus.Service.ServiceImple;

import com.Api.HealthPlus.Entity.ProductCategory;
import com.Api.HealthPlus.Entity.Products;
import com.Api.HealthPlus.Exception.ResourseNotFoundException;
import com.Api.HealthPlus.Payload.ProductDto;
import com.Api.HealthPlus.Repositary.ProductCategoryRepositary;
import com.Api.HealthPlus.Repositary.ProductRepositarty;
import com.Api.HealthPlus.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImple implements ProductService {

    @Autowired
    private ProductCategoryRepositary productCategoryRepositary;
    @Autowired
    private ProductRepositarty productRepositarty;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProducts(ProductDto productDto, int prodCat_Id) {
        ProductCategory productCategory = productCategoryRepositary.findById(prodCat_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Product Category", "prodCat_Id", prodCat_Id));
        Products product = modelMapper.map(productDto, Products.class);
        product.setProductCategory(productCategory);
        Products save = productRepositarty.save(product);
        ProductDto saveData = modelMapper.map(save, ProductDto.class);
        return saveData;
    }

    @Override
    public ProductDto getProductById(int prodId) {
        Products products = productRepositarty.findById(prodId)
                .orElseThrow(() -> new ResourseNotFoundException("Product", "Product Id", prodId));
        ProductDto productDto = modelMapper.map(products, ProductDto.class);
        return productDto;
    }

    @Override
    public List<ProductDto> getProductByProductCategory(int prodCat_Id) {
        ProductCategory productCategory = productCategoryRepositary.findById(prodCat_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Product Category", "Product_Category_Id", prodCat_Id));
        List<Products> byProductCategory = productRepositarty.findByProductCategory(productCategory);
        List<ProductDto> products = byProductCategory
                .stream()
                .map((product) -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return products;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, int prod_Id) {
        Products products = productRepositarty.findById(prod_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Product", "Product_Id", prod_Id));
        Products map = modelMapper.map(productDto, Products.class);
        products.setName(map.getName());
        products.setPrice(map.getPrice());
        products.setDist_per(map.getDist_per());
        products.setDist_price(map.getDist_price());
        Products save = productRepositarty.save(products);
        ProductDto updateData = modelMapper.map(save, ProductDto.class);
        return updateData;
    }

    @Override
    public void deleteProductData(int prod_Id) {
        Products products = productRepositarty.findById(prod_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Product", "Product_Id", prod_Id));
        productRepositarty.delete(products);
    }
}
