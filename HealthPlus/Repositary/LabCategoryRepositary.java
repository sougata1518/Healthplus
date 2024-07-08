package com.Api.HealthPlus.Repositary;

import com.Api.HealthPlus.Entity.LabCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabCategoryRepositary extends JpaRepository<LabCategory,Integer> {
}
