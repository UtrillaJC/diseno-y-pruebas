
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	//Attributes=====================================================================================

	//Constructors====================================================================================

	public Customer() {
		super();
	}


	//Getters & setters================================================================================

	// Relationships ====================================================================================

	private Collection<Application>	applications;
	private Collection<Service>		services;


	@Valid
	@OneToMany(mappedBy = "customer")
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(Collection<Application> applications) {
		this.applications = applications;
	}

	@Valid
	@OneToMany(mappedBy = "customer")
	public Collection<Service> getServices() {
		return this.services;
	}

	public void setServices(Collection<Service> services) {
		this.services = services;
	}

}
