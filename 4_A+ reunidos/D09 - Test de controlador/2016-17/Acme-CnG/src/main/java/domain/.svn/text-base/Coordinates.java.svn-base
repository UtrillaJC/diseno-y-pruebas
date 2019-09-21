/* Campaign.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Range;

@Embeddable
@Access(AccessType.PROPERTY)
public class Coordinates{
	
	//Attributes ====================================================================================
	
	private Double latitude;
	private Double longitude;
	
	// Constructors ====================================================================================

	public Coordinates() {
		super();
	}

		
	//Getters & setters ====================================================================================

	@Range(min = -90, max = 90)
	@Digits(integer = 3, fraction = 13)
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	@Range(min = -180, max = 180)
	@Digits(integer = 3, fraction = 13)
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
}