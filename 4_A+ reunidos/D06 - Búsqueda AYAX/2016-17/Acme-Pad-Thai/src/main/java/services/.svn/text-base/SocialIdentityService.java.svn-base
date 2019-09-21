package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SocialIdentityRepository;
import domain.Actor;
import domain.SocialIdentity;

@Service
@Transactional
public class SocialIdentityService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private SocialIdentityRepository socialIdentityRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private ActorService actorService;
	
	//Constructor methods ============================================================================
	
	public SocialIdentityService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public SocialIdentity create(Actor actor){
		Assert.notNull(actor);
		SocialIdentity result;
		Actor principal;
		
		principal = actorService.findByPrincipal();
		Assert.isTrue(principal.equals(actor));
		result = new SocialIdentity();
		
		actor.setSocialIdentity(result);
		
		return result;
	}
	
	public SocialIdentity save(SocialIdentity socialIdentity){
		Assert.notNull(socialIdentity);
		SocialIdentity result;
		
		result = socialIdentityRepository.save(socialIdentity);
		
		return result;
	}
	
	//Other Business Methods =========================================================================

}
