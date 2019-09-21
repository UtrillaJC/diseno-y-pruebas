
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Ranger extends Actor {

	// Attributes
	private boolean				suspicious;

	// Relationship
	private Collection<Trip>	trips;
	private Curricula			curricula;


	// Constructors
	public Ranger() {
		super();
		this.trips = new ArrayList<Trip>();
	}

	// Getters

	public boolean getSuspicious() {
		return this.suspicious;
	}

	@Valid
	//	@OneToMany
	public Collection<Trip> getTrips() {
		return this.trips;
	}

	@Valid
	//	@OneToOne(optional = true)
	public Curricula getCurricula() {
		return this.curricula;
	}

	// Setters

	public void setSuspicious(final boolean suspicious) {
		this.suspicious = suspicious;
	}

	public void setTrips(final Collection<Trip> trips) {
		this.trips = trips;
	}

	public void setCurricula(final Curricula curricula) {
		this.curricula = curricula;
	}
}
