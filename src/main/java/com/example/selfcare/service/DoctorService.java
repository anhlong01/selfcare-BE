package com.example.selfcare.service;

import com.example.selfcare.dto.DoctorDTO;
import com.example.selfcare.dto.request.DoctorCreationRequest;
import com.example.selfcare.dto.request.PatientCreationRequest;
import com.example.selfcare.entity.AcademicRank;
import com.example.selfcare.entity.Doctor;
import com.example.selfcare.entity.Patient;
import com.example.selfcare.entity.Specialty;
import com.example.selfcare.exception.AppException;
import com.example.selfcare.exception.ErrorCode;
import com.example.selfcare.repository.AcademicRankRepository;
import com.example.selfcare.repository.DoctorRepository;
import com.example.selfcare.repository.SpecialyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.selfcare.mapper.DoctorMapper.toDoctor;
import static com.example.selfcare.mapper.PatientMapper.toPatient;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    SpecialyRepository specialyRepository;
    @Autowired
    AcademicRankRepository academicRankRepository;

    public Doctor createDoctor(DoctorCreationRequest request){
        if(doctorRepository.existsByDocemail(request.getDocemail()))
            throw new AppException(ErrorCode.USER_EXISTED);
        Doctor doctor = toDoctor(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        doctor.setDocpassword(passwordEncoder.encode(request.getDocpassword()));
        return doctorRepository.save(doctor);
    }
    public List<DoctorDTO> getDoctorsBySpecialtyAndChief(int specialtiesId, int chiefId) {
        List<Doctor> doctors = doctorRepository.findBySpecialtiesAndChiefId(specialtiesId, chiefId);

        return doctors.stream().map(doctor -> {
            Specialty specialty = specialyRepository.findById(specialtiesId).orElse(null);
            AcademicRank academicRank = academicRankRepository.findById(doctor.getAcademicRank()).orElse(null);
            DoctorDTO dto = new DoctorDTO();
            dto.setDocId(doctor.getDocid());
            dto.setDocEmail(doctor.getDocemail());
            dto.setDocName(doctor.getDocname());
            dto.setSpecialties(specialtiesId);
            dto.setSpecialtiesName(specialty.getSname());
            dto.setAcademicRank(academicRank.getDescription());
            dto.setSex(doctor.getSex());
            dto.setPrice(doctor.getPrice());
            return dto;
        }).collect(Collectors.toList());
    }
}
