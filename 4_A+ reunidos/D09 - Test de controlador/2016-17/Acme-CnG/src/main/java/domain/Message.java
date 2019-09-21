
package domain;

import java.net.URL;
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

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes={@Index(columnList="recipientCopy")})
public class Message extends DomainEntity {

	//Attributes ====================================================================================

	private Date			createdMoment;
	private String			title;
	private String			text;
	private Collection<URL>	attachments;
	private boolean recipientCopy;


	// Constructors ====================================================================================

	public Message() {
		super();
	}

	//Getters & setters ====================================================================================

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getCreatedMoment() {
		return this.createdMoment;
	}

	public void setCreatedMoment(final Date createdMoment) {
		this.createdMoment = createdMoment;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@ElementCollection
	public Collection<URL> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final Collection<URL> attachments) {
		this.attachments = attachments;
	}
	
	public boolean isRecipientCopy() {
		return recipientCopy;
	}

	
	public void setRecipientCopy(boolean recipientCopy) {
		this.recipientCopy = recipientCopy;
	}


	// Relationships ====================================================================================

	private Actor	sender;
	private Actor	recipient;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getSender() {
		return this.sender;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getRecipient() {
		return this.recipient;
	}

	public void setRecipient(final Actor recipient) {
		this.recipient = recipient;
	}

}
