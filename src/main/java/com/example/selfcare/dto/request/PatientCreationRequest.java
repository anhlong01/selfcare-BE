package com.example.selfcare.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID")
    String pemail;

    @Size(min = 6, message = "INVALID_PASSWORD")
    String ppassword;

    String pname;
    String paddress;

//    @DobConstraint(min = 10, message = "INVALID_DOB")
    LocalDate pdob;
}
