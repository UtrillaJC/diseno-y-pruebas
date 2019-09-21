
package forms;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import utilities.validators.NotFalse;
import utilities.validators.PasswordMatches;

@PasswordMatches
public class RegisterForm {

	//Attributes=====================================================================================

	private String	username;
	private String	password;
	private String	verifyPassword;
	private String	name;
	private String	email;
	private String	phone;

	private Boolean	contractAccepted;


	//Getters & setters================================================================================

	@NotBlank
	@Size(min = 5, max = 32)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@NotBlank
	@Size(min = 5, max = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@NotBlank
	@Size(min = 5, max = 32)
	public String getVerifyPassword() {
		return this.verifyPassword;
	}

	public void setVerifyPassword(final String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return this.email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}

	@NotBlank
	@Pattern(regexp = "^([+]\\d+\\s)?\\d+$")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	@NotFalse
	public Boolean getContractAccepted() {
		return this.contractAccepted;
	}

	public void setContractAccepted(final Boolean contractAccepted) {
		this.contractAccepted = contractAccepted;
	}
}
