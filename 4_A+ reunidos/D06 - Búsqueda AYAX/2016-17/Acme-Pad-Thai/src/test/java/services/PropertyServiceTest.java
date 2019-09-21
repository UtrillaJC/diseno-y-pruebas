package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Property;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class PropertyServiceTest extends AbstractTest{
	
	// Service under test ---------------------------------------
	
	@Autowired
	private PropertyService propertyService;
	
	
	// Tests ----------------------------------------------------
	
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
	
		Property result;
		
			
		result = propertyService.create();
		
		System.out.println("Name: " + result.getName());
		
		
		authenticate(null);
		
		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
		
		authenticate("nutritionist1");
		
		Property result;
		
		String name;
		
		result = propertyService.create();
		
		name = "Nombre de la propiedad";
		
		result.setName(name);
		
		propertyService.save(result);
		
		
		System.out.println("Name: " + result.getName());
		
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
		System.out.println("----------------------------Create Negative------------------------------");		
		
		try {
			authenticate("user1");
	
			Property result;
		
			
			result = propertyService.create();
		
			System.out.println("Name: " + result.getName());
		
		
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
		
		System.out.println("----------------------------Save Negative------------------------------");
		try {
			authenticate("user1");
		
			Property result;
		
			String name;
		
			result = propertyService.create();
		
			name = "Nombre de la propiedad";
		
			result.setName(name);
		
			propertyService.save(result);
		
		
		System.out.println("Name: " + result.getName());
		
		authenticate(null);
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
		}
		finally{
		System.out.println("----------------------------------------------------------------");
		}
	}
	
}
