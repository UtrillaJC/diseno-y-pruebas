package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.BannerRepository;
import domain.Banner;
import domain.Campaign;
import domain.Sponsor;

@Service
@Transactional
public class BannerService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private BannerRepository bannerRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private SponsorService sponsorService;
	
	//Constructor methods ============================================================================
	
	public BannerService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Banner findOne(int bannerId){
		Banner result;
		
		result = bannerRepository.findOne(bannerId);
		
		return result;
	}
	
	public Collection<Banner> findAll(){
		Collection<Banner> result;
	
		result = bannerRepository.findAll();
		
		return result;
	}
	
	public Banner create(Campaign campaign){
		Assert.notNull(campaign);
		Assert.notNull(campaign.getSponsor());
		Banner result;
		Sponsor principal;
		
		principal = sponsorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Sponsor.class, principal);
		Assert.isTrue(principal.equals(campaign.getSponsor()));
		
		result = new Banner();
		
		result.setCampaign(campaign);
		
		return result;
	}
	
	public Banner save(Banner banner){
		Assert.notNull(banner);
		Assert.notNull(banner.getCampaign().getSponsor());
		Banner result;
		Sponsor principal;
		
		principal = sponsorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Sponsor.class, principal);
		Assert.isTrue(principal.equals(banner.getCampaign().getSponsor()));
		Assert.isTrue(banner.getMaxNumDisplayed() >= banner.getNumDisplayed());
		
		result = bannerRepository.save(banner);
		
		return result;	
	}
	
	public void delete(Banner banner) {
		Assert.notNull(banner);
		Sponsor principal;
		
		principal = sponsorService.findByPrincipal();
		Assert.notNull(principal);	
		
		bannerRepository.delete(banner);		
	}
	//Other Business Methods =========================================================================

	public Collection<Banner> findAllByCampaign(Campaign campaign){
		Assert.notNull(campaign);
		Collection<Banner> result;
		
		result = bannerRepository.findAllByCampaignId(campaign.getId());
		
		return result;
	}
	
	public Integer sumNumDisplayedOfBannerForCampaign(Campaign campaign){
		Assert.notNull(campaign);
		Integer result;
		
		result = bannerRepository.sumNumDisplayedOfBannerForCampaignId(campaign.getId());
		
		return result;
	}
	
	public Collection<Banner> findBannerIsStar(){
		Collection<Banner> result;
		
		result = bannerRepository.findBannerIsStar();
		
		return result;
	}
}
