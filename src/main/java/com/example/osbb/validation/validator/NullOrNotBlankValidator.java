package com.example.osbb.validation.validator;

import com.example.osbb.validation.annotation.NullOrNotBlank;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {

  @Override
  public void initialize(NullOrNotBlank constraintAnnotation) {}

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value == null || !value.isBlank();
  }
}
