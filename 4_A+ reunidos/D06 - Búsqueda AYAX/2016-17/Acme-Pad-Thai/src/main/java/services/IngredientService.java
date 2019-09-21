package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.IngredientRepository;
import domain.Administrator;
import domain.Ingredient;
import domain.IngredientLine;
import domain.Nutritionist;
import domain.Property;
import domain.Recipe;

@Service
@Transactional
public class IngredientService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private IngredientRepository ingredientRepository;
		
	//Supported Services =============================================================================
		
	@Autowired
	private NutritionistService nutritionistService;
		
	@Autowired
	private AdministratorService administratorService;
	
	//Constructor methods ============================================================================
		
	public IngredientService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Ingredient findOne(int ingredientId){
		Ingredient result;
		
		result = ingredientRepository.findOne(ingredientId);
		
		return result;
	}
	
	public Collection<Ingredient> findAll(){
		Collection<Ingredient> result;
		
		result = ingredientRepository.findAll();
		
		return result;
	}
	
	public Ingredient create(){
		Ingredient result;
		Nutritionist principal;
		Collection<Property> properties;
		Collection<IngredientLine> ingredientLines;
		
		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Nutritionist.class, principal);
		properties = new HashSet<Property>();
		ingredientLines = new HashSet<IngredientLine>();
			
		result = new Ingredient();

		result.setProperties(properties);
		result.setIngredientLines(ingredientLines);
		
		return result;
	}
	
	public Ingredient save(Ingredient ingredient){
		Assert.notNull(ingredient);
		Ingredient result;
		Nutritionist principal;
				
		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Nutritionist.class, principal);
		
        result = ingredientRepository.save(ingredient);
		
		return result;
	}
	
	public void delete(Ingredient ingredient){
		Assert.notNull(ingredient);		
		Nutritionist principal;
		
		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Nutritionist.class, principal);
		
		Assert.isTrue(ingredient.getIngredientLines().isEmpty());	

		ingredientRepository.delete(ingredient);		
	}
	
	//Other Business Methods =========================================================================

	public Collection<Ingredient> findAllByRecipe(Recipe recipe){
		Assert.notNull(recipe);
		Collection<Ingredient> result;
		
		result = ingredientRepository.findAllByRecipeId(recipe.getId());
		
		return result;
	}
	
	public void addProperty(Ingredient ingredient, Property property){
		ingredient.getProperties().add(property);
		property.getIngredients().add(ingredient);
		
		ingredientRepository.save(ingredient);
	}
	
	//Dashboard =============================================================================

	public Double stddevIngredientsRecipe(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = ingredientRepository.sttdevIngredientsRecipe();
		
		return result;
	}
	
	public Double avgIngredientsRecipe(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = ingredientRepository.avgIngredientsRecipe();
		
		return result;
	}
}
