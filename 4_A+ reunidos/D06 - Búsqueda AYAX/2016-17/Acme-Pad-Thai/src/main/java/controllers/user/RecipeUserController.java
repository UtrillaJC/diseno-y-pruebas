package controllers.user;

import java.util.Collection;

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

import services.BannerService;
import services.CategoryService;
import services.CommentService;
import services.IngredientLineService;
import services.IngredientService;
import services.RecipeService;
import services.StepToCookService;
import services.UserService;
import controllers.AbstractController;
import domain.Banner;
import domain.Category;
import domain.Comment;
import domain.Ingredient;
import domain.IngredientLine;
import domain.Recipe;
import domain.StepToCook;
import domain.User;


@Controller
@RequestMapping("/recipe/user")
public class RecipeUserController extends AbstractController  {
	
	// Services ============================================================================

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private IngredientLineService ingredientLineService;
	
	@Autowired
	private StepToCookService stepToCookService;
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private CommentService commentService;
	
	// Constructors ========================================================================

	public RecipeUserController() {
		super();
	}
		
	//Listing By User ======================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listByUser(){
		ModelAndView result;
		Collection<Recipe> recipes;
		User user;
		int userId;
		
		user = userService.findByPrincipal();
		userId = user.getId();
		recipes = recipeService.findAllByUser(userId);

		result = new ModelAndView("recipe/list");
		result.addObject("recipes", recipes);
		result.addObject("owner", true);
		result.addObject("requestURI","recipe/user/list.do");
		
		return result;
	}
	
	//ShowRecipe ===============================================================
	
	@RequestMapping(value = "/showRecipe", method = RequestMethod.GET)
	public ModelAndView showRecipe(@RequestParam int recipeId) {
		ModelAndView result;
		Recipe recipe;
		Banner banner;
		Collection<StepToCook> stepToCooks;
		Collection<IngredientLine> ingredientLines;
		Collection<Ingredient> ingredients;
		Collection<Category> categories;
		Collection<Comment> comments;
		User principal;
		boolean owner;


		owner = false;
		principal = userService.findByPrincipal();
		banner = bannerService.findOne(50);
		recipe = recipeService.findOne(recipeId);
		stepToCooks = recipe.getStepsToCook();
		ingredientLines = recipe.getIngredientLines(); 
		ingredients = ingredientService.findAllByRecipe(recipe);
		categories = categoryService.findAllByRecipe(recipe); 
		comments = commentService.findAllByNameRecipe(recipe.getTitle());
		
		if(principal.getRecipes().contains(recipe)){
			owner = true;
		}
		
		
		result = new ModelAndView("recipe/showRecipe");
		result.addObject("recipe", recipe);
		result.addObject("row", banner);
		result.addObject("stepToCooks",stepToCooks);
		result.addObject("ingredientLines",ingredientLines);
		result.addObject("ingredients",ingredients);
		result.addObject("categories",categories);
		result.addObject("owner",owner);
		result.addObject("comments",comments);

		return result;
	}
	
	// Creation ==============================================================
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Recipe recipe;
		User user;
		
		user = userService.findByPrincipal();				
		recipe = recipeService.create(user);
		
		result = createEditModelAndView(recipe);
		
		return result;
	}

	// Edition =================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int recipeId) {
		ModelAndView result;
		Recipe recipe;
		
		recipe = recipeService.findOne(recipeId);
		Assert.notNull(recipe);

		result = createEditModelAndView(recipe);

		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@ModelAttribute @Valid Recipe recipe, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(recipe);
		}else{
			try{
				recipeService.save(recipe);
				result = new ModelAndView("redirect:/recipe/user/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(recipe, "recipe.commit.error");
			}
		}
		return result;
	}
	
	//Delete ======================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Recipe recipe, BindingResult binding){
		ModelAndView result;
		
		try{
			recipeService.delete(recipe);
			result = new ModelAndView("redirect:/recipe/user/list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(recipe, "recipe.commit.error");
		}
		return result;
	}
	
	// Ancillary Methods============================================================		
	
	protected ModelAndView createEditModelAndView(Recipe recipe) {
		assert recipe != null;
			    
		ModelAndView result;
		
		recipe.getIngredientLines().removeAll(recipe.getIngredientLines());

		result = createEditModelAndView(recipe, null);
			    
		return result;
		
	}		
	
	protected ModelAndView createEditModelAndView(Recipe recipe, String message) {
		assert recipe != null;
			    
		ModelAndView result;       
		Collection<Category> categories;
		
		categories = categoryService.findAllByRecipe(recipe);

		result = new ModelAndView("recipe/edit");
		result.addObject("recipe", recipe);
		result.addObject("message", message);
		result.addObject("categories", categories);
			    
		return result;
	}	
	
	// List category by recipe ============================================================		
	
	@RequestMapping(value = "/listCategory", method = RequestMethod.GET)
	public ModelAndView addCategoryList(@RequestParam int recipeId) {
		ModelAndView result;
		Collection<Category> categories;
		Recipe recipe;


		recipe = recipeService.findOne(recipeId);
		categories = categoryService.findAllByNotRecipe(recipe);

		result = new ModelAndView("category/list");
		result.addObject("categories", categories);
		result.addObject("requestURI", "category/list.do?recipeId="+ recipeId);
		result.addObject("recipeId", recipeId);

		return result;
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView addCategory(@RequestParam int categoryId, @RequestParam int recipeId) {
		ModelAndView result;
		Category category;
		Recipe recipe;

		category = categoryService.findOne(categoryId);
		recipe = recipeService.findOne(recipeId);
		
		try {
			recipeService.addCategory(recipe, category);	
			result = new ModelAndView("redirect:/recipe/showRecipe.do?recipeId="+recipeId);
		} catch (Throwable oops) {
			result = addCategoryList(recipeId);
			result.addObject("message", "recipe.commit.error");			
		}
		return result;

	}
	
	// List ingredient line by recipe ============================================================		
	
	@RequestMapping(value = "/addIngredientLine", method = RequestMethod.GET)
	public ModelAndView addIngredientLine(@RequestParam int recipeId) {
		ModelAndView result;
		Collection<IngredientLine> ingredientLines;

		ingredientLines = ingredientLineService.findAll();

		result = new ModelAndView("ingredientLine/list");
		result.addObject("ingredientLines", ingredientLines);
		result.addObject("requestURI", "ingredientLine/addIngredientLine.do");
		result.addObject("recipeId", recipeId);

		return result;
	}
	
	// List ingredient line by recipe ============================================================		
	
	@RequestMapping(value = "/addIngredient", method = RequestMethod.GET)
	public ModelAndView addIngredient(@RequestParam int recipeId, @RequestParam int ingredientLineId) {
		ModelAndView result;
		Collection<Ingredient> ingredients;

		ingredients = ingredientService.findAll();

		result = new ModelAndView("ingredient/list");
		result.addObject("ingredients", ingredients);
		result.addObject("requestURI", "ingredient/addIngredient.do");
		result.addObject("recipeId", recipeId);
		result.addObject("ingredientLineId", ingredientLineId);

		return result;
	}
	
	// Add step to cook ============================================================		
	
	@RequestMapping(value = "/addStepToCook", method = RequestMethod.GET)
	public ModelAndView addStepToCook(@RequestParam int recipeId) {
		ModelAndView result;
		StepToCook stepToCook;
		Recipe recipe;
			
		recipe = recipeService.findOne(recipeId);
		stepToCook = stepToCookService.create(recipe);
		Assert.notNull(recipe);
		Assert.notNull(stepToCook);

		result = createEditModelAndView(stepToCook);

		return result;
	}
	
	
	// Save step to cook ======================================================================================
	
	@RequestMapping(value = "/addStepToCook", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute StepToCook stepToCook, BindingResult bindingResult){
		ModelAndView result;
		
		if (bindingResult.hasErrors()) {
			result = createEditModelAndView(stepToCook);
		} else {
			try {			
				stepToCookService.save(stepToCook);
				result = new ModelAndView("redirect:/recipe/showRecipe.do?recipeId=" + stepToCook.getRecipe().getId());
			} catch (Throwable oops) {
				result = createEditModelAndView(stepToCook, "stepToCook.commit.error");				
			}
		}	
		return result;
	}
		
	// Ancillary methods =============================================================================
	
	protected ModelAndView createEditModelAndView(StepToCook stepToCook) {
		assert stepToCook != null;
		
		ModelAndView result;

		result = createEditModelAndView(stepToCook, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(StepToCook stepToCook, String message) {
		assert stepToCook != null;
		
		ModelAndView result;				

		result = new ModelAndView("stepToCook/edit");
		result.addObject("stepToCook", stepToCook);
		result.addObject("message", message);
		
		return result;
	}
}
