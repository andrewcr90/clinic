package com.clinic.service;

import com.clinic.entities.Doctor;
import com.clinic.entities.Specialty;
import com.clinic.entities.Department;
import com.clinic.repository.DoctorRepository;
import com.clinic.repository.SpecialtyRepository;
import com.clinic.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private SpecialtyRepository specialtyRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDoctor() {
        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName("Cardiology");

        Department department = new Department();
        department.setId(1L);
        department.setName("Health");

        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("John");
        doctor.setLastName("Doe");
        doctor.setSpecialty(specialty);
        doctor.setDepartment(department);

        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(specialty));
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        Doctor savedDoctor = doctorService.saveDoctor(doctor);

        assertNotNull(savedDoctor);
        assertEquals("John", savedDoctor.getName());
        assertEquals("Doe", savedDoctor.getLastName());
        assertEquals("Cardiology", savedDoctor.getSpecialty().getName());
        assertEquals("Health", savedDoctor.getDepartment().getName());

        verify(specialtyRepository, times(1)).findById(1L);
        verify(departmentRepository, times(1)).findById(1L);
        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }


    @Test
    public void testFindDoctorById() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("John");

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        Doctor foundDoctor = doctorService.findDoctorById(1L);

        assertNotNull(foundDoctor);
        assertEquals("John", foundDoctor.getName());

        verify(doctorRepository, times(1)).findById(1L);
    }
}
