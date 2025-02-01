package com.clinic.entities;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AppoinmentRequest {

     String patientName;
     String  doctorName;
     LocalDateTime dateTime;

}
