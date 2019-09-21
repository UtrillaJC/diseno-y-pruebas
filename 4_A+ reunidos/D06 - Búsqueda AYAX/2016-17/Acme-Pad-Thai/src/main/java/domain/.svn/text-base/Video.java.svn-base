package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;


@Entity
@Access(AccessType.PROPERTY)
public class Video extends LearningMaterial{
	
	//Attributes ====================================================================================

	private String identifier;
	
	// Constructors ====================================================================================

	public Video() {
		super();
	}

	//Getters & setters ====================================================================================
	
	@NotBlank
	@URL
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
