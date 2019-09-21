package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SpamTermRepository;
import domain.Actor;
import domain.Administrator;
import domain.SpamTerm;

@Service
@Transactional
public class SpamTermService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private SpamTermRepository spamTermRepository;
	
	//Supported Services =============================================================================

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private AdministratorService administratorService;

	//Constructor methods ============================================================================
	
	public SpamTermService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public SpamTerm findOne(int spamTermId){
		SpamTerm result;
		
		result = spamTermRepository.findOne(spamTermId);
		
		return result;
	}
	
	public Collection<SpamTerm> findAll() {
		Actor principal;
		Collection<SpamTerm> result;
		
		principal = actorService.findByPrincipal();
		Assert.notNull(principal);
		
		result = spamTermRepository.findAll();
		
		return result;
	}
	
	public SpamTerm create(){		
		SpamTerm result;
		Administrator principal;
		Collection<String> keywords;
		
		result = new SpamTerm();
		keywords = new HashSet<String>();
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
						
		result.setKeywords(keywords);
		
		return result;
	}
	
	public SpamTerm save(SpamTerm spamTerm){
		Assert.notNull(spamTerm);
		
		SpamTerm result;
		Administrator principal;
		
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		
		result = spamTermRepository.save(spamTerm);
		
		return result;
	}
	
	//Other Business Methods =========================================================================

	public Collection<String> findAllKeywords(SpamTerm spamTerm){
		Collection<String> result;
		
		result = spamTerm.getKeywords();
		
		return result;
	}
}
