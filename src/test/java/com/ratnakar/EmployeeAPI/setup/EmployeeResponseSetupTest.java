package com.ratnakar.EmployeeAPI.setup;

import com.ratnakar.EmployeeAPI.model.Employee;
import com.ratnakar.EmployeeAPI.model.EmployeeResponse;
import com.ratnakar.EmployeeAPI.repository.EmployeeRepository;
import com.ratnakar.EmployeeAPI.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class EmployeeResponseSetupTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeResponseSetup employeeResponseSetup;

    @Test
    void testUserRegistrationResponse_NewEmployee() {
        Employee emp = new Employee();
        emp.setEmployee_id("EMP003");
        emp.setEmployee_name("John Doe");
        emp.setJob_role("Engineer");

        when(employeeService.checkEmployeeAlreadyExists("EMP003")).thenReturn(false);
        when(employeeRepository.save(emp)).thenReturn(emp);

        ResponseEntity<EmployeeResponse> response = employeeResponseSetup.userRegistrationResponse(emp);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Employee Added Sucessfully", response.getBody().getMsg());
        assertEquals("EMP003", response.getBody().getEmployee_id());
    }

    @Test
    void testUserRegistrationResponse_ExistingEmployee() {
        Employee emp = new Employee();
        emp.setEmployee_id("EMP004");

        when(employeeService.checkEmployeeAlreadyExists("EMP004")).thenReturn(true);

        ResponseEntity<EmployeeResponse> response = employeeResponseSetup.userRegistrationResponse(emp);

        assertEquals(202, response.getStatusCodeValue());
        assertEquals("Employee Already Exists", response.getBody().getMsg());
        assertEquals("EMP004", response.getBody().getEmployee_id());
    }
}
