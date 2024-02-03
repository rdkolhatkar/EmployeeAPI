package com.ratnakar.EmployeeAPI.controller;

import com.ratnakar.EmployeeAPI.repository.EmployeeRepository;
import com.ratnakar.EmployeeAPI.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity employeeData(@RequestBody Employee employee){
        String employee_id = employee.getEmployee_id();
        EmployeeResponse employeeResponse = new EmployeeResponse();
        if(!employeeService.checkEmployeeAlreadyExists(employee_id)){
            employee.setEmployee_id(employee_id);
            employeeRepository.save(employee);
            EmployeeResponse Eresponse = new EmployeeResponse();
            Eresponse.setMsg("Employee Added Sucessfully");
            Eresponse.setEmployee_id(employee_id);
            return new ResponseEntity<EmployeeResponse>(Eresponse, HttpStatus.CREATED);
        } else {
            EmployeeResponse Eresponse = new EmployeeResponse();
            Eresponse.setMsg("Employee Already Exists");
            Eresponse.setEmployee_id(employee_id);
            return new ResponseEntity<EmployeeResponse>(Eresponse, HttpStatus.ACCEPTED);
        }
    }
}
