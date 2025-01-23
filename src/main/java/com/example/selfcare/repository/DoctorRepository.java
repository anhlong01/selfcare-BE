package com.example.selfcare.repository;

import com.example.selfcare.entity.Doctor;
import com.example.selfcare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findBySpecialtiesAndChiefId(int specialties, int chiefId);
    boolean existsByDocemail(String docmail);
    @Query("SELECT d.docid FROM Doctor d")
    List<Integer> findAllDoctorIds();
    Optional<Doctor> findByDocemail(String docemail);
}
