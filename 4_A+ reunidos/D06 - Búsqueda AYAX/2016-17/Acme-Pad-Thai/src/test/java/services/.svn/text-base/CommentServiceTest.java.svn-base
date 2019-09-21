package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Comment;
import domain.Recipe;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class CommentServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private RecipeService recipeService;

	// Tests =========================================================================================
	
	/**
	* ################################################################
	*
	* TEST POSITIVOS
	*
	* #################################################################
	*/
	
	@Test
	public void testCreate(){
		System.out.println("-----------------------------Create-----------------------------");
		authenticate("nutritionist1");
		
		Comment comment;
		Recipe recipe;
		
		recipe = recipeService.findOne(126);
		comment = commentService.create(recipe);
		
		System.out.println("Author: " + comment.getPerson().getName());
		System.out.println("Moment Created: " + comment.getMomentCreated());
		System.out.println("Title: " + comment.getTitle());
		System.out.println("Text: " + comment.getText());
		System.out.println("Stars: " + comment.getStars());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testSave(){
		System.out.println("-----------------------------Save-----------------------------");
		authenticate("nutritionist1");
		
		Comment comment;
		Recipe recipe;
		
		recipe = recipeService.findOne(126);
		comment = commentService.create(recipe);
		
		comment.setStars(1);
				
		comment = commentService.save(comment);
		
		System.out.println("Author: " + comment.getPerson().getName());
		System.out.println("Moment Created: " + comment.getMomentCreated());
		System.out.println("Title: " + comment.getTitle());
		System.out.println("Text: " + comment.getText());
		System.out.println("Stars: " + comment.getStars());

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
	public void testCreateNegative(){
		try {
			System.out.println("-----------------------------Create Negative-----------------------------");
			authenticate("admin");
			
			Comment comment;
			Recipe recipe;
			
			recipe = recipeService.findOne(126);
			comment = commentService.create(recipe);

			System.out.println("Author: " + comment.getPerson().getName());
			System.out.println("Moment Created: " + comment.getMomentCreated());
			System.out.println("Title: " + comment.getTitle());
			System.out.println("Text: " + comment.getText());
			System.out.println("Stars: " + comment.getStars());
			
			authenticate(null);
		}
		catch(Exception e){System.out.println("El usuario logueado no puede crear el comentario"); }
		finally{System.out.println("----------------------------------------------------------------"); }
	}
	
	@Test
	public void testSaveNegative(){
		try {
			System.out.println("-----------------------------Save Negative-----------------------------");
			authenticate("admin");
			Comment comment;
			Recipe recipe;
			
			recipe = recipeService.findOne(126);
			comment = commentService.create(recipe);
			
			comment.setStars(1);
					
			comment = commentService.save(comment);
			
			System.out.println("Author: " + comment.getPerson().getName());
			System.out.println("Moment Created: " + comment.getMomentCreated());
			System.out.println("Title: " + comment.getTitle());
			System.out.println("Text: " + comment.getText());
			System.out.println("Stars: " + comment.getStars());

			authenticate(null);
		}
		catch(Exception e){System.out.println("El usuario logueado no puede guardar el comentario"); }
		finally{System.out.println("----------------------------------------------------------------"); }
	}
		
	

}