package com.example.osbb.validation;

public interface EntityValidator<T> {

  boolean isValid(T entity);
}
