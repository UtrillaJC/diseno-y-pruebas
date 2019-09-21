package controllers.sponsor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import services.CampaignService;
import services.SponsorService;
import controllers.AbstractController;
import domain.Banner;
import domain.Campaign;
import domain.Sponsor;


@Controller
@RequestMapping("/campaign/sponsor")
public class CampaignSponsorController extends AbstractController{
	
	@Autowired
	private CampaignService campaignService;
	
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private BannerService bannerService;
	
	//Listing --------------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Campaign> campaigns;
		Sponsor sponsor;
		
		sponsor = sponsorService.findByPrincipal();
		
		campaigns = campaignService.findAllBySponsor(sponsor);

		result = new ModelAndView("campaign/list");
		result.addObject("campaigns", campaigns);
		result.addObject("requestURI", "campaign/sponsor/list.do");

		return result;
	}
	
	//Creation ----------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Campaign campaign;
		Sponsor principal;
		
		principal = sponsorService.findByPrincipal();
		campaign = campaignService.create(principal);
		
		result = createEditModelAndView(campaign);
		
		return result;
	}
	
	//Editing -------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int campaignId){
		ModelAndView result;
		Campaign campaign;
		
		campaign = campaignService.findOne(campaignId);
		Assert.notNull(campaign);
		result = createEditModelAndView(campaign);
		
		return result;	
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Campaign campaign, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(campaign);
		}else{
			try{
				
				campaignService.save(campaign);
				result = new ModelAndView("redirect:/campaign/sponsor/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(campaign, "campaign.commit.error");
			}
		}
		return result;
	}
	
	//Deleting -------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Campaign campaign, BindingResult binding){
		ModelAndView result;
		
		try{
			campaignService.delete(campaign);
			result = new ModelAndView("redirect:/campaign/sponsor/list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(campaign, "campaign.commit.error");
			System.out.println(binding.getObjectName());
			System.out.println(binding.getNestedPath());
			System.out.println(binding.toString());
			System.out.println(binding.getAllErrors());
		}
		return result;
	}
	
	// The ancillary methods ----------------------------------------------------------------

	protected ModelAndView createEditModelAndView(Campaign campaign) {
		ModelAndView result;

		result = createEditModelAndView(campaign, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Campaign campaign, String message) {
		ModelAndView result;
		Collection<Banner> banners;

		banners = bannerService.findAll();
		
		result = new ModelAndView("campaign/edit");
		
		result.addObject("campaign", campaign);
		result.addObject("banners", banners);

		result.addObject("message", message);

		return result;
	}
	

}
