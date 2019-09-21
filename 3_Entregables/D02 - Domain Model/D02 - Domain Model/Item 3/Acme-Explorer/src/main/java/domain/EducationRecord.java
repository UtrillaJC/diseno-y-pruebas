
package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class EducationRecord extends DomainEntity {

	//Attributes 
	private String				title;
	private Date				startDate;
	private Date				endDate;
	private String				institution;
	private String				attachment;
	private Collection<String>	comments;


	//Constructors
	public EducationRecord() {
		super();
		this.comments = new ArrayList<String>();
	}

	//Getters

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy:mm")
	public Date getStartDate() {
		return this.startDate;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy:mm")
	public Date getEndDate() {
		return this.endDate;
	}

	@NotBlank
	public String getInstitution() {
		return this.institution;
	}

	@URL
	@NotNull
	public String getAttarchment() {
		return this.attachment;
	}

	public Collection<String> getComments() {
		return this.comments;
	}

	//Setters
	public void setTitle(final String title) {
		this.title = title;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public void setInstitution(final String institution) {
		this.institution = institution;
	}

	public void setAttachment(final String attachment) {
		this.attachment = attachment;
	}

	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}

}
