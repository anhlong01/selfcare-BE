package com.example.selfcare.controllers;

import com.example.selfcare.dto.AppointmentDTO;
import com.example.selfcare.dto.AppointmentResponse;
import com.example.selfcare.dto.DoctorDTO;
import com.example.selfcare.dto.ScheduleDTO;
import com.example.selfcare.dto.request.DoctorCreationRequest;
import com.example.selfcare.dto.response.ApiResponse;
import com.example.selfcare.entity.Clinic;
import com.example.selfcare.entity.Doctor;
import com.example.selfcare.entity.Specialty;
import com.example.selfcare.repository.ClinicRepository;
import com.example.selfcare.repository.DoctorRepository;
import com.example.selfcare.repository.SpecialyRepository;
import com.example.selfcare.service.AppointmentService;
import com.example.selfcare.service.DoctorService;
import com.example.selfcare.service.RescheduleService;
import com.example.selfcare.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("edoc")
public class ClinicController {
    @Autowired
    ClinicRepository repository;
    @Autowired
    SpecialyRepository  specialyRepository;
    @Autowired
    DoctorService doctorService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private RescheduleService rescheduleService;

    @GetMapping("/clinics")
    List<Clinic> getAllClinics(){
        return repository.findAll();
    }

    @GetMapping("/specialties")
    List<Specialty> getAllSpecilaties(){
        return specialyRepository.findAll();
    }

    @PostMapping("/create-doctor")
    ApiResponse<Doctor> createDoctor(@RequestBody DoctorCreationRequest request){
        ApiResponse<Doctor> apiResponse = new ApiResponse<>();
        apiResponse.setResult(doctorService.createDoctor(request));
        return apiResponse;
    }

    @GetMapping("/doctors")
    public List<DoctorDTO> getDoctors(@RequestParam int chief_id, @RequestParam int spe_id) {
        return doctorService.getDoctorsBySpecialtyAndChief(spe_id, chief_id); }

    @GetMapping("/time")
    public List<ScheduleDTO> getSchedules(@RequestParam int docid, @RequestParam String date) {
        return scheduleService.getSchedules(docid, date);
    }

    @GetMapping("/appointments")
    public List<AppointmentDTO> getAppointments(@RequestParam int pid) {
        return appointmentService.getAppointments(pid);
    }

    @GetMapping("/appointments2")
    public List<AppointmentDTO> getAppointmentsByDocid(@RequestParam int docid){
        return appointmentService.getAppointmentsByDocid(docid);
    }

    @GetMapping("/diagnose")
    public List<AppointmentResponse> getDiagnose(@RequestParam int pid){
        return appointmentService.getAppointmentsWithDiagImage(pid);
    }

    @PostMapping("/reschedule")
    public Map<String, String> reschedule(@RequestParam Long pid, @RequestParam Long docid, @RequestParam String date) {
        String result = rescheduleService.insertReschedule(pid, docid, date);

        Map<String, String> response = new HashMap<>();
        response.put("result", result);

        return response;
    }

    @PostMapping("/schedule/create")
    public String createScheduleForNext7Days() {
        scheduleService.createScheduleForNext7Days();
        return "Schedules created successfully!";
    }
}
