/* Ingredient.java
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
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Ingredient extends DomainEntity {
	
	//Attributes ====================================================================================

	private String name;
	private String description;
	private String picture;
	
	//Constructor ====================================================================================
	
	public Ingredient(){
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
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@URL
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}	
	
	//Relationships ====================================================================================
	
	private Collection<Property> properties;
	private Collection<IngredientLine> ingredientLines;

	@Valid
	@ManyToMany
	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}

	@Valid
	@OneToMany(mappedBy = "ingredient")
	@NotNull
	public Collection<IngredientLine> getIngredientLines() {
		return ingredientLines;
	}

	public void setIngredientLines(Collection<IngredientLine> ingredientLines) {
		this.ingredientLines = ingredientLines;
	}
}
