/* UserAccount.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package security;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import domain.DomainEntity;
import domain.Folder;
import domain.Message;

@Entity
@Access(AccessType.PROPERTY)
public class UserAccount extends DomainEntity implements UserDetails {

	// Constructors -----------------------------------------------------------

	private static final long serialVersionUID = 7254823034213841482L;

	public UserAccount() {
		super();

		this.authorities = new ArrayList<Authority>();
	}

	// Attributes -------------------------------------------------------------

	// UserDetails interface --------------------------------------------------

	private String username;
	private String password;
	private Collection<Authority> authorities;

	@Size(min = 5, max = 32)
	@Column(unique = true)
	@Override
	@NotBlank
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	@Override
	@NotBlank
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotEmpty
	@Valid
	@ElementCollection
	@Override
	public Collection<Authority> getAuthorities() {
		// WARNING: Should return an unmodifiable copy, but it's not possible with hibernate!
		return authorities;
	}

	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	public void addAuthority(Authority authority) {
		Assert.notNull(authority);
		Assert.isTrue(!authorities.contains(authority));

		authorities.add(authority);
	}

	public void removeAuthority(Authority authority) {
		Assert.notNull(authority);
		Assert.isTrue(authorities.contains(authority));
		
		authorities.remove(authority);
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// Relationships ====================================================================================
	
	private Collection<Message> sentMessages; 
	private Collection<Message> receivedMessages; 
	private Collection<Folder> folders;
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="sender")
	public Collection<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(Collection<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="recipient")
	public Collection<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(Collection<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="userAccount")
	public Collection<Folder> getFolders() {
		return folders;
	}

	public void setFolders(Collection<Folder> folders) {
		this.folders = folders;
	}
}
