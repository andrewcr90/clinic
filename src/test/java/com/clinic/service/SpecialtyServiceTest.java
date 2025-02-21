package com.clinic.service;

import com.clinic.entities.Specialty;
import com.clinic.repository.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SpecialtyServiceTest {

    @InjectMocks
    private SpecialtyService specialtyService;

    @Mock
    private SpecialtyRepository specialtyRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveSpecialty() {
        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName("Cardiology");

        when(specialtyRepository.save(any(Specialty.class))).thenReturn(specialty);

        Specialty savedSpecialty = specialtyService.saveSpecialty(specialty);

        assertNotNull(savedSpecialty);
        assertEquals("Cardiology", savedSpecialty.getName());

        verify(specialtyRepository, times(1)).save(any(Specialty.class));
    }

    @Test
    public void testFindAllSpecialties() {
        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName("Cardiology");

        when(specialtyRepository.findAll()).thenReturn(List.of(specialty));

        List<Specialty> specialties = specialtyService.getAllSpecialties();

        assertNotNull(specialties);
        assertEquals(1, specialties.size());
        assertEquals("Cardiology", specialties.get(0).getName());

        verify(specialtyRepository, times(1)).findAll();
    }

    @Test
    public void testFindSpecialtyById() {
        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName("Cardiology");

        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(specialty));

        Specialty foundSpecialty = specialtyService.getSpecialtyById(1L);

        assertNotNull(foundSpecialty);
        assertEquals("Cardiology", foundSpecialty.getName());

        verify(specialtyRepository, times(1)).findById(1L);
    }
}
