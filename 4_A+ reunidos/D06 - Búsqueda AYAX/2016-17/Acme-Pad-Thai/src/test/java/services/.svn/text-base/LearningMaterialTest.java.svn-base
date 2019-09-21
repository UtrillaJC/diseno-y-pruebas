package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Cook;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class LearningMaterialTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private LearningMaterialService learningMaterialService;
				
	// Tests =========================================================================================
	
	/**
	* ################################################################
	*
	* TEST POSITIVOS
	*
	* #################################################################
	*/
	
	
	
	/**
	* ################################################################
	*
	* TEST NEGATIVOS
	*
	* #################################################################
	*/
	
	
	/**
	* ################################################################
	*
	* DASHBOARD
	*
	* #################################################################
	*/
	
	@Test
	public void testAvgNumberLearningMaterialsGroupedLearningMaterials() {
		System.out.println("----------------------------Avg Number Learning Materials Grouped Learning Materials------------------------------");		
		authenticate("admin");
		Double result;
		
		result = learningMaterialService.avgNumberLearningMaterialsGroupedLearningMaterials();
	
		System.out.println("Avg Number Learning Materials Grouped Learning Materials: " + result);
	}
	
	@Test
	public void testNumberOfMasterClassPromoted() {
		System.out.println("----------------------------Number Of Master Class Promoted------------------------------");		
		authenticate("admin");
		Integer result;
		
		result = learningMaterialService.numberOfMasterClassPromoted();
	
		System.out.println("Number Of Master Class Promoted: " + result);
	}
	
	@Test
	public void testListOfCookOrderByNumMasterClassPromoted() {
		System.out.println("----------------------------List Of Cook Order By Num MasterClass Promoted------------------------------");		
		authenticate("admin");
		Collection<Cook> result;
		
		result = learningMaterialService.listOfCookOrderByNumMasterClassPromoted();
	
		System.out.println("List Of Cook Order By Num MasterClass Promoted: " + result);
	}
	
	@Test
	public void testAvgNumberPromotedPerCook() {
		System.out.println("----------------------------Avg Number Promoted Per Cook------------------------------");		
		authenticate("admin");
		Double result;
		
		result = learningMaterialService.avgNumberPromotedPerCook();
	
		System.out.println("Avg Number Promoted Per Cook: " + result);
	}
	
	@Test
	public void testAvgNumberDemotedPerCook() {
		System.out.println("----------------------------Avg Number Demoted Per Cook------------------------------");		
		authenticate("admin");
		Double result;
		
		result = learningMaterialService.avgNumberDemotedPerCook();
	
		System.out.println("Avg Number Demoted Per Cook: " + result);
	}
	
	
}
