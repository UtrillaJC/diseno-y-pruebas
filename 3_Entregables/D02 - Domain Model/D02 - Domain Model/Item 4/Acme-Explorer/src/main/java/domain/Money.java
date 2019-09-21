
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Money {

	//Attributes
	private Double	amount;
	private String	currency;


	//Constructor
	public Money() {
		super();
	}
	// Getters & Setters
	@Min(0)
	@Digits(integer = 9, fraction = 2)
	@Valid
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(final Double amount) {
		this.amount = amount;
	}
	@NotBlank
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

}
