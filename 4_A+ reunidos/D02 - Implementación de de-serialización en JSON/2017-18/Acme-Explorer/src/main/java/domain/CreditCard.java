
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Embeddable
@Access(AccessType.PROPERTY)
public class CreditCard {

	//Attributes
	private String	holder;
	private String	brand;
	private String	number;
	private Integer	expirationMonth;
	private Integer	expirationYear;
	private Integer	cvv;


	//Constructor
	public CreditCard() {
		super();
	}

	//Getters

	@NotBlank
	public String getHolder() {
		return this.holder;
	}

	@NotBlank
	public String getBrand() {
		return this.brand;
	}

	@CreditCardNumber
	public String getNumber() {
		return this.number;
	}

	@Range(min = 1, max = 12)
	public Integer getExpirationMonth() {
		return this.expirationMonth;
	}

	@Range(min = 2017, max = 3000)
	public Integer getExpirationYear() {
		return this.expirationYear;
	}

	@Range(min = 100, max = 999)
	public Integer getCvv() {
		return this.cvv;
	}

	//Setters
	public void setBrand(final String brand) {
		this.brand = brand;
	}

	public void setExpirationMonth(final Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public void setHolder(final String holder) {
		this.holder = holder;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	public void setExpirationYear(final Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	public void setCvv(final Integer cvv) {
		this.cvv = cvv;
	}

}
