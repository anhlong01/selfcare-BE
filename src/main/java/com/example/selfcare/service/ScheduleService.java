package com.example.selfcare.service;

import com.example.selfcare.dto.ScheduleDTO;
import com.example.selfcare.entity.Schedule;
import com.example.selfcare.repository.DoctorRepository;
import com.example.selfcare.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    private static final String[] START_TIME = {"07:00:00", "07:15:00", "07:30:00", "07:45:00", "08:00:00","08:15:00","08:30:00","08:45:00", "09:00:00", "09:15:00", "09:30:00", "09:45:00", "10:00:00", "10:15:00", "10:30:00", "10:45:00", "11:00:00", "11:15:00", "11:30:00", "13:00:00", "13:15:00", "13:30:00","13:45:00", "14:00:00", "14:15:00","14:30:00","14:45:00" };
    private static final String[] END_TIME = {"07:15:00", "07:30:00", "07:45:00", "08:00:00","08:15:00","08:30:00","08:45:00", "09:00:00", "09:15:00", "09:30:00",  "09:45:00", "10:00:00", "10:15:00", "10:30:00", "10:45:00", "11:00:00", "11:15:00", "11:30:00", "11:45:00", "13:15:00", "13:30:00", "13:45:00", "14:00:00", "14:15:00","14:30:00","14:45:00","15:00:00"};
    public List<ScheduleDTO> getSchedules(int docId, String date) {
        List<Schedule> schedules = scheduleRepository.findByDocIdAndScheduleDateOrderByStartTimeAsc(docId, date);

        return schedules.stream().map(schedule -> {
            ScheduleDTO dto = new ScheduleDTO();
            dto.setScheduleId(schedule.getScheduleId());
            dto.setStartTime(schedule.getStartTime());
            dto.setEndTime(schedule.getEndTime());
            dto.setBooked(schedule.getBooked());
            return dto;
        }).collect(Collectors.toList());
    }


    // Arrays of start and end times


    @Transactional
    public void createScheduleForNext7Days() {
        // Get all doctor IDs from the Doctor table
        List<Integer> doctorIds = doctorRepository.findAllDoctorIds(); // Custom query to fetch doctor IDs

        // Current date
        LocalDate currentDate = LocalDate.now();

        // Iterate over each doctor
        for (int docid : doctorIds) {
            int dayOffset = 0;
            while (dayOffset < 7) {
                LocalDate scheduleDate = currentDate.plusDays(dayOffset); // Increment date

                // Insert schedule for each doctor for the current date
                for (int i = 0; i < START_TIME.length; i++) {
                    Schedule schedule = new Schedule();
                    schedule.setDocId(docid);
                    schedule.setScheduleDate(String.valueOf(scheduleDate));
                    schedule.setStartTime(START_TIME[i]);
                    schedule.setEndTime(END_TIME[i]);
                    schedule.setBooked(0); // Set the schedule as not booked initially

                    // Save the schedule
                    scheduleRepository.save(schedule);
                }
                dayOffset++;
            }
        }
    }
}
