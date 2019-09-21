/* SponsorController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.sponsor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SponsorService;
import controllers.AbstractController;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor")
public class ProfileSponsorController extends AbstractController {
	

	@Autowired
	private SponsorService sponsorService;
	
	// Constructors =============================================================================
	
	public ProfileSponsorController() {
		super();
	}
		
	// Edition Profile

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfile() {
		ModelAndView result;
		Sponsor sponsor;

		sponsor = sponsorService.findByPrincipal();
		Assert.notNull(sponsor);

		result = createEditModelAndView(sponsor);

		return result;
	}
		
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEditProfile(@ModelAttribute @Valid Sponsor sponsor, BindingResult binding) {
		ModelAndView result;
	
		if (binding.hasErrors()) {
			result = createEditModelAndView(sponsor);
		} else {
			try {
				sponsorService.saveProfile(sponsor);
				result = new ModelAndView("redirect:/welcome/index.do");
				
			} catch (Throwable oops) {

				result = createEditModelAndView(sponsor, "sponsor.commit.error");
				}
		}
		return result;
	}
		
	// Ancillary methods
	
	protected ModelAndView createEditModelAndView(Sponsor sponsor) {
		ModelAndView result;	
		
		result = createEditModelAndView(sponsor, null);
	
		return result;
	}

	protected ModelAndView createEditModelAndView(Sponsor sponsor, String message) {
		ModelAndView result;
		
		result = new ModelAndView("sponsor/editProfile");
		result.addObject("sponsor", sponsor);
		result.addObject("message", message);
	
		return result;
	}
}