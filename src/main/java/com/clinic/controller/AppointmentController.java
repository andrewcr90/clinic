package com.clinic.controller;


import com.clinic.constant.Messages;
import com.clinic.entities.*;
import com.clinic.service.DoctorService;
import com.clinic.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.clinic.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;


    @Autowired
    private PatientService patientService;


    @Autowired
    private DoctorService doctorService;



    @GetMapping
    public List<Appointment> getAppointments() {
        return service.getAppointments();
    }

    @Operation(summary = "Crea cita", description = "asigna cita a paciente y medico por nombre")
    @PostMapping("/createAppointment")
    public ResponseEntity<Object> createAppointment(@RequestBody @Valid AppoinmentRequest appoinmentRequest) {

     try{
        Patient patient = patientService.findPatientByName(appoinmentRequest.getPatientName());
        Doctor doctor = doctorService.findDoctorByName(appoinmentRequest.getDoctorName());

       if(patient == null){
           return new ResponseEntity<Object>(
                   ApiError.builder().status(HttpStatus.BAD_REQUEST)
                           .message(Messages.PATIENT_NOT_FOUND).build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
       }

        if(doctor == null){
            return new ResponseEntity<Object>(
                    ApiError.builder().status(HttpStatus.BAD_REQUEST)
                            .message(Messages.DOCTOR_NOT_FOUND).build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if (!service.findByMedicoAndFechaHora(doctor, appoinmentRequest.getDateTime()).isEmpty()) {
            return new ResponseEntity<Object>(
                    ApiError.builder().status(HttpStatus.BAD_REQUEST)
                            .message(Messages.DOCTOR_ERROR).build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity
                 .ok()
                 .body(service.createAppointment(Appointment.builder().patient(patient).
                doctor(doctor)
                .appointmentDate( appoinmentRequest.getDateTime()).build()));
     }catch (Exception e) {
         return new ResponseEntity<Object>(
                 ApiError.builder().status(HttpStatus.BAD_REQUEST)
                         .message(Messages.APPOINTMENT_NOT_FOUND).build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);

     }
    }

    @PostMapping("/schedule")
    public Appointment scheduleAppointment(@RequestBody AppoinmentRequest request) throws Exception {
        return service.scheduleAppointment(request.getPatientName(),request.getDoctorName(), request.getDateTime() );
    }


    @Operation(summary = "getAppointmentsByPatient", description = "Obtiene citas asignadas a un paciente por nombre")

    @GetMapping("/patient/getAppointmentsByPatient/{patientName}")
    public ResponseEntity<Object> findAppointmensByPatient(@PathVariable String patientName) {

        Patient patient = patientService.findPatientByName(patientName);
        if(patient == null){
            return new ResponseEntity<Object>(
                    ApiError.builder().status(HttpStatus.BAD_REQUEST)
                            .message(Messages.PATIENT_NOT_FOUND).build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity
                .ok()
                .body( service.getAppointmentsByPatient(patient.getId()));
    }
    @Operation(summary = "getAppointmentsByDoctor", description = "Obtiene citas asignadas a un medico por nombre")
    @GetMapping("/getAppointmentsByDoctor/{doctorName}")
    public ResponseEntity<Object> findAppointmensByDoctor(@PathVariable String doctorName) {

        Doctor doctor = doctorService.findDoctorByName(doctorName);
        if(doctor == null){
            return new ResponseEntity<Object>(
                    ApiError.builder().status(HttpStatus.BAD_REQUEST)
                            .message(Messages.DOCTOR_NOT_FOUND).build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity
                .ok()
                .body( service.getAppointmentsByDoctor(doctor.getId()));
    }

    @Operation(summary = "deleteAppointment", description = "Elimina Cita generarda a paciente por numero de cita")
    @DeleteMapping("/deleteAppointment/{appointmentId}")
    public ResponseEntity deleteAppointment(@PathVariable  Long appointmentId) {
      Appointment appointment=   service.findById(appointmentId);
        if(appointment == null){
            return new ResponseEntity<Object>(
                    ApiError.builder().status(HttpStatus.BAD_REQUEST)
                            .message(Messages.APPOINTMENT_NOT_FOUND_ERROR).build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity
                .ok()
                .body( service.deleteAppointment(appointmentId));
    }
}
