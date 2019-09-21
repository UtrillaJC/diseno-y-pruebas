
package utilities.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import forms.AuditorRegistrationForm;

public class PasswordMatchesValidatorAuditor implements ConstraintValidator<PasswordMatchesAuditor, AuditorRegistrationForm> {

	@Override
	public void initialize(PasswordMatchesAuditor constraintAnnotation) {
	}
	@Override
	public boolean isValid(AuditorRegistrationForm form, ConstraintValidatorContext context) {
		return form.getPassword().equals(form.getVerifyPassword());
	}

}
