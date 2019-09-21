/* ActorController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.actor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SocialIdentityService;
import controllers.AbstractController;
import domain.Actor;
import domain.SocialIdentity;

@Controller
@RequestMapping("/actor")
public class SocialIdentityActorController extends AbstractController {
	

	@Autowired
	private SocialIdentityService socialIdentityService;
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------
	
	public SocialIdentityActorController() {
		super();
	}
		
	// Edition SocialIdentity

	@RequestMapping(value = "/editSocialIdentity", method = RequestMethod.GET)
	public ModelAndView editSocialIdentity() {
		ModelAndView result;
		SocialIdentity socialIdentity;
		Actor actor;
		
		actor = actorService.findByPrincipal();
		socialIdentity = actor.getSocialIdentity();
		
		result = createEditModelAndView(socialIdentity);

		return result;
	}
		
	@RequestMapping(value = "/editSocialIdentity", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEditSocialIdentity(@Valid SocialIdentity socialIdentity, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(socialIdentity);
		} else {
			try {				
				socialIdentityService.save(socialIdentity);
				result = new ModelAndView("redirect:/welcome/index.do");
				
			} catch (Throwable oops) {

				result = createEditModelAndView(socialIdentity, "socialIdentity.commit.error");
			}
		}
		return result;
	}
		
	// Ancillary methods
	
	protected ModelAndView createEditModelAndView(SocialIdentity socialIdentity) {
		ModelAndView result;
	
		result = createEditModelAndView(socialIdentity, null);
	
		return result;
	}

	protected ModelAndView createEditModelAndView(SocialIdentity socialIdentity, String message) {
		ModelAndView result;
	
		result = new ModelAndView("actor/editSocialIdentity");
	
		result.addObject("socialIdentity", socialIdentity);
		result.addObject("errorMessage", message);
		result.addObject("requestURI", "actor/editSocialIdentity.do");
	
		return result;
	}
}