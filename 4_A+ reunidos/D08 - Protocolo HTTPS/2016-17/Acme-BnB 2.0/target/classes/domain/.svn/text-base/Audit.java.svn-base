
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import utilities.validators.AllURLs;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes={@Index(columnList="draft")})
public class Audit extends DomainEntity {

	//Constructors====================================================================================
	
	public Audit() {
		super();
	}


	//Attributes=====================================================================================

	private Date				moment;
	private String				text;
	private Collection<String>	attachments;
	private boolean				draft;


	//Getters & setters================================================================================

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@NotNull
	@AllURLs
	@ElementCollection
	public Collection<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(Collection<String> attachments) {
		this.attachments = attachments;
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}


	// Relationships ====================================================================================

	private Auditor		auditor;
	private Property	property;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Auditor getAuditor() {
		return auditor;
	}

	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

}
