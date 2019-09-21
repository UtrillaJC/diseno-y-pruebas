/* Sponsor.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsor extends Actor{
	
	//Attributes ====================================================================================
	
	private String nameCompany;
	
	//Constructors ====================================================================================
	
	public Sponsor() {
		super();
				
	}
	
	//Getters & setters ====================================================================================
	
	@NotBlank
	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	//Relationships ====================================================================================
	
	private CreditCard creditCard;	
	private Collection<Campaign> campaigns;

	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, optional=false)
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	@Valid
	@OneToMany(mappedBy="sponsor")
	public Collection<Campaign> getCampaigns() {
		return campaigns;
	}
	
	public void setCampaigns(Collection<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
}

