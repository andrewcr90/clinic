package com.clinic.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "patient")
public class Patient {
    @Id
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("patient")
    private HealthPromotingEntity HealthPromotingEntities;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("patient")
    private Set<Appointment> appointments;
}