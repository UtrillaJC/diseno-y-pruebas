
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Curricula extends DomainEntity {

	//Attributes********************************************************
	private String	ticker;


	//Constructor********************************************************
	public Curricula() {
		super();
		this.professionalRecords = new ArrayList<ProfessionalRecord>();
		this.educationRecords = new ArrayList<EducationRecord>();

		this.miscellaneousRecords = new ArrayList<MiscellaneousRecord>();
		this.endorserRecords = new ArrayList<EndorserRecord>();
	}

	//Getters & Setters****************************************************
	@NotBlank
	@Valid
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}


	//RelationShips******************************************************
	private Collection<ProfessionalRecord>	professionalRecords;
	private Collection<EducationRecord>		educationRecords;
	private PersonalRecord					personalRecord;
	private Collection<MiscellaneousRecord>	miscellaneousRecords;
	private Collection<EndorserRecord>		endorserRecords;
	private Ranger							ranger;


	//@OneToMany
	@Valid
	public Collection<ProfessionalRecord> getProfessionalRecord() {
		return this.professionalRecords;
	}

	//@OneToMany
	@Valid
	public Collection<EducationRecord> getEducationRecord() {
		return this.educationRecords;
	}

	//@OneToOne
	@NotNull
	@Valid
	public PersonalRecord getPersonalRecord() {
		return this.personalRecord;
	}

	//@OneToMany
	@Valid
	public Collection<MiscellaneousRecord> getMiscellaneousRecord() {
		return this.miscellaneousRecords;
	}

	//@ManyToOne
	@Valid
	public Ranger getRanger() {
		return this.ranger;
	}

	public void setProfessionalRecord(final Collection<ProfessionalRecord> professionalRecord) {
		this.professionalRecords = professionalRecord;
	}

	public void setEducationRecord(final Collection<EducationRecord> educationRecord) {
		this.educationRecords = educationRecord;
	}

	public void setPersonalRecord(final PersonalRecord personalRecord) {
		this.personalRecord = personalRecord;
	}

	public void setMiscellaneousRecord(final Collection<MiscellaneousRecord> miscellaneousRecord) {
		this.miscellaneousRecords = miscellaneousRecord;
	}

	public void setRanger(final Ranger ranger) {
		this.ranger = ranger;
	}

	//@OneToMany
	public Collection<EndorserRecord> getEndorserRecord() {
		return this.endorserRecords;
	}

	public void setEndorserRecord(final Collection<EndorserRecord> endorserRecord) {
		this.endorserRecords = endorserRecord;
	}

}
