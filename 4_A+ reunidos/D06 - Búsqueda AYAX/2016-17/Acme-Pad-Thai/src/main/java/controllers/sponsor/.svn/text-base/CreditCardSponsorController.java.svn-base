package controllers.sponsor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CreditCardService;
import services.SponsorService;
import controllers.AbstractController;
import domain.CreditCard;
import domain.Sponsor;

@Controller
@RequestMapping("/creditCard/sponsor")
public class CreditCardSponsorController extends AbstractController{

	//Services ===============================================================================
	
	@Autowired
	private CreditCardService creditCardService;
	
	@Autowired
	private SponsorService sponsorService;
	
	//Constructor ============================================================================
	
	public CreditCardSponsorController(){
		super();
	}
	
	//List ===================================================================================
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView listBySponsor(){
		ModelAndView result;
		CreditCard creditCard;
		Sponsor principal;
		int sponsorId;
		
		
		principal = sponsorService.findByPrincipal();
		sponsorId = principal.getId();
		
		creditCard = creditCardService.findBySponsorId(sponsorId);

		result = new ModelAndView("creditCard/list");
		result.addObject("creditCard", creditCard);
		
		return result;
	}
	
	// Edit ======================================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int creditCardId){
		ModelAndView result;
		CreditCard creditCard;
		
		creditCard = creditCardService.findOne(creditCardId);
		Assert.notNull(creditCard);
	
		result = createEditModelAndView(creditCard);
		
		return result;
	}
	
	// Edit ======================================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute CreditCard creditCard, BindingResult bindingResult){
		ModelAndView result;
			
		if (bindingResult.hasErrors()) {
			result = createEditModelAndView(creditCard);
		} else {
			try {			
				creditCardService.save(creditCard);
				result = new ModelAndView("redirect:view.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(creditCard, "creditCard.commit.error");				
			}
		}	
		return result;
	}
		
	
	
	//Ancillary Methods
	
	// Ancillary methods =============================================================================
	
		protected ModelAndView createEditModelAndView(CreditCard creditCard) {
			assert creditCard != null;
			
			ModelAndView result;

			result = createEditModelAndView(creditCard, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(CreditCard creditCard, String message) {
			assert creditCard != null;
			
			ModelAndView result;				

			result = new ModelAndView("creditCard/sponsor/edit");
			result.addObject("creditCard", creditCard);
			result.addObject("message", message);
			
			return result;
		}
}