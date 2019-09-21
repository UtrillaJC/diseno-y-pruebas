
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
public class Finder extends DomainEntity {

	//Attributes
	private String	keyword;
	private Money	priceMin;
	private Money	priceMax;
	private Date	startDateTripMin;
	private Date	endDateTripMax;
	private Date	lastUpdate;


	//Constructor
	public Finder() {
		super();
		this.trips = new ArrayList<Trip>();
	}

	//Getters & Setters
	@NotNull
	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}
	@Valid
	public Money getPriceMin() {
		return this.priceMin;
	}

	public void setPriceMin(final Money priceMin) {
		this.priceMin = priceMin;
	}
	@Valid
	public Money getPriceMax() {
		return this.priceMax;
	}

	public void setPriceMax(final Money priceMax) {
		this.priceMax = priceMax;
	}
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy:mm")
	public Date getStartDateTripmin() {
		return this.startDateTripMin;
	}

	public void setStartDateTripmin(final Date startDateTripmin) {
		this.startDateTripMin = startDateTripmin;
	}
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy:mm")
	public Date getEndDateTripMax() {
		return this.endDateTripMax;
	}

	public void setEndDateTripMax(final Date endDateTripMax) {
		this.endDateTripMax = endDateTripMax;
	}
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy:mm")
	public Date getLastUpDate() {
		return this.lastUpdate;
	}

	public void setLastUpDate(final Date lastUpDate) {
		this.lastUpdate = lastUpDate;
	}


	//RelationShips******************************************************************************

	private Explorer			explorer;
	private Collection<Trip>	trips;


	@NotNull
	@Valid
	//@OneToOne
	public Explorer getExplorer() {
		return this.explorer;
	}

	public void setExplorer(final Explorer explorer) {
		this.explorer = explorer;
	}
	//@ManyToMany
	@NotNull
	@Valid
	public Collection<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(final Collection<Trip> trips) {
		this.trips = trips;
	}

}
