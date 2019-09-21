package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Person{
	
	//Constructors ====================================================================================
	
	public User() {
		super();
					
		}
	
	//Relationship ====================================================================================
	
	private Collection<Recipe> recipes;
	private Collection<MasterClass> masterClasses;

	@Valid
	@OneToMany(mappedBy="user")
	public Collection<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}

	@Valid
	@ManyToMany(mappedBy = "users")
	public Collection<MasterClass> getMasterClasses() {
		return masterClasses;
	}

	public void setMasterClasses(Collection<MasterClass> masterClasses) {
		this.masterClasses = masterClasses;
	}
}
