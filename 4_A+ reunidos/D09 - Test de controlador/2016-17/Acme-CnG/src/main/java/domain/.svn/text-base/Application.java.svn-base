
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes={@Index(columnList="status")})
public class Application extends DomainEntity {

	//Attributes=====================================================================================

	private Status	status;


	//Constructors====================================================================================

	public Application() {
		super();
	}

	//Getters & setters================================================================================

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}


	// Relationships ====================================================================================

	private Customer	customer;
	private Service		service;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Service getService() {
		return this.service;
	}

	public void setService(final Service service) {
		this.service = service;
	}

}
