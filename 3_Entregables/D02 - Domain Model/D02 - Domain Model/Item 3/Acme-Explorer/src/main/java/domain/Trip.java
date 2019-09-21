
package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Trip extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Trip() {
		super();

		this.requirements = new ArrayList<String>();
		this.stages = new ArrayList<Stage>();
		this.registers = new ArrayList<Register>();
		this.notes = new ArrayList<Note>();
		this.auditRecords = new ArrayList<AuditRecord>();
		this.sponsorships = new ArrayList<Sponsorship>();
		this.survivalClasses = new ArrayList<SurvivalClass>();
		this.stories = new ArrayList<Story>();
		this.finders = new ArrayList<Finder>();
		this.applications = new ArrayList<Application>();
	}


	// Attributes -------------------------------------------------------------

	private String				ticker;
	private String				title;
	private String				description;
	private Collection<String>	requirements;
	private Date				publicationDate;
	private Date				startDateTrip;
	private Date				endDateTrip;
	private String				cancelledReason;
	private Money				price;


	@Column(unique = true)
	@Pattern(regexp = "^(\\d{2})(\\d{2})(\\d{2})\\-([A-Z]{4})$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Collection<String> getRequirements() {
		return this.requirements;
	}

	public void setRequirements(final Collection<String> requirements) {
		this.requirements = requirements;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getStartDateTrip() {
		return this.startDateTrip;
	}

	public void setStartDateTrip(final Date startDateTrip) {
		this.startDateTrip = startDateTrip;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getEndDateTrip() {
		return this.endDateTrip;
	}

	public void setEndDateTrip(final Date endDateTrip) {
		this.endDateTrip = endDateTrip;
	}

	public String getCancelledReason() {
		return this.cancelledReason;
	}

	public void setCancelledReason(final String cancelledReason) {
		this.cancelledReason = cancelledReason;
	}

	@Valid
	@Transient
	public Money getPrice() {
		final Money price = new Money();
		Double amount = 0.0;
		for (final Stage s : this.stages)
			amount += s.getPrice().getAmount();
		price.setAmount(amount);
		return price;
	}


	// Relationships ----------------------------------------------------------

	private Collection<Stage>			stages;
	private Category					category;
	private Collection<Register>		registers;
	private LegalText					legalText;
	private Collection<Note>			notes;
	private Collection<AuditRecord>		auditRecords;
	private Collection<Sponsorship>		sponsorships;
	private Collection<SurvivalClass>	survivalClasses;
	private Manager						manager;
	private Ranger						ranger;
	private Collection<Story>			stories;
	private Collection<Finder>			finders;
	private Collection<Application>		applications;


	@NotEmpty
	@Valid
	//	@OneToMany(mappedBy = "trip", cascade = {
	//		CascadeType.ALL
	//	})
	public Collection<Stage> getStages() {
		return this.stages;
	}

	public void setStages(final Collection<Stage> stages) {
		this.stages = stages;
	}

	@Valid
	//	@ManyToOne(optional = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	@Valid
	//	@OneToMany
	public Collection<Register> getRegisters() {
		return this.registers;
	}

	public void setRegisters(final Collection<Register> registers) {
		this.registers = registers;
	}

	@NotNull
	@Valid
	//	@ManyToOne(optional = false)
	public LegalText getLegalText() {
		return this.legalText;
	}

	public void setLegalText(final LegalText legalText) {
		this.legalText = legalText;
	}

	@Valid
	//	@OneToMany
	public Collection<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}

	@Valid
	//	@OneToMany
	public Collection<AuditRecord> getAuditRecords() {
		return this.auditRecords;
	}

	public void setAuditRecords(final Collection<AuditRecord> auditRecords) {
		this.auditRecords = auditRecords;
	}

	@Valid
	//	@OneToMany
	public Collection<Sponsorship> getSponsorships() {
		return this.sponsorships;
	}

	public void setSponsorships(final Collection<Sponsorship> sponsorships) {
		this.sponsorships = sponsorships;
	}

	@Valid
	//	@OneToMany
	public Collection<SurvivalClass> getSurvivalClasses() {
		return this.survivalClasses;
	}

	public void setSurvivalClasses(final Collection<SurvivalClass> survivalClasses) {
		this.survivalClasses = survivalClasses;
	}

	@NotNull
	@Valid
	//	@ManyToOne(optional = false)
	public Manager getManager() {
		return this.manager;
	}

	public void setManager(final Manager manager) {
		this.manager = manager;
	}

	@NotNull
	@Valid
	//	@ManyToOne(optional = false)
	public Ranger getRanger() {
		return this.ranger;
	}

	public void setRanger(final Ranger ranger) {
		this.ranger = ranger;
	}

	@Valid
	//	@OneToMany
	public Collection<Story> getStories() {
		return this.stories;
	}

	public void setStories(final Collection<Story> stories) {
		this.stories = stories;
	}

	@Valid
	//	@ManyToMany
	public Collection<Finder> getFinders() {
		return this.finders;
	}

	public void setFinders(final Collection<Finder> finders) {
		this.finders = finders;
	}

	@Valid
	//	@OneToMany
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}

}
