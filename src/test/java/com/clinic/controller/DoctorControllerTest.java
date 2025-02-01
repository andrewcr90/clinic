package com.clinic.controller;

import com.clinic.entities.Doctor;
import com.clinic.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DoctorControllerTest {

    @InjectMocks
    private DoctorController doctorController;

    @Mock
    private DoctorService doctorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindDoctorByName() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. Smith");

        when(doctorService.findDoctorByName("Dr. Smith")).thenReturn(doctor);

        Doctor result = doctorController.findDoctorByName("Dr. Smith");

        assertNotNull(result);
        assertEquals("Dr. Smith", result.getName());

        verify(doctorService, times(1)).findDoctorByName("Dr. Smith");
    }

    @Test
    public void testCreateDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. Smith");

        when(doctorService.saveDoctor(any(Doctor.class))).thenReturn(doctor);

        Doctor result = doctorController.createDoctor(doctor);

        assertNotNull(result);
        assertEquals("Dr. Smith", result.getName());

        verify(doctorService, times(1)).saveDoctor(any(Doctor.class));
    }

    @Test
    public void testFindDoctorById() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. Smith");

        when(doctorService.findDoctorById(1L)).thenReturn(doctor);

        Doctor result = doctorController.findPatientById(1L);

        assertNotNull(result);
        assertEquals("Dr. Smith", result.getName());

        verify(doctorService, times(1)).findDoctorById(1L);
    }
}
