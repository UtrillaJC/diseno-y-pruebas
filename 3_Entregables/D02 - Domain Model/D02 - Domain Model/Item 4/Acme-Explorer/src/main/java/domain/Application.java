
package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity {

	//Attributes
	private Date				moment;
	private Status				status;
	private Collection<String>	comments;
	private String				rejectReason;
	private CreditCard			creditCard;

	//RelationShip
	private Explorer			applicant;
	private Trip				trips;


	//Constructor
	public Application() {
		super();
		this.comments = new ArrayList<String>();
	}

	//Getters

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy:mm")
	public Date getMoment() {
		return this.moment;
	}

	@NotNull
	@Valid
	public Status getStatus() {
		return this.status;
	}

	@NotNull
	public Collection<String> getComments() {
		return this.comments;
	}

	@NotNull
	public String getRejectReason() {
		return this.rejectReason;
	}

	@NotNull
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	@NotNull
	@Valid
	//@ManyToOne(optional = false)
	public Explorer getApplicant() {
		return this.applicant;
	}

	@NotNull
	@Valid
	//@ManyToOne(optional = false)
	public Trip getTrips() {
		return this.trips;
	}

	//Setters
	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}

	public void setRejectReason(final String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public void setApplicant(final Explorer applicant) {
		this.applicant = applicant;
	}

	public void setTrips(final Trip trips) {
		this.trips = trips;
	}

}
