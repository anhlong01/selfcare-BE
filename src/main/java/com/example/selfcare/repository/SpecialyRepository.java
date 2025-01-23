package com.example.selfcare.repository;

import com.example.selfcare.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialyRepository extends JpaRepository<Specialty, Integer> {

}
