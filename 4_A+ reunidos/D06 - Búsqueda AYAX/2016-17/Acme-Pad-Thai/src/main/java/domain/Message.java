package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity{
	
	//Attributes ====================================================================================
		
	private Date moment;
	private String subject;
	private String body;
	private String priority;
	
	// Constructors ====================================================================================

	public Message() {
		super();
	}
	
	//Getters & setters ====================================================================================
	
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return moment;
	}
	
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@NotBlank
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@NotBlank
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	@NotBlank
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	//Relationships ====================================================================================
	
	private UserAccount sender;
	private UserAccount recipient;
	private Folder folder;

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public UserAccount getSender() {
		return sender;
	}

	public void setSender(UserAccount sender) {
		this.sender = sender;
	}

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public UserAccount getRecipient() {
		return recipient;
	}

	public void setRecipient(UserAccount recipient) {
		this.recipient = recipient;
	}

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}
}
