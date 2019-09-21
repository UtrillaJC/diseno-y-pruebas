package services;


import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StepToCookRepository;
import domain.Administrator;
import domain.Recipe;
import domain.Registration;
import domain.StepToCook;
import domain.User;


@Service
@Transactional
public class StepToCookService {

	//Managed Repository =============================================================================
	
	@Autowired
	private StepToCookRepository stepToCookRepository;
	
	//Supported Services =============================================================================	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdministratorService administratorService;
	
	//Constructor methods ============================================================================
	
	public StepToCookService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Collection<StepToCook> findAll(){
		Collection<StepToCook> result;
		
		result = stepToCookRepository.findAll();
		
		return result;
	}
	
	public StepToCook create(Recipe recipe){
		Assert.notNull(recipe);
		StepToCook result;
		
		result = new StepToCook();
		
		result.setRecipe(recipe);
	
		return result;
	}
	
	public StepToCook save(StepToCook stepToCook){
		Assert.notNull(stepToCook);
		Assert.notNull(stepToCook.getRecipe().getUser());
		StepToCook result;
		User principal;
		int number;
		Collection<StepToCook> stepToCooks;
		Date moment;
		
		stepToCooks = stepToCook.getRecipe().getStepsToCook();
		number = stepToCooks.size()+1;
		moment = new Date(System.currentTimeMillis() - 1000);

		for(Registration r : stepToCook.getRecipe().getRegistrations()){
			Assert.isTrue(moment.before(r.getContest().getMomentOpening()) || moment.after(r.getContest().getMomentClosing()));
		}
		
		principal = userService.findByPrincipal();
		Assert.isTrue(principal.equals(stepToCook.getRecipe().getUser()));
		
		stepToCook.setNumber(number);
		
		result = stepToCookRepository.save(stepToCook);
		
		return result;
		
	}
	
	//Other Business Methods =========================================================================

	//Dashboard =============================================================================

	public Double avgStepsRecipe(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = stepToCookRepository.avgStepsRecipe();
		
		return result;
	}
	
	public Double stddevStepsRecipe(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = stepToCookRepository.stddevStepsRecipe();
		
		return result;
	}
	
}
