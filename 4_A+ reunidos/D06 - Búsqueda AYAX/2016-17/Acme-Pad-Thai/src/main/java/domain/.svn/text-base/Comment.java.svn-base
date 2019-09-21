/* Comment.java
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity{
	
	//Attributes ====================================================================================
	
	private Date momentCreated;
	private String title;
	private String text;
	private int stars;
	private String nameRecipe;

	
	//Constructors ====================================================================================

	public Comment() {
		super();
	}
	
	//Getters & setters ====================================================================================

	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMomentCreated() {
		return momentCreated;
	}

	public void setMomentCreated(Date momentCreated) {
		this.momentCreated = momentCreated;
	}
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Range(min = 0, max = 5)
	public int getStars() {
		return stars;
	}
	
	public void setStars(int stars) {
		this.stars = stars;
	}
				
	@NotBlank
	public String getNameRecipe() {
		return nameRecipe;
	}
	
	public void setNameRecipe(String nameRecipe) {
		this.nameRecipe = nameRecipe;
	}
	
	//Relationships ====================================================================================

	private Person person;

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
