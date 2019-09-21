package domain;



import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Embeddable
@Access(AccessType.PROPERTY)
@Table(indexes={@Index(columnList="number")})
public class CreditCard {
	//Constructors----------------------------------------------------------------------
	public CreditCard(){
		super();
	}

	//Attributes-------------------------------------------------------------------------
	private String holderName;
	private String brandName;
	private String number;
	private int expirationMonth;
	private int expirationYear;
	private int cvvCode;
	
	@NotBlank
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	
	@NotBlank
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	@NotBlank
	@CreditCardNumber
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Range(min=1, max=12)
	public int getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	
	@Range(min=2017, max=2100)
	public int getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}
	
	@Range(min=100, max=999)
	public int getcvvCode() {
		return cvvCode;
	}
	public void setcvvCode(int cvvCode) {
		this.cvvCode = cvvCode;
	}

}
