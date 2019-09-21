package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity{

	//Attributes ====================================================================================
	
	private String name;
	private String description;
	private String picture;
	private Collection<String> tags;
	
	//Constructors ====================================================================================

	public Category() {
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

	@ElementCollection
	public Collection<String> getTags() {
		return tags;
	}


	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}
	
	//Relationships ====================================================================================
	
	private Collection<Recipe> recipes;
	private Collection<Category> subcategories;
	private Category parent;

	@Valid
	@ManyToMany(mappedBy = "categories")
	public Collection<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}

	@Valid
	@OneToMany(mappedBy = "parent")
	public Collection<Category> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(Collection<Category> subcategories) {
		this.subcategories = subcategories;
	}
	
	@Valid
	@ManyToOne(optional=true)
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}
}
