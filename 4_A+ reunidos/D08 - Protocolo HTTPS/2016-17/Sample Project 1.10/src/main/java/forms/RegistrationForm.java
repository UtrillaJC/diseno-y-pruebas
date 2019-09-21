package forms;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import utilities.validators.NotFalse;
import utilities.validators.PasswordMatches;

@PasswordMatches
public class RegistrationForm {
	
	private String username;
	private String password;
	private String verifyPassword;
		
	private Boolean contractAccepted;
	
	@Size(min = 5, max = 32)
	@Column(unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Size(min = 5, max = 32)
	public String getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

	
	@NotFalse
	public Boolean getContractAccepted() {
		return contractAccepted;
	}

	public void setContractAccepted(Boolean contractAccepted) {
		this.contractAccepted = contractAccepted;
	}
	

}
