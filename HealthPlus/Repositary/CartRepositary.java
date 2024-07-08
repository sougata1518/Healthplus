package com.Api.HealthPlus.Repositary;

import com.Api.HealthPlus.Entity.Cart;
import com.Api.HealthPlus.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepositary extends JpaRepository<Cart,Integer> {

    public List<Cart> findByUser(User user);
}
