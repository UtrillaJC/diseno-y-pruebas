package services;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class IngredientServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
//	/*@Autowired
//	private IngredientService ingredientService;
//	
//	@Autowired
//	private IngredientLineService ingredientLineService;
//	
//	@Autowired
//	private PropertyService propertyService;
//		
//	// Tests =========================================================================================
//	
//	/**
//	* ################################################################
//	*
//	* TEST POSITIVOS
//	*
//	* #################################################################
//	*/
//	
//	@Test
//	public void testCreate(){
//		System.out.println("-----------------------------Create-----------------------------");
//		authenticate("nutritionist1");
//		Ingredient ingredient;
//		
//		IngredientLine ingredientLine;
//		ingredientLine = ingredientLineService.findOne(144);
//		
//		ingredient = ingredientService.create(ingredientLine);
//		System.out.println("Name: " + ingredient.getName());
//		System.out.println("Description: " + ingredient.getDescription());
//		System.out.println("Picture: " + ingredient.getPicture());
//		
//		authenticate(null);
//		System.out.println("----------------------------------------------------------------");
//	}
//	
//	@Test
//	public void testSave(){
//		System.out.println("-----------------------------Save-----------------------------");
//		authenticate("nutritionist1");
//		Ingredient ingredient;
//		IngredientLine ingredientLine;
//		Property property;
//		
//		ingredientLine = ingredientLineService.findOne(144);
//		ingredient = ingredientService.create(ingredientLine);
//		property = propertyService.findOne(120);
//		
//		ingredient.setName("Ingredient Test");
//		ingredient.setDescription("Description Test");
//		ingredient.setPicture("www.picturetest.com");
//		ingredient.getProperties().add(property);
//				
//		ingredient = ingredientService.save(ingredient);
//		
//		System.out.println("Name: " + ingredient.getName());
//		System.out.println("Description: " + ingredient.getDescription());
//		System.out.println("Picture: " + ingredient.getPicture());
//		System.out.println("Unit: " + ingredient.getIngredientLine().getUnit());
//		System.out.println("Amount: " + ingredient.getIngredientLine().getMultiplicity());
//
//		authenticate(null);
//		System.out.println("----------------------------------------------------------------");
//	}
//	
//	@Test
//	public void testDelete(){
//		System.out.println("----------------------------Delete------------------------------");
//		authenticate("nutritionist1");
//		Ingredient ingredient;
//		IngredientLine ingredientLine;
//		Property property;
//		Collection<Property> properties;
//		
//		ingredientLine = ingredientLineService.findOne(144);
//		ingredient = ingredientService.create(ingredientLine);
//		property = propertyService.findOne(120);
//		properties = new HashSet<Property>();
//		
//		properties.add(property);
//		
//		ingredient.setName("Ingredient Test");
//		ingredient.setDescription("Description Test");
//		ingredient.setPicture("www.picturetest.com");
//		ingredient.setProperties(properties);
//		ingredient.getIngredientLine().setRecipe(null);
//				
//		ingredient = ingredientService.save(ingredient);
//		
//		System.out.println("Name: " + ingredient.getName());
//		System.out.println("Description: " + ingredient.getDescription());
//		System.out.println("Picture: " + ingredient.getPicture());
//		System.out.println("Unit: " + ingredient.getIngredientLine().getUnit());
//		System.out.println("Amount: " + ingredient.getIngredientLine().getMultiplicity());
//
//		ingredient = ingredientService.save(ingredient);
//				
//		ingredientService.delete(ingredient);
//		
//		authenticate(null);
//
//		System.out.println("----------------------------------------------------------------");
//	}
//	
//	
//	/**
//	* ################################################################
//	*
//	* TEST NEGATIVOS
//	*
//	* #################################################################
//	*/
//
//	@Test
//	public void testCreateNegative(){
//		System.out.println("-----------------------------Create Negative-----------------------------");
//		try{
//			authenticate("user1");
//			Ingredient ingredient;
//			
//			IngredientLine ingredientLine;
//			ingredientLine = ingredientLineService.findOne(144);
//			
//			ingredient = ingredientService.create(ingredientLine);
//			System.out.println("Name: " + ingredient.getName());
//			System.out.println("Description: " + ingredient.getDescription());
//			System.out.println("Picture: " + ingredient.getPicture());
//			
//			authenticate(null);
//			
//		}catch (IllegalArgumentException  exception) {
//			System.out.println("El usuario logueado no puede hacer esta operación"); 
//			}
//		finally{
//			System.out.println("----------------------------------------------------------------"); 
//			}
//	}
//	
//	@Test
//	public void testSaveNegative(){
//		System.out.println("-----------------------------Save Negative-----------------------------");
//		try{
//			authenticate("user1");
//			Ingredient ingredient;
//			IngredientLine ingredientLine;
//			Property property;
//			Collection<Property> properties;
//			
//			ingredientLine = ingredientLineService.findOne(144);
//			ingredient = ingredientService.create(ingredientLine);
//			property = propertyService.findOne(120);
//			properties = new HashSet<Property>();
//			
//			properties.add(property);
//			
//			ingredient.setName("Ingredient Test");
//			ingredient.setDescription("Description Test");
//			ingredient.setPicture("www.picturetest.com");
//			ingredient.setProperties(properties);
//					
//			ingredient = ingredientService.save(ingredient);
//			
//			System.out.println("Name: " + ingredient.getName());
//			System.out.println("Description: " + ingredient.getDescription());
//			System.out.println("Picture: " + ingredient.getPicture());
//			System.out.println("Unit: " + ingredient.getIngredientLine().getUnit());
//			System.out.println("Amount: " + ingredient.getIngredientLine().getMultiplicity());
//	
//			authenticate(null);
//			
//		}catch (IllegalArgumentException  exception) {
//			System.out.println("El usuario logueado no puede hacer esta operación"); 
//			}
//		finally{
//			System.out.println("----------------------------------------------------------------"); 
//			}
//	}
//	
//	@Test
//	public void testDeleteNegative(){
//		System.out.println("----------------------------Delete Negatve------------------------------");
//		
//		try{
//			authenticate("user1");
//			Ingredient ingredient;
//			IngredientLine ingredientLine;
//			Property property;
//			Collection<Property> properties;
//			
//			ingredientLine = ingredientLineService.findOne(144);
//			ingredient = ingredientService.create(ingredientLine);
//			property = propertyService.findOne(120);
//			properties = new HashSet<Property>();
//			
//			properties.add(property);
//			
//			ingredient.setName("Ingredient Test");
//			ingredient.setDescription("Description Test");
//			ingredient.setPicture("www.picturetest.com");
//			ingredient.setProperties(properties);
//			ingredient.getIngredientLine().setRecipe(null);
//					
//			ingredient = ingredientService.save(ingredient);
//			
//			System.out.println("Name: " + ingredient.getName());
//			System.out.println("Description: " + ingredient.getDescription());
//			System.out.println("Picture: " + ingredient.getPicture());
//			System.out.println("Unit: " + ingredient.getIngredientLine().getUnit());
//			System.out.println("Amount: " + ingredient.getIngredientLine().getMultiplicity());
//	
//			ingredient = ingredientService.save(ingredient);
//					
//			ingredientService.delete(ingredient);
//			
//			authenticate(null);
//
//		}catch (IllegalArgumentException  exception) {
//			System.out.println("El usuario logueado no puede hacer esta operación"); 
//			}
//		finally{
//			System.out.println("----------------------------------------------------------------"); 
//			}
//	}
//	
//	/**
//	* ################################################################
//	*
//	* DASHBOARD
//	*
//	* #################################################################
//	*/
//	
//	@Test
//	public void testStddevIngredientsRecipe() {
//		System.out.println("----------------------------Stddev Ingredients Recipe------------------------------");		
//		authenticate("admin");
//		Double result;
//		
//		result = ingredientService.stddevIngredientsRecipe();
//	
//		System.out.println("Stddev Ingredients Recipe: " + result);
//	}
//	
//	@Test
//	public void testAvgIngredientsRecipe() {
//		System.out.println("----------------------------Avg Ingredients Recipe------------------------------");		
//		authenticate("admin");
//		Double result;
//		
//		result = ingredientService.avgIngredientsRecipe();
//	
//		System.out.println("Avg Ingredients Recipe: " + result);
//	}*/
	
	
	
	
}