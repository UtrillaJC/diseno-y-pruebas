package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Bill;
import domain.Campaign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class BillServiceTest extends AbstractTest{
	
	// Service under test ---------------------------------------
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private CampaignService campaignService;
	
	// Tests ----------------------------------------------------
	
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

		Bill result;
		Campaign campaign;
		
		campaign = campaignService.findOne(44);
		
		result = billService.create(campaign);
		
		System.out.println("Moment Created: " + result.getMomentCreated());
		System.out.println("Moment Paid: " + result.getMomentPaid());
		System.out.println("Cost: " + result.getCost());
		System.out.println("Description: " + result.getDescription());		

		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
		
		Bill result;
		Campaign campaign;
		
		campaign = campaignService.findOne(44);
		
		result = billService.create(campaign);
		
		billService.save(result);
		
		System.out.println("Moment Created: " + result.getMomentCreated());
		System.out.println("Moment Paid: " + result.getMomentPaid());
		System.out.println("Cost: " + result.getCost());
		System.out.println("Description: " + result.getDescription());
				
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
			Bill result;
			Campaign campaign;
			
			campaign = campaignService.findOne(12);
		
			result = billService.create(campaign);
		
			System.out.println("Moment Created: " + result.getMomentCreated());
			System.out.println("Moment Paid: " + result.getMomentPaid());
			System.out.println("Cost: " + result.getCost());
			System.out.println("Description: " + result.getDescription());
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("La campaña no existe"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			
		}
	}
	
	@Test
	public void testSaveNegative() {
		
		System.out.println("----------------------------Save Negative------------------------------");
		
		try {
			Bill result;
			Campaign campaign;
			
			campaign = campaignService.findOne(12);
		
			result = billService.create(campaign);
				
			billService.save(result);
		
			System.out.println("Moment Created: " + result.getMomentCreated());
			System.out.println("Moment Paid: " + result.getMomentPaid());
			System.out.println("Cost: " + result.getCost());
			System.out.println("Description: " + result.getDescription());
				
			}catch (IllegalArgumentException  exception) {
				System.out.println("La campaña no existe"); 
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
	public void testAvgBillsPaid() {
		System.out.println("----------------------------Avg Bills Paid------------------------------");		
		authenticate("admin");
		Double result;
		
		result = billService.avgBillsPaid();
	
		System.out.println("Avg Bills Paid: " + result);
	}
	
	@Test
	public void testAvgBillsNotPaid() {
		System.out.println("----------------------------Avg Bills Not Paid------------------------------");		
		authenticate("admin");
		Double result;
		
		result = billService.avgBillsNotPaid();
	
		System.out.println("Avg Bills Not Paid: " + result);
	}
	
	@Test
	public void testStddevBillsPaid() {
		System.out.println("----------------------------Stddev Bills Paid------------------------------");		
		authenticate("admin");
		Double result;
		
		result = billService.stddevBillsPaid();
	
		System.out.println("Stddev Bills Paid: " + result);
	}
	
	@Test
	public void testStddevBillsNotPaid() {
		System.out.println("----------------------------Stddev Bills Not Paid------------------------------");		
		authenticate("admin");
		Double result;
		
		result = billService.stddevBillsNotPaid();
	
		System.out.println("Stddev Bills Not Paid: " + result);
	}
	
	
}
	
	
	

