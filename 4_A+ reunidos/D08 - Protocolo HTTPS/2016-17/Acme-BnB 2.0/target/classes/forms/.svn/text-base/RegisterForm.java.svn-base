
package forms;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import utilities.validators.NotFalse;
import utilities.validators.PasswordMatches;

@PasswordMatches
public class RegisterForm {

	//Attributes=====================================================================================

	private String	username;
	private String	password;
	private String	verifyPassword;
	private String	name;
	private String	surname;
	private String	email;
	private String	phone;
	private String	picture;

	private Boolean	contractAccepted;


	//Getters & setters================================================================================

	@NotBlank
	@SafeHtml
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotBlank
	@SafeHtml
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotBlank
	@SafeHtml
	public String getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

	@NotBlank
	@SafeHtml
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@NotBlank
	@SafeHtml
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	@SafeHtml
	@Pattern(regexp = "^[+]\\d+\\s\\d+$")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@NotBlank
	@SafeHtml
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@NotFalse
	public Boolean getContractAccepted() {
		return contractAccepted;
	}

	public void setContractAccepted(Boolean contractAccepted) {
		this.contractAccepted = contractAccepted;
	}
}
