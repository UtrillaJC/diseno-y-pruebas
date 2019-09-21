/* AdministratorController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import domain.Administrator;

@Controller
@RequestMapping("/administrator")
public class ProfileAdministratorController extends AbstractController {
	

	@Autowired
	private AdministratorService administratorService;
	
	// Constructors =============================================================================
	
	public ProfileAdministratorController() {
		super();
	}
		
	// Edition Profile

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfile() {
		ModelAndView result;
		Administrator administrator;

		administrator = administratorService.findByPrincipal();
		Assert.notNull(administrator);

		result = createEditModelAndView(administrator);

		return result;
	}
		
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEditProfile(@ModelAttribute @Valid Administrator administrator, BindingResult binding) {
		ModelAndView result;
	
		if (binding.hasErrors()) {
			result = createEditModelAndView(administrator);
		} else {
			try {
				administratorService.saveProfile(administrator);
				result = new ModelAndView("redirect:/welcome/index.do");
				
			} catch (Throwable oops) {

				result = createEditModelAndView(administrator, "administrator.commit.error");
				}
		}
		return result;
	}
		
	// Ancillary methods
	
	protected ModelAndView createEditModelAndView(Administrator administrator) {
		ModelAndView result;	
		
		result = createEditModelAndView(administrator, null);
	
		return result;
	}

	protected ModelAndView createEditModelAndView(Administrator administrator, String message) {
		ModelAndView result;
		
		result = new ModelAndView("administrator/editProfile");
		result.addObject("administrator", administrator);
		result.addObject("message", message);
	
		return result;
	}
}