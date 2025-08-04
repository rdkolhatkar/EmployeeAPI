package com.ratnakar.EmployeeAPI.service;

import com.ratnakar.EmployeeAPI.model.Employee;
import com.ratnakar.EmployeeAPI.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void testCheckEmployeeAlreadyExists_ReturnsTrue() {
        String employeeId = "EMP001";
        Employee emp = new Employee();
        emp.setEmployee_id(employeeId);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(emp));

        boolean exists = employeeService.checkEmployeeAlreadyExists(employeeId);
        assertTrue(exists);
    }

    @Test
    void testCheckEmployeeAlreadyExists_ReturnsFalse() {
        String employeeId = "EMP002";

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        boolean exists = employeeService.checkEmployeeAlreadyExists(employeeId);
        assertFalse(exists);
    }
}
