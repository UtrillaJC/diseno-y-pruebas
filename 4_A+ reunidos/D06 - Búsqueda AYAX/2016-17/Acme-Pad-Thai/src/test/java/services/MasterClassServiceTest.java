package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Cook;
import domain.MasterClass;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class MasterClassServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private MasterClassService masterClassService;
	
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

		authenticate("cook1");
	
		MasterClass result;
		Cook cook;
		
		cook = cookService.findOne(26);
		result = masterClassService.create(cook);
		
		System.out.println("Title: " + result.getTitle());
		System.out.println("Description: " + result.getDescription());
		System.out.println("Is promoted: " + result.getIsPromoted());
		System.out.println("Cook name: " + result.getCook().getName());

		authenticate(null);
		
		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
		
		authenticate("cook1");
		
		MasterClass result;
		Cook cook;
		
		cook = cookService.findOne(26);
		result = masterClassService.create(cook);
		
		result.setDescription("Descrition Test");
		result.setTitle("Title test");
		
		masterClassService.save(result);
		
		System.out.println("Title: " + result.getTitle());
		System.out.println("Description: " + result.getDescription());
		System.out.println("Is promoted: " + result.getIsPromoted());
		System.out.println("Cook name: " + result.getCook().getName());

		authenticate(null);
		
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testDelete() {
		
		System.out.println("----------------------------Delete------------------------------");
		
		authenticate("cook1");
		
		MasterClass result;
		Cook cook;
		
		cook = cookService.findOne(26);
		result = masterClassService.create(cook);
		
		result.setDescription("Descrition Test");
		result.setTitle("Title test");
		
		masterClassService.save(result);
		
		System.out.println("Title: " + result.getTitle());
		System.out.println("Description: " + result.getDescription());
		System.out.println("Is promoted: " + result.getIsPromoted());
		System.out.println("Cook name: " + result.getCook().getName());
		
		masterClassService.delete(result);

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
			
			MasterClass result;
			Cook cook;
			
			cook = cookService.findOne(26);
			result = masterClassService.create(cook);
			
			System.out.println("Title: " + result.getTitle());
			System.out.println("Description: " + result.getDescription());
			System.out.println("Is promoted: " + result.getIsPromoted());
			System.out.println("Cook name: " + result.getCook().getName());

			authenticate(null);

		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario autenticado no puede realizar la operacion"); 
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
			
			MasterClass result;
			Cook cook;
			
			cook = cookService.findOne(26);
			result = masterClassService.create(cook);
			
			result.setDescription("Descrition Test");
			result.setTitle("Title test");
			
			masterClassService.save(result);
			
			System.out.println("Title: " + result.getTitle());
			System.out.println("Description: " + result.getDescription());
			System.out.println("Is promoted: " + result.getIsPromoted());
			System.out.println("Cook name: " + result.getCook().getName());

			authenticate(null);
		
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario autenticado no puede realizar la operacion"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@Test
	public void testDeleteNegative(){
		System.out.println("----------------------------Delete Negative------------------------------");
		
		try{
			
			authenticate("user1");
			
			MasterClass result;
			Cook cook;
			
			cook = cookService.findOne(26);
			result = masterClassService.create(cook);
			
			result.setDescription("Descrition Test");
			result.setTitle("Title test");
			
			masterClassService.save(result);
			
			System.out.println("Title: " + result.getTitle());
			System.out.println("Description: " + result.getDescription());
			System.out.println("Is promoted: " + result.getIsPromoted());
			System.out.println("Cook name: " + result.getCook().getName());
			
			masterClassService.delete(result);

			authenticate(null);
				
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario autenticado no puede realizar la operacion"); 
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
	public void testMinMasterClassesCook() {
		System.out.println("----------------------------Min Master Classes Cook------------------------------");		
		authenticate("admin");
		Double result;
		
		result = masterClassService.minMasterClassesCook();
	
		System.out.println("Min Master Classes Cook: " + result);
	}
	
	@Test
	public void testMaxMasterClassesCook() {
		System.out.println("----------------------------Max Master Classes Cook------------------------------");		
		authenticate("admin");
		Double result;
		
		result = masterClassService.maxMasterClassesCook();
	
		System.out.println("Max Master Classes Cook: " + result);
	}
	
	@Test
	public void testAvgMasterClassesCook() {
		System.out.println("----------------------------Avg Master Classes Cook------------------------------");		
		authenticate("admin");
		Double result;
		
		result = masterClassService.avgMasterClassesCook();
	
		System.out.println("Avg Master Classes Cook: " + result);
	}
	
	@Test
	public void testStddevMasterClassesCook() {
		System.out.println("----------------------------Stddev Master Classes Cook------------------------------");		
		authenticate("admin");
		Double result;
		
		result = masterClassService.stddevMasterClassesCook();
	
		System.out.println("Stddev Master Classes Cook: " + result);
	}
	
	
}

