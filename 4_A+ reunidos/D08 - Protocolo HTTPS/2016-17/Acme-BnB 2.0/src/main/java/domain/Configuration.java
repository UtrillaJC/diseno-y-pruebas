
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	// Attributes ====================================================================================

	private Double	fee;
	private Integer	vatNumber;


	// Constructors ====================================================================================

	public Configuration() {
		super();
	}

	//Getters & setters ====================================================================================

	@Min(0)
	@Digits(integer = 99, fraction = 2)
	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	@NotNull
	public Integer getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(Integer vatNumber) {
		this.vatNumber = vatNumber;
	}
}
