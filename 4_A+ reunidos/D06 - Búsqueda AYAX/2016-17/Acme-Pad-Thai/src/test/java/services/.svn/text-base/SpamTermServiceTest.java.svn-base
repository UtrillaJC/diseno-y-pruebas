package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.SpamTerm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class SpamTermServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private SpamTermService spamTermService;
	
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
	
		SpamTerm result;
		
		result = spamTermService.create();
		
		System.out.println("Keywords: " + result.getKeywords());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
		authenticate("admin");
		
		SpamTerm result;
		
		result = spamTermService.create();
		
		result.getKeywords().add("Melkor");
		
		spamTermService.save(result);
		
		System.out.println("Keywords: " + result.getKeywords());
		
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
			
			SpamTerm result;
			
			result = spamTermService.create();
			
			System.out.println("Keywords: " + result.getKeywords());
			
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
			
			SpamTerm result;
			
			result = spamTermService.create();
			
			result.getKeywords().add("Melkor");
			
			spamTermService.save(result);
			
			System.out.println("Keywords: " + result.getKeywords());
			
			authenticate(null);
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario autenticado no puede realizar la operacion"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
				}
	}
}