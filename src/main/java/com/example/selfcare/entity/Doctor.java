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
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int docid;
    String docemail;
    String docname;
    String docpassword;
    String doctel;
    int specialties;
    int academicRank;
    @Column(name = "chief_id")
    int chiefId;
    String sex;
    int price;
//    String token;
}
