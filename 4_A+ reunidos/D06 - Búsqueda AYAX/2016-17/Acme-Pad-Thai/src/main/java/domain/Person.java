package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Person extends Actor{
	
	//Attributes ====================================================================================
	
	//Constructors ====================================================================================
	
	public Person(){
		super();
	}
	
	//Getters & setters ====================================================================================

	//Relationship ====================================================================================
	
	private Collection<Person> followings;
	private Collection<Person> followers;
	private Collection<Comment> comments;
	private Collection<Taste> tastes;
	
	@Valid
	@ManyToMany(cascade = CascadeType.ALL)
	public Collection<Person> getFollowings() {
		return followings;
	}

	public void setFollowings(Collection<Person> followings) {
		this.followings = followings;
	}

	@Valid
	@OneToMany(mappedBy = "person")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Valid
	@ManyToMany(mappedBy = "followings")
	public Collection<Person> getFollowers() {
		return followers;
	}

	public void setFollowers(Collection<Person> followers) {
		this.followers = followers;
	}

	@Valid
	@OneToMany(mappedBy="person")
	public Collection<Taste> getTastes() {
		return tastes;
	}

	public void setTastes(Collection<Taste> tastes) {
		this.tastes = tastes;
	}
}
