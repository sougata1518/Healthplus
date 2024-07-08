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
@Table(name = "Lab_Test")
public class LabTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "labTest_img")
    private String img;
    @Column(name = "labTest_name",nullable = false)
    private String name;
    @Column(name = "labTest_test_include",nullable = false)
    private String test_include;
    @Column(name = "labTest_mainPrice",nullable = false)
    private String mainPrice;
    @Column(name = "labTest_distPercentage",nullable = false)
    private String distPer;
    @Column(name = "labTest_distPrice",nullable = false)
    private String distPrice;
    @ManyToOne
    @JoinColumn(name = "labCategory_Id")
    private LabCategory labCategory;
}
