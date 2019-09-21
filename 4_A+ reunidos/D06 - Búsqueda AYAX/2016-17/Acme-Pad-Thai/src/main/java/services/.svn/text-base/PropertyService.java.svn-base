package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PropertyRepository;
import domain.Ingredient;
import domain.Nutritionist;
import domain.Property;

@Service
@Transactional
public class PropertyService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private NutritionistService nutritionistService;
	
	//Constructor methods ============================================================================
	
	public PropertyService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Property findOne(int propertyId){
		Property result;
		
		result = propertyRepository.findOne(propertyId);
		
		return result;
	}
	
	public Collection<Property> findAll(){
		Collection<Property> result;
		
		result = propertyRepository.findAll();
		
		return result;
	}
	
	public Property create(){
		Property result;
		Nutritionist principal;
		
		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Nutritionist.class, principal);
		
		result = new Property();

		return result;	
	}
	
	public Property save(Property property){
		Assert.notNull(property);
		Property result;
		Nutritionist nutritionist;
		Collection<Ingredient> ingredients;
		
		nutritionist = nutritionistService.findByPrincipal();
		Assert.notNull(nutritionist);
		Assert.isInstanceOf(Nutritionist.class, nutritionist);
		ingredients = new HashSet<Ingredient>();
		
		property.setIngredients(ingredients);
			
		result = propertyRepository.save(property);
		
		return result;
	}
	
	public void delete(Property property){
		Assert.notNull(property);
		Assert.isTrue(property.getIngredients().isEmpty());
		
		propertyRepository.delete(property);
	}
	
	//Other Business Methods =========================================================================
	
	public Collection<Property> findAllByNotIngredient(Ingredient ingredient){
		Assert.notNull(ingredient);
		Collection<Property> result;

		result = propertyRepository.findAllByNotIngredientId(ingredient.getId());
		
		return result;
	}
	
	public Collection<Property> findAllByIngredient(Ingredient ingredient){
		Assert.notNull(ingredient);
		Collection<Property> result;
	
		result = propertyRepository.findAllByIngredientId(ingredient.getId());
		
		return result;
	}
}
