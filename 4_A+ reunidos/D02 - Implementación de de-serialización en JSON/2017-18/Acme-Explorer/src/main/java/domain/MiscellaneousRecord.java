
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class MiscellaneousRecord extends DomainEntity {

	//Attributes
	private String				title;
	private String				attachment;
	private Collection<String>	comments;


	//Constructors
	public MiscellaneousRecord() {
		super();
		this.comments = new ArrayList<String>();
	}

	//Getters

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	@URL
	public String getAttachment() {
		return this.attachment;
	}

	public Collection<String> getComments() {
		return this.comments;
	}

	//Setters
	public void setTitle(final String title) {
		this.title = title;
	}

	public void setAttachment(final String attachment) {
		this.attachment = attachment;
	}

	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}

}
