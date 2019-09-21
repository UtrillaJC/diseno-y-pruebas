package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Embeddable
@Access(AccessType.PROPERTY)
public class CreditCard {


	// Constructor ------------------------------------------------------
	
	public CreditCard() {

	}
	
	// Attributes -------------------------------------------------------
	
	private String brandName;
	private String holderName;
	private String number;
	private int expMonth;
	private int expYear;
	private int CVV;
	
	@NotBlank
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@NotBlank
	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	
	@NotBlank
	@Pattern(regexp = "^\\d+$")
	@CreditCardNumber
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Range(min = 1, max = 12)
	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}
	
	@Range(min = 2000, max = 2100)
	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
	
	@Range(min = 100, max = 999)
	public int getCVV() {
		return CVV;
	}

	public void setCVV(int cVV) {
		CVV = cVV;
	}


}
