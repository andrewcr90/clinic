package com.clinic.controller;

import com.clinic.constant.Messages;
import com.clinic.entities.*;
import com.clinic.service.AppointmentService;
import com.clinic.service.DoctorService;
import com.clinic.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private PatientService patientService;

    @Mock
    private DoctorService doctorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAppointments() {
        List<Appointment> appointments = List.of(new Appointment());
        when(appointmentService.getAppointments()).thenReturn(appointments);

        List<Appointment> result = appointmentController.getAppointments();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(appointmentService, times(1)).getAppointments();
    }

    @Test
    public void testCreateAppointment() {
        AppoinmentRequest request = new AppoinmentRequest();
        request.setPatientName("John Doe");
        request.setDoctorName("Dr. Smith");
        request.setDateTime(LocalDateTime.now());

        Patient patient = new Patient();
        Doctor doctor = new Doctor();

        when(patientService.findPatientByName("John Doe")).thenReturn(patient);
        when(doctorService.findDoctorByName("Dr. Smith")).thenReturn(doctor);
        when(appointmentService.findByMedicoAndFechaHora(any(), any())).thenReturn(Collections.emptyList());
        when(appointmentService.createAppointment(any())).thenReturn(new Appointment());

        ResponseEntity<Object> response = appointmentController.createAppointment(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(patientService, times(1)).findPatientByName("John Doe");
        verify(doctorService, times(1)).findDoctorByName("Dr. Smith");
        verify(appointmentService, times(1)).findByMedicoAndFechaHora(any(), any());
        verify(appointmentService, times(1)).createAppointment(any());
    }

    @Test
    public void testFindAppointmentsByPatient() {
        Patient patient = new Patient();
        patient.setId(1L);

        when(patientService.findPatientByName("John Doe")).thenReturn(patient);
        when(appointmentService.getAppointmentsByPatient(1L)).thenReturn(List.of(new Appointment()));

        ResponseEntity<Object> response = appointmentController.findAppointmensByPatient("John Doe");

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(patientService, times(1)).findPatientByName("John Doe");
        verify(appointmentService, times(1)).getAppointmentsByPatient(1L);
    }

    @Test
    public void testFindAppointmentsByDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);

        when(doctorService.findDoctorByName("Dr. Smith")).thenReturn(doctor);
        when(appointmentService.getAppointmentsByDoctor(1L)).thenReturn(List.of(new Appointment()));

        ResponseEntity<Object> response = appointmentController.findAppointmensByDoctor("Dr. Smith");

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(doctorService, times(1)).findDoctorByName("Dr. Smith");
        verify(appointmentService, times(1)).getAppointmentsByDoctor(1L);
    }

    @Test
    public void testDeleteAppointment() {
        Appointment appointment = new Appointment();
        when(appointmentService.findById(1L)).thenReturn(appointment);

        ResponseEntity<Object> response = appointmentController.deleteAppointment(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(appointmentService, times(1)).findById(1L);
        verify(appointmentService, times(1)).deleteAppointment(1L);
    }
}
