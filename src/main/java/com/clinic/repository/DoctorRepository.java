package com.clinic.repository;

import com.clinic.entities.Appointment;
import com.clinic.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DoctorRepository  extends JpaRepository<Doctor, Long> {

    Doctor findByName(String doctorName);

}
