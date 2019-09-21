
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	//Constructors====================================================================================
	public Comment() {
		super();
	}


	//Attributes=====================================================================================

	private String	title;
	private Date	createdMoment;
	private String	text;
	private int		stars;
	private boolean	banned;
	private int receiverId;

	//Getters & setters================================================================================

	
	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getCreatedMoment() {
		return this.createdMoment;
	}

	public void setCreatedMoment(Date createdMoment) {
		this.createdMoment = createdMoment;
	}

	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText( String text) {
		this.text = text;
	}

	@Range(min = 0, max = 5)
	public int getStars() {
		return this.stars;
	}

	public void setStars( int stars) {
		this.stars = stars;
	}

	public boolean isBanned() {
		return this.banned;
	}

	public void setBanned( boolean banned) {
		this.banned = banned;
	}

	public int getReceiverId() {
		return receiverId;
	}

	
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	// Relationships ====================================================================================

	private Actor			author;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getAuthor() {
		return this.author;
	}

	public void setAuthor(Actor author) {
		this.author = author;
	}


}
