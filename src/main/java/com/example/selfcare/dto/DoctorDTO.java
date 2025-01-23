package com.example.selfcare.dto;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DoctorDTO {
    private int docId;
    private String docEmail;
    private String docName;
    private int specialties;
    private String specialtiesName;
    private String academicRank;
    private String sex;
    private int price;
}
