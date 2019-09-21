package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Fee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class FeeServiceTest extends AbstractTest{

	// Service under test ============================================================================

	@Autowired
	private FeeService feeService;
	
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
	
		Fee result;
		
		result = feeService.create();
		
		System.out.println("Amount: " + result.getAmount());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
		authenticate("admin");
		
		Fee result;
		
		result = feeService.create();
		
		result.setAmount(3.75);
		
		feeService.save(result);
		
		System.out.println("Amount: " + result.getAmount());
		
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
		System.out.println("----------------------------Create Negative------------------------------");
		try{
			authenticate("user1");
			
			Fee result;
			
			result = feeService.create();
						
			System.out.println("Amount: " + result.getAmount());
			
			authenticate(null);
				
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario autenticado no puede realizar la operacion"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
				}
	}
	
	@Test
	public void testSaveNegative(){
		System.out.println("----------------------------Save Negative------------------------------");
		try{
			authenticate("user1");
			
			Fee result;
			
			result = feeService.create();
			
			result.setAmount(3.75);
			
			feeService.save(result);
			
			System.out.println("Amount: " + result.getAmount());
			
			authenticate(null);
				
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario autenticado no puede realizar la operacion"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
				}
	}
}
