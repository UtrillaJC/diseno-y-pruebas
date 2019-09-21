package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;
import domain.Endorser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class EndorserServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private EndorserService endorserService;
		
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
		System.out.println("----------------------------Create------------------------------");		
		authenticate("nutritionist1");
		Endorser endorser;
		
		endorser = endorserService.create();
		System.out.println("Name: " + endorser.getName());
		System.out.println("HomePage: " + endorser.getHomePage());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------"); 
	}	
	
	
	
	@Test
	public void testSave() {
		System.out.println("----------------------------Save------------------------------");
		authenticate("nutritionist1");
		Endorser endorser;
		
		endorser = endorserService.create();
		
		endorser.setName("Endorser Name");
		endorser.setHomePage("Endorser HomePage");
		
		endorserService.save(endorser);
		
		System.out.println("Name: " + endorser.getName());
		System.out.println("HomePage: " + endorser.getHomePage());
		
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
	public void testcreateNegative(){		
		try {
			System.out.println("-----------------------------Create Negtive-----------------------------");
			authenticate("user1");
			Endorser endorser;
			
			endorser = endorserService.create();
			System.out.println("Name: " + endorser.getName());
			System.out.println("HomePage: " + endorser.getHomePage());
		
			authenticate(null);
		}
		catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede realizar esta operación"); 
		}
		finally{System.out.println("----------------------------------------------------------------"); 
		}

	}
	
	@Test
	public void testSaveNegative() { 
		try {
			System.out.println("----------------------------Save Negative------------------------------");
			authenticate("user1");
			Endorser endorser;
			
			endorser = endorserService.create();
			
			endorser.setName("Endorser Name");
			endorser.setHomePage("Endorser HomePage");
			
			endorserService.save(endorser);
			
			System.out.println("Name: " + endorser.getName());
			System.out.println("HomePage: " + endorser.getHomePage());
		
		authenticate(null);
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede realizar esta operación"); 
		}
		finally{System.out.println("----------------------------------------------------------------");
		}
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	

