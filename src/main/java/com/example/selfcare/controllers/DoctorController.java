package com.example.selfcare.controllers;

import com.example.selfcare.dto.request.DoctorCreationRequest;
import com.example.selfcare.dto.request.PatientCreationRequest;
import com.example.selfcare.dto.response.ApiResponse;
import com.example.selfcare.entity.Doctor;
import com.example.selfcare.entity.Patient;
import com.example.selfcare.service.DoctorService;
import com.example.selfcare.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("edoc")
public class DoctorController {
    @Autowired
    DoctorService service;

}
