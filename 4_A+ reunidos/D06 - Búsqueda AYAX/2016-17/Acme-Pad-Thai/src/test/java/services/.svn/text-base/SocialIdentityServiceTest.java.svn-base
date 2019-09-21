package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.SocialIdentity;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class SocialIdentityServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private SocialIdentityService socialIdentityService;
	
	@Autowired
	private UserService userService;
	
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

		authenticate("user3");
	
		SocialIdentity result;
		User user;
		
		user = userService.findOne(24);
		result = socialIdentityService.create(user);
		
		System.out.println("Nick: " + result.getNick());
		System.out.println("Name: " + result.getName());
		System.out.println("Link: " + result.getLink());
		System.out.println("Picture: " + result.getPicture());
		
		authenticate(null);
		
		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSave() {
		
		System.out.println("----------------------------Save------------------------------");
		
		authenticate("user3");
		
		SocialIdentity result;
		User user;
		
		user = userService.findOne(24);
		result = socialIdentityService.create(user);
		
		result.setNick("Nick Test");
		result.setName("Name Test");
		result.setLink("https://linktest.com");
		result.setPicture("https://picturetest.com");
		
		socialIdentityService.save(result);
		
		System.out.println("Nick: " + result.getNick());
		System.out.println("Name: " + result.getName());
		System.out.println("Link: " + result.getLink());
		System.out.println("Picture: " + result.getPicture());
		
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
			authenticate("cook1");
			
			SocialIdentity result;
			User user;
			
			user = userService.findOne(24);
			result = socialIdentityService.create(user);
			
			System.out.println("Nick: " + result.getNick());
			System.out.println("Name: " + result.getName());
			System.out.println("Link: " + result.getLink());
			System.out.println("Picture: " + result.getPicture());
			
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
			authenticate("cook1");
			
			SocialIdentity result;
			User user;
			
			user = userService.findOne(24);
			result = socialIdentityService.create(user);
			
			result.setNick("Nick Test");
			result.setName("Name Test");
			result.setLink("https://linktest.com");
			result.setPicture("https://picturetest.com");
			
			socialIdentityService.save(result);
			
			System.out.println("Nick: " + result.getNick());
			System.out.println("Name: " + result.getName());
			System.out.println("Link: " + result.getLink());
			System.out.println("Picture: " + result.getPicture());
			
			authenticate(null);
		
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
}