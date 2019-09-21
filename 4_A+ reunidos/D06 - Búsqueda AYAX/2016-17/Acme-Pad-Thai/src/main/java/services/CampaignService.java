package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CampaignRepository;
import domain.Administrator;
import domain.Banner;
import domain.Bill;
import domain.Campaign;
import domain.Sponsor;

@Service
@Transactional
public class CampaignService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private BillService billService;
	
	//Constructor methods ============================================================================
	
	public CampaignService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Campaign findOne(int campaignId){
		Campaign result;
		
		result = campaignRepository.findOne(campaignId);
		
		return result;
	}
	
	public Campaign create(Sponsor sponsor){
		Assert.notNull(sponsor);	
		Campaign result;
		Sponsor principal;
		Collection<Banner> banners;

		principal = sponsorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(sponsor));
		Assert.isInstanceOf(Sponsor.class, principal);
		banners = new HashSet<Banner>();
		
		result = new Campaign();
		result.setSponsor(sponsor);
		result.setBanners(banners);
		sponsor.getCampaigns().add(result);
	
		return result;
	}
	
	public Campaign save(Campaign campaign){
		Assert.notNull(campaign);	
		Assert.isTrue(campaign.getDateEnd().after(campaign.getDateStart()));
		Campaign result;
		Sponsor principal;
		
		principal = sponsorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(campaign.getSponsor()));
		Assert.isInstanceOf(Sponsor.class, principal);
		
		campaign.setIsStar(false);
		result = campaignRepository.save(campaign);
		
		return result;		
	}
	
	public void delete(Campaign campaign){
		Assert.notNull(campaign);	
		Sponsor principal;
		Date moment;
		Collection<Banner> banners;
		Collection<Bill> bills;

		
		moment = new Date(System.currentTimeMillis());
		principal = sponsorService.findByPrincipal();
		Assert.notNull(principal);	
		Assert.isTrue(principal.equals(campaign.getSponsor()));
		Assert.isInstanceOf(Sponsor.class, principal);
		Assert.isTrue(moment.before(campaign.getDateStart()));
		banners = campaign.getBanners();
		bills = campaign.getBills();
	
		if(banners != null){
			for(Banner banner: banners){
				bannerService.delete(banner);
			}
		}
		
		if(bills != null){
			for(Bill bill: bills){
				Assert.isTrue(bill.getMomentPaid().equals(null));
				billService.delete(bill);
			}
		}

		campaignRepository.delete(campaign);
	}
	
	//Other Business Methods =========================================================================
	
	public Collection<Campaign> findAllBySponsor(Sponsor sponsor){
		Assert.notNull(sponsor);
		Collection<Campaign> result;
		
		result = campaignRepository.findAllBySponsorId(sponsor.getId());
		
		return result;
	}
	
	public Collection<Campaign> findAllFinished(){
		Collection<Campaign> result;
		
		result = campaignRepository.findAllFinished();
		
		return result;
	}
	
	//Dashboard =============================================================================
	
	public Double findMinNumber() {
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = campaignRepository.findMinNumber();
		
		return result;
	}
	
	public Double findAvgNumber() {
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = campaignRepository.findAvgNumber();
		
		return result;
	}
	
	public Double findMaxNumber() {
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = campaignRepository.findMaxNumber();
		
		return result;
	}
	
	public Double findMinNumberActiveCampaign() {
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = campaignRepository.findMinNumberActiveCampaign();
		
		return result;
	}
	
	public Double findAvgNumberActiveCampaign() {
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = campaignRepository.findAvgNumberActiveCampaign();
		
		return result;
	}
	
	public Double findMaxNumberActiveCampaign() {
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = campaignRepository.findMaxNumberActiveCampaign();
		
		return result;
	}
}
