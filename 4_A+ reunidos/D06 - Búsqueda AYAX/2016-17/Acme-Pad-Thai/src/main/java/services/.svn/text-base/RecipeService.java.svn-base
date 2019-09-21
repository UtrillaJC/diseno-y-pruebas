package services;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RecipeRepository;
import domain.Administrator;
import domain.Category;
import domain.Contest;
import domain.IngredientLine;
import domain.Person;
import domain.Recipe;
import domain.Registration;
import domain.StepToCook;
import domain.Taste;
import domain.User;

@Service
@Transactional
public class RecipeService {

	//Managed Repository =============================================================================
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private TasteService tasteService;
	
	//Constructor methods ============================================================================
	
	public RecipeService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Recipe findOne(int recipeId){
	    Recipe result;
	    
	    result = recipeRepository.findOne(recipeId);
	    
	    return result;
	}
	
	public Collection<Recipe> findAll(){
		Collection<Recipe> result;
		
		result = recipeRepository.findAll();
		
		return result;
	}
	
	public Recipe create(User user){
		Assert.notNull(user);
		Recipe result;
		User principal;
		Date momentAuthored;
		String ticker;
		int likes;
		int dislikes;
		Collection<URL> pictures;
		Collection<String> hints;
		Collection<Category> categories;
		Collection<StepToCook> stepsToCook;
		Collection<Registration> registrations;
		Collection<IngredientLine> ingredientLines;
		Collection<Contest> contestsWon;
		
		momentAuthored = new Date(System.currentTimeMillis());
		ticker = generateTicker();
		likes = 0;
		dislikes = 0;
		pictures = new ArrayList<URL>();
		hints = new ArrayList<String>();
		principal = userService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(user));
		categories = new HashSet<Category>();
		stepsToCook = new HashSet<StepToCook>();
		registrations = new HashSet<Registration>();
		ingredientLines = new HashSet<IngredientLine>();
		contestsWon = new HashSet<Contest>();

		result = new Recipe();
		
		result.setUser(user);
		result.setMomentAuthored(momentAuthored);
		result.setTicker(ticker);
		result.setLikes(likes);
		result.setDislikes(dislikes);
		result.setPictures(pictures);
		result.setHints(hints);
		result.setCategories(categories);
		result.setStepsToCook(stepsToCook);
		result.setIngredientLines(ingredientLines);
		result.setRegistrations(registrations);
		result.setContestsWon(contestsWon);
		
		user.getRecipes().add(result);
		
		return result;
	}
	
	public Recipe save(Recipe recipe){
		Assert.notNull(recipe);
		Recipe result;
		User principal;
		Date momentAuthored;
		Date momentUpdate;
		Date momentActual;


		principal = userService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(recipe.getUser()));
		
		if(recipe.getId() == 0){
			momentAuthored = new Date(System.currentTimeMillis()-1000);
			recipe.setMomentAuthored(momentAuthored);
			momentUpdate = new Date(System.currentTimeMillis()-1000);
			recipe.setMomentUpdated(momentUpdate);

		}
		
		if(recipe.getId() != 0){
			momentActual = new Date(System.currentTimeMillis() - 1000);

			for(Registration r : recipe.getRegistrations()){
				Assert.isTrue(momentActual.before(r.getContest().getMomentOpening()) || momentActual.after(r.getContest().getMomentClosing()));
			}

		}
		
		momentUpdate = new Date(System.currentTimeMillis()-1000);
		recipe.setMomentUpdated(momentUpdate);
		
		result = recipeRepository.save(recipe);
		
		return result;
	}
	
	public void delete(Recipe recipe){
		Assert.notNull(recipe);
		User principal;
		
		principal = userService.findByPrincipal();
		Assert.isTrue(principal.equals(recipe.getUser()));
		
		for(Registration r : recipe.getRegistrations()){
			registrationService.delete(r);
		}
				
		recipeRepository.delete(recipe);
	}
	
	//Other Business Methods =========================================================================

	public Collection<Recipe> findAllOrderByCategories(){
		Collection<Recipe> result;
		
		result = recipeRepository.findAllOrderByCategories();
		
		return result;
	}
	
	public String generateTicker(){
		String result = null;
		List<String> tickers;
		String lastTicker;
		String[] parts;
		String temp;
		Integer num;
		
		temp = "";
		tickers = recipeRepository.searchLastTicker();
		lastTicker = tickers.get(0);
		parts = lastTicker.split("-");
		
			if(parts[0].equals("999999")){
				if(parts[0].toUpperCase().charAt(3) == 'Z'){
					
					temp = String.valueOf(parts[0].charAt(0))+ String.valueOf(parts[0].charAt(1)) + String.valueOf((char)(parts[0].charAt(2) + 1)) + String.valueOf((char)(parts[0].charAt(3) + 1));
				}else if(parts[0].toUpperCase().charAt(2) == 'Z' && parts[0].toUpperCase().charAt(3) == 'Z'){
				
					temp = String.valueOf(parts[0].charAt(0))+ String.valueOf((char)(parts[0].charAt(1) + 1)) + String.valueOf((char)(parts[0].charAt(2) + 1)) + String.valueOf((char)(parts[0].charAt(3) + 1));
				}else if(parts[0].toUpperCase().charAt(1) == 'Z' && parts[0].toUpperCase().charAt(2) == 'Z' && parts[0].toUpperCase().charAt(3) == 'Z'){
					
					temp = String.valueOf((char)parts[0].charAt(0) + 1)+ String.valueOf((char)(parts[0].charAt(1) + 1)) + String.valueOf((char)(parts[0].charAt(2) + 1)) + String.valueOf((char)(parts[0].charAt(3) + 1));
				}else if(parts[0].toUpperCase().charAt(0) == 'Z' && parts[0].toUpperCase().charAt(1) == 'Z' && parts[0].toUpperCase().charAt(2) == 'Z' && parts[0].toUpperCase().charAt(3) == 'Z'){
					
					temp = "AAAA";
				}else{
					temp = String.valueOf(parts[0].charAt(0))+ String.valueOf(parts[0].charAt(1)) + String.valueOf(parts[0].charAt(2)) + String.valueOf((char)(parts[0].charAt(3) + 1));
				}
				result = "000000-" + temp.toUpperCase();
			}else{
				num = Integer.valueOf(parts[0]);
				num += 1;
				temp = String.valueOf(num);
				if(temp.length() != 6){
					while(temp.length() != 6){
						
						temp = "0" + temp;
					}
				}
					result = temp + "-" + parts[1].toUpperCase();
				}

			Assert.isTrue(!tickers.contains(result));
			
			return result;	
	}
	
	public Collection<Recipe> findAllByContest(Contest contest){
		Assert.notNull(contest);
		Collection<Recipe> result;
		
		result = recipeRepository.findAllByContestId(contest.getId());
		
		return result;
	}
	
	public 	Collection<Recipe> findAllByContestWinners(Contest contest){
		Assert.notNull(contest);
		Collection<Recipe> result;
		
		result = recipeRepository.findAllByContestIdWinners(contest.getId());
		
		return result;
	}
	
	public 	Collection<Recipe> findAllByUser(int userId){
		Collection<Recipe> result;
				
		result = recipeRepository.findAllByUserId(userId);
		
		return result;
	}
	
	public Collection<Recipe> getRecipeByKeyWord(String keyWord){
		Assert.notNull(keyWord);
		Collection<Recipe> result;
		
		result = recipeRepository.searchRecipeByWords(keyWord);
		
		return result;
	}
	
	public Collection<Recipe> findAllByMomentAuthored(){
		Collection<Recipe> result;
		Person person;
		
		person = personService.findByPrincipal();
		Assert.notNull(person);
		Assert.isInstanceOf(Person.class, person);
		
		result = recipeRepository.findAllByMomentAuthored();
		
		return result;
	}
	
	public Collection<Recipe> findAllDisplayMomAuthByFollow(Person person){
		Collection<Recipe> result;
		Collection<Recipe> recipes;
		
		result = new HashSet<Recipe>();
		recipes = recipeRepository.findAllByMomentAuthored();
		
		for(Recipe r : recipes){
			if(r.getUser().getFollowers().contains(person)){
				result.add(r);
			}
		}
		return result;
	}
	
	public Collection<Recipe> findRecipeOrderDescByLikesMinusDislikes(Contest contest){
		Assert.notNull(contest);
		Collection<Recipe> result;
		Date moment;
		
		moment = new Date(System.currentTimeMillis());
		Assert.isTrue(moment.after(contest.getMomentOpening()) && moment.after(contest.getMomentClosing()));
		
		result = recipeRepository.findRecipeOrderDescByLikesMinusDislikes(contest.getId());
		
		return result;	
	}
	
	public Recipe saveByAdministrator(Recipe recipe){
		Assert.notNull(recipe);
		Recipe result;
		Administrator principal;

		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);

		result = recipeRepository.save(recipe);
		
		return result;
	}
	
	public void addCategory(Recipe recipe, Category category){
		recipe.getCategories().add(category);
		category.getRecipes().add(recipe);
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1000);

		for(Registration r : recipe.getRegistrations()){
			Assert.isTrue(moment.before(r.getContest().getMomentOpening()) || moment.after(r.getContest().getMomentClosing()));
		}
		
		recipeRepository.save(recipe);
	}
	
	public Collection<Recipe> findAllWithoutByPerson(Person person){
		Assert.notNull(person);
		Collection<Recipe> result;
		Collection<Taste> tastes;

		tastes = person.getTastes();
		result = recipeRepository.findAllByNotUserId(person.getId());
				
		for(Taste t : tastes){
			if(result.contains(t.getRecipe())){
				result.remove(t.getRecipe());
			}
		}
		
		for(Recipe r : result){
			if(r.getUser().getId() == person.getId()){
				result.remove(r);
			}			
		}
		return result;
	}
	
	public Collection<Recipe> findAllLikes(Person person){
		Assert.notNull(person);
		Collection<Recipe> result;
		Collection<Taste> tastes;

		tastes = person.getTastes();
		result = new HashSet<Recipe>();
		
		for(Taste t : tastes){
			if(t.getLikes() == true){
				result.add(t.getRecipe());
			}
		}
		return result;
	}
	
	public Collection<Recipe> findAllDislikes(Person person){
		Assert.notNull(person);
		Collection<Recipe> result;
		Collection<Taste> tastes;

		tastes = person.getTastes();
		result = new HashSet<Recipe>();
		
		for(Taste t : tastes){
			if(t.getLikes() == false){
				result.add(t.getRecipe());
			}
		}
		return result;
	}
	
	public void likes(int recipeId) {
		Recipe  recipe;
		Taste taste;
		int likes;
		
		recipe = findOne(recipeId);
		taste = tasteService.create(recipe);
		likes = recipe.getLikes();
		likes++;
		
		recipe.setLikes(likes);
		taste.setLikes(true);
		
		tasteService.save(taste);
		recipeRepository.save(recipe);		  
	}
	
	public void dislikes(int recipeId) {
		Recipe  recipe;
		Taste taste;
		int dislikes;
		
		recipe = findOne(recipeId);
		taste = tasteService.create(recipe);
		dislikes = recipe.getDislikes();
		dislikes++;
		
		recipe.setDislikes(dislikes);
		taste.setLikes(false);
		
		tasteService.save(taste);
		recipeRepository.save(recipe);		  
	}
	
	//Dashboard =============================================================================
	
	public Double avgRecipesUser(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = recipeRepository.avgRecipesUser();
		
		return result;
	}
	
	public Double minRecipesUser(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = recipeRepository.minRecipesUser();
		
		return result;
	}
	
	public Double maxRecipesUser(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = recipeRepository.maxRecipesUser();
		
		return result;
	}
	
	public Double minRecipesHaveQualifiedContest(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = recipeRepository.minRecipesHaveQualifiedContest();
		
		return result;
		
	}
	
	public Double maxRecipesHaveQualifiedContest(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
			
		result = recipeRepository.maxRecipesHaveQualifiedContest();
			
		return result;
	}
	
	public Double avgRecipesHaveQualifiedContest(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = recipeRepository.avgRecipesHaveQualifiedContest();
		
		return result;
		
	}
}
