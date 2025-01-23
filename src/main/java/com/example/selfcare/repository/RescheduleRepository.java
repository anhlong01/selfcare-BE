package com.example.selfcare.repository;

import com.example.selfcare.entity.Reschedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RescheduleRepository extends JpaRepository<Reschedule, Long> {
}
