package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TasteRepository;
import domain.Person;
import domain.Recipe;
import domain.Taste;

@Service
@Transactional
public class TasteService {
	
	//Managed Repository =============================================================================

	@Autowired
	private TasteRepository tasteRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private PersonService personService;
	
	//Constructor methods ============================================================================
	
	public TasteService(){
		super();
	}
	 
	//Simple CRUD methods ============================================================================

	public Taste create(Recipe recipe){
		Assert.notNull(recipe);
		Person principal;
		Taste result;
		
		result = new Taste();
		principal = personService.findByPrincipal();
		
		result.setPerson(principal);
		result.setRecipe(recipe);
		
		return result;
	}
	
	public Taste save(Taste taste){
		Assert.notNull(taste);
		Assert.notNull(taste.getRecipe());
		Assert.notNull(taste.getPerson());
		Taste result;
		
		result = tasteRepository.save(taste);
		
		return result;
	}
	
	//Other Business Methods =========================================================================
}
