package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Cook;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class CookServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private CookService cookService;	

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
	
		Cook result;
		
		result = cookService.create();
		
		System.out.println("Username: " + result.getUserAccount().getUsername());
		System.out.println("Name: " + result.getName());
		System.out.println("Surname: " + result.getSurname());
		System.out.println("Phonenumber: " + result.getPhoneNumber());
		System.out.println("Email: " + result.getEmail());
		System.out.println("Address: " + result.getAddress());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
		
		authenticate("admin");
		
		Cook result;
		
		result = cookService.create();
		result.getUserAccount().setUsername("CookTest");
		result.getUserAccount().setPassword("passwordTest");
		
		result.setName("Cook Name Test");
		result.setSurname("Cook Surname Test");
		result.setEmail("cook@test.com");
		result.setPhoneNumber("+11 (111) T-E-S-T");
		result.setAddress("Cook Addresss  Test");
		
		cookService.save(result);
		
		System.out.println("Username: " + result.getUserAccount().getUsername());
		System.out.println("Name: " + result.getName());
		System.out.println("Surname: " + result.getSurname());
		System.out.println("Phonenumber: " + result.getPhoneNumber());
		System.out.println("Email: " + result.getEmail());
		System.out.println("Address: " + result.getAddress());
		
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
			authenticate("cook1");
		
			Cook result;
			
			result = cookService.create();
			
			System.out.println("Username: " + result.getUserAccount().getUsername());
			System.out.println("Name: " + result.getName());
			System.out.println("Surname: " + result.getSurname());
			System.out.println("Phonenumber: " + result.getPhoneNumber());
			System.out.println("Email: " + result.getEmail());
			System.out.println("Address: " + result.getAddress());
			
			authenticate(null);
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}	
	}
	
	@Test
	public void testSaveNegative() {
		
		System.out.println("----------------------------Save negative------------------------------");
		try{
			authenticate("cook2");
			
			Cook result;
			
			result = cookService.create();
			result.getUserAccount().setUsername("CookTest");
			result.getUserAccount().setPassword("passwordTest");
			
			result.setName("Cook Name Test");
			result.setSurname("Cook Surname Test");
			result.setEmail("cook@.com");
			result.setPhoneNumber("+11 (111) T-E-S-T");
			result.setAddress("Cook Addresss Test");
			
			cookService.save(result);
			
			System.out.println("Username: " + result.getUserAccount().getUsername());
			System.out.println("Name: " + result.getName());
			System.out.println("Surname: " + result.getSurname());
			System.out.println("Phonenumber: " + result.getPhoneNumber());
			System.out.println("Email: " + result.getEmail());
			System.out.println("Address: " + result.getAddress());
			
			authenticate(null);
		
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}		
	}
}