
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	//Managed Repository =============================================================================

	@Autowired
	private ActorRepository	actorRepository;


	//Supported Services =============================================================================

	//Constructor methods ============================================================================	

	public ActorService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = this.actorRepository.findAll();

		return result;
	}

	public Actor update(final Actor actor) {
		return this.actorRepository.save(actor);

	}

	public Actor findOne(final int actorId) {
		Actor result;

		result = this.actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		Actor result;

		result = this.actorRepository.save(actor);

		return result;
	}

	//Other Business Methods =========================================================================

	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Actor result;

		result = this.actorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Actor findByUsername(final String username) {
		Assert.notNull(username);
		Actor result;

		result = this.actorRepository.findByUsername(username);

		return result;
	}

	public Collection<Actor> findAllExceptMe() {
		Collection<Actor> result;

		result = this.findAll();
		result.remove(this.findByPrincipal());

		return result;
	}

}
