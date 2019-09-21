
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends Actor {

	// Attributes
	private String	nick;
	private String	nameSocialNetwork;
	private String	link;
	private String	photo;

	// Relationship
	private Actor	actor;


	// Constructors
	public SocialIdentity() {
		super();
	}

	// Getters

	@NotBlank
	public String getNick() {
		return this.nick;
	}

	@NotBlank
	public String getNameSocialNetwork() {
		return this.nameSocialNetwork;
	}

	@URL
	public String getLink() {
		return this.link;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}

	@NotNull
	@Valid
	//	@ManyToOne(optional = false)
	public Actor getActor() {
		return this.actor;
	}

	// Setters

	public void setNick(final String nick) {
		this.nick = nick;
	}

	public void setNameSocialNetwork(final String nameSocialNetwork) {
		this.nameSocialNetwork = nameSocialNetwork;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

}
