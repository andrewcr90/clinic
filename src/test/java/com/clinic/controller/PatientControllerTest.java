package com.clinic.controller;

import com.clinic.entities.Patient;
import com.clinic.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindPatientByName() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");

        when(patientService.findPatientByName("John Doe")).thenReturn(patient);

        ResponseEntity<Patient> response = patientController.findPatientByName("John Doe");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getName());

        verify(patientService, times(1)).findPatientByName("John Doe");
    }

    @Test
    public void testFindPatientByName_NotFound() {
        when(patientService.findPatientByName("John Doe")).thenReturn(null);

        ResponseEntity<Patient> response = patientController.findPatientByName("John Doe");

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(patientService, times(1)).findPatientByName("John Doe");
    }

    @Test
    public void testFindPatientById() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");

        when(patientService.findPatientById(1L)).thenReturn(patient);

        ResponseEntity<Patient> response = patientController.findPatientById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getName());

        verify(patientService, times(1)).findPatientById(1L);
    }

    @Test
    public void testSavePatient() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");

        when(patientService.savePatient(any(Patient.class))).thenReturn(patient);

        ResponseEntity<Patient> response = patientController.savePatient(patient);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getName());

        verify(patientService, times(1)).savePatient(any(Patient.class));
    }
}
