package com.example.project.validation;

import com.example.project.domain.AddAdvertisementDTO;
import com.example.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementValidator implements EntityValidator<AddAdvertisementDTO> {

  private EmployeeService employeeService;

  @Override
  public boolean isValid(AddAdvertisementDTO entity) {
    return !entity.getTitle().isBlank()
        && !entity.getContent().isBlank()
        && employeeService.employeeExists(entity.getAuthorId());
  }

  @Autowired
  public void setEmployeeService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }
}
