package controllers.user;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.IngredientLineService;
import services.IngredientService;
import services.RecipeService;
import controllers.AbstractController;
import domain.Ingredient;
import domain.IngredientLine;
import domain.Recipe;

@Controller
@RequestMapping("/ingredientLine/user")
public class IngredientLineUserController extends AbstractController {

	// Services ============================================================================

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private IngredientLineService ingredientLineService;
	
	// Constructors ========================================================================

	public IngredientLineUserController() {
		super();
	}
	
	// Create ========================================================================
	
	@RequestMapping(value = "/addIngredientLine", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int recipeId) {
		ModelAndView result;
		IngredientLine ingredientLine;
		Recipe recipe;
			
		recipe = recipeService.findOne(recipeId);
		ingredientLine = ingredientLineService.create(recipe);
		Assert.notNull(recipe);
		
		result = createEditModelAndView(ingredientLine);

		return result;
	}
	
	// Save step to cook ======================================================================================
	
	@RequestMapping(value = "/addIngredientLine", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute IngredientLine ingredientLine, BindingResult bindingResult){
		ModelAndView result;
		
		if (bindingResult.hasErrors()) {
			result = createEditModelAndView(ingredientLine);
		} else {
			try {			
				ingredientLineService.save(ingredientLine);
				result = new ModelAndView("redirect:/recipe/showRecipe.do?recipeId=" + ingredientLine.getRecipe().getId());
			} catch (Throwable oops) {
				result = createEditModelAndView(ingredientLine, "ingredientLine.commit.error");				
			}
		}	
		return result;
	}
		
	// Ancillary methods =============================================================================
	
	protected ModelAndView createEditModelAndView(IngredientLine ingredientLine) {
		assert ingredientLine != null;
		
		ModelAndView result;

		result = createEditModelAndView(ingredientLine, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(IngredientLine ingredientLine, String message) {
		assert ingredientLine != null;
	
		ModelAndView result;				
		Collection<Ingredient> ingredients;
		Collection<String> units;
		
		units = new HashSet<String>();
		ingredients = ingredientService.findAll();
		
		units.add("GRAMS");
		units.add("KILOGRAMS");
		units.add("OUNCES");
		units.add("POUNDS");
		units.add("MILILITRES");
		units.add("LITRES");
		units.add("SPOONS");
		units.add("CUPS");
		units.add("PIECES");	

		result = new ModelAndView("ingredientLine/edit");
		result.addObject("ingredientLine", ingredientLine);
		result.addObject("units", units);
		result.addObject("ingredients", ingredients);
		result.addObject("message", message);
		
		return result;
	}
}
