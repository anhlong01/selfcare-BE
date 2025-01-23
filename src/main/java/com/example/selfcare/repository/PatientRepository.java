package com.example.selfcare.repository;

import com.example.selfcare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    boolean existsByPemail(String pemail);
    Optional<Patient> findByPemail(String pemail);
}
