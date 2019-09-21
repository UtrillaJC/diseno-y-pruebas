
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends Actor {

	// Constructors -----------------------------------------------------------

	public Admin() {
		super();
	}


	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------
	private Collection<Category>	categories;


	@Valid
	@NotNull
	@OneToMany(mappedBy = "admin")
	public Collection<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(final Collection<Category> categories) {
		this.categories = categories;
	}
}
