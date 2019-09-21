package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CommentService;
import services.LessorService;
import domain.Actor;
import domain.Comment;
import domain.Lessor;
import domain.SocialIdentity;
import domain.Tenant;
import forms.CreditCardForm;


@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController{
	
	// Services -------------------------------------------------------------------

		@Autowired
		private ActorService actorService;
		
		@Autowired
		private CommentService commentService;
		
		@Autowired
		private LessorService lessorService;
		
		// Constructors -----------------------------------------------------------


		public ActorController() {
			super();
		}
	
	@RequestMapping(value="/display", method = RequestMethod.GET)
	public ModelAndView display(){

		ModelAndView result;
		Actor actor;
		Collection<Comment> comments = new ArrayList<Comment>();
		Collection<SocialIdentity> socialIdentities;

		actor = this.actorService.findByPrincipal();
		socialIdentities = actor.getSocialIdentities();
		
		result = new ModelAndView("actor/display");
		
		if(actor instanceof Lessor){
			comments = this.commentService.findByLessorId(actor.getId());
			Boolean validCreditCard = lessorService.checkCreditCard((Lessor) actor);
			CreditCardForm creditCard = new CreditCardForm();
			result.addObject("expieredCC", !validCreditCard);
			result.addObject("creditCard", creditCard);
			}
		if(actor instanceof Tenant){
			comments = this.commentService.findByTenantId(actor.getId());
		}
		
		
		result.addObject("actorDisplay", actor);
		result.addObject("commentsLessorOrTenant", comments);
		result.addObject("socialIdentitiesActor", socialIdentities);
		result.addObject("requestURI", "actor/display.do");
		
		return result;
	}

}
