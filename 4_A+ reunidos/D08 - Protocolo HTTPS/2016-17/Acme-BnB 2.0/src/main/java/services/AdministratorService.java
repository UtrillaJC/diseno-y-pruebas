
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {

	//Managed Repository =============================================================================

	@Autowired
	private AdministratorRepository administratorRepository;

	//Supported Services =============================================================================


	//Constructor methods ============================================================================

	public AdministratorService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	public Administrator create() {
		Administrator result;
		UserAccount userAccount;
		Authority authority;

		authority = new Authority();
		userAccount = new UserAccount();

		authority.setAuthority("ADMINISTRATOR");
		userAccount.addAuthority(authority);

		result = new Administrator();

		result.setUserAccount(userAccount);

		return result;
	}

	public Administrator save(Administrator administrator) {
		Assert.notNull(administrator);
		Assert.notNull(administrator.getUserAccount());
		Administrator result;

		result = administratorRepository.save(administrator);

		return result;
	}

	public Administrator saveProfile(Administrator administrator) {
		Assert.notNull(administrator);
		Administrator result;

		result = administratorRepository.save(administrator);

		return result;
	}

	public Administrator update(Administrator administrator) {
		Assert.notNull(administrator);

		Administrator result;

		result = administratorRepository.saveAndFlush(administrator);

		return result;

	}
	//Other Business Methods =========================================================================

	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Administrator findByUserAccount(UserAccount userAccount) {
		Administrator result;

		result = administratorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public void checkPrincipal() {
		UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		Authority auth = new Authority();
		auth.setAuthority("ADMINISTRATOR");

		Assert.isTrue(authorities.contains(auth));

	}
}
