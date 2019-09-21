/* Endorser.java
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
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Access(AccessType.PROPERTY)
public class Endorser extends DomainEntity {

	//Attributes ====================================================================================

	private String name;
	private String homePage;
	
	//Constructors ====================================================================================

	public Endorser() {
		super();
	}
	
	//Getters & setters ====================================================================================
	
	@NotBlank
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getHomePage() {
		return homePage;
	}
	
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	//Relationships ====================================================================================
	
	private Collection<Curriculum> curricula;

	@Valid
	@ManyToMany
	public Collection<Curriculum> getCurricula() {
		return curricula;
	}
	
	public void setCurricula(Collection<Curriculum> curricula) {
		this.curricula = curricula;
	}
}
