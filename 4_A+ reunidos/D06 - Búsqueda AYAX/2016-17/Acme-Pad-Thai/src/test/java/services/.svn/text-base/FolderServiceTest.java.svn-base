package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Folder;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class FolderServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private FolderService folderService;
	
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

		Folder result;
		User user;
		
		user = userService.create();
		
		result = folderService.create(user);
		
		System.out.println("Name: " + result.getName());
		System.out.println("Actor: " + result.getUserAccount());
		System.out.println("Folders : " + result.getUserAccount().getFolders());

	    System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testCreateByActor() {
		System.out.println("----------------------------Create By Actor------------------------------");		
		authenticate("user1");
		
		Folder result;
		User user;
		
		user = userService.findOne(16);
		result = folderService.createByActor(user);
		
		System.out.println("Name: " + result.getName());
		System.out.println("Actor: " + result.getUserAccount());
		System.out.println("Folders: " + result.getUserAccount().getFolders());

		authenticate(null);
	    System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testCreateChildFolder() {
		System.out.println("----------------------------Create Child Folder------------------------------");		
		authenticate("user1");
		
		Folder result;
		User user;
		Folder parent;
		
		user = userService.findOne(16);
		parent = folderService.findOneByPrincipal(61);
				
		result = folderService.createChildFolder(user,parent);
		
		System.out.println("Name: " + result.getName());	
		System.out.println("Actor: " + result.getUserAccount());
		System.out.println("Folders: " + result.getUserAccount().getFolders());
		System.out.println("Folder parent: " + result.getParent().getId());
		
		authenticate(null);
	    System.out.println("----------------------------------------------------------------"); 	
	}
	
	
	@Test
	public void testSave() {
		System.out.println("----------------------------Save------------------------------");		
		User user;
		
		user = userService.create();

		userService.save(user);
		
		System.out.println("Folders: " + user.getUserAccount().getFolders());
		System.out.println("Folders: " + user.getUserAccount().getFolders());
		
		System.out.println("----------------------------------------------------------------");
	  
	}
	
	@Test
	public void testSaveFolder() {
		System.out.println("----------------------------Save Folder------------------------------");		
		authenticate("user1");
		
		Folder result;
		User user;		
			
		user = userService.findOne(16);
		result = folderService.createByActor(user);
		
		result.setName("Folder Name Test");
		
		result = folderService.saveFolderByActor(result);
		
		System.out.println("Nombre de Folder: "	+ result.getName());
		
		authenticate(null);
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

		try{
		Folder result;
		User user;
		
		user = null;
				
		result = folderService.create(user);
		
		System.out.println("Name: " + result.getName());	
		System.out.println("Actor: " + result.getUserAccount());

		authenticate(null);
		
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario es nulo"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
		}
	}
	
	@Test
	public void testCreateByActorNegative() {
		System.out.println("----------------------------Create By Actor Negative------------------------------");		

		try{
			authenticate("sponsor1");
			Folder result;
			User user;
			
			user = userService.findOne(16);
			result = folderService.createByActor(user);
			
			System.out.println("Name: " + result.getName());
			System.out.println("Actor: " + result.getUserAccount());
	
			authenticate(null);
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede hacer esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
	  
		}
		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSaveNegative() {
		System.out.println("----------------------------Save Negative------------------------------");		

		
		try{
			User user;
			
			user = null;

			user = userService.save(user);
			
			System.out.println("Folders: " + user.getUserAccount().getFolders());
			System.out.println("Folders: " + user.getUserAccount().getFolders());	
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario no puede ser nulo"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
		}
	}
	
	@Test
	public void testSaveFolderNegative() {
		System.out.println("----------------------------Save Folder Negative------------------------------");		
		try{
			authenticate("sponsor1");
			
			Folder result;
			User user;		
				
			user = userService.findOne(16);
			result = folderService.createByActor(user);
			
			result.setName("Inbox");
			
			result = folderService.saveFolderByActor(result);
			
			System.out.println("Nombre de Folder: "	+ result.getName());
			
			authenticate(null);
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario logueado no puede ralizar esta operacion"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
		}
	}
}