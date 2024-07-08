package com.Api.HealthPlus.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "All_Payment")
public class AllPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "payment_details",nullable = false)
    private String detail;
    @Column(name = "payment_TotalPrice",nullable = false)
    private String price;
    @Column(name = "payment_Status",nullable = false)
    private String status;
    @Column(name = "cart_type(1=lab and 2=product and 3=doctor)")
    private String cartType;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;
}
