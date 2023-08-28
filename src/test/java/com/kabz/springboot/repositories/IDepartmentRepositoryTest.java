package com.kabz.springboot.repositories;

import com.kabz.springboot.entities.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IDepartmentRepositoryTest {

    @Autowired
    private IDepartmentRepository repo;

    @Autowired
    private TestEntityManager entityManager;


    @BeforeEach
    void setUp() {
        Department dummy = Department.builder()
                .name("IT-Test")
                .address("Fake Address")
                .code("IT-001")

                .build();

        entityManager.persist(dummy);
    }

    @Test
    @Disabled
    public void whenFindById_thenReturnDepartment() {
        Department d = repo.findById(1L).get();

        assertEquals("IT-Test", d.getName());
    }




}