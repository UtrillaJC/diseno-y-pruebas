
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends Actor {

	// Attributes
	// Relationship
	private Collection<Category>	categories;


	// Constructors
	public Admin() {
		super();
		this.categories = new ArrayList<Category>();
	}

	// Getters

	@Valid
	//	@OneToMany
	public Collection<Category> getCategories() {
		return this.categories;
	}

	// Setters

	public void setCategories(final Collection<Category> categories) {
		this.categories = categories;
	}
}
