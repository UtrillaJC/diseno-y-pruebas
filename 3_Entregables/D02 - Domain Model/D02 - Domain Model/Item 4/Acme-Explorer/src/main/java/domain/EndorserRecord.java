
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class EndorserRecord extends DomainEntity {

	//Attributes
	private String				fullName;
	private String				email;
	private String				phone;
	private String				link;
	private Collection<String>	comments;


	//Constructor
	public EndorserRecord() {
		super();
		this.comments = new ArrayList<String>();
	}

	//Getters

	@NotBlank
	public String getFullName() {
		return this.fullName;
	}

	@Email
	public String getEmail() {
		return this.email;
	}

	@Pattern(regexp = "^(\\+\\d{1,3}\\ (\\(\\d{1,3}\\)\\ )?)?(\\d{4,})|.\\d+$")
	public String getPhone() {
		return this.phone;
	}

	@URL
	public String getLink() {
		return this.link;
	}

	@NotNull
	public Collection<String> getComments() {
		return this.comments;
	}

	//Setters
	public void setFullName(final String fullName) {
		this.fullName = fullName;
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

	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}
}
