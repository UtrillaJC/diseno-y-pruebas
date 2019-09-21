
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Folder {

	//Attributes*******************************************************************************
	private String	name;
	private boolean	predefined;


	//Constructor*********************************************************************************
	public Folder() {
		super();

		this.childrens = new ArrayList<Folder>();
	}

	//Getters & Setters***************************************************************************
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean getPredefined() {
		return this.predefined;
	}

	public void setPredefined(final boolean predefined) {
		this.predefined = predefined;
	}


	//RelashionShips**********************************************************************

	private Folder				parent;
	private Collection<Folder>	childrens;
	private Actor				actor;
	private Collection<Message>	message;


	//@ManyToMany
	@Valid
	@NotNull
	public Folder getParent() {
		return this.parent;
	}

	public void setParent(final Folder parent) {
		this.parent = parent;
	}
	//@ManyToMany
	@Valid
	@NotNull
	public Collection<Folder> getChildrens() {
		return this.childrens;
	}

	public void setChild(final Collection<Folder> childrens) {
		this.childrens = childrens;
	}
	@NotNull
	@Valid
	//@ManyToOne
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}
	//@OneToMany
	@Valid
	public Collection<Message> getMessage() {
		return this.message;
	}

	public void setMessage(final Collection<Message> message) {
		this.message = message;
	}
}
