/* NutritionistController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.nutritionist;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.NutritionistService;
import controllers.AbstractController;
import domain.Nutritionist;

@Controller
@RequestMapping("/socialIdentity/nutritionist")
public class SocialIdentityNutritionistController extends AbstractController {
	

	@Autowired
	private NutritionistService nutritionistService;
	
	// Constructors =============================================================================
	
	public SocialIdentityNutritionistController() {
		super();
	}
	
	// Edition Profile

	@RequestMapping(value = "/editSocialIdentity", method = RequestMethod.GET)
	public ModelAndView editSocialIdentity() {
		ModelAndView result;
		Nutritionist nutritionist;

		nutritionist = nutritionistService.findByPrincipal();
		Assert.notNull(nutritionist);

		result = createEditModelAndView(nutritionist);

		return result;
	}
		
	@RequestMapping(value = "/editSocialIdentity", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEditProfile(@ModelAttribute @Valid Nutritionist nutritionist, BindingResult binding) {
		ModelAndView result;
	
		if (binding.hasErrors()) {
			result = createEditModelAndView(nutritionist);
		} else {
			try {
				nutritionistService.saveProfile(nutritionist);
				result = new ModelAndView("redirect:/welcome/index.do");
				
			} catch (Throwable oops) {

				result = createEditModelAndView(nutritionist, "nutritionist.commit.error");
				}
		}
		return result;
	}
		
	// Ancillary methods
	
	protected ModelAndView createEditModelAndView(Nutritionist nutritionist) {
		ModelAndView result;	
		
		result = createEditModelAndView(nutritionist, null);
	
		return result;
	}

	protected ModelAndView createEditModelAndView(Nutritionist nutritionist, String message) {
		ModelAndView result;
		
		result = new ModelAndView("nutritionist/editSocialIdentity");
		result.addObject("nutritionist", nutritionist);
		result.addObject("message", message);
	
		return result;
	}

}