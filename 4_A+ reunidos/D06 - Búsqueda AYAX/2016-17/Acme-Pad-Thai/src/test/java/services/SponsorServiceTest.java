package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.CreditCard;
import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class SponsorServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	SponsorService sponsorService;
	
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
	
		Sponsor result;
		
		result = sponsorService.create();
		
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
				
		Sponsor result;
		CreditCard creditCard;
		
		result = sponsorService.create();
		creditCard = new CreditCard();
		
		creditCard.setBrandName("brandName");
		creditCard.setHolderName("holderName");
		creditCard.setNumber("4539321119299180");
		creditCard.setExpirationMonth(11);
		creditCard.setExpirationYear(2017);
		creditCard.setCvv(111);
		
		result.getUserAccount().setUsername("Sponsor Test");
		result.getUserAccount().setPassword("passwordTest");
		
		result.setName("Sponsor Name Test");
		result.setSurname("Sponsor Surname Test");
		result.setEmail("sponsor@test.com");
		result.setPhoneNumber("+11 (111) T-E-S-T");
		result.setAddress("Sponsor Addresss  Test");
		result.setNameCompany("Company Test");
		result.setCreditCard(creditCard);
		
		sponsorService.save(result);
		
		System.out.println("Username: " + result.getUserAccount().getUsername());
		System.out.println("Name: " + result.getName());
		System.out.println("Surname: " + result.getSurname());
		System.out.println("Phonenumber: " + result.getPhoneNumber());
		System.out.println("Email: " + result.getEmail());
		System.out.println("Address: " + result.getAddress());
		System.out.println("Card number: " + result.getCreditCard().getNumber());
		System.out.println("Brand name: " + result.getCreditCard().getBrandName());
		System.out.println("Holder name: " + result.getCreditCard().getHolderName());
		
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
	public void testSaveNegative() {
		
		System.out.println("----------------------------Save Negative------------------------------");
		try{
			Sponsor result;
			
			result = sponsorService.create();
			result.getUserAccount().setUsername("Sponsor Test");
			result.getUserAccount().setPassword("passwordTest");
			
			result.setName("Sponsor Name Test");
			result.setSurname("Sponsor Surname Test");
			result.setEmail("sponsor@test.com");
			result.setPhoneNumber("+11 (111)");
			result.setAddress("Sponsor Addresss  Test");
			
			sponsorService.save(result);
			
			System.out.println("Username: " + result.getUserAccount().getUsername());
			System.out.println("Name: " + result.getName());
			System.out.println("Surname: " + result.getSurname());
			System.out.println("Phonenumber: " + result.getPhoneNumber());
			System.out.println("Email: " + result.getEmail());
			System.out.println("Address: " + result.getAddress());
		
		}catch (IllegalArgumentException  exception) {
			System.out.println("Este spsonsor no dispone de CreditCard"); 
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
	public void testRankingsOfCompaniesForCampaigns() {
		System.out.println("----------------------------Rankings Of Companies For Campaigns------------------------------");		
		authenticate("admin");
		Collection<String> result;
		
		result = sponsorService.rankingsOfCompaniesForCampaigns();
	
		System.out.println("Rankings Of Companies For Campaigns: " + result);
	}
	
	@Test
	public void testRankingsOfCompaniesForBills() {
		System.out.println("----------------------------Rankings Of Companies For Bills------------------------------");		
		authenticate("admin");
		Collection<String> result;
		
		result = sponsorService.rankingsOfCompaniesForBills();
	
		System.out.println("Rankings Of Companies For Bills: " + result);
	}
	
	/**
	* ################################################################
	*
	* DASHBOARD
	*
	* #################################################################
	*/
	
	@Test
	public void testSponsorsWhoNotManagedCampaignLastThreeMonths() {
		System.out.println("----------------------------Sponsors Who Not Managed Campaign Last Three Months------------------------------");		
		authenticate("admin");
		Collection<Sponsor> result;
		
		result = sponsorService.sponsorsWhoNotManagedCampaignLastThreeMonths();
	
		System.out.println("Sponsors Who Not Managed Campaign Last Three Months: " + result);
	}
	
	@Test
	public void testCompaniesSpentLessAvgCampaigns() {
		System.out.println("----------------------------Companies Spent Less Avg Campaigns------------------------------");		
		authenticate("admin");
		Collection<String> result;
		
		result = sponsorService.companiesSpentLessAvgCampaigns();
	
		System.out.println("Companies Spent Less Avg Campaigns: " + result);
	}
	
	@Test
	public void testCompaniesSpentLeast90PerCent() {
		System.out.println("----------------------------Companies Spent Least 90 Per Cent------------------------------");		
		authenticate("admin");
		Collection<String> result;
		
		result = sponsorService.companiesSpentLeast90PerCent();
	
		System.out.println("Companies Spent Least 90 Per Cent: " + result);
	}
	
}