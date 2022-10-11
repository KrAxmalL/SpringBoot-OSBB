package com.example.project.validation;

public interface EntityValidator<T> {

  boolean isValid(T entity);
}
