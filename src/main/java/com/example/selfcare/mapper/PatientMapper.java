package com.example.selfcare.mapper;

import com.example.selfcare.dto.request.PatientCreationRequest;
import com.example.selfcare.entity.Patient;



public class PatientMapper {
    public static Patient toPatient(PatientCreationRequest request){
        Patient patient = new Patient();
        patient.setPemail(request.getPemail());
        patient.setPname(request.getPname());
        patient.setPpassword(request.getPpassword());
        patient.setPaddress(request.getPaddress());
        patient.setPdob(request.getPdob());
        return patient;
    }
}
