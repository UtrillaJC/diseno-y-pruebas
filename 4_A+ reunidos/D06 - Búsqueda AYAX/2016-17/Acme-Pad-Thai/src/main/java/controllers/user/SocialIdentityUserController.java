/* UserController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import controllers.AbstractController;
import domain.User;

@Controller
@RequestMapping("/socialIdentity/user")
public class SocialIdentityUserController extends AbstractController {
	

	@Autowired
	private UserService userService;
	
	// Constructors =============================================================================
	
	public SocialIdentityUserController() {
		super();
	}
	
	// Edition Profile

	@RequestMapping(value = "/editSocialIdentity", method = RequestMethod.GET)
	public ModelAndView editSocialIdentity() {
		ModelAndView result;
		User user;

		user = userService.findByPrincipal();
		Assert.notNull(user);

		result = createEditModelAndView(user);

		return result;
	}
		
	@RequestMapping(value = "/editSocialIdentity", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEditProfile(@ModelAttribute @Valid User user, BindingResult binding) {
		ModelAndView result;
	
		if (binding.hasErrors()) {
			result = createEditModelAndView(user);
		} else {
			try {
				userService.saveProfile(user);
				result = new ModelAndView("redirect:/welcome/index.do");
				
			} catch (Throwable oops) {

				result = createEditModelAndView(user, "user.commit.error");
				}
		}
		return result;
	}
		
	// Ancillary methods
	
	protected ModelAndView createEditModelAndView(User user) {
		ModelAndView result;	
		
		result = createEditModelAndView(user, null);
	
		return result;
	}

	protected ModelAndView createEditModelAndView(User user, String message) {
		ModelAndView result;
		
		result = new ModelAndView("user/editSocialIdentity");
		result.addObject("user", user);
		result.addObject("message", message);
	
		return result;
	}

}