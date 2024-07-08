package com.Api.HealthPlus.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product_Category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "productCategory_name",nullable = false)
    private String name;
    @OneToMany(mappedBy = "productCategory",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Products> products = new ArrayList<>();
}
