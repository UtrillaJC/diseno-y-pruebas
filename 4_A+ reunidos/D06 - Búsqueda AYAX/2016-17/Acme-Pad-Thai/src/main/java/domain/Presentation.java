package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;


@Entity
@Access(AccessType.PROPERTY)
public class Presentation extends LearningMaterial{
	
	//Attributes ====================================================================================

	private String path;
		
	// Constructors ====================================================================================

	public Presentation() {
		super();
	}
	
	//Getters & setters====================================================================================
	
	@NotBlank
	@URL
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
