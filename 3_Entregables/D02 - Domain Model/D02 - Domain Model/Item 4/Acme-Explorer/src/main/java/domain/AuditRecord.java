
package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class AuditRecord extends DomainEntity {

	private Date				moment;
	private String				title;
	private String				description;
	private Collection<String>	attachement;
	private boolean				finalVersion;

	private Auditor				auditor;
	private Trip				trip;


	public AuditRecord() {
		super();
		this.attachement = new ArrayList<String>();
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy:mm")
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	@SafeHtml
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}

	@URL
	public Collection<String> getAttachement() {
		return this.attachement;
	}
	public void setAttachement(final Collection<String> attachement) {
		this.attachement = attachement;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public boolean getFinalVersion() {
		return this.finalVersion;
	}
	public void setFinalVersion(final Boolean finalVersion) {
		this.finalVersion = finalVersion;
	}

	@NotNull
	@Valid
	//@ManyToOne
	public Auditor getAuditor() {
		return this.auditor;
	}
	public void setAuditor(final Auditor auditor) {
		this.auditor = auditor;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@Valid
	//@ManyToOne
	public Trip getTrip() {
		return this.trip;
	}
	public void setTrip(final Trip trip) {
		this.trip = trip;
	}

}
