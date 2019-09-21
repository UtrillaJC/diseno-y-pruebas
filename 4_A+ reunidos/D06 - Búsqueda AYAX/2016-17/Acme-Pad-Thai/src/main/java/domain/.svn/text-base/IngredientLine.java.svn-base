package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class IngredientLine extends DomainEntity{
	
	//Attributes ====================================================================================
	
	private String unit;
	private Double multiplicity;
	
	// Constructors ====================================================================================

	public IngredientLine() {
		super();
	}
	
	//Getters & setters ====================================================================================
	
	@NotBlank
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Min(1)
	@Digits(integer = 12, fraction = 2)
	public Double getMultiplicity() {
		return multiplicity;
	}
	
	public void setMultiplicity(Double multiplicity) {
		this.multiplicity = multiplicity;
	}
	
	//Relationships ====================================================================================

	private Ingredient ingredient;
	private Recipe recipe;
	
	@Valid
	@ManyToOne(optional = false)
	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	@Valid
	@ManyToOne(optional = false)
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}
