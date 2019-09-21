
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	// Constructor --------------------------------------------------------------------------------

	public Finder() {
		super();
	}


	// Attributes ---------------------------------------------------------------------------------

	private String	keyword;
	private Money	priceMin;
	private Money	priceMax;
	private Date	startDateTripMin;
	private Date	startDateTripMax;
	private Date	lastUpdate;


	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}

	@Valid
	@AttributeOverrides({
		@AttributeOverride(name = "amount", column = @Column(name = "minimumAmount")), @AttributeOverride(name = "currency", column = @Column(name = "minimumCurrency"))
	})
	public Money getPriceMin() {
		return this.priceMin;
	}

	public void setPriceMin(final Money priceMin) {
		this.priceMin = priceMin;
	}

	@Valid
	@AttributeOverrides({
		@AttributeOverride(name = "amount", column = @Column(name = "maximimAmount")), @AttributeOverride(name = "currency", column = @Column(name = "maximumCurrency"))
	})
	public Money getPriceMax() {
		return this.priceMax;
	}

	public void setPriceMax(final Money priceMax) {
		this.priceMax = priceMax;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getStartDateTripMin() {
		return this.startDateTripMin;
	}

	public void setStartDateTripMin(final Date startDateTripmin) {
		this.startDateTripMin = startDateTripmin;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getStartDateTripMax() {
		return this.startDateTripMax;
	}

	public void setStartDateTripMax(final Date startDateTripMax) {
		this.startDateTripMax = startDateTripMax;
	}

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(final Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	// RelationShips ----------------------------------------------------------------------------

	private Explorer			explorer;
	private Collection<Trip>	trips;


	@NotNull
	@Valid
	@OneToOne(optional = false, mappedBy = "finder")
	public Explorer getExplorer() {
		return this.explorer;
	}

	public void setExplorer(final Explorer explorer) {
		this.explorer = explorer;
	}
	@Valid
	@NotNull
	@ManyToMany
	public Collection<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(final Collection<Trip> trips) {
		this.trips = trips;
	}

}
