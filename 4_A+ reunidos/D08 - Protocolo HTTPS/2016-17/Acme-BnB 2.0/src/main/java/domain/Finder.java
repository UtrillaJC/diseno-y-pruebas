
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	//Attributes=====================================================================================

	private String	destinationCity;
	private Double	minimumPrice;
	private Double	maximumPrice;
	private String	keyword;
	private Date	lastSearch;


	//Constructors====================================================================================

	public Finder() {
		super();
	}

	//Getters & setters================================================================================

	@NotBlank
	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	@Min(0)
	@Digits(integer = 99, fraction = 2)
	public Double getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(Double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	@Min(0)
	@Digits(integer = 99, fraction = 2)
	public Double getMaximumPrice() {
		return maximumPrice;
	}

	public void setMaximumPrice(Double maximumPrice) {
		this.maximumPrice = maximumPrice;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getLastSearch() {
		return lastSearch;
	}

	public void setLastSearch(Date lastSearch) {
		this.lastSearch = lastSearch;
	}

	// Relationships ====================================================================================

	private Collection<Property> properties;

	@Valid
	@ManyToMany
	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}
}
