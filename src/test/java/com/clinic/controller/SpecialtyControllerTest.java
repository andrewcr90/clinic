package com.clinic.controller;

import com.clinic.entities.Specialty;
import com.clinic.service.SpecialtyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SpecialtyControllerTest {

    @InjectMocks
    private SpecialtyController specialtyController;

    @Mock
    private SpecialtyService specialtyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveSpecialty() {
        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName("Cardiology");

        when(specialtyService.saveSpecialty(any(Specialty.class))).thenReturn(specialty);

        Specialty result = specialtyController.saveSpecialty(specialty);

        assertNotNull(result);
        assertEquals("Cardiology", result.getName());

        verify(specialtyService, times(1)).saveSpecialty(any(Specialty.class));
    }

    @Test
    public void testGetAllSpecialties() {
        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName("Cardiology");

        when(specialtyService.getAllSpecialties()).thenReturn(List.of(specialty));

        List<Specialty> result = specialtyController.getAllSpecialties();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Cardiology", result.get(0).getName());

        verify(specialtyService, times(1)).getAllSpecialties();
    }

    @Test
    public void testGetSpecialtyById() {
        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName("Cardiology");

        when(specialtyService.getSpecialtyById(1L)).thenReturn(specialty);

        Specialty result = specialtyController.getSpecialtyById(1L);

        assertNotNull(result);
        assertEquals("Cardiology", result.getName());

        verify(specialtyService, times(1)).getSpecialtyById(1L);
    }
}
