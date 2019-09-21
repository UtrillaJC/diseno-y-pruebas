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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Auditor;
import services.AuditorService;

@Controller
@RequestMapping("/auditor")
public class AuditorController extends AbstractController {

	// Services -------------------------------------------------------------------

	@Autowired
	private AuditorService auditorService;


	// Constructors ---------------------------------------------------------------

	public AuditorController() {
		super();
	}

	// Listing methods -----------------------------------------------------------

	// Create methods --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Auditor auditor;

		auditor = auditorService.findByPrincipal();
		result = editModelAndView(auditor);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView update(@ModelAttribute("auditor") @Valid Auditor auditor, BindingResult binding) {

		ModelAndView result;
		Auditor auditorUpdate;

		if (binding.hasErrors()) {
			result = editModelAndView(auditor);
		} else {
			try {
				auditorUpdate = auditorService.findByPrincipal();
				auditorUpdate.setCompanyName(auditor.getCompanyName());
				auditorUpdate.setName(auditor.getName());
				auditorUpdate.setSurname(auditor.getSurname());
				auditorUpdate.setEmail(auditor.getEmail());
				auditorUpdate.setPhone(auditor.getPhone());
				auditorUpdate.setPicture(auditor.getPicture());
				auditorService.update(auditorUpdate);
				result = new ModelAndView("redirect:../welcome/index.do");

			} catch (Throwable oops) {
				result = editModelAndView(auditor, "edit.commit.error");
			}
		}
		return result;

	}

	// Ancillary methods ---------------------------------------------------------

	protected ModelAndView editModelAndView(Auditor auditor) {

		ModelAndView result;

		result = editModelAndView(auditor, null);

		return result;
	}

	protected ModelAndView editModelAndView(Auditor auditor, String message) {

		ModelAndView result;
		result = new ModelAndView("auditor/edit");
		result.addObject("auditor", auditor);
		result.addObject("message", message);
		return result;
	}

}
