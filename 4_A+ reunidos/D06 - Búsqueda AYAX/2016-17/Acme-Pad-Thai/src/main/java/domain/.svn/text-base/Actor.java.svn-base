/* Actor.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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

	//Attributes=====================================================================================
	
	private String name;
	private String surname;
	private String email;
	private String phoneNumber;
	private String address;

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

	@Email
	@NotBlank
	@Column(unique=true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Pattern(regexp = "[\\+]?[1-9]?[0-9]?[0-9]?\\s[(][0-9][0-9][1-9][)]?\\s(\\w[-\\s]?\\w[-\\s]?\\w[-\\s]?\\w){1,}([-\\s]?\\w)*")     
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// Relationships ====================================================================================

	private UserAccount userAccount;
	private SocialIdentity socialIdentity;

	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, optional=false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional=true)
	public SocialIdentity getSocialIdentity() {
		return socialIdentity;
	}

	public void setSocialIdentity(SocialIdentity socialIdentity) {
		this.socialIdentity = socialIdentity;
	}
}

	

