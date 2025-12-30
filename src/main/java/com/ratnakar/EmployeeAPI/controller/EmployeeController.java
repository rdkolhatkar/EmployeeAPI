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

    @PutMapping("/api/employee/update")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody Employee employee) {
        return employeeResponseSetup.updateEmployeeResponse(employee);
    }

    @PatchMapping("/api/employee/update/jobRole/{employee_id}")
    public ResponseEntity<EmployeeResponse> updateJobRole(
            @PathVariable String employee_id,
            @RequestBody Employee employee) {

        return employeeResponseSetup
                .updateEmployeeJobRoleResponse(employee_id, employee.getJob_role());
    }

    @RequestMapping(value = "/api/employee/{employee_id}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkEmployeeExists(@PathVariable String employee_id) {

        if (employeeService.checkEmployeeAlreadyExists(employee_id)) {
            return ResponseEntity.ok().build();      // 200 OK, no body
        } else {
            return ResponseEntity.notFound().build(); // 404 NOT FOUND
        }
    }

    @RequestMapping(
            value = {"/api/employees", "/api/addEmployee", "/api/employee/**"},
            method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> optionsEmployeeApi() {
        return ResponseEntity.ok()
                .header("Allow", "GET,POST,PUT,PATCH,DELETE,HEAD,OPTIONS")
                .build();
    }

}
