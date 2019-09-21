
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	//Managed Repository =============================================================================

	@Autowired
	private AdministratorRepository	administratorRepository;


	//Supported Services =============================================================================

	//Constructor methods ============================================================================

	public AdministratorService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	//Other Business Methods =========================================================================

	public Administrator findByPrincipal() {
		Administrator result;
		int administratorId;
		
		administratorId = LoginService.getPrincipal().getId();
		Assert.notNull(administratorId);

		result = administratorRepository.findByUserAccountId(administratorId);
		Assert.notNull(result);
		
		return result;
	}

	public Administrator findByUserAccount(final UserAccount userAccount) {
		Administrator result;

		result = this.administratorRepository.findByUserAccountId(userAccount.getId());

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
