package com.ratnakar.EmployeeAPI.setup;

import com.ratnakar.EmployeeAPI.model.Employee;
import com.ratnakar.EmployeeAPI.model.EmployeeResponse;
import com.ratnakar.EmployeeAPI.repository.EmployeeRepository;
import com.ratnakar.EmployeeAPI.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeResponseSetup {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;

    // ✅ POST logic
    public ResponseEntity<EmployeeResponse> userRegistrationResponse(Employee employee) {
        String employee_id = employee.getEmployee_id();
        EmployeeResponse employeeResponse = new EmployeeResponse();
        if (!employeeService.checkEmployeeAlreadyExists(employee_id)) {
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

    // ✅ GET logic
    public ResponseEntity<List<Employee>> getAllEmployeesResponse() {
        List<Employee> employeeList = employeeRepository.findAll();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    // ✅ DELETE logic
    public ResponseEntity<EmployeeResponse> deleteEmployeeResponse(String employee_id) {
        EmployeeResponse response = new EmployeeResponse();
        if (employeeService.checkEmployeeAlreadyExists(employee_id)) {
            employeeRepository.deleteById(employee_id);
            response.setMsg("Employee with ID " + employee_id + " deleted successfully.");
            response.setEmployee_id(employee_id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMsg("Employee with ID " + employee_id + " not found.");
            response.setEmployee_id(employee_id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
