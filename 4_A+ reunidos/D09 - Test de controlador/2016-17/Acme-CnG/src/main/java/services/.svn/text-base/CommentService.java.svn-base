
package services;

import java.sql.Date;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Actor;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	//Managed Repository =============================================================================

	@Autowired
	private CommentRepository	commentRepository;

	//Supported Services =============================================================================

	@Autowired
	private ActorService		actorService;
	
	@Autowired
	private AdministratorService		administratorService;
	


	//Constructor methods ============================================================================

	public CommentService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public Comment findOne(final int commentId) {
		Comment result;

		result = this.commentRepository.findOne(commentId);

		return result;
	}
	
	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = this.commentRepository.findAll();

		return result;
	}
	
	
//	public Comment create() {
//		Comment result;
//		Actor a;
//
//		a = this.actorService.findByPrincipal();
//		result = new Comment();
//		result.setCreatedMoment(new Date(System.currentTimeMillis() - 10));
//		result.setAuthor(a);
//		result.setBanned(false);
//
//		return result;
//	}

	public Comment create(int commentClassId) {
		Comment result;

		Actor a;

		a = this.actorService.findByPrincipal();
		result = new Comment();
		result.setCreatedMoment(new Date(System.currentTimeMillis() - 10));
		result.setAuthor(a);
		result.setBanned(false);
		result.setReceiverId(commentClassId);

		return result;
	}

	public Comment save(Comment comment) {
		Comment result;
		Assert.notNull(comment);

//		Assert.isTrue(this.actorService.findByPrincipal().getId()!=comment.getReceiver().getId());
		result = this.commentRepository.saveAndFlush(comment);
		

		Actor author = this.actorService.findByPrincipal();
		
		author.getCreatedComments().add(result);
		actorService.save(author);
		

		return result;
	}

	// Other business methods -----------------------------------------------

	public Collection<Comment> findByCommentClassId(int commentClassId) {
		Collection<Comment> result = this.commentRepository.findByCommentClass(commentClassId);

		Assert.notNull(result);

		return result;
	}
	
	
	public void banComment(int commentId) {
		Comment comment = findOne(commentId);
		administratorService.checkPrincipal();;

		comment.setBanned(true);
		
		this.commentRepository.saveAndFlush(comment);
	}


//		public Collection<Comment> findByActorId(int actorId) {
//			Collection<Comment> result;
//			Actor principal = this.actorService.findOne(actorId);
//			result = principal.getReceivedComments();
//			Assert.notNull(result);
//	
//			return result;
//		}
//	
//		public Collection<Comment> findByServiceId(int serviceId) {
//			Collection<Comment> result;
//			domain.Service service = this.serviceService.findOne(serviceId);
//	
//			result = service.getReceivedComments();
//	
//			return result;
//		}

	public Double avgCommentPerActor() {
		administratorService.checkPrincipal();
		Double result;

		result = this.commentRepository.avgCommentPerActor();

		if (result == null)
			result = 0.0;

		return result;
	}

	public Double avgCommentPerOffer() {
		administratorService.checkPrincipal();
		Double result;

		result = this.commentRepository.avgCommentPerOffer();

		if (result == null)
			result = 0.0;

		return result;
	}

	public Double avgCommentPerRequest() {
		administratorService.checkPrincipal();
		Double result;

		result = this.commentRepository.avgCommentPerRequest();

		if (result == null)
			result = 0.0;

		return result;
	}
	
	public Double avgCommentPostedActor() {
		administratorService.checkPrincipal();
		Double result;

		result = this.commentRepository.avgCommentPostedActor();

		if (result == null)
			result = 0.0;

		return result;
	}
	
	public Collection<Actor> postedActor10Avg() {
		administratorService.checkPrincipal();
		Collection<Actor> result = this.commentRepository.postedActor10Avg();

		Assert.notNull(result);

		return result;
	}
}
