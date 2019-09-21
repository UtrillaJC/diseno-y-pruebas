package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Contest;
import domain.Registration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class RegistrationServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private ContestService contestService;
	
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
		authenticate("user2");
	
		Registration result;
		Contest contest;
		
		contest = contestService.findOne(238);
		
		result = registrationService.create(contest);
		
		System.out.println("Moment: " + result.getMoment());
		System.out.println("Recipe ID: " + result.getRecipe().getId());
		System.out.println("Contest ID: " + result.getContest().getId());

		authenticate(null);
		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
		authenticate("user2");
		
		Registration result;
		Contest contest;
		
		contest = contestService.findOne(238);
		
		result = registrationService.create(contest);
		
		registrationService.save(result);
		
		System.out.println("Moment: " + result.getMoment());
		System.out.println("Recipe ID: " + result.getRecipe().getId());
		System.out.println("Contest ID: " + result.getContest().getId());

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
		try{
			authenticate("user1");
			
			Registration result;
			Contest contest;
			
			contest = contestService.findOne(238);
			
			result = registrationService.create(contest);
			
			System.out.println("Moment: " + result.getMoment());
			System.out.println("Recipe ID: " + result.getRecipe().getId());
			System.out.println("Contest ID: " + result.getContest().getId());

			authenticate(null);
		}catch (IllegalArgumentException  exception) {
			System.out.println("La receta no cumple con los likes y dislikes para ser apto para una competicion"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@Test
	public void testSaveNegative() {
		try{
			System.out.println("----------------------------Save Negative------------------------------");
			authenticate("user1");
			
			Registration result;
			Contest contest;
			
			contest = contestService.findOne(238);
			
			result = registrationService.create(contest);
			
			registrationService.save(result);
			
			System.out.println("Moment: " + result.getMoment());
			System.out.println("Recipe ID: " + result.getRecipe().getId());
			System.out.println("Contest ID: " + result.getContest().getId());

			authenticate(null);
		}catch (IllegalArgumentException  exception) {
			System.out.println("La receta no cumple con los likes y dislikes para ser apto para una competicion"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
}