package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Property extends DomainEntity{

	//Attributes ====================================================================================

	private String name;
	
	// Constructors ====================================================================================

	public Property() {
		super();
	}

	//Getters & setters ====================================================================================
	
	@NotBlank
	@Column(unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//Relationships ====================================================================================
	
	private Collection<Ingredient> ingredients;

	@Valid
	@ManyToMany(mappedBy = "properties")
	public Collection<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Collection<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
