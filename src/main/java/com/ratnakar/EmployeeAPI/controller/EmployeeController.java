package com.ratnakar.EmployeeAPI.controller;

import com.ratnakar.EmployeeAPI.model.Employee;
import com.ratnakar.EmployeeAPI.model.EmployeeResponse;
import com.ratnakar.EmployeeAPI.repository.EmployeeRepository;
import com.ratnakar.EmployeeAPI.service.EmployeeService;
import com.ratnakar.EmployeeAPI.setup.EmployeeResponseSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeResponseSetup employeeResponseSetup;

    @PostMapping("/addEmployee")
    public ResponseEntity employeeData(@RequestBody Employee employee) {
        return employeeResponseSetup.userRegistrationResponse(employee);
    }
}
