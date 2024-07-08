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
@Table(name = "Doctors")
public class Doctors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "doc_name",nullable = false)
    private String name;
    @Column(name = "doc_degree",nullable = false)
    private String degree;
    @Column(name = "medical_experience",nullable = false)
    private String med_exp;
    @Column(name = "available_time",nullable = false)
    private String avail_time;
    @Column(name = "doc_fee",nullable = false)
    private String fee;
    @ManyToOne
    @JoinColumn(name = "doctorCategory_Id")
    private DoctorCategory doctorCategory;
}
