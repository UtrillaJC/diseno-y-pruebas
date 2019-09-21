package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Banner;
import domain.Campaign;
import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class CampaignServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private CampaignService campaignService;
	
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private BannerService bannerService;
	
	
	// Tests =========================================================================================
	
	/**
	* ################################################################
	*
	* TEST POSITIVOS
	*
	* #################################################################
	*/
	
	@Test
	public void testFindAllBySponsor(){
		System.out.println("--------------------------Find All By Sponsor--------------------------");
		authenticate("sponsor1");
		
		Sponsor sponsor;
		Collection<Campaign> result;
		
		sponsor = sponsorService.findOne(30);
		result = campaignService.findAllBySponsor(sponsor);
		
		System.out.println("Number of campaigns from Sponsor " + sponsor.getId() + " - " + result.size());
		
		for (Campaign campaign : result) {
			String s = "";
			System.out.println(s = s + "Campaign ID: " + campaign.getId());
		}
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testCreate(){
		System.out.println("-----------------------------Create-----------------------------");
		authenticate("sponsor1");
		
		Campaign campaign;
		Sponsor sponsor;
		
		sponsor = sponsorService.findByPrincipal();
		
		campaign = campaignService.create(sponsor);
		
		System.out.println("Date Start: " + campaign.getDateStart());
		System.out.println("Date End: " + campaign.getDateEnd());
		System.out.println("Is Star: " + campaign.getIsStar());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testSave(){
		System.out.println("-----------------------------Save-----------------------------");
		authenticate("sponsor1");
		
		Campaign campaign;
		Sponsor sponsor;
		Banner banner;
		Date dateStart;
		Date dateEnd;
		
		sponsor = sponsorService.findByPrincipal();
		campaign = campaignService.create(sponsor);
		dateStart = new Date(System.currentTimeMillis() - 1000);
		dateEnd = new Date();
		banner = bannerService.findOne(48);
		
		campaign.setDateStart(dateStart);
		campaign.setDateEnd(dateEnd);
		campaign.getBanners().add(banner);
				
		campaign = campaignService.save(campaign);
		
		System.out.println("Date Start: " + campaign.getDateStart());
		System.out.println("Date End: " + campaign.getDateEnd());
		System.out.println("Is Star: " + campaign.getIsStar());

		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDelete(){
		System.out.println("----------------------------Delete------------------------------");
		authenticate("sponsor1");
		
		Campaign campaign;
		Sponsor sponsor;
		Banner banner;
		Collection<Banner> banners;
		Date dateStart;
		Date dateEnd;
		
		sponsor = sponsorService.findByPrincipal();
		campaign = campaignService.create(sponsor);
		dateStart = new Date(116,11,15);
		dateEnd = new Date(117,0,15);
		banner = bannerService.findOne(48);
		banners = new HashSet<Banner>();
		
		banners.add(banner);
		
		campaign.setDateStart(dateStart);
		campaign.setDateEnd(dateEnd);
		campaign.setBanners(banners);
				
		campaign = campaignService.save(campaign);
		
		System.out.println("Date Star: " + campaign.getDateStart());
		System.out.println("Date End: " + campaign.getDateEnd());
		System.out.println("Is Star: " + campaign.getIsStar());
		
		campaign = campaignService.save(campaign);
				
		campaignService.delete(campaign);
		
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
	public void testFindBySponsorNegative(){
		System.out.println("--------------------------Find By Sponsor Negative--------------------------");

		try {
			authenticate("sponsor1");
			
			Sponsor sponsor;
			Collection<Campaign> result;
			
			sponsor = sponsorService.findOne(14);
			result = campaignService.findAllBySponsor(sponsor);
			
			System.out.println("Number of campaigns from Sponsor " + sponsor.getId() + " - " + result.size());
			
			for (Campaign campaign : result) {
				String s = "";
				System.out.println(s = s + "Campaign ID: " + campaign.getId());
			}
			authenticate(null);
		}
		catch(Exception e){System.out.println("No existe un sponsor con el id indicado"); }
		finally{System.out.println("----------------------------------------------------------------"); }
	}
	
	@Test
	public void testCreateNegative(){
		try{
			System.out.println("-----------------------------Create Negative-----------------------------");
			authenticate("admin");
			Campaign campaign;
			Sponsor sponsor;
			sponsor = sponsorService.findByPrincipal();
			
			campaign = campaignService.create(sponsor);
			
			System.out.println("Date Start: " + campaign.getDateStart());
			System.out.println("Date End: " + campaign.getDateEnd());
			System.out.println("Is Star: " + campaign.getIsStar());
			
			authenticate(null);
		}
		catch(Exception e){System.out.println("El actor logueado no puede crear una campaña"); }
		finally{System.out.println("----------------------------------------------------------------"); }
	}
	
	@Test
	public void testSaveNegative(){
		try {
			System.out.println("-----------------------------Save Negative-----------------------------");
			authenticate("admin");
			Campaign campaign;
			Sponsor sponsor;
			Banner banner;
			Date dateStart;
			Date dateEnd;
			
			sponsor = sponsorService.findByPrincipal();
			campaign = campaignService.create(sponsor);
			dateStart = new Date();
			dateEnd = new Date();
			banner = bannerService.findOne(48);
			
			campaign.setDateStart(dateStart);
			campaign.setDateEnd(dateEnd);
			campaign.getBanners().add(banner);
					
			campaign = campaignService.save(campaign);
			
			System.out.println("Date Start: " + campaign.getDateStart());
			System.out.println("Date End: " + campaign.getDateEnd());
			System.out.println("Is Star: " + campaign.getIsStar());

			authenticate(null);
		}
		catch(Exception e){System.out.println("El actor logueado no puede guardar una campaña"); }
		finally{System.out.println("----------------------------------------------------------------"); }	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDeleteNegative(){
		System.out.println("----------------------------Delete Negative------------------------------");

		try {
			authenticate("admin");
			Campaign campaign;
			Sponsor sponsor;
			Banner banner;
			Collection<Banner> banners;
			Date dateStart;
			Date dateEnd;
			
			sponsor = sponsorService.findByPrincipal();
			campaign = campaignService.create(sponsor);
			dateStart = new Date(116,11,15);
			dateEnd = new Date(117,0,15);
			banner = bannerService.findOne(48);
			banners = new HashSet<Banner>();
			
			banners.add(banner);
			
			campaign.setDateStart(dateStart);
			campaign.setDateEnd(dateEnd);
			campaign.setBanners(banners);
					
			campaign = campaignService.save(campaign);
			
			System.out.println("Date Star: " + campaign.getDateStart());
			System.out.println("Date End: " + campaign.getDateEnd());
			System.out.println("Is Star: " + campaign.getIsStar());
								
			campaignService.delete(campaign);
			
			authenticate(null);
		}
		catch(Exception e){System.out.println("El actor logueado no puede borrar una campaña"); }
		finally{System.out.println("----------------------------------------------------------------"); }	
	}
	
	/**
	* ################################################################
	*
	* DASHBOARD
	*
	* #################################################################
	*/
	
	@Test
	public void testFindMinNumber() {
		System.out.println("----------------------------Find Min Number------------------------------");		
		authenticate("admin");
		Double result;
		
		result = campaignService.findMinNumber();
	
		System.out.println("Find Min Number: " + result);
	}
	
	@Test
	public void testFindAvgNumber() {
		System.out.println("----------------------------Find Avg Number------------------------------");		
		authenticate("admin");
		Double result;
		
		result = campaignService.findAvgNumber();
	
		System.out.println("Find Avg Number: " + result);
	}
	
	@Test
	public void testFindMaxNumber() {
		System.out.println("----------------------------Find Max Number------------------------------");		
		authenticate("admin");
		Double result;
		
		result = campaignService.findMaxNumber();
	
		System.out.println("Find Max Number: " + result);
	}
	
	@Test
	public void testFindMinNumberActiveCampaign() {
		System.out.println("----------------------------Find Min Number Active Campaign------------------------------");		
		authenticate("admin");
		Double result;
		
		result = campaignService.findMinNumberActiveCampaign();
	
		System.out.println("Find Min Number Active Campaign: " + result);
	}
	
	@Test
	public void testFindAvgNumberActiveCampaign() {
		System.out.println("----------------------------Find Avg Number Active Campaign------------------------------");		
		authenticate("admin");
		Double result;
		
		result = campaignService.findAvgNumberActiveCampaign();
	
		System.out.println("Find Avg Number Active Campaign: " + result);
	}
	
	@Test
	public void testFindMaxNumberActiveCampaign() {
		System.out.println("----------------------------Find Max Number Active Campaign------------------------------");		
		authenticate("admin");
		Double result;
		
		result = campaignService.findMaxNumberActiveCampaign();
	
		System.out.println("Find Max Number Active Campaign: " + result);
	}
	
}

