package com.ratnakar.EmployeeAPI.controller;

import com.ratnakar.EmployeeAPI.model.Employee;
import com.ratnakar.EmployeeAPI.model.EmployeeResponse;
import com.ratnakar.EmployeeAPI.repository.EmployeeRepository;
import com.ratnakar.EmployeeAPI.service.EmployeeService;
import com.ratnakar.EmployeeAPI.setup.EmployeeResponseSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeResponseSetup employeeResponseSetup;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/api/addEmployee")
    public ResponseEntity employeeData(@RequestBody Employee employee) {
        return employeeResponseSetup.userRegistrationResponse(employee);
    }

    @GetMapping("/api/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return employeeResponseSetup.getAllEmployeesResponse();
    }

    @DeleteMapping("/api/employee/delete/{employee_id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable String employee_id) {
        return employeeResponseSetup.deleteEmployeeResponse(employee_id);
    }
}
