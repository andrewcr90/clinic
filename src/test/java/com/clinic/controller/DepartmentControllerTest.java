package com.clinic.controller;

import com.clinic.entities.Department;
import com.clinic.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentControllerTest {

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDepartment() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Health");

        when(departmentService.saveDepartment(any(Department.class))).thenReturn(department);

        Department result = departmentController.saveDepartment(department);

        assertNotNull(result);
        assertEquals("Health", result.getName());

        verify(departmentService, times(1)).saveDepartment(any(Department.class));
    }

    @Test
    public void testGetAllDepartments() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Health");

        when(departmentService.getAllDepartments()).thenReturn(List.of(department));

        List<Department> result = departmentController.getAllDepartments();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Health", result.get(0).getName());

        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    public void testGetDepartmentById() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Health");

        when(departmentService.getDepartmentById(1L)).thenReturn(department);

        Department result = departmentController.getDepartmentById(1L);

        assertNotNull(result);
        assertEquals("Health", result.getName());

        verify(departmentService, times(1)).getDepartmentById(1L);
    }
}
