package com.Api.HealthPlus.Repositary;

import com.Api.HealthPlus.Entity.Consult_us;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultUsRepositary extends JpaRepository<Consult_us,Integer> {
}
