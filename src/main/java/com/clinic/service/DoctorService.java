package com.clinic.service;

import com.clinic.constant.Messages;
import com.clinic.entities.Appointment;
import com.clinic.entities.Doctor;
import com.clinic.entities.Patient;
import com.clinic.repository.AppointmentRepository;
import com.clinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    /**
     * Saves a doctor to the database.
     *
     * @param doctor the doctor to be saved
     * @return the doctor that was saved
     */
    public Doctor saveDoctor(Doctor doctor) {
        return repository.save(doctor);
    }


    /**
     * Find a doctor by name.
     *
     * @param doctorName the doctor's name
     * @return the doctor with the given name
     */
    public Doctor findDoctorByName(String doctorName) {
        return repository.findByName(doctorName);
    }
    public Doctor findDoctorById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(Messages.DOCTOR_NOT_FOUND));
    }

}
