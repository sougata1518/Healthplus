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
@Table(name = "Lab_Category")
public class LabCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "LabCate_img")
    private String img;
    @Column(name = "LabCate_name",nullable = false)
    private String name;
    @OneToMany(mappedBy = "labCategory",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<LabTest> labTest = new ArrayList<>();
}
