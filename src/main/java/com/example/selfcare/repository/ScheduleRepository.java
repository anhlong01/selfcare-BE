package com.example.selfcare.repository;

import com.example.selfcare.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByDocIdAndScheduleDateOrderByStartTimeAsc(int docId, String scheduleDate);
}
