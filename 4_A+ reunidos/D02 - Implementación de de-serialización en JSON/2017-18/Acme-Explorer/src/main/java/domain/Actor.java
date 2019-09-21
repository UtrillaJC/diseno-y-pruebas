
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	// Attributes
	private String						name;
	private String						surname;
	private String						email;
	private String						address;
	private String						phone;

	// Relationship
	private Collection<SocialIdentity>	socialIdentity;
	private UserAccount					userAccount;
	private Folder						folder;
	private Collection<Message>			sentMessages;
	private Collection<Message>			recipientMessages;


	// Constructors
	public Actor() {
		super();
		this.socialIdentity = new ArrayList<SocialIdentity>();
		this.sentMessages = new ArrayList<Message>();
		this.recipientMessages = new ArrayList<Message>();
	}

	// Getters

	@NotBlank
	public String getName() {
		return this.name;
	}

	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	@Email
	public String getEmail() {
		return this.email;
	}

	public String getAddress() {
		return this.address;
	}

	@Pattern(regexp = "^(\\+\\d{1,3}\\ (\\(\\d{1,3}\\)\\ )?)?(\\d{4,})|.\\d+$")
	public String getPhone() {
		return this.phone;
	}

	@Valid
	//	@OneToMany
	public Collection<SocialIdentity> getSocialIdentity() {
		return this.socialIdentity;
	}

	@Valid
	@NotNull
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	@Valid
	//	@OneToMany
	public Folder getFolder() {
		return this.folder;
	}

	@Valid
	//	@OneToMany(mappedBy = "sender")
	public Collection<Message> getSentMessages() {
		return this.sentMessages;
	}

	@Valid
	//	@ManyToMany(mappedBy = "recipients")
	public Collection<Message> getRecipientMessages() {
		return this.recipientMessages;
	}

	// Setters

	public void setName(final String name) {
		this.name = name;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public void setSocialIdentity(final Collection<SocialIdentity> socialIdentity) {
		this.socialIdentity = socialIdentity;
	}

	public void setFolder(final Folder folder) {
		this.folder = folder;
	}

	public void setSentMessages(final Collection<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public void setRecipientMessages(final Collection<Message> recipientMessages) {
		this.recipientMessages = recipientMessages;
	}
}
