
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity {

	//Attributes=====================================================================================

	private String	text;


	//Constructors====================================================================================

	public Banner() {
		super();
	}

	//Getters & setters ====================================================================================
	
	@URL
	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	// Relationships ====================================================================================

}
