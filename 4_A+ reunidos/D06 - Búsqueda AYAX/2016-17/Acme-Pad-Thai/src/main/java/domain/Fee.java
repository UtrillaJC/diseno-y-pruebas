package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Fee extends DomainEntity{

	// Attributes ====================================================================================
	
	private Double amount;
	
	// Constructors ====================================================================================

	public Fee(){
		super();
	}

	//Getters & setters ====================================================================================
	
	@Min(0)
	@Digits(integer = 12, fraction = 2)
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
