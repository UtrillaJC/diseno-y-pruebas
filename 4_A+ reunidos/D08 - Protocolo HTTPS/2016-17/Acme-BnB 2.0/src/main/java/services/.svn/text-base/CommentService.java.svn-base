
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Comment;
import domain.Lessor;
import domain.Tenant;
import repositories.CommentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CommentRepository	commentRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private LessorService		lessorService;
	
	@Autowired
	private TenantService tenantService;

	// Supporting services ----------------------------------------------------


	// Constructors-------------------------------------------------------
	public CommentService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public Comment create() {
		Comment result;
		Actor a;

		a = this.actorService.findByPrincipal();
		result = new Comment();
		result.setMoment(new Date(System.currentTimeMillis() - 10));
		result.setAuthor(a);

		return result;
	}

	public Comment save(Comment comment) {
		Comment result;
		Assert.notNull(comment);
		this.checkPrincipalLessorOrTenant();

		result = this.commentRepository.save(comment);

		return result;
	}

	// Other business methods -----------------------------------------------

	public void checkPrincipalLessorOrTenant() {
		UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		Authority auth1 = new Authority();
		Authority auth2 = new Authority();
		auth1.setAuthority("LESSOR");
		auth2.setAuthority("TENANT");

		Assert.isTrue(authorities.contains(auth1) || authorities.contains(auth2));

	}

	public Collection<Comment> findByLessorId(int lessorId) {
		Collection<Comment> result;
		Lessor principal = lessorService.findOne(lessorId);
		result = principal.getCommentReceivers();
		Assert.notNull(result);

		return result;
	}
	
	public Collection<Comment> findByTenantId(int tenantId) {
		Collection<Comment> result;
		Tenant principal = tenantService.findOne(tenantId);
		result = principal.getCommentReceivers();
		Assert.notNull(result);

		return result;
	}

}
