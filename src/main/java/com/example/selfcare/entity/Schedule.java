package com.example.selfcare.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduleid")
    int scheduleId;
    @Column(name = "docid")
    int docId;
    @Column(name = "scheduledate")
    String scheduleDate;
    @Column(name = "starttime")
    String startTime;
    @Column(name = "endtime")
    String endTime;
    int booked;
}
