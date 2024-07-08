package com.Api.HealthPlus.Repositary;

import com.Api.HealthPlus.Entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepositary extends JpaRepository<ProductCategory,Integer> {
}
