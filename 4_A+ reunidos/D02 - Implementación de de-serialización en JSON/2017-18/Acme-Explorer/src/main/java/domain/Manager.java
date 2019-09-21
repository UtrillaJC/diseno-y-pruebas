
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

	// Attributes
	private boolean						suspicious;

	// Relationship
	private Collection<SurvivalClass>	survivalClasses;
	private Collection<Trip>			trips;


	// Constructors
	public Manager() {
		super();
		this.survivalClasses = new ArrayList<SurvivalClass>();
		this.trips = new ArrayList<Trip>();
	}

	// Getters

	public boolean getSuspicious() {
		return this.suspicious;
	}

	@Valid
	//	@OneToMany
	public Collection<SurvivalClass> getSurvivalClasses() {
		return this.survivalClasses;
	}

	@Valid
	//	@OneToMany
	public Collection<Trip> getTrips() {
		return this.trips;
	}

	// Setters

	public void setSuspicious(final boolean suspicious) {
		this.suspicious = suspicious;
	}

	public void setSurvivalClasses(final Collection<SurvivalClass> survivalClasses) {
		this.survivalClasses = survivalClasses;
	}

	public void setTrips(final Collection<Trip> trips) {
		this.trips = trips;
	}
}
