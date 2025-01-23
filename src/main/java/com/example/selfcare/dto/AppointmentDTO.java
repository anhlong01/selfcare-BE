package com.example.selfcare.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
public class AppointmentDTO {
    private int appoid;
    private int docid;
    private int pid;
    private String pname;
    private String docname;
    private int hasDone;
    private String scheduledate;
    private String starttime;
    private String endtime;
    private String clinicName;
    private String address;
}
