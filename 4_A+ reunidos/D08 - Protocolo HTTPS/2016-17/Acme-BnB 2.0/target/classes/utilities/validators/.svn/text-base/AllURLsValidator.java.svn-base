package utilities.validators;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AllURLsValidator implements ConstraintValidator<AllURLs, Collection<String>> {

	@Override
	public void initialize(AllURLs arg0) {

	}

	@Override
	public boolean isValid(Collection<String> pictures, ConstraintValidatorContext cvc) {

		boolean correct = true;

		for (String p : pictures) {
			try {
				new URL(p);
			} catch (MalformedURLException e1) {
				try {
					new URL("http://" + p);
				} catch (MalformedURLException e2) {
					correct = false;
					break;
				}
			}
		}

		return correct;
	}

}
