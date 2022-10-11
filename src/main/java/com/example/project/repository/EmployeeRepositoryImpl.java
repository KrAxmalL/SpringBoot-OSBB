package com.example.project.repository;

import com.example.project.domain.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

  private static final List<Employee> employees =
      List.of(
          Employee.builder().id(1).firstName("John").lastName("Doe").build(),
          Employee.builder().id(2).firstName("Tom").lastName("Roberts").build());

  @Override
  public Optional<Employee> getEmployeeById(Integer employeeId) {
    return employees.stream()
        .filter(employee -> employee.getId().compareTo(employeeId) == 0)
        .findFirst();
  }
}
