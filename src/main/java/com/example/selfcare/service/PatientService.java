package com.example.selfcare.service;

import com.example.selfcare.dto.request.PatientCreationRequest;
import com.example.selfcare.entity.Patient;
import com.example.selfcare.exception.AppException;
import com.example.selfcare.exception.ErrorCode;
import com.example.selfcare.mapper.PatientMapper;
import com.example.selfcare.repository.PatientRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.selfcare.mapper.PatientMapper.toPatient;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientService {
    @Autowired
    PatientRepository repository;
    public Patient createPatient(PatientCreationRequest request){
        if(repository.existsByPemail(request.getPemail()))
            throw new AppException(ErrorCode.USER_EXISTED);
        Patient patient = toPatient(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        patient.setPpassword(passwordEncoder.encode(request.getPpassword()));
        return repository.save(patient);
    }

    public List<Patient> getAllPatient(){
        return repository.findAll();
    }
}
