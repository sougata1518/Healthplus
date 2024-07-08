package com.Api.HealthPlus.Repositary;

import com.Api.HealthPlus.Entity.ProductCategory;
import com.Api.HealthPlus.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositarty extends JpaRepository<Products,Integer> {

    public List<Products> findByProductCategory(ProductCategory productCategory);
}
