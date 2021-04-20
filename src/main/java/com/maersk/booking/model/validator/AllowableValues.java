package com.maersk.booking.model.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * A basic value checker constraint. 
 * Accepts comma separated values
 * @author karthik udayakumar
 */
@Constraint(validatedBy = AllowableValuesValidator.class)
@Documented
@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
public @interface AllowableValues {

	String message() default "Not an accepted value";
	String value() default "";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default {};
}
