package controllers.tenant;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import domain.Actor;
import domain.Comment;
import domain.Lessor;
import domain.Tenant;

import services.CommentService;
import services.LessorService;
import services.TenantService;

@Controller
@RequestMapping("/comment/tenant")
public class CommentTenantController extends AbstractController{
	
	// Services -------------------------------------------------------------------
	
				@Autowired
				private CommentService commentService;
				
				@Autowired
				private LessorService lessorService;
				
				@Autowired
				private TenantService tenantService;
				
				// Constructors ---------------------------------------------------------------
				
				public CommentTenantController(){
					super();
				}
				
				
				// CRUD methods -----------------------------------------------------------
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		Comment comment;
		
		comment = commentService.create();
		result = createEditModelAndView(comment);
		
		return result;
		
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Comment comment, BindingResult binding){
		ModelAndView result;
		
		
		if(binding.hasErrors()){
			result = createEditModelAndView(comment);
			}else{
				try{
					commentService.save(comment);
					result = new ModelAndView("redirect:../../welcome/index.do");
						
				}catch(Throwable oops){
					result = createEditModelAndView(comment, "comment.commit.error");
				}
			}
		
		return result;
	}
	
	// Ancillary methods ---------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Comment comment) {
		ModelAndView result;

		result = createEditModelAndView(comment, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Comment comment, String message){
		ModelAndView result;
		Collection<Lessor> lessors;
		Tenant principal = this.tenantService.findByPrincipal();
		Collection<Actor> lessorsAndTenant = new ArrayList<Actor>();

		lessors = this.lessorService.findByTenantPrincipal();
		lessorsAndTenant.addAll(lessors);
		lessorsAndTenant.add(principal);
		
		result = new ModelAndView("comment/tenant/create");
		result.addObject("commentTenant", comment);
		result.addObject("message", message);
		result.addObject("receiversTenant", lessorsAndTenant);
		
		return result;
	}


}
