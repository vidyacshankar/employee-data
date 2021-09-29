
package com.tcs.employeedata.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Retention(RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NameValidation.class)
public @interface Name {

	String message() default "Name cannot be numeric";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };



}
