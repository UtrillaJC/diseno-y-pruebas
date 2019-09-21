
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

import org.apache.log4j.Priority;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	//Attributes
	private Date				moment;
	private String				subject;
	private String				body;
	private Priority			priority;

	//RelationShip
	private Actor				sender;
	private Collection<Actor>	recipients;
	private Folder				folder;


	//Constructor
	public Message() {
		super();
		this.recipients = new ArrayList<Actor>();
	}

	//Getters

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy:mm")
	public Date getMoment() {
		return this.moment;
	}

	@NotBlank
	@SafeHtml
	public String getSubject() {
		return this.subject;
	}

	@NotBlank
	@SafeHtml
	public String getBody() {
		return this.body;
	}
	@Valid
	@NotNull
	public Priority getPriority() {
		return this.priority;
	}

	@NotNull
	@Valid
	//	@ManyToOne(optional = false)
	public Folder getFolder() {
		return this.folder;
	}

	@NotNull
	@Valid
	//	@ManyToMany
	public Collection<Actor> getRecipient() {
		return this.recipients;
	}

	@NotNull
	@Valid
	//	@ManyToOne(optional = false)
	public Actor getSender() {
		return this.sender;
	}

	//Setters
	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	public void setPriority(final Priority priority) {
		this.priority = priority;
	}

	public void setFolder(final Folder folder) {
		this.folder = folder;
	}

	public void setRecipient(final Collection<Actor> recipients) {
		this.recipients = recipients;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}

}
