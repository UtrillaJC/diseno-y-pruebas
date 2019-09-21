
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsor extends Actor {

	// Attributes
	// Relationship
	private Collection<Sponsorship>	sponsorship;


	// Constructors
	public Sponsor() {
		super();
		this.sponsorship = new ArrayList<Sponsorship>();
	}

	// Getters

	@Valid
	//	@OneToMany
	public Collection<Sponsorship> getSponsorship() {
		return this.sponsorship;
	}

	// Setters

	public void setSponsorship(final Collection<Sponsorship> sponsorship) {
		this.sponsorship = sponsorship;
	}
}
