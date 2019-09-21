
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Contact {

	//Attributes**********************************************************
	private String	name;
	private String	email;
	private String	phone;


	//Constructor*********************************************************
	public Contact() {
		super();
	}
	//Getters & Setters***************************************************
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	@NotNull
	@Email
	@Valid
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Pattern(regexp = "^(\\+\\d{1,3}\\ (\\(\\d{1,3}\\)\\ )?)?(\\d{4,})|.\\d+$")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

}
