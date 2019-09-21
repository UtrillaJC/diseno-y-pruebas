
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Lessor extends Actor {

	//Constructors====================================================================================
	public Lessor() {
		super();
	}


	//Attributes=====================================================================================

	private CreditCard	creditCard;
	private double		amount;


	@Digits(integer = 99, fraction = 2)
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	private Collection<Property> properties;


	@NotNull
	@Valid
	@OneToMany(mappedBy = "lessor")
	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}

}
