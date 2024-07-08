package com.Api.HealthPlus.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Consult_Us")
public class Consult_us {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String number;
    private String gender;
    private String dob;
    private String address;
    private String medical_conditions;
    private String current_medications;
    private String reason;
    @Column(name = "prefer_date")
    private String app_date;
    @Column(name = "prefer_time")
    private String consultation_time;

}
