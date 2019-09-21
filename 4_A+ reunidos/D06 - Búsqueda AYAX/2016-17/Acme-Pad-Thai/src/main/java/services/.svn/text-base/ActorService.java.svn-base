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
	private ActorRepository actorRepository;
	
	//Supported Services =============================================================================
	
	//Constructor methods ============================================================================	

	public ActorService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Collection<Actor> findAll(){
		Collection<Actor> result;
		
		result = actorRepository.findAll();
		
		return result;
	}
	
	public Actor save(Actor actor){
		Assert.notNull(actor);
		Actor result;
		
		result = actorRepository.save(actor);
		
		return result;
	}
	
	//Other Business Methods =========================================================================
	
	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Actor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Actor result;

		result = actorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Actor findByUsername(String username) {
		Assert.notNull(username);
		Actor result;
		
		result = actorRepository.findByUsername(username);
		
		return result;
	}
}
