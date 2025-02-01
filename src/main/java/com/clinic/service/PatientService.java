package com.clinic.service;

import com.clinic.constant.Messages;
import com.clinic.entities.Doctor;
import com.clinic.entities.Patient;
import com.clinic.repository.DoctorRepository;
import com.clinic.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository repository;

    public Patient findPatientByName(String name) {
//        return repository.findByName(name);
        return repository.findByNameWithAppointments(name)
                .orElseThrow(() -> new EntityNotFoundException(Messages.PATIENT_NOT_FOUND));

    }
    public Patient savePatient(Patient patient) {
        return repository.save(patient);
    }

    public Patient findPatientById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(Messages.PATIENT_NOT_FOUND));
    }


}
