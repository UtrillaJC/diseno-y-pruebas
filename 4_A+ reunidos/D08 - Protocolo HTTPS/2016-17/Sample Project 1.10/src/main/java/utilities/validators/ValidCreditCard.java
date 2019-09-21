package utilities.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;


/**
 * Validate that the annotated credit card has not expired.
 *
 * @author Jesus Pardo
 */
@Documented
@Constraint(validatedBy = {CreditCardValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface ValidCreditCard { 
	String message() default "{jeparca.constraints.creditCard.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}