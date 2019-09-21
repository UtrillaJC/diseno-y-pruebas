package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Banner;
import domain.Campaign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class BannerServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private CampaignService campaignService;
	
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

		authenticate("sponsor1");
	
		Banner result;
		Campaign campaign;
		
		campaign = campaignService.findOne(44);
		result = bannerService.create(campaign);
		
		System.out.println("Num displayed: " + result.getNumDisplayed());
		System.out.println("Num Max displayed: " + result.getMaxNumDisplayed());
		
		authenticate(null);
		

		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
		
		authenticate("sponsor1");
		
		Banner result;
		Campaign campaign;
		
		campaign = campaignService.findOne(44);
		result = bannerService.create(campaign);
		
		result.setMaxNumDisplayed(7);
		result.setNumDisplayed(3);
		
		bannerService.save(result);
		
		System.out.println("Num displayed: " + result.getNumDisplayed());
		System.out.println("Num Max displayed: " + result.getMaxNumDisplayed());
		
		authenticate(null);
		
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testFindAllByCampaign(){
		System.out.println("----------------------------Find All By Campaign------------------------------");

		Campaign campaign;
		
		campaign = campaignService.findOne(44);
		
		bannerService.findAllByCampaign(campaign);
		
		authenticate(null);
		
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testFindBannerIsStar(){
		System.out.println("----------------------------Find Banner in Starred Active Campaign------------------------------");
		
		bannerService.findBannerIsStar();
		
		authenticate(null);
		
		System.out.println("----------------------------------------------------------------");
	}
	
	
	@Test
	public void testSumNumDisplayedOfBannerForCampaign(){
		System.out.println("----------------------------Sum Num Displayed Of Banner For Campaign------------------------------");

		Campaign campaign;
		Integer cont;
		campaign = campaignService.findOne(44);
		
		cont = bannerService.sumNumDisplayedOfBannerForCampaign(campaign);
		
		System.out.println(cont);
				
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
			authenticate("sponsor1");
		
			Banner result;
			Campaign campaign;
			
			campaign = campaignService.findOne(0);
			result = bannerService.create(campaign);
			
			System.out.println("Num displayed: " + result.getNumDisplayed());
			System.out.println("Num Max displayed: " + result.getMaxNumDisplayed());
			
			authenticate(null);
			

		}catch (IllegalArgumentException  exception) {
			System.out.println("La campaña no puede ser null"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@Test
	public void testSaveNegative() {
		try{
			System.out.println("----------------------------Save Negative------------------------------");
			
			authenticate("sponsor1");
			
			Banner result;
			Campaign campaign;
			
			campaign = campaignService.findOne(42);
			result = bannerService.create(campaign);
			
			result.setMaxNumDisplayed(7);
			result.setNumDisplayed(20);
			
			bannerService.save(result);
			
			System.out.println("Num displayed: " + result.getNumDisplayed());
			System.out.println("Num Max displayed: " + result.getMaxNumDisplayed());
			
			authenticate(null);
		
		}catch (IllegalArgumentException  exception) {
			System.out.println("El número deplegado de banner no puede ser mayor que el número máximo de veces desplegado"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@Test
	public void testFindAllByCampaignNegative(){
		System.out.println("----------------------------Find All By Campaign Negative------------------------------");
		
		try{
			
			Campaign campaign;
			
			campaign = campaignService.findOne(0);
			
			bannerService.findAllByCampaign(campaign);
				
		}catch (IllegalArgumentException  exception) {
			System.out.println("Campaña nula"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@Test
	public void testSumNumDisplayedOfBannerForCampaignNegative(){
		System.out.println("----------------------------Sum Num Displayed Of Banner ForCampaign Negative------------------------------");
		try{
			Campaign campaign;
			Integer cont;
			campaign = campaignService.findOne(0);
			
			cont = bannerService.sumNumDisplayedOfBannerForCampaign(campaign);
			
			System.out.println(cont);
				
		}catch (IllegalArgumentException  exception) {
			System.out.println("Campaña nula"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@Test
	public void testFindBannerIsStarNegative(){
		System.out.println("----------------------------Find Banner in Starred Active Campaign Negative------------------------------");
		try{
		
			bannerService.findBannerIsStar();
		
			authenticate(null);
		}catch (IllegalArgumentException  exception) {
			System.out.println("Banner nulo"); 
			}
		finally{
			
		System.out.println("----------------------------------------------------------------");
		}
	}

}