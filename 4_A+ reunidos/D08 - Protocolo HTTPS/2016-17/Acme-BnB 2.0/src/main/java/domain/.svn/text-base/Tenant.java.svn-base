
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Tenant extends Actor {

	//Attributes=====================================================================================

	//Constructors====================================================================================

	public Tenant() {
		super();
	}

	//Getters & setters================================================================================


	// Relationships ====================================================================================

	private Finder				finder;
	private Collection<Request>	requests;


	@Valid
	@OneToOne(optional = true)
	public Finder getFinder() {
		return finder;
	}

	public void setFinder(Finder finder) {
		this.finder = finder;
	}

	@Valid
	@OneToMany(mappedBy = "tenant")
	public Collection<Request> getRequests() {
		return requests;
	}

	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}

}
