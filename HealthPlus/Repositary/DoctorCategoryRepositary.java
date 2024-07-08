package com.Api.HealthPlus.Repositary;

import com.Api.HealthPlus.Entity.DoctorCategory;
import com.Api.HealthPlus.Entity.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorCategoryRepositary extends JpaRepository<DoctorCategory,Integer> {

    DoctorCategory findByDoctor(Doctors doctors);
}
