package com.example.selfcare.controllers;

import com.example.selfcare.dto.request.DoctorCreationRequest;
import com.example.selfcare.dto.request.PatientCreationRequest;
import com.example.selfcare.dto.response.ApiResponse;
import com.example.selfcare.entity.Patient;
import com.example.selfcare.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edoc/patient")
public class PatientController {
    @Autowired
    PatientService service;
    @PostMapping("")
    ApiResponse<Patient> createPatient(@RequestBody @Valid PatientCreationRequest request){
        ApiResponse<Patient> apiResponse = new ApiResponse<>();
        apiResponse.setResult(service.createPatient(request));
        return apiResponse;
    }

    @GetMapping
    List<Patient> getAllPatient(){
        return service.getAllPatient();
    }

}
