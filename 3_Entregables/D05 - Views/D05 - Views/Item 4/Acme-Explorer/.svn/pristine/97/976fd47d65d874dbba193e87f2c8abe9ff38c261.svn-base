
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActorRepository	actorRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ActorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Actor findOne(final int actorId) {

		Actor result = null;
		result = this.actorRepository.findOne(actorId);
		return result;
	}

	public Collection<Actor> findAll() {

		Collection<Actor> result = null;
		result = this.actorRepository.findAll();
		return result;
	}

	public Actor save(final Actor actor) {

		Actor result = null;
		result = this.actorRepository.save(actor);
		return result;
	}

	public void delete(final Actor actor) {

		this.actorRepository.delete(actor);
	}

	// Other business methods -------------------------------------------------

	public Actor findByPrincipal() {

		Actor result = null;
		final UserAccount userAccount = LoginService.getPrincipal();
		result = this.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Actor findByUserAccountId(final int userAccountId) {

		Actor result = null;
		result = this.actorRepository.findByUserAccountId(userAccountId);
		return result;
	}

}
