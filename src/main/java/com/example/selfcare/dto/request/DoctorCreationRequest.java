package com.example.selfcare.dto.request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorCreationRequest {
    String docemail;
    String docname;
    String docpassword;
    String doctel;
    int specialties;
    int academicRank;
    int chiefId;
    String sex;
    int price;
}
