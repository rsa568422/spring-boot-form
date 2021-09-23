package com.bolsadeideas.springboot.form.app.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bolsadeideas.springboot.form.app.validations.labels.IdentificadorRegex;

public class IdentificadorRegexValidador implements ConstraintValidator<IdentificadorRegex, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("[\\d]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}");
	}

}
