package controllers.nutritionist;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.IngredientService;
import services.NutritionistService;
import services.PropertyService;
import controllers.AbstractController;
import domain.Ingredient;
import domain.Nutritionist;
import domain.Property;

@Controller
@RequestMapping("/ingredient/nutritionist")
public class IngredientNutritionistController extends AbstractController{
	
	@Autowired
	private NutritionistService nutritionistService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PropertyService propertyService;
	/*
	@Autowired
	private IngredientLineService ingredientLineService;
	*/
	
	//Listing --------------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Ingredient> ingredients;
		Nutritionist nutritionist;
		
		nutritionist = nutritionistService.findByPrincipal();
		Assert.notNull(nutritionist);
		ingredients = ingredientService.findAll();

		result = new ModelAndView("ingredient/list");
		result.addObject("ingredients", ingredients);
		result.addObject("requestURI", "ingredient/nutritionist/list.do");

		return result;
	}
	
	//Creation ----------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Ingredient ingredient;
		Nutritionist principal;
		
		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		ingredient = ingredientService.create();
		
		result = createEditModelAndView(ingredient);
		
		return result;
	}
	
	// Edit Ingredient

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int ingredientId) {
		ModelAndView result;
		Ingredient ingredient;
		Nutritionist nutritionist;

		nutritionist = nutritionistService.findByPrincipal();
		Assert.notNull(nutritionist);
		ingredient = ingredientService.findOne(ingredientId);

		result = createEditModelAndView(ingredient);

		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Ingredient ingredient, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(ingredient);
		}else{
			try{
				
				ingredientService.save(ingredient);
				result = new ModelAndView("redirect:/ingredient/nutritionist/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(ingredient, "ingredient.commit.error");
			}
		}
		return result;
	}
	
	//Deleting -------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Ingredient ingredient, BindingResult binding){
		ModelAndView result;
		
		try{
			ingredientService.delete(ingredient);
			result = new ModelAndView("redirect:/ingredient/nutritionist/list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(ingredient, "ingredient.commit.error");
		}
		return result;
	}
	
	//ShowIngredient --------------------------------------------------------------------
	
	@RequestMapping(value = "/showIngredient", method = RequestMethod.GET)
	public ModelAndView showIngredient(@RequestParam int ingredientId) {
		ModelAndView result;
		Ingredient ingredient;
		Collection<Property> properties;

		ingredient = ingredientService.findOne(ingredientId);
		properties = propertyService.findAllByIngredient(ingredient);
		
		result = new ModelAndView("ingredient/showIngredient");
		result.addObject("ingredient", ingredient);
		result.addObject("properties",properties);

		return result;
	}
	
	// Ancillary methods
	
	protected ModelAndView createEditModelAndView(Ingredient ingredient) {
		ModelAndView result;

		result = createEditModelAndView(ingredient, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Ingredient ingredient, String message) {
		ModelAndView result;

		result = new ModelAndView("ingredient/edit");

		result.addObject("ingredient", ingredient);
		result.addObject("message", message);
		result.addObject("requestURI", "ingredient/edit.do");

		return result;
	}
	
	@RequestMapping(value = "/addProperty", method = RequestMethod.GET)
	public ModelAndView addProperty(@RequestParam int ingredientId) {
		ModelAndView result;
		Collection<Property> properties;
		Ingredient ingredient;
		boolean addProperty;

		addProperty = true;
		ingredient = ingredientService.findOne(ingredientId);
		properties = propertyService.findAllByNotIngredient(ingredient);

		result = new ModelAndView("property/list");
		result.addObject("properties", properties);
		result.addObject("requestURI", "property/nutritionist/list.do?ingredientId="+ ingredientId);
		result.addObject("ingredientId", ingredientId);
		result.addObject("addProperty", addProperty);

		return result;

	}

}
