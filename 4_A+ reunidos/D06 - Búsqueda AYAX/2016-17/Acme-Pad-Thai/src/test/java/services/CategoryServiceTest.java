package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class CategoryServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private CategoryService categoryService;
	
	// Tests =========================================================================================
	
	/**
	* ################################################################
	*
	* TEST POSITIVOS
	*
	* #################################################################
	*/
	
	@Test
	public void testCreate() {
		System.out.println("----------------------------Create------------------------------");		

		authenticate("admin");
		
		Category result;
		
		result = categoryService.create();
		
		System.out.println("Name: " + result.getName());
		System.out.println("Description: " + result.getDescription());
		System.out.println("Picture: " + result.getPicture());
		System.out.println("Tags: " + result.getTags());
		
		authenticate(null);
		
		System.out.println("----------------------------------------------------------------"); 
	}
	
	
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
		
		authenticate("admin");
		
		Category result;
		
		String name;
		String description;
		String picture;
		
		result = categoryService.create();
		
		name = "Nombre";
		description = "Descripción";
		picture = "Picture";
		
		result.setName(name);
		result.setDescription(description);
		result.setPicture(picture);
		
		categoryService.save(result);
		
		System.out.println("Name: " + result.getName());
		System.out.println("Descripción: " + result.getDescription());
		System.out.println("Picture: " + result.getPicture());
		
		authenticate(null);
		
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testSaveWithParentCategory() {
		
		System.out.println("----------------------------Save With Parent Category------------------------------");
		
		authenticate("admin");
		
		Category result;
		Category categoryParent;
		
		String name;
		String description;
		String picture;
			
		result = categoryService.create();
		
		name = "Nombre";
		description = "Descripción";
		picture = "Picture";
		categoryParent = categoryService.findOne(162);
			
		result.setName(name);
		result.setDescription(description);
		result.setPicture(picture);	
		
		categoryService.saveWithParentCategory(result, categoryParent);
			
		System.out.println("Name: " + result.getName());
		System.out.println("Descripción: " + result.getDescription());
		System.out.println("Picture: " + result.getPicture());
		System.out.println("Category: " + result.getParent());
		
		authenticate(null);
		
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testDelete() {
		System.out.println("----------------------------Delete------------------------------");
		
		authenticate("admin");
		
		Category result;
		
		String name;
		String description;
		String picture;	
		
		result = categoryService.create();
		
		name = "Nombre";
		description = "Descripción";
		picture = "Picture";	
		
		result.setName(name);
		result.setDescription(description);
		result.setPicture(picture);
		
		categoryService.save(result);
			
		System.out.println("Name: " + result.getName());
		System.out.println("Descripción: " + result.getDescription());
		System.out.println("Picture: " + result.getPicture());
		
		categoryService.delete(result);
		
		authenticate(null);
	
		System.out.println("----------------------------------------------------------------");
	}
	
	
	/**
	* ################################################################
	*
	* TEST NEGATIVOS
	*
	* #################################################################
	*/
		
	@Test
	public void testCreateNegative() {
		System.out.println("----------------------------Create Negative------------------------------");		

		try {
			authenticate("user1");
			
			Category result;
			
			result = categoryService.create();
			
			System.out.println("Name: " + result.getName());
			System.out.println("Description: " + result.getDescription());
			System.out.println("Picture: " + result.getPicture());
			System.out.println("Tags: " + result.getTags());
			
			authenticate(null);
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
		}
		finally{
		
		System.out.println("----------------------------------------------------------------"); 
		}
	
	}
	
	@Test
	public void testSaveNegative() {
		
		System.out.println("----------------------------Save Negative------------------------------");
		
		try {
			authenticate("user1");
			
			Category result;
			
			String name;
			String description;
			String picture;	
			
			result = categoryService.create();
			
			name = "Nombre";
			description = "Descripción";
			picture = "Picture";	
			
			result.setName(name);
			result.setDescription(description);
			result.setPicture(picture);
			
			categoryService.save(result);
			
			System.out.println("Name: " + result.getName());
			System.out.println("Name: " + result.getDescription());
			System.out.println("Name: " + result.getPicture());
			
			authenticate(null);
		
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
		}
		finally{
		
		System.out.println("----------------------------------------------------------------");
		}
	
	}
	
	@Test
	public void testSaveWithParentCategoryNegative() {
		
		System.out.println("----------------------------Save With Parent Category Negative------------------------------");
		try {
			authenticate("user1");
			
			Category result;
			Category categoryParent;
			
			String name;
			String description;
			String picture;
				
			result = categoryService.create();
			
			name = "Nombre";
			description = "Descripción";
			picture = "Picture";
			categoryParent = categoryService.findOne(159);			
			
			result.setName(name);
			result.setDescription(description);
			result.setPicture(picture);		
			
			categoryService.saveWithParentCategory(result, categoryParent);			
			
			System.out.println("Name: " + result.getName());
			System.out.println("Descripción: " + result.getDescription());
			System.out.println("Picture: " + result.getPicture());
			System.out.println("Category: " + result.getParent());
			
			authenticate(null);
		
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
		}
		finally{
		System.out.println("----------------------------------------------------------------");
		}
	}
	
	@Test
	public void testDeleteNegative() {
		System.out.println("----------------------------Delete Negative------------------------------");
		
		try {
		authenticate("user1");
		
		Category result;
		
		String name;
		String description;
		String picture;
		
		
		result = categoryService.create();
		
		name = "Nombre";
		description = "Descripción";
		picture = "Picture";
			
		result.setName(name);
		result.setDescription(description);
		result.setPicture(picture);
	
		categoryService.save(result);
		
		System.out.println("Name: " + result.getName());
		System.out.println("Descripción: " + result.getDescription());
		System.out.println("Picture: " + result.getPicture());
		
		categoryService.delete(result);
		
		authenticate(null);

		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
		}
		finally{
		System.out.println("----------------------------------------------------------------");
	}
	
}
	
	
}