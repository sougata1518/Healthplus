package com.Api.HealthPlus.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cart_productImg",nullable = false)
    private String img;
    @Column(name = "cart_productName",nullable = false)
    private String name;
    @Column(name = "cart_productMainPrice",nullable = false)
    private String main_price;
    @Column(name = "cart_productQuantity",nullable = false)
    private String quantity;
    @Column(name = "cart_productTotalPrice",nullable = false)
    private String total_price;
    @Column(name = "cart_type(1=lab and 2=product)")
    private String cartType;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;
}
