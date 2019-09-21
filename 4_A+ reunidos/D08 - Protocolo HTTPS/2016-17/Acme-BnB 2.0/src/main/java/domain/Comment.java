
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
	private Date	moment;
	private String	text;
	private int		stars;
	private Actor	author;
	private Actor	receiver;


	//Getters & setters================================================================================
	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Range(min = 0, max = 5)
	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
	// Relationships ====================================================================================

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getAuthor() {
		return author;
	}

	public void setAuthor(Actor author) {
		this.author = author;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getReceiver() {
		return receiver;
	}

	public void setReceiver(Actor receiver) {
		this.receiver = receiver;
	}

}
