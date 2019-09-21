package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Nutritionist;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class NutritionistServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private NutritionistService nutritionistService;
	
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
	
		Nutritionist result;
		
		result = nutritionistService.create();
		
		System.out.println("Username: " + result.getUserAccount().getUsername());
		System.out.println("Name: " + result.getName());
		System.out.println("Surname: " + result.getSurname());
		System.out.println("Phonenumber: " + result.getPhoneNumber());
		System.out.println("Email: " + result.getEmail());
		System.out.println("Address: " + result.getAddress());
		
		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
				
		Nutritionist result;
		
		result = nutritionistService.create();
		
		result.getUserAccount().setUsername("Nutritionist Test");
		result.getUserAccount().setPassword("passwordTest");
		
		result.setName("Nutritionist Name Test");
		result.setSurname("Nutritionist Surname Test");
		result.setEmail("nutritionist@test.com");
		result.setPhoneNumber("+11 (111) T-E-S-T");
		result.setAddress("Nutritionist Addresss  Test");
		
		nutritionistService.save(result);
		
		System.out.println("Username: " + result.getUserAccount().getUsername());
		System.out.println("Name: " + result.getName());
		System.out.println("Surname: " + result.getSurname());
		System.out.println("Phonenumber: " + result.getPhoneNumber());
		System.out.println("Email: " + result.getEmail());
		System.out.println("Address: " + result.getAddress());
		
		System.out.println("----------------------------------------------------------------");
	}
	
	/**
	* ################################################################
	*
	* TEST NEGATIVOS
	*
	* #################################################################
	*/	
}