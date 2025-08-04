package com.ratnakar.EmployeeAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratnakar.EmployeeAPI.model.Employee;
import com.ratnakar.EmployeeAPI.repository.EmployeeRepository;
import com.ratnakar.EmployeeAPI.service.EmployeeService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddNewEmployee() throws Exception {
        Employee employee = new Employee();
        EmployeeService employeeService = new EmployeeService();
        employee.setEmployee_id("E001");

        // Mock service to say the employee does not already exist
        Mockito.when(employeeService.checkEmployeeAlreadyExists("E001")).thenReturn(false);

        mockMvc.perform((RequestBuilder) post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.msg").value("Employee has been added successfully"))
                .andExpect(jsonPath("$.employee_id").value("E001"));
    }

    @Test
    public void testAddExistingEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmployee_id("E001");

        // Mock service to say the employee already exists
        Mockito.when(employeeService.checkEmployeeAlreadyExists("E001")).thenReturn(true);

        mockMvc.perform((RequestBuilder) post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.msg").value("Employee Already exists"))
                .andExpect(jsonPath("$.employee_id").value("E001"));
    }

}
