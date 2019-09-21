package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class UserServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	UserService userService;
	
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

		User result;
		
		result = userService.create();
		
		System.out.println("Username: " + result.getUserAccount().getUsername());
		System.out.println("Name: " + result.getName());
		System.out.println("Surname: " + result.getSurname());
		System.out.println("Phonenumber: " + result.getPhoneNumber());
		System.out.println("Email: " + result.getEmail());
		System.out.println("Address: " + result.getAddress());
		
		authenticate(null);
	    System.out.println("----------------------------------------------------------------"); 
	  
	}
	
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
				
		User result;
		
		result = userService.create();
		
		result.getUserAccount().setUsername("Nutritionist Test");
		result.getUserAccount().setPassword("passwordTest");
		
		result.setName("Nutritionist Name Test");
		result.setSurname("Nutritionist Surname Test");
		result.setEmail("nutritionist@test.com");
		result.setPhoneNumber("+11 (111) T-E-S-T");
		result.setAddress("Nutritionist Addresss  Test");
		
		userService.save(result);
		
		System.out.println("Username: " + result.getUserAccount().getUsername());
		System.out.println("Name: " + result.getName());
		System.out.println("Surname: " + result.getSurname());
		System.out.println("Phonenumber: " + result.getPhoneNumber());
		System.out.println("Email: " + result.getEmail());
		System.out.println("Address: " + result.getAddress());
		
		System.out.println("----------------------------------------------------------------");
	}
	
	/**
	* ################################################################
	*
	* TEST NEGATIVOS
	*
	* #################################################################
	*/
	
	
	/**
	* ################################################################
	*
	* DASHBOARD
	*
	* #################################################################
	*/
	
	@Test
	public void testUsersAuthoredMoreRecipes() {
		System.out.println("----------------------------Users Authored More Recipes------------------------------");		
		authenticate("admin");
		Collection<User> result;
		
		result = userService.usersAuthoredMoreRecipes();
	
		System.out.println("Users Authored More Recipes: " + result);
		
	}
	
	@Test
	public void testListUsersDescendingPopularity() {
		System.out.println("----------------------------List Users Descending Popularity------------------------------");		
		authenticate("admin");
		Collection<User> result;
		
		result = userService.listUsersDescendingPopularity();
	
		System.out.println("List Users Descending Popularity: " + result);
		
	}
	
	@Test
	public void testListUsersLikes() {
		System.out.println("----------------------------List Users Likes------------------------------");		
		authenticate("admin");
		Collection<User> result;
		
		result = userService.listUsersLikes();
	
		System.out.println("List Users Likes: " + result);
		
	}
	
	@Test
	public void testListUsersDislikes() {
		System.out.println("----------------------------List Users Dislikes------------------------------");		
		authenticate("admin");
		Collection<User> result;
		
		result = userService.listUsersDislikes();
	
		System.out.println("List Users Dislikes: " + result);
		
	}
	
	
	

}