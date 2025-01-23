package com.example.selfcare.repository;

import com.example.selfcare.dto.AppointmentDTO;
import com.example.selfcare.dto.AppointmentResponse;
import com.example.selfcare.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByPid(Integer pid);
    List<Appointment> findByDocid(Integer docid);
    List<Appointment> findByPidAndDiagImageIsNotNull(int pid);
}
