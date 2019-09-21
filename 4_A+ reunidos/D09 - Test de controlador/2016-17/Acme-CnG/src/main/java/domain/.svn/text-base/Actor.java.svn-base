/*
 * Actor.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends CommentClass {

	//Attributes=====================================================================================

	private String	name;
	private String	email;
	private String	phone;


	//Constructors====================================================================================

	public Actor() {
		super();
	}

	//Getters & setters================================================================================

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	@Pattern(regexp = "^([+]\\d+\\s)?\\d+$")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	// Relationships ====================================================================================

	private UserAccount			userAccount;
	private Collection<Message>	sentMessages;
	private Collection<Message>	receivedMessages;
	private Collection<Comment>	createdComments;


	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Valid
	@OneToMany(mappedBy = "sender")
	public Collection<Message> getSentMessages() {
		return this.sentMessages;
	}

	public void setSentMessages(Collection<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	@Valid
	@OneToMany(mappedBy = "recipient")
	public Collection<Message> getReceivedMessages() {
		return this.receivedMessages;
	}

	public void setReceivedMessages(Collection<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	@Valid
	@OneToMany(mappedBy = "author")
	public Collection<Comment> getCreatedComments() {
		return this.createdComments;
	}

	public void setCreatedComments(Collection<Comment> createdComments) {
		this.createdComments = createdComments;
	}
}
