package com.kabz.springboot.infrastructure;

public class DepartmentNotFoundException extends Exception {

    private static final String MESSAGE = "Department not found for id = %s";

    public DepartmentNotFoundException(Long departmentId) {

        super(String.format(MESSAGE, departmentId));
    }
}
