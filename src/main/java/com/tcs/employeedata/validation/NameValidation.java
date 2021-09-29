package com.tcs.employeedata.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class NameValidation implements ConstraintValidator<Name, String> {	



	@Override
	public boolean isValid(String name1, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (StringUtils.isNumeric(name1)) {
			return false;
		} else {
			return true;
		}
	}

} 
