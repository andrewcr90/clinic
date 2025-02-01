package com.clinic.entities;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Doctor {
    @Id
    private Long id;
    private String name;
    private String lastName;
    private String speciality;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("doctor")
    private Set<Appointment> appointments;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}