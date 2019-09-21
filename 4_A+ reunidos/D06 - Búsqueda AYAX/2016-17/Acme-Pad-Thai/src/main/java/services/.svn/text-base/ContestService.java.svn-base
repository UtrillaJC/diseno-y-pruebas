package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ContestRepository;
import domain.Administrator;
import domain.Contest;
import domain.Recipe;
import domain.Registration;

@Service
@Transactional
public class ContestService {
	
	//Managed Repository =============================================================================
		
	@Autowired
	private ContestRepository contestRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private AdministratorService administratorService;
	
	//Constructor methods ============================================================================
	public ContestService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Contest findOne(int contestId){
		Contest result;
		
		result = contestRepository.findOne(contestId);
		
		return result;
	}
	
	public Collection<Contest> findAll(){
		Collection<Contest> result;

		result = contestRepository.findAll();
		
		return result;
	}
	
	public Contest create(){
		Contest result;
		Administrator principal;
		Collection<Registration> registrations;
		Collection<Recipe> winners;

		
		principal = administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		registrations = new HashSet<Registration>();
		winners = new HashSet<Recipe>();

		result = new Contest();
		
		result.setRegistrations(registrations);
		result.setWinners(winners);

		return result;
	}
	
	public Contest save(Contest contest){
		Assert.notNull(contest);
		Contest result;
		Administrator principal;
		Date momentActual;
	    
	    momentActual = new Date(System.currentTimeMillis() -1000);

	    Assert.isTrue(momentActual.before(contest.getMomentClosing()));
	    Assert.isTrue(contest.getMomentOpening().before(contest.getMomentClosing()));
	    
		principal = administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		
		result = contestRepository.save(contest);
		
		return result;
	}
	
	public void delete(Contest contest){
		Assert.notNull(contest);
		Assert.isTrue(contest.getRegistrations().isEmpty());
		contestRepository.delete(contest);
	}
	
	//Other Business Methods =========================================================================
	
	public Collection<Contest> findAllActive(){
		Collection<Contest> result;
		
		result = contestRepository.findAllActive();
		
		return result;
	}
	
	public Collection<Contest> findAllFinished(){
		Collection<Contest> result;
		
		result = contestRepository.findAllFinished();
		
		return result;
	}
	
	//Dashboard =============================================================================
	
	public Collection<Contest> contestswhichMoreRecipesQualified(){
		Collection<Contest> result;
		
		result = contestRepository.contestswhichMoreRecipesQualified();
		
		return result;
	}
}
