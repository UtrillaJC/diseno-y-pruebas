
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Register extends DomainEntity {

	//Attributes*************************************************************
	private Date	moment;


	//Constructor************************************************************
	public Register() {
		super();
	}

	//Getters & Setters*******************************************************
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy:mm")
	@Valid
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}


	//RelashionShips
	private Tag		tag;
	private Trip	trip;


	@NotNull
	@Valid
	//@ManyToOne
	public Tag getTag() {
		return this.tag;
	}

	public void setTag(final Tag tag) {
		this.tag = tag;
	}
	@NotNull
	@Valid
	//@ManyToOne
	public Trip getTrip() {
		return this.trip;
	}

	public void setTrips(final Trip trip) {
		this.trip = trip;
	}

}
