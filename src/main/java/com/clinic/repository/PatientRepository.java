package com.clinic.repository;

import com.clinic.entities.Doctor;
import com.clinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


    Patient findByName(String name);

    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments WHERE p.name = :name")
    Optional<Patient> findByNameWithAppointments(@Param("name") String name);

}
