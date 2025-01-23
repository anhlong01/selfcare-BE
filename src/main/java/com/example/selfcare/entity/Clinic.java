package com.example.selfcare.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="clinic")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clinic_id")
    int clinicId;
    @Column(name = "chief_id")
    int chiefId;
    String address;
    @Column(name = "clinic_image")
    String image;
    @Column(name = "clinic_name")
    String name;
    double latitude;
    double longitude;
}
