package com.clinic.controller;

import com.clinic.constant.Messages;
import com.clinic.entities.ApiError;
import com.clinic.entities.Appointment;
import com.clinic.entities.Doctor;
import com.clinic.entities.Patient;
import com.clinic.service.AppointmentService;
import com.clinic.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Doctor")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @Operation(summary = "findDoctorByName", description = "Obtiene informacion de medico por nombre")
    @GetMapping("{name}")
    public Doctor findDoctorByName(@PathVariable  String name) {
        return service.findDoctorByName(name);
    }
    @Operation(summary = "createDoctor", description = "Crea informacion de medico")
    @PostMapping("/createDoctor")
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return service.saveDoctor(doctor);
    }
    @Operation(summary = "findByName", description = "Busca medico por id")
    @GetMapping("/findByName/{id}")
    public Doctor findPatientById(@PathVariable Long id) {
        return service.findDoctorById(id);
    }


}
