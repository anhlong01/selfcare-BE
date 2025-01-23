package com.example.selfcare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ScheduleDTO {
    int scheduleId;
    String startTime;
    String endTime;
    int booked;
}
