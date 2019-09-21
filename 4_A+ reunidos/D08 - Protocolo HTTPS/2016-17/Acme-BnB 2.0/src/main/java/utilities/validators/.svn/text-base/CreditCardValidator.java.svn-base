
package utilities.validators;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import domain.CreditCard;

public class CreditCardValidator implements ConstraintValidator<ValidCreditCard, CreditCard> {

	@Override
	public void initialize(ValidCreditCard constraintAnnotation) {
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(CreditCard cc, ConstraintValidatorContext context) {
		Boolean result = false;

		Date currMoment = new Date();
		Integer month = currMoment.getMonth() + 1;
		Integer year = currMoment.getYear() + 1900;

		if (cc.getExpirationYear() < year) {
			result = false;
		} else if (cc.getExpirationYear() == year) {
			if (cc.getExpirationMonth() < month) {
				result = false;
			} else
				result = true;
		} else
			result = true;

		return result;
	}
}
