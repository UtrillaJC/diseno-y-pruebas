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
@RequestMapping("/property/nutritionist")
public class PropertyNutritionistController extends AbstractController{
	
	@Autowired
	private NutritionistService nutritionistService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PropertyService propertyService;
	
	//Listing --------------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Property> properties;
		Nutritionist nutritionist;
		boolean addProperty;
		
		addProperty = false;
		nutritionist = nutritionistService.findByPrincipal();
		Assert.notNull(nutritionist);
		properties = propertyService.findAll();

		result = new ModelAndView("property/list");
		result.addObject("properties", properties);
		result.addObject("addProperty", addProperty);
		result.addObject("requestURI", "property/nutritionist/list.do");

		return result;
	}
	
	//Creation ----------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Property property;
		Nutritionist principal;
		
		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		property = propertyService.create();
		
		result = createEditModelAndView(property);
		
		return result;
	}
	
	// Edit Property

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int propertyId) {
		ModelAndView result;
		Property property;
		Nutritionist nutritionist;

		nutritionist = nutritionistService.findByPrincipal();
		Assert.notNull(nutritionist);
		property = propertyService.findOne(propertyId);

		result = createEditModelAndView(property, "property/edit");

		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Property property, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(property);
		}else{
			try{
				
				propertyService.save(property);
				result = new ModelAndView("redirect:/property/nutritionist/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(property, "property.commit.error");
			}
		}
		return result;
	}
	
	//Deleting -------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Property property, BindingResult binding){
		ModelAndView result;
		
		try{
			propertyService.delete(property);
			result = new ModelAndView("redirect:/property/nutritionist/list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(property, "property.commit.error");
		}
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addProperty(@RequestParam int propertyId, @RequestParam int ingredientId) {
		ModelAndView result;
		Property property;
		Ingredient ingredient;

		property = propertyService.findOne(propertyId);
		ingredient = ingredientService.findOne(ingredientId);
		
		ingredientService.addProperty(ingredient, property);
		result = new ModelAndView("redirect:/ingredient/nutritionist/showIngredient.do?ingredientId="+ingredientId);

		return result;

	}
	
	// Ancillary methods
	
	protected ModelAndView createEditModelAndView(Property property) {
		ModelAndView result;

		result = createEditModelAndView(property, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Property property, String errorMessage) {
		ModelAndView result;

		result = new ModelAndView("property/edit");

		result.addObject("property", property);
		result.addObject("errorMessage", errorMessage);
		result.addObject("requestURI", "property/edit.do");

		return result;
	}

}
