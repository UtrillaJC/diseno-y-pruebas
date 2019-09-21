package services;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Recipe;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class RecipeServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private RecipeService recipeService;
		
	@Autowired
	private UserService userService;
	
	// Tests =========================================================================================
	
	/**
	* ################################################################
	*
	* TEST POSITIVOS
	*
	* #################################################################
	*/

	@Test
	public void testCreate() {
		System.out.println("----------------------------Create------------------------------");		
		authenticate("user1");
	
		Recipe result;
		User user;
		user = userService.findOne(16);
		
		result = recipeService.create(user);
		
		System.out.println("Ticker: " + result.getTicker());
		System.out.println("Title: " + result.getTitle());
		System.out.println("Summary " + result.getSummary());
		System.out.println("Moment Authored: " + result.getMomentAuthored());
		System.out.println("Moment Updated: " + result.getMomentUpdated());
		System.out.println("Pictures: " + result.getPictures());
		System.out.println("Hints: " + result.getHints());
		System.out.println("Likes: " + result.getDislikes());
		System.out.println("Dislikes: " + result.getDislikes());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSave() throws MalformedURLException{
		System.out.println("----------------------------Save------------------------------");
		authenticate("user1");
		
		Recipe result;
		User user;
		URL url;
		
		user = userService.findOne(16);
		
		result = recipeService.create(user);
		
		url = new URL("https://picturestest.com");
		
		result.setTitle("Title Test");
		result.setSummary("Summary Test");
		result.getPictures().add(url);

		recipeService.save(result);
		
		System.out.println("Ticker: " + result.getTicker());
		System.out.println("Title: " + result.getTitle());
		System.out.println("Summary " + result.getSummary());
		System.out.println("Moment Authored: " + result.getMomentAuthored());
		System.out.println("Moment Updated: " + result.getMomentUpdated());
		System.out.println("Pictures: " + result.getPictures().size());
		System.out.println("Likes: " + result.getDislikes());
		System.out.println("Dislikes: " + result.getDislikes());
		
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
	public void testCreateNegative() {
		System.out.println("----------------------------Create Negative------------------------------");		
		try {
			authenticate("user1");
			
			Recipe result;
			User user;
			user = userService.findOne(16);
			
			result = recipeService.create(user);
			
			System.out.println("Ticker: " + result.getTicker());
			System.out.println("Title: " + result.getTitle());
			System.out.println("Summary " + result.getSummary());
			System.out.println("Moment Authored: " + result.getMomentAuthored());
			System.out.println("Moment Updated: " + result.getMomentUpdated());
			System.out.println("Pictures: " + result.getPictures());
			System.out.println("Hints: " + result.getHints());
			System.out.println("Likes: " + result.getDislikes());
			System.out.println("Dislikes: " + result.getDislikes());
			
			authenticate(null);	
		}catch (IllegalArgumentException  exception) {
			System.out.println("El nutricionista logueado no puede realizar esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}

	@Test
	public void testSaveNegative()throws MalformedURLException{
		System.out.println("----------------------------Save------------------------------");		
		try {
			authenticate("user1");
			
			Recipe result;
			User user;
			URL url;
			
			user = userService.findOne(16);
			
			result = recipeService.create(user);
			
			url = new URL("https://picturestest.com");
			
			result.setTitle("Title Test");
			result.setSummary("Summary Test");
			result.getPictures().add(url);

			recipeService.save(result);
			
			System.out.println("Ticker: " + result.getTicker());
			System.out.println("Title: " + result.getTitle());
			System.out.println("Summary " + result.getSummary());
			System.out.println("Moment Authored: " + result.getMomentAuthored());
			System.out.println("Moment Updated: " + result.getMomentUpdated());
			System.out.println("Pictures: " + result.getPictures().size());
			System.out.println("Likes: " + result.getDislikes());
			System.out.println("Dislikes: " + result.getDislikes());
			
			authenticate(null);	
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("El nutricionista logueado no puede realizar esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@Test
	public void testDeleteNegative() throws MalformedURLException {
		System.out.println("----------------------------Delete------------------------------");		
		try {
			authenticate("user1");
			
			Recipe result;
			User user;
			URL url;
			
			user = userService.findOne(16);
			
			result = recipeService.create(user);
			
			url = new URL("https://picturestest.com");
			
			result.setTitle("Title Test");
			result.setSummary("Summary Test");
			result.getPictures().add(url);

			recipeService.save(result);
			
			System.out.println("Ticker: " + result.getTicker());
			System.out.println("Title: " + result.getTitle());
			System.out.println("Summary " + result.getSummary());
			System.out.println("Moment Authored: " + result.getMomentAuthored());
			System.out.println("Moment Updated: " + result.getMomentUpdated());
			System.out.println("Pictures: " + result.getPictures().size());
			System.out.println("Likes: " + result.getDislikes());
			System.out.println("Dislikes: " + result.getDislikes());
			
			recipeService.delete(result);
			
			authenticate(null);	
		}catch (IllegalArgumentException  exception) {
			System.out.println("El nutricionista logueado no puede realizar esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	
	/**
	* ################################################################
	*
	* DASHBOARD
	*
	* #################################################################
	*/
	
	@Test
	public void testAvgRecipesUser() {
		System.out.println("----------------------------Avg Recipes User------------------------------");		
		authenticate("admin");
		Double result;
		
		result = recipeService.avgRecipesUser();
	
		System.out.println("Avg Recipes per User: " + result);
	}
	
	@Test
	public void testMinRecipesUser() {
		System.out.println("----------------------------Min Recipes User------------------------------");		
		authenticate("admin");
		Double result;
		
		result = recipeService.minRecipesUser();
	
		System.out.println("Min Recipes per User: " + result);
	}
	
	@Test
	public void testMaxRecipesUser() {
		System.out.println("----------------------------Max Recipes User------------------------------");		
		authenticate("admin");
		Double result;
		
		result = recipeService.maxRecipesUser();
	
		System.out.println("Max Recipes per User: " + result);
	}
	
	@Test
	public void testMinRecipesHaveQualifiedContest() {
		System.out.println("----------------------------Min Recipes Have Qualified Contest------------------------------");		
		authenticate("admin");
		Double result;
		
		result = recipeService.minRecipesHaveQualifiedContest();
	
		System.out.println("Min Recipes Have Qualified Contest: " + result);
	}
	
	@Test
	public void testMaxRecipesHaveQualifiedContest() {
		System.out.println("----------------------------Max Recipes Have Qualified Contest------------------------------");		
		authenticate("admin");
		Double result;
		
		result = recipeService.maxRecipesHaveQualifiedContest();
	
		System.out.println("Max Recipes Have Qualified Contest: " + result);
	}
	
	@Test
	public void avgRecipesHaveQualifiedContest() {
		System.out.println("----------------------------Avg Recipes Have Qualified Contest------------------------------");		
		authenticate("admin");
		Double result;
		
		result = recipeService.avgRecipesHaveQualifiedContest();
	
		System.out.println("Avg Recipes Have Qualified Contest: " + result);
	}
	
	
}