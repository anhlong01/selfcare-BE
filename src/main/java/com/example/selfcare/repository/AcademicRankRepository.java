package com.example.selfcare.repository;

import com.example.selfcare.entity.AcademicRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicRankRepository extends JpaRepository<AcademicRank, Integer> {
}
