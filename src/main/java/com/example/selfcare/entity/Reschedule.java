package com.example.selfcare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reschedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     Long pid;
     Long docid;
     String scheduledate;
}
