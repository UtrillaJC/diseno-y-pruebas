package forms;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

public class RequestCreditCardForm {

	//Attributes Request=====================================================================================

	private Date checkInDate;
	private Date checkOutDate;
	private boolean smoker;
	
	// Relationships Request ====================================================================================

		private int	tenantId;
		private int	propertyId;
		
	
	//Attributes CreditCard=====================================================================================

	private String holderName;
	private String brandName;
	private String number;
	private int expirationMonth;
	private int expirationYear;
	private int cvvCode;
	
	//Getters & setters Request================================================================================
	

	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCheckInDate() {
		return checkInDate;
	}
	
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	public boolean isSmoker() {
		return smoker;
	}
	
	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}
	
	
	@NotNull
	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	
	@NotNull
	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	
	//Getters & setters CreditCard================================================================================
	
	
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
