
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Category() {
		super();

		this.childrenCategories = new ArrayList<Category>();
		this.trips = new ArrayList<Trip>();
	}


	// Attributes -------------------------------------------------------------

	private String	name;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}


	// Relationships ----------------------------------------------------------

	private Category				parentCategory;
	private Collection<Category>	childrenCategories;
	private Collection<Trip>		trips;
	private Admin					admin;


	@Valid
	//	@OneToOne(optional = true)
	public Category getParentCategory() {
		return this.parentCategory;
	}

	public void setParentCategory(final Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	@Valid
	//	@OneToMany
	public Collection<Category> getChildrenCategories() {
		return this.childrenCategories;
	}

	public void setChildrenCategories(final Collection<Category> childrenCategories) {
		this.childrenCategories = childrenCategories;
	}

	@Valid
	//	@OneToMany
	public Collection<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(final Collection<Trip> trips) {
		this.trips = trips;
	}

	@NotNull
	@Valid
	//	@ManyToOne(optional = false)
	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(final Admin admin) {
		this.admin = admin;
	}

}
