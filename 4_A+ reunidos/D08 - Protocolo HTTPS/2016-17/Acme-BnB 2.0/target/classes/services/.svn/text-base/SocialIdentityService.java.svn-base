package services;

import java.util.Collection;

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
	
	public SocialIdentity create(){
		;
		SocialIdentity result;
		Actor principal;
		principal = actorService.findByPrincipal();
			
		result = new SocialIdentity();
		result.setActor(principal);
				
		return result;
	}
	
	public SocialIdentity save(SocialIdentity socialIdentity){
		Assert.notNull(socialIdentity);
		SocialIdentity result;
		
		result = socialIdentityRepository.save(socialIdentity);
		
		return result;
	}
	
	
	public void delete(SocialIdentity s){
		Assert.isTrue(actorService.findByPrincipal().equals(s.getActor()));
		socialIdentityRepository.delete(s);
	}
	
	//Other Business Methods =========================================================================
	
	
	public SocialIdentity findOne(int id) {
		return socialIdentityRepository.findOne(id);
	}
	
	
	public void assignSocialIdentityToActor(SocialIdentity si){
		SocialIdentity si2 = save(si);
		Actor actor = actorService.findByPrincipal();
		Assert.notNull(actor);
		Collection<SocialIdentity> sis = actor.getSocialIdentities();
		sis.add(si2);
		actor.setSocialIdentities(sis);
		actorService.update(actor);
	}
	
	public void deleteSocialIdentityToActor(Integer idSI){
		Actor actor = actorService.findByPrincipal();
		Assert.notNull(actor);
		Collection<SocialIdentity> sis = findAllSocialIdentityByActor(actor.getId());
		
		for(SocialIdentity si: sis){
			if(si.getId()==new Integer(idSI)){
				sis.remove(si);
				break;
			}
		}
		
		actor.setSocialIdentities(sis);
		actorService.update(actor);
		
		SocialIdentity si = findOne(new Integer(idSI));
		
		delete(si);
	}

	public Collection<SocialIdentity> findAllSocialIdentityByActor(int id) {
		return socialIdentityRepository.findAllSocialIdentityByActor(id);
	}
	
	
}
