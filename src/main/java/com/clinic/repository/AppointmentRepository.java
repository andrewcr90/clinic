package com.clinic.repository;

import com.clinic.entities.Appointment;
import com.clinic.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorAndAppointmentDate(Doctor doctor, LocalDateTime dateTime);
    boolean existsByDoctorAndAppointmentDate(Doctor doctor, LocalDateTime dateTime);

    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long patientId);
}
