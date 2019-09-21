
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Invoice extends DomainEntity {

	//Attributes=====================================================================================	

	private Date		moment;
	private String		tenantInformation;
	private String		details;
	private Double		totalAmountDue;
	private CreditCard	creditCard;


	//Constructors====================================================================================

	public Invoice() {
		super();
	}

	//Getters & setters================================================================================

	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getTenantInformation() {
		return tenantInformation;
	}

	public void setTenantInformation(String tenantInformation) {
		this.tenantInformation = tenantInformation;
	}

	@NotBlank
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Digits(integer = 99, fraction = 2)
	@NotNull
	public Double getTotalAmountDue() {
		return totalAmountDue;
	}

	public void setTotalAmountDue(Double totalAmountDue) {
		this.totalAmountDue = totalAmountDue;
	}

	@NotNull
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}


	// Relationships ====================================================================================

	private Request request;


	@Valid
	@NotNull
	@OneToOne(optional = false)
	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

}
