
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Story extends DomainEntity {

	//Attributes
	private String				title;
	private String				text;
	private Collection<String>	attachements;


	//Constructor*********************************************************************
	public Story() {
		super();
		this.attachements = new ArrayList<String>();
	}

	//Getters & Setters****************************************************************

	@NotBlank
	@Valid
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	@Valid
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@URL
	public Collection<String> getAttachements() {
		return this.attachements;
	}

	public void setAttachements(final Collection<String> attachements) {
		this.attachements = attachements;
	}


	//RelationShips**************************************************************
	private Explorer	explorer;
	private Trip		trip;


	@NotNull
	@Valid
	//@ManyToOne
	public Explorer getExplorer() {
		return this.explorer;
	}

	public void setExplorer(final Explorer explorer) {
		this.explorer = explorer;
	}
	@NotNull
	@Valid
	//@ManyToOne
	public Trip getTrip() {
		return this.trip;
	}

	public void setTrip(final Trip trip) {
		this.trip = trip;
	}

}
