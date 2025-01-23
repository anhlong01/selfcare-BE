package com.example.selfcare.service;

import com.example.selfcare.dto.AppointmentDTO;
import com.example.selfcare.dto.AppointmentResponse;
import com.example.selfcare.entity.*;
import com.example.selfcare.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<AppointmentDTO> getAppointments(int pid) {
        List<Appointment> appointments = appointmentRepository.findByPid(pid);

        return appointments.stream().map(appointment -> {
            AppointmentDTO dto = new AppointmentDTO();
            dto.setAppoid(appointment.getAppoid());
            dto.setDocid(appointment.getDocid());
            dto.setPid(pid);
            dto.setHasDone(appointment.getHasDone());
            dto.setScheduledate(appointment.getScheduledate());
            dto.setStarttime(appointment.getStarttime());
            dto.setEndtime(appointment.getEndtime());

            Doctor doctor = doctorRepository.findById(appointment.getDocid()).orElse(null);
            if (doctor != null) {
                dto.setDocname(doctor.getDocname());
            }

            Patient patient = patientRepository.findById(pid).orElse(null);
            if (patient != null) {
                dto.setPname(patient.getPname());
            }

            Clinic clinic = clinicRepository.findById(doctor.getChiefId()).orElse(null);
            if (clinic != null) {
                dto.setClinicName(clinic.getName());
                dto.setAddress(clinic.getAddress());
            }

            return dto;
        }).collect(Collectors.toList());
    }

    public List<AppointmentDTO> getAppointmentsByDocid(int docid) {
        List<Appointment> appointments = appointmentRepository.findByDocid(docid);

        return appointments.stream().map(appointment -> {
            AppointmentDTO dto = new AppointmentDTO();
            dto.setAppoid(appointment.getAppoid());
            dto.setDocid(docid);
            dto.setPid(appointment.getPid());
            dto.setHasDone(appointment.getHasDone());
            dto.setScheduledate(appointment.getScheduledate());
            dto.setStarttime(appointment.getStarttime());
            dto.setEndtime(appointment.getEndtime());

            Doctor doctor = doctorRepository.findById(docid).orElse(null);
            if (doctor != null) {
                dto.setDocname(doctor.getDocname());
            }

            Patient patient = patientRepository.findById(appointment.getPid()).orElse(null);
            if (patient != null) {
                dto.setPname(patient.getPname());
            }

            Clinic clinic = clinicRepository.findById(doctor.getChiefId()).orElse(null);
            if (clinic != null) {
                dto.setClinicName(clinic.getName());
                dto.setAddress(clinic.getAddress());
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void createAppointment(int pid, int scheduleid) {
        // Fetch schedule by scheduleid
        Schedule schedule = scheduleRepository.findById(scheduleid).orElse(null);

        if (schedule == null) {
            return;
        }

        // Create a new Appointment and set values from Schedule
        Appointment appointment = new Appointment();
        appointment.setPid(pid);
        appointment.setDocid(schedule.getDocId());
        appointment.setScheduledate(schedule.getScheduleDate());
        appointment.setStarttime(schedule.getStartTime());
        appointment.setEndtime(schedule.getEndTime());
        appointment.setHasDone(0); // Assuming the appointment has not been done initially

        // Save the appointment to the database
        appointmentRepository.save(appointment);
    }

    public List<AppointmentResponse> getAppointmentsWithDiagImage(int pid) {
        // Fetch all appointments with diagImage for the given patient
        List<Appointment> appointments = appointmentRepository.findByPidAndDiagImageIsNotNull(pid);

        // Convert Appointment entities to AppointmentResponse DTOs
        return appointments.stream()
                .map(appointment -> {
                    // Fetch Patient and Doctor details using the foreign keys
                    Patient patient = patientRepository.findById(appointment.getPid()).orElse(null);
                    Doctor doctor = doctorRepository.findById(appointment.getDocid()).orElse(null);

                    // Map to AppointmentResponse DTO
                    return new AppointmentResponse(
                            appointment.getAppoid(),
                            patient != null ? patient.getPname() : null,
                            doctor != null ? doctor.getDocname() : null,
                            appointment.getHasDone(),
                            appointment.getScheduledate(),
                            appointment.getStarttime(),
                            appointment.getEndtime(),
                            appointment.getDiagImage()
                    );
                })
                .collect(Collectors.toList());
    }
}

