/*
 * Actor.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 * 
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
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends CommentClass {

	//Attributes=====================================================================================

	private String	name;
	private String	surname;
	private String	email;
	private String	phone;
	private String	picture;


	//Constructors====================================================================================

	public Actor() {
		super();
	}

	//Getters & setters================================================================================

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	@Pattern(regexp = "^[+]\\d+\\s\\d+$")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@NotBlank
	@URL
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}


	// Relationships ====================================================================================

	private UserAccount					userAccount;
	private Collection<SocialIdentity>	socialIdentities;


	@Valid
	@OneToMany(mappedBy = "actor")
	public Collection<SocialIdentity> getSocialIdentities() {
		return socialIdentities;
	}

	public void setSocialIdentities(Collection<SocialIdentity> socialIdentities) {
		this.socialIdentities = socialIdentities;
	}

	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
