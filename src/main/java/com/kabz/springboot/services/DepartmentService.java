package com.kabz.springboot.services;

import com.google.gson.Gson;
import com.kabz.springboot.entities.Department;
import com.kabz.springboot.infrastructure.DepartmentNotFoundException;
import com.kabz.springboot.repositories.IDepartmentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {

    private final Logger Log = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    @Autowired
    private Gson gson;
    private final IDepartmentRepository _repo;

    @Autowired
    public DepartmentService(IDepartmentRepository repo) {
        _repo = repo;
    }

    @Override
    public Department saveDepartment(Department department) {
        Log.info(String.format("Saving new department: %s", gson.toJson(department)));
        Department entity = _repo.save(department);
        return entity;
    }

    @Override
    public List<Department> getAll() {
        return _repo.findAll();
    }

    @Override
    public Department getById(Long id) throws Exception {
        Optional<Department> department = _repo.findById(id);

        if(department.isEmpty())
            throw new DepartmentNotFoundException(id);

        return department.get();
    }

    @Override
    public Integer deleteDepartment(Long id) {
        return _repo.deleteCustom(id);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Optional<Department> dbDepartment = _repo.findById(id);
        if(dbDepartment.isEmpty())
            return department;

        Department entity = dbDepartment.get();
        entity.setName(department.getName());
        entity.setAddress(department.getAddress());
        entity.setCode(department.getCode());

        return _repo.save(entity);
    }

    @Override
    public Department getDepartmentByName(String name) {
        Department department = _repo.findDepartmentByNameIgnoreCase(name);
        return department;
    }
}
