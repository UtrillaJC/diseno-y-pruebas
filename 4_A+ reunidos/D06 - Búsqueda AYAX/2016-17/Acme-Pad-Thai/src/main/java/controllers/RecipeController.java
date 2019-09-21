package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import services.CategoryService;
import services.CommentService;
import services.ContestService;
import services.IngredientService;
import services.PersonService;
import services.RecipeService;
import domain.Banner;
import domain.Category;
import domain.Comment;
import domain.Contest;
import domain.Ingredient;
import domain.IngredientLine;
import domain.Person;
import domain.Recipe;
import domain.StepToCook;
import forms.SearchForm;

@Controller
@RequestMapping("/recipe")
public class RecipeController extends AbstractController{

	//Services ===============================================================================
	
	@Autowired
	private RecipeService recipeService;
		
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private CommentService commentService;

	//Constructor ============================================================================
	
	public RecipeController(){
		super();
	}
	
	//SearchForm ==============================================================================
	
	@RequestMapping(value = "/searchForm", method = RequestMethod.GET)
	public ModelAndView listSearch(@RequestParam(required = false) String keyword){
		ModelAndView result;
		Collection<Recipe> recipes;
		SearchForm search;

		recipes = new ArrayList<Recipe>();
		search = new SearchForm();

		recipes = recipeService.findAll();

		if (keyword != null){
			recipes = recipeService.getRecipeByKeyWord(keyword);
		}

		result = new ModelAndView("recipe/search");
		result.addObject("recipes", recipes);
		result.addObject("search", search);
		result.addObject("requestURI", "recipe/searchForm.do");
		result.addObject("listAll", true);

		return result;
	}
	
	//Search ==============================================================================	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam(required = true) String keyword) {
		ModelAndView result;
		Collection<Recipe> recipes;
		
		recipes = recipeService.getRecipeByKeyWord(keyword);

		result = new ModelAndView("recipe/table");
		result.addObject("recipes", recipes);
		result.addObject("requestURI", "recipe/search.do");
		result.addObject("listAll", true);

		return result;
	}

	//Listing By User ==========================================================================================
	
	@RequestMapping(value = "/listByUser", method = RequestMethod.GET)
	public ModelAndView listByUser(@RequestParam int userId){
		ModelAndView result;
		Collection<Recipe> recipes;
		
		recipes = recipeService.findAllByUser(userId);

		result = new ModelAndView("recipe/list");
		result.addObject("recipes", recipes);
		result.addObject("requestURI","recipe/listByUser.do?userId="+userId);
		
		return result;
	}
	
	//Listing order by Category ==========================================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Recipe> recipes;

		recipes = recipeService.findAllOrderByCategories();

		result = new ModelAndView("recipe/list");
		result.addObject("recipes", recipes);
		result.addObject("requestURI","recipe/list.do");
		result.addObject("owner", false);
		
		return result;
	}
	
	//Listing recipes qualify in a contest ==========================================================================================

	@RequestMapping(value = "/listQualifyByContest", method = RequestMethod.GET)
	public ModelAndView listQualifyByContest(@RequestParam int contestId){
		ModelAndView result;
		Collection<Recipe> recipes;
		Contest contest;
		
		contest = contestService.findOne(contestId);
		recipes = recipeService.findAllByContest(contest);

		result = new ModelAndView("recipe/list");
		result.addObject("recipes", recipes);
		result.addObject("requestURI","recipe/listQualifyByContest.do");
		
		return result;
	}
	
	//Listing recipes winners of contest ==========================================================================================

	@RequestMapping(value = "/listWinnersContest", method = RequestMethod.GET)
	public ModelAndView listWinnersContest(@RequestParam int contestId){
		ModelAndView result;
		Collection<Recipe> recipes;
		Contest contest;
		
		contest = contestService.findOne(contestId);
		recipes = recipeService.findAllByContestWinners(contest);

		result = new ModelAndView("recipe/list");
		result.addObject("recipes", recipes);
		result.addObject("requestURI","recipe/listWinnersContest.do");
		
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
		boolean owner;


		owner = false;
		banner = bannerService.findOne(50);
		recipe = recipeService.findOne(recipeId);
		stepToCooks = recipe.getStepsToCook();
		ingredientLines = recipe.getIngredientLines(); 
		ingredients = ingredientService.findAllByRecipe(recipe);
		categories = categoryService.findAllByRecipe(recipe); 
		comments = commentService.findAllByNameRecipe(recipe.getTitle());
		
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
	
	// List without tastes============================================================================

	@RequestMapping("/list-without-tastes")
	public ModelAndView listWithoutTastes() {
		ModelAndView result;
		Collection<Recipe> recipes;
		Person person;	
		int intTaste;

		intTaste = 0;
		person = personService.findByPrincipal();
		recipes = recipeService.findAllWithoutByPerson(person);
		
		result = new ModelAndView("recipe/list");
		result.addObject("recipes", recipes);
		result.addObject("intTaste", intTaste);

		result.addObject("requestURI", "recipe/list-without-tastes.do");
		
		return result;
	}
	
	// List likes ============================================================================
	
	@RequestMapping("/list-likes")
	public ModelAndView listLikes(){
		ModelAndView result;
		Collection<Recipe> recipes;
		Person person;
		int intTaste;

		intTaste = 1;
		person = personService.findByPrincipal();
		recipes = recipeService.findAllLikes(person);
		
		result = new ModelAndView("recipe/list");
		result.addObject("recipes", recipes);
		result.addObject("intTaste", intTaste);
		result.addObject("requestURI", "recipe/list-likes.do");		
		return result;
	}
	
	// List dislikes ============================================================================

	@RequestMapping("/list-dislikes")
	public ModelAndView listDislikes(){
		ModelAndView result;
		Collection<Recipe> recipes;
		Person person;
		int intTaste;

		intTaste = 2;
		person = personService.findByPrincipal();
		recipes = recipeService.findAllDislikes(person);
		
		result = new ModelAndView("recipe/list");
		result.addObject("recipes", recipes);
		result.addObject("intTaste", intTaste);
		result.addObject("requestURI", "recipe/list-dislikes.do");
		
		return result;
	}
	
	//Likes ===============================================================
	
	@RequestMapping(value = "/likes", method = RequestMethod.GET)
	public ModelAndView likes(@RequestParam int recipeId) {
		ModelAndView result;		
				
		try {			
			recipeService.likes(recipeId);
			result = listLikes();
			result.addObject("message", "recipe.commit.ok");			
		} catch (Throwable oops) {			
			result = listLikes();
			result.addObject("message", "recipe.commit.error");			
		}
		return result;
	}
	
	@RequestMapping(value = "/dislikes", method = RequestMethod.GET)
	public ModelAndView dislikes(@RequestParam int recipeId) {
		ModelAndView result;		
				
		try {			
			recipeService.dislikes(recipeId);
			result = listDislikes();
			result.addObject("message", "recipe.commit.ok");			
		} catch (Throwable oops) {			
			result = listDislikes();
			result.addObject("message", "recipe.commit.error");			
		}
		return result;
	}
	
	// List recipes of follow ============================================================================

	@RequestMapping("/list-recipes-follow")
	public ModelAndView listRecipesFollow(){
		ModelAndView result;
		Collection<Recipe> recipes;
		Person person;

		person = personService.findByPrincipal();
		recipes = recipeService.findAllDisplayMomAuthByFollow(person);
		
		result = new ModelAndView("recipe/list");
		result.addObject("recipes", recipes);
		result.addObject("requestURI", "recipe/list-recipes-follow.do");
		
		return result;
	}
}
