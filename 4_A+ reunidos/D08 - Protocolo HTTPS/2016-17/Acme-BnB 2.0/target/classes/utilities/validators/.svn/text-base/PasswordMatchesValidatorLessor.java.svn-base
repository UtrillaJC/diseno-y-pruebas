
package utilities.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import forms.LessorRegistrationForm;

public class PasswordMatchesValidatorLessor implements ConstraintValidator<PasswordMatchesLessor, LessorRegistrationForm> {

	@Override
	public void initialize(PasswordMatchesLessor constraintAnnotation) {
	}

	@Override
	public boolean isValid(LessorRegistrationForm form, ConstraintValidatorContext context) {
		return form.getPassword().equals(form.getVerifyPassword());
	}
}
