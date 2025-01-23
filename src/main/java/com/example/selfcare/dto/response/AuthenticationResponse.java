package com.example.selfcare.dto.response;

import com.example.selfcare.entity.Patient;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    String token;
    Patient patient;
    boolean authenticated;
}