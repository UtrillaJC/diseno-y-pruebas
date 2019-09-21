package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.BillService;
import services.SponsorService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Bill;
import domain.Sponsor;

@Controller
@RequestMapping("/bill/administrator")
public class BillAdministratorController extends AbstractController{
	
	// Services ---------------------------------------------------------------
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private SponsorService sponsorService;
	
		
	// Constructors -----------------------------------------------------------

	public BillAdministratorController(){
		super();
	}
		
	//List --------------------------------------------------------------------
		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Bill> bills;
		Administrator administrator;
		boolean paid;

		administrator = administratorService.findByPrincipal();
		Assert.notNull(administrator);
		bills = billService.findAllBillsUnpaid();
		if(bills.isEmpty()){
			paid=true;
		}else{
			paid=false;
		}

		result = new ModelAndView("bill/sponsor/list");
		result.addObject("bills", bills);
		result.addObject("requestURI", "bill/administrator/sendMessage.do");
		result.addObject("paid", paid);

		return result;
	
	}
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
	public ModelAndView sendMessage() {
		ModelAndView result;
		
		try {			
			administratorService.sendABulk();
			result = list();
			result.addObject("message", "sendBulkMessage.commit.ok");	
		} catch (Throwable oops) {	
			result = list();
			result.addObject("message", "sendBulkMessage.commit.error");	

		}

		return result;

	}
		
	//ShowBill --------------------------------------------------------------------
	
		@RequestMapping(value = "/showBill", method = RequestMethod.GET)
		public ModelAndView showRecipe(@RequestParam int sponsorId) {
			ModelAndView result;
			Sponsor sponsor;
			Double getCostTotal;
			
			sponsor = sponsorService.findOne(sponsorId);
			getCostTotal = administratorService.getCostTotal(sponsorId);
			
			result = new ModelAndView("bill/showBill");
			result.addObject("sponsor", sponsor);
			result.addObject("getCostTotal",getCostTotal);

			return result;
		}
}

