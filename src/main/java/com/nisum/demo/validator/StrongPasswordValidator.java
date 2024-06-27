package com.nisum.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.nisum.demo.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {
	@Autowired
	UserService userService;
	
	@Value("${validator.password}")
	String patternExt;

	
	  @Override
	  public boolean isValid(String value, ConstraintValidatorContext context) {
	    
		  return value.matches(patternExt);
	  }
}
