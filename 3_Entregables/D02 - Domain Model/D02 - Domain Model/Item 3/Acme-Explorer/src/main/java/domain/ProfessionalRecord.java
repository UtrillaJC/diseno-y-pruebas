
package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class ProfessionalRecord extends DomainEntity {

	//Attributes
	private String				company;
	private Date				startDate;
	private Date				endDate;
	private String				role;
	private String				attachment;
	private Collection<String>	comments;


	//Constructor
	public ProfessionalRecord() {
		super();
		this.comments = new ArrayList<String>();
	}

	//Getters

	@NotBlank
	public String getCompany() {
		return this.company;
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
	public String getRole() {
		return this.role;
	}

	@URL
	public String getAttachment() {
		return this.attachment;
	}

	public Collection<String> getComments() {
		return this.comments;
	}

	//Setters
	public void setCompany(final String company) {
		this.company = company;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public void setAttachment(final String attachment) {
		this.attachment = attachment;
	}

	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}

}
