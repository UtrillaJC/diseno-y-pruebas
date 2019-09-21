package controllers.administrator;

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

import services.CategoryService;
import controllers.AbstractController;
import domain.Category;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController extends AbstractController {

	// Services ============================================================================
	
	@Autowired
	private CategoryService categoryService;
	
	// Constructors ============================================================================

	public CategoryAdministratorController() {
		super();
	}
	
	// List ======================================================================================
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Category> categories;
		boolean listParent;
		
		listParent = false;
		categories = categoryService.findAll();

		result = new ModelAndView("category/list");
		result.addObject("categories", categories);
		result.addObject("listParent", listParent);
		result.addObject("requestURI", "category/administrator/list.do");

	return result;
	
	}
	
	// List parent category ======================================================================================

	
	@RequestMapping(value = "/list-addCategoryParent", method = RequestMethod.GET)
	public ModelAndView listParentCategories(@RequestParam int categoryParentId) {
		ModelAndView result;
		Collection<Category> categories;
		Category categoryParent;
		boolean listParent;
		
		listParent = true;
		categoryParent = categoryService.findOne(categoryParentId);
		categories = categoryService.findAllAddParentCategory(categoryParent);

		result = new ModelAndView("category/list");
		result.addObject("categories", categories);
		result.addObject("listParent", listParent);
		result.addObject("requestURI", "category/administrator/list-addCategoryParent.do");
		result.addObject("categoryParentId", categoryParentId);

		return result;
	}
	
	// Add category parent ======================================================================================

	@RequestMapping(value = "/addCategoryParent", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam int categoryId, @RequestParam int categoryParentId) {
		ModelAndView result;		
		Category category;
		Category categoryParent;
		
		category = categoryService.findOne(categoryId);
		categoryParent = categoryService.findOne(categoryParentId);
		try {			
			categoryService.saveWithParentCategory(categoryParent, category);
			result = list();
			result.addObject("message", "category.commit.ok");	

		} catch (Throwable oops) {			
			result = listParentCategories(categoryId);
			result.addObject("message", "category.commit.error");			
		}
		
		return result;
	}
	// Create ======================================================================================
		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Category category;
		
		category = categoryService.create();
	
		result = createModelAndView(category);
		
		return result;
	}
	
	// Edit ======================================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int categoryId){
		ModelAndView result;
		Category category;
		
		category = categoryService.findOne(categoryId);
		Assert.notNull(category);
	
		result = editModelAndView(category);
		
		return result;
	}
	
	// Save ======================================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Category category, BindingResult bindingResult){
		ModelAndView result;
		
		if (bindingResult.hasErrors()) {
			result = createModelAndView(category);
		} else {
			try {			
				categoryService.save(category);
				result = new ModelAndView("redirect:/category/administrator/list.do");
			} catch (Throwable oops) {
				result = createModelAndView(category, "category.commit.error");				
			}
		}	
		return result;
	}
	
	// Delete ======================================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Category category, BindingResult bindingResult){
		ModelAndView result;
		
		if (bindingResult.hasErrors()) {
			result = createModelAndView(category);
		} else {
			try {			
				categoryService.delete(category);
				result = new ModelAndView("redirect:/category/administrator/list.do");
			} catch (Throwable oops) {
				result = createModelAndView(category, "category.commit.error");				
			}
		}	
		return result;
	}
	
	// Ancillary methods =============================================================================
	
	protected ModelAndView createModelAndView(Category category) {
		assert category != null;
		
		ModelAndView result;

		result = createModelAndView(category, null);
		
		return result;
	}
	
	protected ModelAndView createModelAndView(Category category, String message) {
		assert category != null;
		
		ModelAndView result;				

		result = new ModelAndView("category/create");
		result.addObject("category", category);
		result.addObject("message", message);
		
		return result;
	}
	
	protected ModelAndView editModelAndView(Category category) {
		assert category != null;
		
		ModelAndView result;

		result = editModelAndView(category, null);
		
		return result;
	}
	
	protected ModelAndView editModelAndView(Category category, String message) {
		assert category != null;
		
		ModelAndView result;				

		result = new ModelAndView("category/edit");
		result.addObject("category", category);
		result.addObject("message", message);
		
		return result;
	}
}
