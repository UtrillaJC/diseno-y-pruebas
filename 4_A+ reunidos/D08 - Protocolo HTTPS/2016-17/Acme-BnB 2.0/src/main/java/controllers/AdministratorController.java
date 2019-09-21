/*
 * AdministratorController.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Administrator;
import services.AdministratorService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {
	// Services -------------------------------------------------------------------

	@Autowired
	private AdministratorService administratorService;
	// Constructors -----------------------------------------------------------


	public AdministratorController() {
		super();
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Administrator administrator;

		administrator = administratorService.findByPrincipal();
		result = editModelAndView(administrator);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView update(@ModelAttribute("administrator") @Valid Administrator administrator, BindingResult binding) {

		ModelAndView result;
		Administrator administratorUpdate;

		if (binding.hasErrors()) {
			result = editModelAndView(administrator);
		} else {
			try {
				administratorUpdate = administratorService.findByPrincipal();
				administratorUpdate.setName(administrator.getName());
				administratorUpdate.setSurname(administrator.getSurname());
				administratorUpdate.setEmail(administrator.getEmail());
				administratorUpdate.setPhone(administrator.getPhone());
				administratorUpdate.setPicture(administrator.getPicture());
				administratorService.update(administratorUpdate);
				result = new ModelAndView("redirect:../welcome/index.do");

			} catch (Throwable oops) {
				result = editModelAndView(administrator, "edit.commit.error");
			}
		}
		return result;

	}
	// Ancillary methods ---------------------------------------------------------

	protected ModelAndView editModelAndView(Administrator administrator) {

		ModelAndView result;

		result = editModelAndView(administrator, null);

		return result;
	}

	protected ModelAndView editModelAndView(Administrator administrator, String message) {

		ModelAndView result;
		result = new ModelAndView("administrator/edit");
		result.addObject("administrator", administrator);
		result.addObject("message", message);
		return result;
	}

}
