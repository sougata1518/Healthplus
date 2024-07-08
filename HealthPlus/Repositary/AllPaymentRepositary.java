package com.Api.HealthPlus.Repositary;

import com.Api.HealthPlus.Entity.AllPayment;
import com.Api.HealthPlus.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllPaymentRepositary extends JpaRepository<AllPayment,Integer> {

    public List<AllPayment> findByUser(User user);
}
