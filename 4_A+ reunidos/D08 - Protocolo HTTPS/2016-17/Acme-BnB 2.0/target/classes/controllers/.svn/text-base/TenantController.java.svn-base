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

import domain.Tenant;
import forms.RegistrationForm;
import services.TenantService;

@Controller
@RequestMapping("/tenant")
public class TenantController extends AbstractController {

	// Services -------------------------------------------------------------------

	@Autowired
	private TenantService tenantService;


	// Constructors ---------------------------------------------------------------

	public TenantController() {
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
		Tenant tenant;

		if (binding.hasErrors()) {

			if (binding.getGlobalError() != null)
				result = createEditModelAndView(form, binding.getGlobalError().getCode());
			else
				result = createEditModelAndView(form);
		} else {
			try {
				tenant = tenantService.reconstruct(form);

				tenantService.save(tenant);

				result = new ModelAndView("redirect:/");

			} catch (DataIntegrityViolationException exc) {
				result = createEditModelAndView(form, "register.duplicated.user");
			} catch (Throwable oops) {
				result = createEditModelAndView(form, "register.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Tenant tenant;

		tenant = tenantService.findByPrincipal();
		result = editModelAndView(tenant);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView update(@ModelAttribute("tenant") @Valid Tenant tenant, BindingResult binding) {

		ModelAndView result;
		Tenant tenantUpdate;

		if (binding.hasErrors()) {
			result = editModelAndView(tenant);
		} else {
			try {
				tenantUpdate = tenantService.findByPrincipal();
				tenantUpdate.setName(tenant.getName());
				tenantUpdate.setSurname(tenant.getSurname());
				tenantUpdate.setEmail(tenant.getEmail());
				tenantUpdate.setPhone(tenant.getPhone());
				tenantUpdate.setPicture(tenant.getPicture());
				tenantService.update(tenantUpdate);
				result = new ModelAndView("redirect:../welcome/index.do");

			} catch (Throwable oops) {
				result = editModelAndView(tenant, "edit.commit.error");
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

		result = new ModelAndView("tenant/register");
		result.addObject("form", registrationForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView editModelAndView(Tenant tenant) {

		ModelAndView result;

		result = editModelAndView(tenant, null);

		return result;
	}

	protected ModelAndView editModelAndView(Tenant tenant, String message) {

		ModelAndView result;
		result = new ModelAndView("tenant/edit");
		result.addObject("tenant", tenant);
		result.addObject("message", message);
		return result;
	}

}
