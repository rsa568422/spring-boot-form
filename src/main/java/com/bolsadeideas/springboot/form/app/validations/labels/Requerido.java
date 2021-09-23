package com.bolsadeideas.springboot.form.app.validations.labels;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bolsadeideas.springboot.form.app.validations.RequeridoValidador;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = RequeridoValidador.class)
public @interface Requerido {

	String message() default "el campo es requerido - usando anotaciones";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
