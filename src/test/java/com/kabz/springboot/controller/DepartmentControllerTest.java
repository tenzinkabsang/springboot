package com.kabz.springboot.controller;

import com.google.gson.Gson;
import com.kabz.springboot.entities.Department;
import com.kabz.springboot.services.IDepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDepartmentService departmentService;

    private Department entity;

    @BeforeEach
    void setUp() {
        entity = Department.builder()
                .address("DB-Salt Lake")
                .code("DB-Code")
                .name("DB-Department").build();
    }

    @Test
    void getById() throws Exception {
        when(departmentService.getById(1L)).thenReturn(entity);

        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address")
                        .value(entity.getAddress()));

    }

    @Test
    void saveDepartment() throws Exception {
        // Arrange
        when(departmentService.saveDepartment(any())).thenReturn(entity);

        // Act
        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"iT\", \"address\": \"5308 Bakement Center\", \"code\": \"87909\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(entity.getName()));

        // Called once
        verify(departmentService, atLeastOnce()).saveDepartment(any());

    }
}