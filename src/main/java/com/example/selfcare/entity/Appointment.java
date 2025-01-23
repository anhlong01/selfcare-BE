package com.example.selfcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appoid;

    private int pid;
    private int docid;
    @Column(name = "hasDone")
    private int hasDone;
    private String scheduledate;
    private String starttime;
    private String endtime;
    private Integer scheduleid;
    @Column(name = "diagImage")
    private String diagImage;
}
