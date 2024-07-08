package com.Api.HealthPlus.Repositary;

import com.Api.HealthPlus.Entity.LabCategory;
import com.Api.HealthPlus.Entity.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabTestRespositary extends JpaRepository<LabTest,Integer> {

    public List<LabTest> findByLabCategory(LabCategory labCategory);
}
