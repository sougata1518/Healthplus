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
@Table(name = "Doctor_Category")
public class DoctorCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cate_img")
    private String img;
    @Column(name = "Doctor_Field" , nullable = false)
    private String name;
    @OneToMany(mappedBy = "doctorCategory",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Doctors> doctor = new ArrayList<>();
}
