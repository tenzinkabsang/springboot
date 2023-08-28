package com.kabz.springboot.services;

import com.kabz.springboot.entities.Department;
import com.kabz.springboot.infrastructure.DepartmentNotFoundException;

import java.util.List;

public interface IDepartmentService {
    Department saveDepartment(Department department);

    List<Department> getAll();

    Department getById(Long id) throws Exception;

    Integer deleteDepartment(Long id);

    Department updateDepartment(Long id, Department department);

    Department getDepartmentByName(String name);
}

