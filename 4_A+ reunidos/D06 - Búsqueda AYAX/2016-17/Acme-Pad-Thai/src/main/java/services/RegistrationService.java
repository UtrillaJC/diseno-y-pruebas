package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RegistrationRepository;
import domain.Contest;
import domain.Registration;
import domain.User;

@Service
@Transactional
public class RegistrationService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private UserService userService;
	
	//Constructor methods ============================================================================
	
	public RegistrationService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Collection<Registration> findAll(){
		Collection<Registration> result;
		
		result = registrationRepository.findAll();
		
		return result;
	}
	
	public Registration create(Contest contest){
		Assert.notNull(contest);
		Registration result;
		Date  moment;
		
		result = new Registration();
		moment = new Date(System.currentTimeMillis());
		
		result.setMoment(moment);
		result.setContest(contest);
		contest.getRegistrations().add(result);
		
		return result;
	}
	
	public Registration save(Registration registration){
		Assert.notNull(registration);
		Assert.isTrue(registration.getRecipe().getLikes() >= 5 && registration.getRecipe().getDislikes() == 0);
		Assert.notNull(registration.getContest());
		Assert.notNull(registration.getRecipe());
		Registration result;
		User principal;
		Date moment;	
		
		principal = userService.findByPrincipal();
		Assert.isTrue(principal.equals(registration.getRecipe().getUser()));
		moment = new Date(System.currentTimeMillis()-1000);
		Assert.isTrue(moment.after(registration.getContest().getMomentOpening()) && moment.before(registration.getContest().getMomentClosing()) || moment.equals(registration.getContest().getMomentOpening()) || moment.equals(registration.getContest().getMomentClosing()));
		
		registration.setMoment(moment);
		
		result = registrationRepository.save(registration);
		
		return result;	
	}
	
	public void delete(Registration registration){
		Assert.notNull(registration);
		registrationRepository.delete(registration);
	}
	
	//Other Business Methods =========================================================================
	
	public Collection<Registration> findAllByUser(User user){
		Assert.notNull(user);
		Collection<Registration> result;
		User principal;
		
		principal = userService.findByPrincipal();
		Assert.isTrue(user.equals(principal));
		
		result = registrationRepository.findAllByUser(user.getId());
		
		return result;
	}
}
