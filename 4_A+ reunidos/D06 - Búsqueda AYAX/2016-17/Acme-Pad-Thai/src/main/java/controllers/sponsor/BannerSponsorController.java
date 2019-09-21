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
import controllers.AbstractController;
import domain.Banner;
import domain.Campaign;


@Controller
@RequestMapping("/banner/sponsor")
public class BannerSponsorController extends AbstractController{
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private CampaignService campaignService;
	
	//Listing banners--------------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int campaignId){
		ModelAndView result;
		Collection<Banner> banners;
		Campaign campaign;		
		
		campaign = campaignService.findOne(campaignId);
		banners = bannerService.findAllByCampaign(campaign);
		
		result = new ModelAndView("banner/list");
		result.addObject("banners", banners);
		result.addObject("requestURI", "banner/sponsor/list.do");

		return result;
	}
	
	//Creation ----------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int campaignId){
		ModelAndView result;
		Banner banner;
		Campaign campaign;
		
		campaign = campaignService.findOne(campaignId);
		Assert.notNull(campaign);
		banner = bannerService.create(campaign);
		
		result = createEditModelAndView(banner);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Banner banner, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(banner);
		}else{
			try{
				
				bannerService.save(banner);
				result = new ModelAndView("redirect:/campaign/sponsor/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(banner, "banner.commit.error");
			}
		}
		return result;
	}
	
	// The ancillary methods ----------------------------------------------------------------

	protected ModelAndView createEditModelAndView(Banner banner) {
		ModelAndView result;

		result = createEditModelAndView(banner, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Banner banner, String message) {
		ModelAndView result;
		
		result = new ModelAndView("banner/edit");
		
		result.addObject("banner", banner);
		result.addObject("message", message);

		return result;
	}
}
