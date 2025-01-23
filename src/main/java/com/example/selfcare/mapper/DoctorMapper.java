package com.example.selfcare.mapper;

import com.example.selfcare.dto.request.DoctorCreationRequest;
import com.example.selfcare.dto.request.PatientCreationRequest;
import com.example.selfcare.entity.Doctor;
import com.example.selfcare.entity.Patient;

public class DoctorMapper {
    public static Doctor toDoctor(DoctorCreationRequest request){
        Doctor doctor = new Doctor();
        doctor.setDocemail(request.getDocemail());
        doctor.setDocname(request.getDocname());
        doctor.setDocpassword(request.getDocpassword());
        doctor.setSpecialties(request.getSpecialties());
        doctor.setSex(request.getSex());
        doctor.setChiefId(request.getChiefId());
        doctor.setPrice(request.getPrice());
        doctor.setAcademicRank(request.getAcademicRank());
        return doctor;
    }
}
