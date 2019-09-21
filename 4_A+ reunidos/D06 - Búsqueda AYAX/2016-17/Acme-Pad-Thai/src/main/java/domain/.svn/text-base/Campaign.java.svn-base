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

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Campaign extends DomainEntity{
	
	//Attributes ====================================================================================
	
	private Date dateStart;
	private Date dateEnd;
	private Boolean isStar;
	
	// Constructors ====================================================================================

	public Campaign() {
		super();
	}
	
	//Getters & setters ====================================================================================

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateStart() {
		return dateStart;
	}
	
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateEnd() {
		return dateEnd;
	}
	
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	public Boolean getIsStar() {
		return isStar;
	}
	
	public void setIsStar(Boolean isStar) {
		this.isStar = isStar;
	} 

	//Relationships ====================================================================================

	private Sponsor sponsor;
	private Collection<Banner> banners;
	private Collection<Bill> bills;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Sponsor getSponsor() {
		return sponsor;
	}
	
	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	@Valid
	@OneToMany(mappedBy="campaign")
	public Collection<Banner> getBanners() {
		return banners;
	}
	
	public void setBanners(Collection<Banner> banners) {
		this.banners = banners;
	}

	@Valid
	@OneToMany(mappedBy = "campaign")
	public Collection<Bill> getBills() {
		return bills;
	}

	public void setBills(Collection<Bill> bills) {
		this.bills = bills;
	}
}
