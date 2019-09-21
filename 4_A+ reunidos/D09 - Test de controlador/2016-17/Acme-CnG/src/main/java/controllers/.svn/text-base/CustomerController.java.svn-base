/*
 * CustomerController.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
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

import domain.Customer;
import forms.RegisterForm;
import services.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	// Services -------------------------------------------------------------------

	@Autowired
	private CustomerService customerService;


	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// Listing methods -----------------------------------------------------------

	// Create methods --------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		final RegisterForm form = new RegisterForm();
		result = this.createEditModelAndView(form);

		return result;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "register")
	public ModelAndView save(@ModelAttribute("form") @Valid final RegisterForm form, final BindingResult binding) {

		ModelAndView result;
		Customer customer;

		if (binding.hasErrors()) {

			if (binding.getGlobalError() != null)
				result = this.createEditModelAndView(form, binding.getGlobalError().getCode());
			else
				result = this.createEditModelAndView(form);
		} else
			try {
				customer = this.customerService.reconstruct(form);

				this.customerService.save(customer);

				result = new ModelAndView("redirect:/");

			} catch (final DataIntegrityViolationException exc) {
				result = this.createEditModelAndView(form, "register.duplicated.user");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(form, "register.commit.error");
			}

		return result;
	}
	// Ancillary methods ---------------------------------------------------------

	protected ModelAndView createEditModelAndView(final RegisterForm registerForm) {

		ModelAndView result;

		result = this.createEditModelAndView(registerForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final RegisterForm registerForm, final String message) {

		ModelAndView result;

		result = new ModelAndView("customer/register");
		result.addObject("form", registerForm);
		result.addObject("message", message);

		return result;
	}
}
