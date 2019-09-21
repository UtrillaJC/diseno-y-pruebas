
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Auditor extends Actor {

	// Attributes
	// Relationship
	private Collection<Note>		note;
	private Collection<AuditRecord>	auditRecord;


	// Constructors
	public Auditor() {
		super();
		this.note = new ArrayList<Note>();
		this.auditRecord = new ArrayList<AuditRecord>();
	}
	// Getters

	@Valid
	//	@OneToMany
	public Collection<Note> getNote() {
		return this.note;
	}

	@Valid
	//	@OneToMany
	public Collection<AuditRecord> getAuditRecord() {
		return this.auditRecord;
	}

	// Setters

	public void setNote(final Collection<Note> note) {
		this.note = note;
	}

	public void setAuditRecord(final Collection<AuditRecord> auditRecord) {
		this.auditRecord = auditRecord;
	}
}
