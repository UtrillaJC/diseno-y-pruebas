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

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Comment;
import domain.Lessor;
import domain.SocialIdentity;
import forms.CreditCardForm;
import forms.LessorRegistrationForm;

import services.CommentService;
import services.LessorService;

@Controller
@RequestMapping("/lessor")
public class LessorController extends AbstractController {

	// Services -------------------------------------------------------------------

	@Autowired
	private LessorService lessorService;

	@Autowired
	private CommentService commentService;
	
	

	// Constructors ---------------------------------------------------------------

	public LessorController() {
		super();
	}

	// Listing methods -----------------------------------------------------------

	//ShowUser --------------------------------------------------------------------

	@RequestMapping(value = "/showLessor", method = RequestMethod.GET)
	public ModelAndView showLessor(@RequestParam int lessorId) {
		ModelAndView result;
		Lessor lessor;
		Collection<Comment> comments;
		Collection<SocialIdentity> socialIdentities;

		comments = this.commentService.findByLessorId(lessorId);
		lessor = lessorService.findOne(lessorId);
		socialIdentities = lessor.getSocialIdentities();
	
		result = new ModelAndView("lessor/showLessor");
		result.addObject("lessor", lessor);
		result.addObject("commentsLessor", comments);
		result.addObject("socialIdentitiesLessor", socialIdentities);
		result.addObject("requestURI", "lessor/showLessor.do");
		
		return result;
	}

	//ShowAmount --------------------------------------------------------------------

	@RequestMapping(value = "/showAmount", method = RequestMethod.GET)
	public ModelAndView showAmount() {
		ModelAndView result;
		Double amount;
		Lessor principal;

		principal = lessorService.findByPrincipal();

		amount = principal.getAmount();

		result = new ModelAndView("lessor/showAmount");
		result.addObject("amount", amount);

		return result;
	}

	// Create methods --------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		LessorRegistrationForm form = new LessorRegistrationForm();
		result = createEditModelAndView(form);

		return result;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "register")
	public ModelAndView save(@ModelAttribute("form") @Valid LessorRegistrationForm form, BindingResult binding) {

		ModelAndView result;
		Lessor lessor;
		if (binding.hasErrors()) {

			if (binding.getGlobalError() != null)
				result = createEditModelAndView(form, binding.getGlobalError().getCode());
			else
				result = createEditModelAndView(form);
		} else {
			try {
				lessor = lessorService.reconstruct(form);
				Assert.isTrue(lessorService.checkCreditCard(lessor));
				lessorService.save(lessor);

				result = new ModelAndView("redirect:/");

			} catch (DataIntegrityViolationException exc) {
				result = createEditModelAndView(form, "register.duplicated.user");
			}catch (NumberFormatException esc) {
				result = createEditModelAndView(form, "register.unvalidCreditCard");
			} catch (Throwable oops) {
				result = createEditModelAndView(form, "register.password.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Lessor lessor;

		lessor = lessorService.findByPrincipal();
		result = editModelAndView(lessor);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView update(@ModelAttribute("lessor") @Valid Lessor lessor, BindingResult binding) {

		ModelAndView result;
		Lessor lessorUpdate;

		if (binding.hasErrors()) {
			result = editModelAndView(lessor);
		} else {
			try {
				lessorUpdate = lessorService.findByPrincipal();
				lessorUpdate.setName(lessor.getName());
				lessorUpdate.setSurname(lessor.getSurname());
				lessorUpdate.setEmail(lessor.getEmail());
				lessorUpdate.setPhone(lessor.getPhone());
				lessorUpdate.setPicture(lessor.getPicture());
				
							
				lessorService.update(lessorUpdate);
				
				
				result = new ModelAndView("redirect:../welcome/index.do");
				

			} catch (Throwable oops) {
				result = editModelAndView(lessor, "edit.commit.error");
			}
		}
		return result;

	}
	
	
	@RequestMapping(value = "/updateCreditCard", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("creditCard") @Valid CreditCardForm cc, BindingResult binding) {

		ModelAndView result;
		Lessor lessor = lessorService.findByPrincipal();
		
		
		if (binding.hasErrors()) {
			result = editCreditCardModel(cc);
			
		} else {
			try {
				lessorService.updateCreditCard(lessor, cc);
				lessorService.update(lessor);
				
				
				result = new ModelAndView("redirect:../welcome/index.do");
			
				
			}catch (NumberFormatException esc) {
				result = editCreditCardModel(cc,"register.unvalidCreditCard");
			} catch (Throwable oops) {
				result = editCreditCardModel(cc, "register.unvalidCreditCard");
			}
		}
		return result;

	}

	private ModelAndView editCreditCardModel(CreditCardForm cc) {
		ModelAndView result;

		result = editCreditCardModel(cc, null);

		return result;
	}

	private ModelAndView editCreditCardModel(CreditCardForm cc, String message) {
		Lessor lessor = lessorService.findByPrincipal();
		ModelAndView result;
		Collection<Comment> comments = new ArrayList<Comment>();
		Collection<SocialIdentity> socialIdentities=lessor.getSocialIdentities();
		result = new ModelAndView("actor/display");
		comments = this.commentService.findByLessorId(lessor.getId());
		Boolean validCreditCard = lessorService.checkCreditCard(lessor);
		
		result.addObject("expieredCC", !validCreditCard);
		result.addObject("actorDisplay", lessor);
		result.addObject("commentsLessorOrTenant", comments);
		result.addObject("socialIdentitiesActor", socialIdentities);
		result.addObject("requestURI", "actor/display.do");
		result.addObject("creditCard",cc);
		result.addObject("message", message);
		return result;
	}
	
	

	// Ancillary methods ---------------------------------------------------------

	protected ModelAndView createEditModelAndView(LessorRegistrationForm registrationForm) {

		ModelAndView result;

		result = createEditModelAndView(registrationForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(LessorRegistrationForm registrationForm, String message) {

		ModelAndView result;

		result = new ModelAndView("lessor/register");
		result.addObject("form", registrationForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView editModelAndView(Lessor lessor) {

		ModelAndView result;

		result = editModelAndView(lessor, null);

		return result;
	}

	protected ModelAndView editModelAndView(Lessor lessor, String message) {

		ModelAndView result;
		result = new ModelAndView("lessor/edit");
		result.addObject("lessor", lessor);
		result.addObject("message", message);
		return result;
	}
}
