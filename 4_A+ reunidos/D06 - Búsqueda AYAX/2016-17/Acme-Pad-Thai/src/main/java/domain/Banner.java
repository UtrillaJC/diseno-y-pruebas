/* Banner.java
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
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity{
	
	//Attributes ====================================================================================
	
	private String description;
	private int maxNumDisplayed;
	private int numDisplayed;
	
	// Constructors ====================================================================================

	public Banner() {
		super();
	}
		
	//Getters & setters ====================================================================================
	
	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getMaxNumDisplayed() {
		return maxNumDisplayed;
	}


	public void setMaxNumDisplayed(int maxNumDisplayed) {
		this.maxNumDisplayed = maxNumDisplayed;
	}
	
	public int getNumDisplayed() {
		return numDisplayed;
	}

	public void setNumDisplayed(int numDisplayed) {
		this.numDisplayed = numDisplayed;
	}
	
	
	//Relationships ====================================================================================
	
	private Campaign campaign;
			
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Campaign getCampaign() {
		return campaign;
	}
		
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
}
