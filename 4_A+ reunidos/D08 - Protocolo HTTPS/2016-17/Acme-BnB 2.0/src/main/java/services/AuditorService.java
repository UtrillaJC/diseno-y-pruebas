
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Audit;
import domain.Auditor;
import domain.SocialIdentity;
import forms.AuditorRegistrationForm;
import repositories.AuditorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AuditorService {

	//Managed Repository =============================================================================

	@Autowired
	private AuditorRepository		auditorRepository;

	//Supported Services =============================================================================

	@Autowired
	private AdministratorService	administratorService;


	//Constructor methods ============================================================================

	public AuditorService() {
		super();
	}

	//Simple CRUD methods ============================================================================
	public Auditor create() {
		administratorService.checkPrincipal();

		Auditor result;
		UserAccount userAccount;
		Authority authority;
		Collection<SocialIdentity> socialIdentities;
		Collection<Audit> audits;

		authority = new Authority();
		userAccount = new UserAccount();
		socialIdentities = new HashSet<SocialIdentity>();
		audits = new HashSet<Audit>();

		authority.setAuthority("AUDITOR");
		userAccount.addAuthority(authority);

		result = new Auditor();

		result.setUserAccount(userAccount);
		result.setSocialIdentities(socialIdentities);
		result.setAudits(audits);

		return result;
	}
	public Auditor update(Auditor auditor) {
		Assert.notNull(auditor);

		Auditor result;

		result = auditorRepository.saveAndFlush(auditor);

		return result;

	}

	public Auditor save(Auditor auditor) {
		Assert.notNull(auditor);
		Auditor result;

		String password = auditor.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		auditor.getUserAccount().setPassword(password);

		result = auditorRepository.save(auditor);

		return result;
	}

	public Auditor findOne(int auditorId) {
		Auditor result;

		result = auditorRepository.findOne(auditorId);

		return result;
	}

	public Collection<Auditor> findAll() {
		Collection<Auditor> result;

		result = auditorRepository.findAll();

		return result;
	}

	//Other Business Methods =========================================================================

	public Auditor findByPrincipal() {
		Auditor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Auditor findByUserAccount(UserAccount userAccount) {
		Auditor result;

		result = auditorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public void checkPrincipal() {
		UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		Authority auth = new Authority();
		auth.setAuthority("AUDITOR");

		Assert.isTrue(authorities.contains(auth));

	}

	public Auditor reconstruct(AuditorRegistrationForm form) {
		Auditor auditor;

		auditor = this.create();

		auditor.getUserAccount().setUsername(form.getUsername());
		auditor.getUserAccount().setPassword(form.getPassword());
		auditor.setCompanyName(form.getCompanyName());
		;
		auditor.setName(form.getName());
		auditor.setSurname(form.getSurname());
		auditor.setEmail(form.getEmail());
		auditor.setPhone(form.getPhone());
		auditor.setPicture(form.getPicture());

		return auditor;

	}
}
