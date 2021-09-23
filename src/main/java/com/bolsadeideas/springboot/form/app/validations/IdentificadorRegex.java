package com.bolsadeideas.springboot.form.app.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = IdentificadorRegexValidador.class)
public @interface IdentificadorRegex {

	String message() default "Identificador inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
