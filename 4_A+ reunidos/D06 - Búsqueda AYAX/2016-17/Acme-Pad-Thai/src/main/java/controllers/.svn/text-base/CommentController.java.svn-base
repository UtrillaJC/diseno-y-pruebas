/* CustomerController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.RecipeService;
import domain.Comment;
import domain.Recipe;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	//Services ----------------------------------------------------------------
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private RecipeService recipeService;
	
	//Constructor ----------------------------------------------------------------
	
	public CommentController(){
		super();
	}
	
	//Listing --------------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int recipeId) {
		ModelAndView result;
		Collection<Comment> comments;
		
		comments = commentService.findAllByMomentCreatedDesc();

		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("actor", "");
		
		return result;
	}
	
	//Creation --------------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int recipeId) {
		ModelAndView result;
		Comment comment;
		Recipe recipe;
	
		recipe = recipeService.findOne(recipeId);
		comment = commentService.create(recipe);
		
		result = createEditModelAndView(comment);

		return result;
	}

	//Saving ----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Comment comment, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(comment);
		}else{
			try{
				commentService.save(comment);
								
				result = new ModelAndView("redirect:/welcome/index.do");
				
			}catch(Throwable oops){
				result = createEditModelAndView(comment, "comment.commit.error");
			}
		}
		return result;
	}
	
	// The ancillary methods ----------------------------------------------------------------

	protected ModelAndView createEditModelAndView(Comment comment) {
		ModelAndView result;

		result = createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Comment comment, String message) {
		ModelAndView result;

		result = new ModelAndView("comment/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);

		return result;
	}
	
	
}