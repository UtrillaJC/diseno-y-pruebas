/* Bill.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Access(AccessType.PROPERTY)
public class Bill extends DomainEntity {

	//Attributes ====================================================================================
	
	private Date momentCreated;
	private Date momentPaid;
	private Double cost;
	private String description;

	//Constructors ====================================================================================

	public Bill() {
		super();
	}
	
	//Getters & setters ====================================================================================
	
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMomentCreated() {
		return momentCreated;
	}
	
	public void setMomentCreated(Date momentCreated) {
		this.momentCreated = momentCreated;
	}
	
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMomentPaid() {
		return momentPaid;
	}
	
	public void setMomentPaid(Date momentPaid) {
		this.momentPaid = momentPaid;
	}

	@Digits(integer=12,fraction=2)
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	//Relationships ====================================================================================
	

	private Campaign campaign;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	
	
}


