/*
 * CustomerController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import forms.RegistrationForm;
import security.UserAccount;
import services.UserAccountService;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	// Services -------------------------------------------------------------------

	@Autowired
	private UserAccountService userAccountService;


	// Constructors ---------------------------------------------------------------

	public UserController() {
		super();
	}

	// Listing methods -----------------------------------------------------------

	// Create methods --------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		RegistrationForm form = new RegistrationForm();
		result = createEditModelAndView(form);

		return result;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "register")
	public ModelAndView save(@ModelAttribute("form") @Valid RegistrationForm form, BindingResult binding) {

		ModelAndView result;
		UserAccount userAccount;

		if (binding.hasErrors()) {

			if (binding.getGlobalError() != null)
				result = createEditModelAndView(form, binding.getGlobalError().getCode());
			else
				result = createEditModelAndView(form);
		} else {
			try {
				userAccount = userAccountService.reconstruct(form);
				userAccountService.save(userAccount);
				result = new ModelAndView("redirect:/");

			} catch (DataIntegrityViolationException exc) {
				result = createEditModelAndView(form, "register.duplicated.user");
			} catch (Throwable oops) {
				result = createEditModelAndView(form, "register.commit.error");
			}
		}

		return result;
	}

	// Ancillary methods ---------------------------------------------------------

	protected ModelAndView createEditModelAndView(RegistrationForm registrationForm) {

		ModelAndView result;

		result = createEditModelAndView(registrationForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(RegistrationForm registrationForm, String message) {

		ModelAndView result;

		result = new ModelAndView("user/register");
		result.addObject("form", registrationForm);
		result.addObject("message", message);

		return result;
	}

}
