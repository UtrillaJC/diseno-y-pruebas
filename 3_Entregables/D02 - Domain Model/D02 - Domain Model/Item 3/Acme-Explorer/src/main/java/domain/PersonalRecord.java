
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class PersonalRecord extends DomainEntity {

	//Attributes
	private String	fullName;
	private String	photo;
	private String	email;
	private String	phone;
	private String	link;


	//Constructors
	public PersonalRecord() {
		super();
	}

	//Getters

	@NotBlank
	public String getfullName() {
		return this.fullName;
	}

	@URL
	public String getphoto() {
		return this.photo;
	}

	@Email
	public String getEmail() {
		return this.email;
	}

	@Pattern(regexp = "\\+\\d{1,3}\\ (\\(\\d{1,3}\\)\\ )?)?(\\d{4,2}$")
	public String getPhone() {
		return this.phone;
	}

	@URL
	public String getLink() {
		return this.link;
	}

	//Setters
	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public void setLink(final String link) {
		this.link = link;
	}

}
