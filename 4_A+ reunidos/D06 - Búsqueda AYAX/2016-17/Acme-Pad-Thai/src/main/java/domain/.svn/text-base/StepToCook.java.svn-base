/* StepToCook.java */

package domain;

import java.util.Collection;

import javax.validation.Valid;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class StepToCook extends DomainEntity {


	//Attributes ====================================================================================
	
	private int number;
	private String description;
	private String picture;
	private Collection<String> hints;
	
	//Constructors ====================================================================================

	public StepToCook() {
		super();
	}
	
	//Getters & setters ====================================================================================
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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
	
	@ElementCollection
	public Collection<String> getHints() {
		return hints;
	}
	
	public void setHints(Collection<String> hints) {
		this.hints = hints;
	}

	//Relationships ====================================================================================
	
	private Recipe recipe;
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Recipe getRecipe() {
		return recipe;
	}
	
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}
