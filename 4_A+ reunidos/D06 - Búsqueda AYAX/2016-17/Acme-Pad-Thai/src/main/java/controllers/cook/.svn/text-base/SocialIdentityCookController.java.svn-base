/* CookController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.cook;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CookService;

import controllers.AbstractController;
import domain.Cook;

@Controller
@RequestMapping("/socialIdentity/cook")
public class SocialIdentityCookController extends AbstractController {
	

	@Autowired
	private CookService cookService;
	
	// Constructors =============================================================================
	
	public SocialIdentityCookController() {
		super();
	}
	
	// Edition Profile

	@RequestMapping(value = "/editSocialIdentity", method = RequestMethod.GET)
	public ModelAndView editSocialIdentity() {
		ModelAndView result;
		Cook cook;

		cook = cookService.findByPrincipal();
		Assert.notNull(cook);

		result = createEditModelAndView(cook);

		return result;
	}
		
	@RequestMapping(value = "/editSocialIdentity", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEditProfile(@ModelAttribute @Valid Cook cook, BindingResult binding) {
		ModelAndView result;
	
		if (binding.hasErrors()) {
			result = createEditModelAndView(cook);
		} else {
			try {
				cookService.saveProfile(cook);
				result = new ModelAndView("redirect:/welcome/index.do");
				
			} catch (Throwable oops) {

				result = createEditModelAndView(cook, "cook.commit.error");
				}
		}
		return result;
	}
		
	// Ancillary methods
	
	protected ModelAndView createEditModelAndView(Cook cook) {
		ModelAndView result;	
		
		result = createEditModelAndView(cook, null);
	
		return result;
	}

	protected ModelAndView createEditModelAndView(Cook cook, String message) {
		ModelAndView result;
		
		result = new ModelAndView("cook/editSocialIdentity");
		result.addObject("cook", cook);
		result.addObject("message", message);
	
		return result;
	}

}