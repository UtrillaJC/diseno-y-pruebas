package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Taste extends DomainEntity {

	// Attributes ====================================================================================
	
	private Boolean likes;
	
	// Constructors ====================================================================================

	public Taste(){
		super();
	}

	//Getters & setters ====================================================================================
	
	public Boolean getLikes() {
		return likes;
	}

	public void setLikes(Boolean likes) {
		this.likes = likes;
	}
	
	//Relationships ====================================================================================
	
	private Recipe recipe;
	private Person person;

	@Valid
	@ManyToOne(optional = false)
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
