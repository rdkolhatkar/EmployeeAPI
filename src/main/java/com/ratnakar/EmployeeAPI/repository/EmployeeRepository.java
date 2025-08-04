package com.ratnakar.EmployeeAPI.repository;

import com.ratnakar.EmployeeAPI.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
