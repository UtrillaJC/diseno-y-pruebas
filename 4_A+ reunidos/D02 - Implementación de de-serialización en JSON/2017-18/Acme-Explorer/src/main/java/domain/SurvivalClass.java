
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class SurvivalClass extends DomainEntity {

	//Attributes
	private String			title;
	private String			description;
	private Date			moment;
	private GPSCoordinates	location;

	//RelationShip
	private Manager			manager;
	private Trip			trip;


	//Constructor
	public SurvivalClass() {
		super();
	}

	//Getters

	@NotBlank
	@SafeHtml
	public String getTitle() {
		return this.title;
	}

	@NotBlank
	@SafeHtml
	public String getDescription() {
		return this.description;
	}

	public Date getMoment() {
		return this.moment;
	}

	@Valid
	public GPSCoordinates getLocation() {
		return this.location;
	}

	@NotNull
	@Valid
	//@ManyToOne(optional = false)
	public Trip getTrip() {
		return this.trip;
	}

	@NotNull
	@Valid
	//@ManyToOne(optional = false)
	public Manager getManager() {
		return this.manager;
	}

	//Setters
	public void setTitle(final String title) {
		this.title = title;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setLocation(final GPSCoordinates location) {
		this.location = location;
	}

	public void setTrip(final Trip trip) {
		this.trip = trip;
	}

	public void setManager(final Manager manager) {
		this.manager = manager;
	}

}
