package com.clinic.controller;

import com.clinic.constant.Messages;
import com.clinic.entities.Doctor;
import com.clinic.entities.Patient;
import com.clinic.repository.PatientRepository;
import com.clinic.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService service;
    /**
     * Retrieves a patient by their name.
     *
     * @param name The name of the patient to search for.
     * @return The Patient object matching the given name, or null if not found.
     */
    @Operation(summary = "findByName", description = "Busca paciente por nombre")
    @GetMapping("/findByName/{name}")
    public ResponseEntity<Patient> findPatientByName(@PathVariable String name) {
        Patient patient = service.findPatientByName(name);
        if(patient == null)
            return ResponseEntity.notFound().build();

        return  ResponseEntity
                .ok()
                .body(patient );
    }


    @Operation(summary = "findPatientById", description = "Busca paciente por id")
    @GetMapping("{id}")
    public ResponseEntity<Patient> findPatientById(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body( service.findPatientById(id));
    }
    @Operation(summary = "savePatient", description = "Guarda informacion de paciente ")
    @PostMapping
    public  ResponseEntity<Patient>  savePatient(@RequestBody Patient patient) {
        return  ResponseEntity
                .ok()
                .body(service.savePatient(patient));
    }
}
