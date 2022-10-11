package com.example.project.repository;

import com.example.project.domain.Employee;
import java.util.Optional;

public interface EmployeeRepository {

  Optional<Employee> getEmployeeById(Integer employeeId);
}
