
package forms;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import domain.Type;

public class ServiceCoordinatesForm {

	//Attributes Service=====================================================================================

	private Type	type;
	private String	title;
	private String	description;
	private Date	moment;
	private String	originPlace;
	private String	destinationPlace;

	// Relationships Service ====================================================================================

	private int		customerId;

	//Attributes Coordinates=====================================================================================

	private Double	originLatitude;
	private Double	originLongitude;

	private Double	destinationLatitude;
	private Double	destinationLongitude;


	//Getters & setters Service================================================================================

	@NotNull
	public Type getType() {
		return this.type;
	}

	public void setType(final Type type) {
		this.type = type;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getOriginPlace() {
		return this.originPlace;
	}

	public void setOriginPlace(final String originPlace) {
		this.originPlace = originPlace;
	}

	@NotBlank
	public String getDestinationPlace() {
		return this.destinationPlace;
	}

	public void setDestinationPlace(final String destinationPlace) {
		this.destinationPlace = destinationPlace;
	}

	@NotNull
	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(final int customerId) {
		this.customerId = customerId;
	}

	@Range(min = -90, max = 90)
	@Digits(integer = 3, fraction = 13)
	public Double getOriginLatitude() {
		return originLatitude;
	}

	
	public void setOriginLatitude(Double originLatitude) {
		this.originLatitude = originLatitude;
	}

	@Range(min = -180, max = 180)
	@Digits(integer = 3, fraction = 13)
	public Double getOriginLongitude() {
		return originLongitude;
	}

	
	public void setOriginLongitude(Double originLongitude) {
		this.originLongitude = originLongitude;
	}

	@Range(min = -90, max = 90)
	@Digits(integer = 3, fraction = 13)
	public Double getDestinationLatitude() {
		return destinationLatitude;
	}

	
	public void setDestinationLatitude(Double destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	@Range(min = -180, max = 180)
	@Digits(integer = 3, fraction = 13)
	public Double getDestinationLongitude() {
		return destinationLongitude;
	}

	
	public void setDestinationLongitude(Double destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}



}
