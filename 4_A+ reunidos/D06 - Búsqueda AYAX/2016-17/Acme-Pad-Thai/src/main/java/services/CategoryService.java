package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Administrator;
import domain.Category;
import domain.Recipe;
import domain.User;

@Service
@Transactional
public class CategoryService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private UserService userService;
	
	//Constructor methods ============================================================================
	
	public CategoryService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Category findOne(int categoryId){
		Category result;
		
		result = categoryRepository.findOne(categoryId);
		
		return result;
	}
	
	public Collection<Category> findAll(){
		Collection<Category> result;
				
		result = categoryRepository.findAll();
		
		return result;
	}
	
	public Category create(){
		Category result;
		Administrator principal;
		Collection<Category> subcategories;
		Collection<Recipe> recipes;
		Collection<String> tags;
		
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		
		result = new Category();
		
		subcategories = new HashSet<Category>();
		recipes = new HashSet<Recipe>();
		tags = new HashSet<String>();
				
		result.setSubcategories(subcategories);
		result.setRecipes(recipes);
		result.setTags(tags);
		
		return result;
	}
	
	public Category save(Category category){
		Assert.notNull(category);
		Category result;
		Administrator principal;
		
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
				
		result = categoryRepository.save(category);
		
		return result;
	}
	
	public Category saveWithParentCategory(Category category, Category categoryParent){
		Assert.notNull(category);
		Assert.notNull(categoryParent);
		Assert.isTrue(!categoryParent.getSubcategories().contains(category));
		Assert.isTrue(!category.getSubcategories().contains(categoryParent));
		Category result;
		Administrator principal;
		
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		category.setParent(categoryParent);
				
		result = categoryRepository.save(category);
		
		return result;
	}
	
	public void delete(Category category){
		Assert.notNull(category);
		Assert.isTrue(category.getRecipes().isEmpty());
		Administrator principal;
		
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		
		Assert.isTrue(category.getRecipes().isEmpty());
		for(Category c : category.getSubcategories()){
			Assert.isTrue(c.getRecipes().isEmpty());
		}
		
		if(category.getSubcategories().isEmpty()){
			categoryRepository.delete(category);
		}else{
			category.getSubcategories().clear();
			categoryRepository.delete(category);
		}
	}
	
	//Other Business Methods =========================================================================

	public Collection<Category> findAllByNotRecipe(Recipe recipe){
		Assert.notNull(recipe);
		User principal;
		Collection<Category> result;
		
		principal = userService.findByPrincipal();
		Assert.isTrue(principal.equals(recipe.getUser()));
		result = categoryRepository.findAllByNotRecipeId(recipe.getId());
		
		return result;
	}
	
	public Collection<Category> findAllByRecipe(Recipe recipe){
		Assert.notNull(recipe);
		Collection<Category> result;
	
		result = categoryRepository.findAllByRecipeId(recipe.getId());
		
		return result;
	}
	
	public Collection<Category> findAllAddParentCategory(Category category){
		Assert.notNull(category);
		Collection<Category> result;
	
		result = categoryRepository.findAllParentCategory(category.getId());
		result.remove(category);

		return result;
	}
}
