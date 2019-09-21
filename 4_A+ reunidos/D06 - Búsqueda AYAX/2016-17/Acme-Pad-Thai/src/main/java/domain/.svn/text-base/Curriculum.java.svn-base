/* Curricula.java
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
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {
	
	//Attributes ====================================================================================

	private String photo;
	private String educationSection;
	private String experienceSection;
	private String hobbiesSection;	
	
	//Constructors ====================================================================================

	public Curriculum() {
		super();
	}

	//Getters & setters ====================================================================================
	
	@URL
	@NotBlank
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@NotBlank
	public String getEducationSection() {
		return educationSection;
	}
	public void setEducationSection(String educationSection) {
		this.educationSection = educationSection;
	}
	
	@NotBlank
	public String getExperienceSection() {
		return experienceSection;
	}
	public void setExperienceSection(String experienceSection) {
		this.experienceSection = experienceSection;
	}
	
	@NotBlank
	public String getHobbiesSection() {
		return hobbiesSection;
	}
	public void setHobbiesSection(String hobbiesSection) {
		this.hobbiesSection = hobbiesSection;
	}

	//Relationships ====================================================================================
	
	private Collection<Endorser> endorsers;
	private Nutritionist nutritionist;
	
	@Valid
	@ManyToMany(mappedBy = "curricula")
	public Collection<Endorser> getEndorsers() {
		return endorsers;
	}
	
	public void setEndorsers(Collection<Endorser> endorsers) {
		this.endorsers = endorsers;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Nutritionist getNutritionist() {
		return nutritionist;
	}
	
	public void setNutritionist(Nutritionist nutritionist) {
		this.nutritionist = nutritionist;
	}
}
