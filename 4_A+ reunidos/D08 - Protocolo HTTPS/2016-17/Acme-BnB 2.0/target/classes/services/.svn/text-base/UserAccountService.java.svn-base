
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import forms.RegistrationForm;
import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;

@Service
@Transactional
public class UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;


	public UserAccount create() {
		UserAccount result = new UserAccount();
		Authority auth = new Authority();
		auth.setAuthority("TENANT");
		result.addAuthority(auth);
		return result;
	}

	public void save(UserAccount userAccount) {

		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		String pass = md5PasswordEncoder.encodePassword(userAccount.getPassword(), null);
		userAccount.setPassword(pass);
		userAccountRepository.save(userAccount);
	}

	public UserAccount reconstruct(RegistrationForm form) {

		UserAccount userAccount = create();
		userAccount.setUsername(form.getUsername());
		userAccount.setPassword(form.getPassword());
		return userAccount;
	}
}
