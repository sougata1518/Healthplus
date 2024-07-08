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
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_img")
    private String img;
    @Column(name = "product_name",nullable = false)
    private String name;
    @Column(name = "product_price",nullable = false)
    private String price;
    @Column(name = "product_discountedPercentage",nullable = false)
    private String dist_per;
    @Column(name = "product_discountedPrice",nullable = false)
    private String dist_price;
    @ManyToOne
    @JoinColumn(name = "productCategory_Id")
    private ProductCategory productCategory;
}
