
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public SocialIdentity() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private String	nick;
	private String	nameSocialNetwork;
	private String	link;
	private String	photo;


	@NotBlank
	public String getNick() {
		return this.nick;
	}

	public void setNick(final String nick) {
		this.nick = nick;
	}

	@NotBlank
	public String getNameSocialNetwork() {
		return this.nameSocialNetwork;
	}

	public void setNameSocialNetwork(final String nameSocialNetwork) {
		this.nameSocialNetwork = nameSocialNetwork;
	}

	@NotBlank
	@URL
	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}


	// Relationships ----------------------------------------------------------

	private Actor	actor;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}

}
