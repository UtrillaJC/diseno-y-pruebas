
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Explorer extends Actor {

	// Attributes
	private Collection<Contact>		contacts;

	// Relationship
	private Collection<Story>		stories;
	private Finder					finder;
	private Collection<Application>	applications;


	// Constructors
	public Explorer() {
		super();
		this.contacts = new ArrayList<Contact>();
		this.stories = new ArrayList<Story>();
		this.applications = new ArrayList<Application>();
	}

	// Getters

	@Valid
	public Collection<Contact> getContacts() {
		return this.contacts;
	}

	@Valid
	//	@OneToMany
	public Collection<Story> getStories() {
		return this.stories;
	}

	@NotNull
	@Valid
	//	@OneToOne(optional = false)
	public Finder getFinder() {
		return this.finder;
	}

	@Valid
	//	@OneToMany
	public Collection<Application> getApplications() {
		return this.applications;
	}

	// Setters

	public void setContacts(final Collection<Contact> contacts) {
		this.contacts = contacts;
	}

	public void setStories(final Collection<Story> stories) {
		this.stories = stories;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}

	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}
}
