
package utilities.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import forms.RegisterForm;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegisterForm> {

	@Override
	public void initialize(final PasswordMatches constraintAnnotation) {
	}
	@Override
	public boolean isValid(final RegisterForm form, final ConstraintValidatorContext context) {
		return form.getPassword().equals(form.getVerifyPassword());
	}

}
