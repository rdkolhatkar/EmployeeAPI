package com.ratnakar.EmployeeAPI.service;

import com.ratnakar.EmployeeAPI.repository.EmployeeRepository;
import com.ratnakar.EmployeeAPI.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public boolean checkEmployeeAlreadyExists(String employee_id) {
        Optional<Employee> emp = employeeRepository.findById(employee_id);
        if (emp.isPresent())
            return true;
        else
            return false;
    }
}
