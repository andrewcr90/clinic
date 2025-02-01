package com.clinic.service;

import com.clinic.constant.Messages;
import com.clinic.entities.*;
import com.clinic.repository.AppointmentRepository;
import com.clinic.repository.DepartmentRepository;
import com.clinic.repository.DoctorRepository;
import com.clinic.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Saves a doctor to the database.
     *
     * @param doctor the doctor to be saved
     * @return the doctor that was saved
     */
    public Doctor saveDoctor(Doctor doctor) {
        Specialty specialty = specialtyRepository.findById(doctor.getSpecialty().getId()).orElse(null);
        if (specialty == null) {
            specialty = specialtyRepository.save(doctor.getSpecialty());
        }
        doctor.setSpecialty(specialty);

        Department department = departmentRepository.findById(doctor.getDepartment().getId()).orElse(null);
        if (department == null) {
            department = departmentRepository.save(doctor.getDepartment());
        }
        doctor.setDepartment(department);
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
