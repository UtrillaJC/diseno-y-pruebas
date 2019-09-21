package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.IngredientLineRepository;
import domain.IngredientLine;
import domain.Nutritionist;
import domain.Recipe;
import domain.Registration;
import domain.User;

@Service
@Transactional
public class IngredientLineService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private IngredientLineRepository ingredientLineRepository;
	
	//Supported Services =============================================================================

	@Autowired
	private NutritionistService nutritionistService;
	
	@Autowired
	private UserService userService;
	
	//Constructor methods ============================================================================
	
	public IngredientLineService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public IngredientLine findOne(int ingredientLineId){
		IngredientLine result;
		
		result = ingredientLineRepository.findOne(ingredientLineId);
		
		return result;
	}
	
	public Collection<IngredientLine> findAll(){
		Collection<IngredientLine> result;
		
		result = ingredientLineRepository.findAll();
		
		return result;
	}
	
	public IngredientLine create(Recipe recipe){
		Assert.notNull(recipe);
		IngredientLine result;
		User principal;

		principal = userService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(recipe.getUser().equals(principal));
		
		result = new IngredientLine();
		
		result.setRecipe(recipe);
		
		return result;
	}
	
	public IngredientLine save(IngredientLine ingredientLine){
		Assert.notNull(ingredientLine);
		Assert.isTrue(ingredientLine.getUnit().toUpperCase().equals("GRAMS") || ingredientLine.getUnit().toUpperCase().equals("KILOGRAMS") || ingredientLine.getUnit().toUpperCase().equals("OUNCES") || ingredientLine.getUnit().toUpperCase().equals("POUNDS") || ingredientLine.getUnit().toUpperCase().equals("MILILITRES") || ingredientLine.getUnit().toUpperCase().equals("LITRES") || ingredientLine.getUnit().toUpperCase().equals("SPOONS") || ingredientLine.getUnit().toUpperCase().equals("CUPS") || ingredientLine.getUnit().toUpperCase().equals("PIECES"));
		IngredientLine result;
		User principal;
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1000);

		for(Registration r : ingredientLine.getRecipe().getRegistrations()){
			Assert.isTrue(moment.before(r.getContest().getMomentOpening()) || moment.after(r.getContest().getMomentClosing()));
		}
		
		principal = userService.findByPrincipal();
		Assert.notNull(principal);
				
		result = ingredientLineRepository.save(ingredientLine);
		
		return result;
	}
	
	public void delete(IngredientLine ingredientLine){
		Assert.notNull(ingredientLine);
		Nutritionist principal;

		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Nutritionist.class, principal);
		
		ingredientLineRepository.delete(ingredientLine);
	}
	//Other Business Methods =========================================================================
	
	public Collection<IngredientLine> findAllByRecipe(Recipe recipe){
		Collection<IngredientLine> result;
		
		result = ingredientLineRepository.findAllByRecipeId(recipe.getId());
		
		return result;
	}
}
