package com.ratnakar.EmployeeAPI.repository;

import com.ratnakar.EmployeeAPI.controller.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
