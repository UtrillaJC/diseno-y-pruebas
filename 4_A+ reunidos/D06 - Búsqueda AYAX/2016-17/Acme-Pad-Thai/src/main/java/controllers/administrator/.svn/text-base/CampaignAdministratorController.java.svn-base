package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BillService;
import services.CampaignService;
import controllers.AbstractController;
import domain.Campaign;

@Controller
@RequestMapping("/campaign/administrator")
public class CampaignAdministratorController extends AbstractController {

	// Services ============================================================================
	
	@Autowired
	private CampaignService campaignService;
	
	@Autowired
	private BillService billService;
	
	// Constructors ============================================================================

	public CampaignAdministratorController() {
		super();
	}
	
	// List ============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Campaign> campaigns;
		
		
		campaigns = campaignService.findAllFinished();

		result = new ModelAndView("campaign/list");
		result.addObject("campaigns", campaigns);
		result.addObject("requestURI", "campaign/administrator/list.do");

		return result;
	}
	
	// Generate bills ============================================================================

	
	@RequestMapping(value = "/generateMonthlyBill", method = RequestMethod.GET)
	public ModelAndView generateMonthlyBill( int campaignId) {
		ModelAndView result;		
		Campaign campaign;
		
		campaign = campaignService.findOne(campaignId);
		
		try {			
			billService.generateMonthyBills(campaign);
			result = list();
			result.addObject("message", "campaign.commit.ok");			
		} catch (Throwable oops) {			
			result = list();
			result.addObject("message", "campaign.commit.error");			
		}
		return result;
	}

	

}
