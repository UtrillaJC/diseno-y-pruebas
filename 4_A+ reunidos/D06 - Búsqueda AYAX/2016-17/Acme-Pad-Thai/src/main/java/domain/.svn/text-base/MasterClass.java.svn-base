package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class MasterClass extends DomainEntity{
	
	//Attributes ====================================================================================

	private String title;
	private String description;
	private Boolean isPromoted;
	
	// Constructors ====================================================================================

	public MasterClass() {
		super();
	}
		
	//Getters & setters ====================================================================================

	@NotBlank
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getIsPromoted() {
		return isPromoted;
	}

	public void setIsPromoted(Boolean isPromoted) {
		this.isPromoted = isPromoted;
	}
	
	//Relationships ====================================================================================

	private Cook cook;
	private Collection<LearningMaterial> learningMaterials;
	private Collection<User> users;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Cook getCook() {
		return cook;
	}
	
	public void setCook(Cook cook) {
		this.cook = cook;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="masterClass")
	public Collection<LearningMaterial> getLearningMaterials() {
		return learningMaterials;
	}

	public void setLearningMaterials(Collection<LearningMaterial> learningMaterials) {
		this.learningMaterials = learningMaterials;
	}

	@Valid
	@ManyToMany
	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}	
}
