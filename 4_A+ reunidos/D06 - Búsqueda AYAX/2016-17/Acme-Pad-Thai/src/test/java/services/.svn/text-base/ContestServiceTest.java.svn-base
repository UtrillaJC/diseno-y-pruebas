package services;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Contest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ContestServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
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

		authenticate("admin");
	
		Contest result;
		
		result = contestService.create();
		
		System.out.println("Title: " + result.getTitle());
		System.out.println("Moment Opening: " + result.getMomentOpening());
		System.out.println("Moment Closing: " + result.getMomentClosing());
		
		authenticate(null);
		

		System.out.println("----------------------------------------------------------------"); 
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
		
		authenticate("admin");
		
		Contest result;
		Date momentOpening;
		Date momentClosing;
		
		result = contestService.create();
		momentOpening = new Date(System.currentTimeMillis());
		momentClosing = new Date(2016,02,15);
		
		result.setTitle("Title Test");
		result.setMomentOpening(momentOpening);
		result.setMomentClosing(momentClosing);
		
		contestService.save(result);
		
		System.out.println("Title: " + result.getTitle());
		System.out.println("Moment Opening: " + result.getMomentOpening());
		System.out.println("Moment Closing: " + result.getMomentClosing());
		System.out.println("Winners: " + result.getWinners());
		System.out.println("Registrations: " + result.getRegistrations());
		
		authenticate(null);
		
		System.out.println("----------------------------------------------------------------");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDelete() {
		
		System.out.println("----------------------------Delete------------------------------");
		
		authenticate("admin");
		
		Contest result;
		Date momentOpening;
		Date momentClosing;
		
		result = contestService.create();
		momentOpening = new Date(System.currentTimeMillis());
		momentClosing = new Date(2016,02,15);
		
		result.setTitle("Title Test");
		result.setMomentOpening(momentOpening);
		result.setMomentClosing(momentClosing);
		
		contestService.save(result);
		
		System.out.println("Title: " + result.getTitle());
		System.out.println("Moment Opening: " + result.getMomentOpening());
		System.out.println("Moment Closing: " + result.getMomentClosing());
		System.out.println("Winners: " + result.getWinners());
		System.out.println("Registrations: " + result.getRegistrations());
		
		contestService.delete(result);
		
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
		
			Contest result;
			
			result = contestService.create();
			
			System.out.println("Title: " + result.getTitle());
			System.out.println("Moment Opening: " + result.getMomentOpening());
			System.out.println("Moment Closing: " + result.getMomentClosing());
			
			authenticate(null);
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSaveNegative() {
		
		System.out.println("----------------------------Save Negative------------------------------");
		try {
			authenticate("user1");
			
			Contest result;
			Date momentOpening;
			Date momentClosing;
			
			result = contestService.create();
			momentOpening = new Date(System.currentTimeMillis());
			momentClosing = new Date(2016,02,15);
			
			result.setTitle("Title Test");
			result.setMomentOpening(momentOpening);
			result.setMomentClosing(momentClosing);
			
			contestService.save(result);
			
			System.out.println("Title: " + result.getTitle());
			System.out.println("Moment Opening: " + result.getMomentOpening());
			System.out.println("Moment Closing: " + result.getMomentClosing());
			System.out.println("Winners: " + result.getWinners());
			System.out.println("Registrations: " + result.getRegistrations());
			
			authenticate(null);
		
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDeleteNegative() {
		
		System.out.println("----------------------------Delete Negative------------------------------");
		try {
			authenticate("user1");
			
			Contest result;
			Date momentOpening;
			Date momentClosing;
			
			result = contestService.create();
			momentOpening = new Date(System.currentTimeMillis());
			momentClosing = new Date(2016,02,15);
			
			result.setTitle("Title Test");
			result.setMomentOpening(momentOpening);
			result.setMomentClosing(momentClosing);
			
			contestService.save(result);
			
			System.out.println("Title: " + result.getTitle());
			System.out.println("Moment Opening: " + result.getMomentOpening());
			System.out.println("Moment Closing: " + result.getMomentClosing());
			System.out.println("Winners: " + result.getWinners());
			System.out.println("Registrations: " + result.getRegistrations());
			
			contestService.delete(result);
			
			authenticate(null);
		
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
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
	public void testContestswhichMoreRecipesQualified() {
		System.out.println("----------------------------Contests which More Recipes Qualified------------------------------");		
		authenticate("admin");
		Collection<Contest> result;
		
		result = contestService.contestswhichMoreRecipesQualified();
	
		System.out.println("Contests which More Recipes Qualified: " + result);
	}
}