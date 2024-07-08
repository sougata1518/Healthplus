package com.Api.HealthPlus.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name",nullable = false)
    private String name;
    @Column(name = "user_email",nullable = false)
    private String email;
    @Column(name = "user_phone",nullable = false)
    private String phone;
    @Column(name = "user_address")
    private String address;
    @Column(name = "user_city")
    private String city;
    @Column(name = "user_state")
    private String state;
    @Column(name = "user_pin")
    private String pin;
    @Column(name = "user_password",nullable = false)
    private String password;
    @Column(name = "user_role",nullable = false)
    private String role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Cart> cart = new ArrayList<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<AllPayment> allPayments = new ArrayList<>();
}
