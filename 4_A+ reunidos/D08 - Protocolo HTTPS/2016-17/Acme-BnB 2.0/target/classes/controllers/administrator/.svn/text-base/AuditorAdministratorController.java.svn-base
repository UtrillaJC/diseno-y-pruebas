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

package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Auditor;
import forms.AuditorRegistrationForm;
import services.AuditorService;

@Controller
@RequestMapping("/auditor/administrator")
public class AuditorAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------------

	@Autowired
	private AuditorService auditorService;


	// Constructors ---------------------------------------------------------------

	public AuditorAdministratorController() {
		super();
	}

	// Listing methods -----------------------------------------------------------

	// Create methods --------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		AuditorRegistrationForm form = new AuditorRegistrationForm();
		result = createEditModelAndView(form);

		return result;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "register")
	public ModelAndView save(@ModelAttribute("form") @Valid AuditorRegistrationForm form, BindingResult binding) {

		ModelAndView result;
		Auditor auditor;

		if (binding.hasErrors()) {

			if (binding.getGlobalError() != null)
				result = createEditModelAndView(form, binding.getGlobalError().getCode());
			else
				result = createEditModelAndView(form);
		} else {
			try {
				auditor = auditorService.reconstruct(form);

				auditorService.save(auditor);

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

	protected ModelAndView createEditModelAndView(AuditorRegistrationForm registrationForm) {

		ModelAndView result;

		result = createEditModelAndView(registrationForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(AuditorRegistrationForm registrationForm, String message) {

		ModelAndView result;

		result = new ModelAndView("auditor/administrator/register");
		result.addObject("form", registrationForm);
		result.addObject("message", message);

		return result;
	}

}
