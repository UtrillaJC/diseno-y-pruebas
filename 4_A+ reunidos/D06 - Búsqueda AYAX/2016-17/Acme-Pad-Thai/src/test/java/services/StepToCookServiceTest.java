package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Recipe;
import domain.StepToCook;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class StepToCookServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private StepToCookService stepToCookService;
	
	@Autowired
	private RecipeService recipeService;
	
	
	// Tests =========================================================================================

	/**
	* ################################################################
	*
	* TEST POSITIVOS
	*
	* #################################################################
	*/

	@Test
	public void testCreate(){
		System.out.println("-----------------------------Create-----------------------------");
		authenticate("user1");
		
		StepToCook stepToCook;
		Recipe recipe;
		recipe = recipeService.findOne(126);
		
		stepToCook = stepToCookService.create(recipe);
		System.out.println("Number: " + stepToCook.getNumber());
		System.out.println("Description: " + stepToCook.getDescription());
		System.out.println("Picture: " + stepToCook.getPicture());
		System.out.println("Hints: " + stepToCook.getHints());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}

	@Test
	public void testSave(){
		System.out.println("-----------------------------Save-----------------------------");
		authenticate("user1");
		
		StepToCook result;
		Recipe recipe;
		recipe = recipeService.findOne(126);
		result = stepToCookService.create(recipe);
		
		result.setNumber(3);
		result.setDescription("Description Test");
		result.setPicture("https://picturetest.com");

		stepToCookService.save(result);
		
		System.out.println("Number: " + result.getNumber());
		System.out.println("Description: " + result.getDescription());
		System.out.println("Picture: " + result.getPicture());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	/**
	* ################################################################
	*
	* TEST NEGATIVOS
	*
	* #################################################################
	*/
	
	@Test
	public void testCreateNegative(){
		try{
			System.out.println("-----------------------------Create Negative-----------------------------");
			authenticate("admin");
			
			StepToCook stepToCook;
			Recipe recipe;
			recipe = recipeService.findOne(126);
			
			stepToCook = stepToCookService.create(recipe);
			System.out.println("Number: " + stepToCook.getNumber());
			System.out.println("Description: " + stepToCook.getDescription());
			System.out.println("Picture: " + stepToCook.getPicture());
			System.out.println("Hints: " + stepToCook.getHints());
			authenticate(null);
		}
		catch(Exception e){System.out.println("El administrador logueado no puede realizar esta operación"); }
		finally{System.out.println("----------------------------------------------------------------"); }
	}
	
	@Test
	public void testSaveNegative(){
		try {
			System.out.println("-----------------------------Save Negative-----------------------------");
			authenticate("admin");
			
			StepToCook result;
			Recipe recipe;
			recipe = recipeService.findOne(126);
			result = stepToCookService.create(recipe);
			
			result.setNumber(3);
			result.setDescription("Description Test");
			result.setPicture("https://picturetest.com");

			stepToCookService.save(result);
			
			System.out.println("Number: " + result.getNumber());
			System.out.println("Description: " + result.getDescription());
			System.out.println("Picture: " + result.getPicture());

			authenticate(null);
		}
		catch(Exception e){System.out.println("El administrador logueado no puede realizar esta operación"); }
		finally{System.out.println("----------------------------------------------------------------"); }	
	}

	
	/**
	* ################################################################
	*
	* DASHBOARD
	*
	* #################################################################
	*/
	
	@Test
	public void testAvgStepsRecipe() {
		System.out.println("----------------------------Avg Steps Recipe------------------------------");		
		authenticate("admin");
		Double result;
		
		result = stepToCookService.avgStepsRecipe();
	
		System.out.println("Avg Steps Recipe: " + result);
	}
	
	
	@Test
	public void testStddevStepsRecipe() {
		System.out.println("----------------------------Stddev Steps Recipe------------------------------");		
		authenticate("admin");
		Double result;
		
		result = stepToCookService.stddevStepsRecipe();
	
		System.out.println("Stddev Steps Recipe: " + result);
	}
	
}

