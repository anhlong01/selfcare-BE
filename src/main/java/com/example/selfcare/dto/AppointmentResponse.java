package com.example.selfcare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@AllArgsConstructor
@Getter
@Setter
public class AppointmentResponse {
    private int appoid;
    private String pname;
    private String docname;
    private int hasDone;
    private String scheduledate;
    private String starttime;
    private String endtime;
    private String image;
}
