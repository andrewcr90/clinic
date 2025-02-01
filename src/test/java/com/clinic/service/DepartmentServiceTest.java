package com.clinic.service;

import com.clinic.entities.Department;
import com.clinic.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDepartment() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Health");

        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Department savedDepartment = departmentService.saveDepartment(department);

        assertNotNull(savedDepartment);
        assertEquals("Health", savedDepartment.getName());

        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testFindAllDepartments() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Health");

        when(departmentRepository.findAll()).thenReturn(List.of(department));

        List<Department> departments = departmentService.getAllDepartments();

        assertNotNull(departments);
        assertEquals(1, departments.size());
        assertEquals("Health", departments.get(0).getName());

        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    public void testFindDepartmentById() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Health");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Department foundDepartment = departmentService.getDepartmentById(1L);

        assertNotNull(foundDepartment);
        assertEquals("Health", foundDepartment.getName());

        verify(departmentRepository, times(1)).findById(1L);
    }
}
