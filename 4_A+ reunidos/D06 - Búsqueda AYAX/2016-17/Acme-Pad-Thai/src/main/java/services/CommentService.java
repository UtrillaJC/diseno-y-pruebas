package services;

import java.sql.Date;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import domain.Person;
import domain.Recipe;

@Service
@Transactional
public class CommentService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private CommentRepository commentRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private PersonService personService;
		
	//Constructor methods ============================================================================
	
	public CommentService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Comment findOne(int commentId){
		Comment result;
		
		result = commentRepository.findOne(commentId);
		
		return result;
	}
	
	public Comment create(Recipe recipe){
		Comment result;
		Person principal;
		Date momentCreated;
		
		momentCreated = new Date(System.currentTimeMillis());
		principal = personService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Person.class, principal);
		
		result = new Comment();
		
		result.setMomentCreated(momentCreated);
		result.setPerson(principal);
		result.setNameRecipe(recipe.getTitle());
		principal.getComments().add(result);
		
		return result;
	}
	
	public Comment save(Comment comment){
		Assert.notNull(comment);	
		Assert.notNull(comment.getPerson());		
		Comment result;
		Person principal;
		Date momentCreated;
				
		principal = personService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(comment.getPerson()));
		Assert.isInstanceOf(Person.class, principal);
		
		momentCreated = new Date(System.currentTimeMillis()-1000);
		
		comment.setPerson(principal);
		comment.setMomentCreated(momentCreated);
		
        result = commentRepository.save(comment);
		
		return result;
	}
	
	//Other Business Methods =========================================================================
	
	public Collection<Comment> findAllByMomentCreatedDesc() {
		Collection<Comment> result;
		
		result = commentRepository.findAllByMomentCreatedDesc();
		
		return result;
	}
	
	public Collection<Comment> findAllByNameRecipe(String nameRecipe){
		Collection<Comment> result;
		
		result = commentRepository.findAllByNameRecipe(nameRecipe);
		
		return result;
	}
}
