package com.example.osbb.validation.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.example.osbb.validation.validator.NullOrNotBlankValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = NullOrNotBlankValidator.class)
public @interface NullOrNotBlank {

  String message() default "Field must be null or not blank";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
