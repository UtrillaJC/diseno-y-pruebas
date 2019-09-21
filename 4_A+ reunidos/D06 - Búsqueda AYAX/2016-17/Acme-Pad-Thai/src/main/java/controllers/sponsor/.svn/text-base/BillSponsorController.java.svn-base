package controllers.sponsor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BillService;
import services.SponsorService;
import controllers.AbstractController;
import domain.Bill;
import domain.Sponsor;

@Controller
@RequestMapping("/bill/sponsor")
public class BillSponsorController extends AbstractController{
	
	// Services ---------------------------------------------------------------
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private SponsorService sponsorService;
	
	// Constructors -----------------------------------------------------------

	public BillSponsorController(){
		super();
	}
		
	//List paid ==================================================================
		
	@RequestMapping(value = "/list-paid", method = RequestMethod.GET)
	public ModelAndView listPaid() {
		ModelAndView result;
		Collection<Bill> bills;
		Sponsor sponsor;
		boolean paid;
		
		paid = true;
		sponsor = sponsorService.findByPrincipal();
		bills = billService.findAllBySponsorPaid(sponsor);

		result = new ModelAndView("bill/sponsor/list");
		result.addObject("bills", bills);
		result.addObject("paid", paid);
		result.addObject("requestURI", "bill/sponsor/list-paid.do");

		return result;
	}
	
	//List paid ==================================================================
	
	@RequestMapping(value = "/list-unpaid", method = RequestMethod.GET)
	public ModelAndView listUnpaid() {
		ModelAndView result;
		Collection<Bill> bills;
		Sponsor sponsor;
		boolean paid;
		
		paid = false;
		sponsor = sponsorService.findByPrincipal();
		bills = billService.findAllBySponsorNotPaid(sponsor);

		result = new ModelAndView("bill/sponsor/list");
		result.addObject("bills", bills);
		result.addObject("paid", paid);
		result.addObject("requestURI", "bill/sponsor/list-unpaid.do");

		return result;
	}
	
	//Pay ==================================================================
	
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public ModelAndView pay(@RequestParam int billId) {
		ModelAndView result;		
		
		try {			
			billService.pay(billId);
			result = listUnpaid();
			result.addObject("message", "bill.commit.ok");			
		} catch (Throwable oops) {			
			result = listUnpaid();
			result.addObject("message", "bill.commit.error");			
		}
		return result;
	}
}

