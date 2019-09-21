package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.BannerService;
import domain.Banner;

@Controller
@RequestMapping("/banner")
public class BannerController extends AbstractController{
	
	// Services ---------------------------------------------------------------
	
	@Autowired
	private BannerService bannerService;
		
	// Constructors -----------------------------------------------------------

	public BannerController(){
		super();
	}
		
	//List ---------------------------------------------------------------------
		
	@RequestMapping(value = "/listBannerStarredCampaign", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int bannerId) {
		ModelAndView result;
		Banner banner;

		banner = bannerService.findOne(bannerId);

		result = new ModelAndView("banner/listBannerStarredCampaign");
		result.addObject("row", banner);
		result.addObject("requestURI", "banner/listBannerStarredCampaign.do?bannerId="+bannerId);

	return result;
	
	} 

}