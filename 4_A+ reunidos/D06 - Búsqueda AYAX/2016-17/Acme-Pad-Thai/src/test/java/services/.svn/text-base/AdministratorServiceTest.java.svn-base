package services;

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
public class AdministratorServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private AdministratorService administratorService;
	
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
	public void  testWinnersOfContest(){
		
		System.out.println("----------------------------Winners Of Contest------------------------------");
		authenticate("admin");
		Contest contest;
		
		contest = contestService.findOne(205);
		
		administratorService.winnersOfContest(contest);
		
		System.out.println(contest.getWinners());

		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
		
	@Test
	public void testSendABulk(){
		System.out.println("----------------------------Send A Bulk------------------------------");
		authenticate("admin");

		administratorService.sendABulk();

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
	public void  testWinnersOfContestNegative(){
		
		System.out.println("----------------------------Winners Of Contest Negative------------------------------");
		
		try{
			authenticate("sponsor1");
			Contest contest;
			
			contest = contestService.findOne(205);
			
			administratorService.winnersOfContest(contest);
			
			System.out.println(contest.getWinners());
	
			authenticate(null);
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}	
	}
	
	@Test
	public void testSendABulkNegative(){
		System.out.println("----------------------------Send A Bulk Negatve------------------------------");
		
		try{
			authenticate("sponsor1");
	
			administratorService.sendABulk();
	
			authenticate(null);
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}	
	}
}
