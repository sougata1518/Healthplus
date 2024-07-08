package com.Api.HealthPlus.Repositary;

import com.Api.HealthPlus.Entity.DoctorCategory;
import com.Api.HealthPlus.Entity.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepositary extends JpaRepository<Doctors,Integer> {
    List<Doctors> findByDoctorCategory(DoctorCategory doctorCategory);
}
