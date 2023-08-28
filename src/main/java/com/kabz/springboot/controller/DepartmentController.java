package com.kabz.springboot.controller;

import com.google.gson.Gson;
import com.kabz.springboot.entities.Department;
import com.kabz.springboot.infrastructure.DepartmentNotFoundException;
import com.kabz.springboot.infrastructure.RespApiController;
import com.kabz.springboot.services.IDepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

//@RestController
//@RequestMapping("departments")
@RespApiController("departments")
public class DepartmentController {

    private final Logger Log = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    @Autowired
    private Gson gson;

    @Autowired
    private IDepartmentService _departmentService;

    @GetMapping
    public List<Department> fetchDepartmentList() {
        return _departmentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable("id") Long idX) throws Exception {
        Department department = _departmentService.getById(idX);
        return ok(department);
    }

    @PostMapping
    public Department saveDepartment(@Valid @RequestBody Department department) {

        Log.info(String.format("Saving new department: %s", gson.toJson(department)));

        Department d = _departmentService.saveDepartment(department);
        return d;
    }


    @PutMapping("{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) {

        Department d = _departmentService.updateDepartment(id, department);

        return ok(d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteDepartment(@PathVariable("id") Long id) {
        Integer deletedId = _departmentService.deleteDepartment(id);

        return ok(deletedId);
    }

    @GetMapping("/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String name){
        Department d = _departmentService.getDepartmentByName(name);

        return d;
    }
}




















