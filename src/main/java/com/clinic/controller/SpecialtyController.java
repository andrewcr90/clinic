package com.clinic.controller;

import com.clinic.entities.Specialty;
import com.clinic.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {
    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping
    public Specialty saveSpecialty(@RequestBody Specialty specialty) {
        return specialtyService.saveSpecialty(specialty);
    }

    @GetMapping
    public List<Specialty> getAllSpecialties() {
        return specialtyService.getAllSpecialties();
    }

    @GetMapping("/{id}")
    public Specialty getSpecialtyById(@PathVariable Long id) {
        return specialtyService.getSpecialtyById(id);
    }
}
