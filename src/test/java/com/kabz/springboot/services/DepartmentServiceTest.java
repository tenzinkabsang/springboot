package com.kabz.springboot.services;

import com.kabz.springboot.entities.Department;
import com.kabz.springboot.infrastructure.DepartmentNotFoundException;
import com.kabz.springboot.repositories.IDepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private IDepartmentService departmentService;

    @MockBean
    private IDepartmentRepository repo;

    final static Department dummy = Department.builder()
            .name("IT")
            .address("Fake Address")
            .code("IT-001")
            .build();

    @BeforeEach
    void setUp() {
    }

    @Test
    public void whenValidDepartmentId_thenDepartmentShouldBeFound() throws Exception {
        long departmentId = 1;
        var dummyDepartment = Optional.ofNullable(dummy);
        Mockito.when(repo.findById(departmentId)).thenReturn(dummyDepartment);

        Department result = departmentService.getById(departmentId);

        assertEquals("IT", result.getName());
    }

    @Test
    @DisplayName("Attempting to get department by non-existent id - throws error")
    public void whenInvalidDepartmentId_shouldThrowDepartmentNotFoundException() {
        long invalidId = 111;
        Mockito.when(repo.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class,
                () -> departmentService.getById(invalidId));
    }

    @Test
    @DisplayName("Get department based on valid name")
    public void whenValidDepartmentName_thenDepartmentShouldBeFound() {
        final String departmentName = "IT";
        Mockito.when(repo.findDepartmentByNameIgnoreCase(any())).thenReturn(dummy);


        Department department = departmentService.getDepartmentByName(departmentName);

        assertEquals(departmentName, department.getName());
    }
}