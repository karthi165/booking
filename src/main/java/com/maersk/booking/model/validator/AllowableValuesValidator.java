package com.maersk.booking.model.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class AllowableValuesValidator implements ConstraintValidator<AllowableValues, Object> {

	private List<String> valueList = new ArrayList<>();

	@Override
	public void initialize(AllowableValues constraintAnnotation) {
		this.valueList = Arrays.asList(constraintAnnotation.value().split(","));
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (!(value instanceof Integer)) {
			throw new IllegalArgumentException("Illegal method signature, expected integer value");
		}

		else {
			if (valueList.contains(Integer.toString((Integer) value)))
				return true;
			else
				return false;
		}
	}

}
