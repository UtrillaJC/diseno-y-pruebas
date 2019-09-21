package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Folder extends DomainEntity{
	
	//Attributes ====================================================================================

	private String name;
	private Boolean systemFolder;
	
	// Constructors ====================================================================================

	public Folder() {
		super();
	}
		
	//Getters & setters ====================================================================================
	
	@NotBlank
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Boolean getSystemFolder(){
		return systemFolder;
	}

	public void setSystemFolder(Boolean systemFolder){
		this.systemFolder = systemFolder;
	}

	//Relationships ====================================================================================
	
	private Collection<Message> messages;
	private Folder parent;
	private Collection<Folder> children;
	private UserAccount userAccount;

	@NotNull
	@Valid
	@OneToMany(mappedBy="folder")
	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}
	
	@Valid
	@ManyToOne(optional=true)
	public Folder getParent() {
		return parent;
	}

	public void setParent(Folder parent) {
		this.parent = parent;
	}
	
	@Valid
	@OneToMany(mappedBy="parent")
	public Collection<Folder> getChildren() {
		return children;
	}

	public void setChildren(Collection<Folder> children) {
		this.children = children;
	}

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
}