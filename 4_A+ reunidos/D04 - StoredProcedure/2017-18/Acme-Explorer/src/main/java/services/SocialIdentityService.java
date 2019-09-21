
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SocialIdentityRepository;
import domain.Actor;
import domain.SocialIdentity;

@Service
@Transactional
public class SocialIdentityService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SocialIdentityRepository	socialIdentityRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService				actorService;


	// Constructors -----------------------------------------------------------

	public SocialIdentityService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public SocialIdentity create(final Actor actor) {

		Assert.notNull(actor);

		SocialIdentity result = null;
		result = new SocialIdentity();
		result.setActor(actor);
		actor.getSocialIdentities().add(result);
		return result;
	}

	public SocialIdentity findOne(final int socialIdentityId) {

		SocialIdentity result = null;
		result = this.socialIdentityRepository.findOne(socialIdentityId);
		return result;
	}

	public SocialIdentity findOneToEdit(final int socialIdentityId) {

		SocialIdentity result = null;
		result = this.socialIdentityRepository.findOne(socialIdentityId);
		this.checkPrincipal(result);
		return result;
	}

	public Collection<SocialIdentity> findAll() {

		Collection<SocialIdentity> result = null;
		result = this.socialIdentityRepository.findAll();
		return result;
	}

	public SocialIdentity save(final SocialIdentity socialIdentity) {

		Assert.notNull(socialIdentity);

		SocialIdentity result = null;
		this.checkPrincipal(socialIdentity);
		result = this.socialIdentityRepository.save(socialIdentity);
		return result;
	}

	public void delete(final SocialIdentity socialIdentity) {

		Assert.notNull(socialIdentity);

		this.checkPrincipal(socialIdentity);
		this.socialIdentityRepository.delete(socialIdentity);
	}

	// Other business methods -------------------------------------------------

	public void deleteByActor(final Actor actor) {

		Assert.notNull(actor);

		final Collection<SocialIdentity> socialIdentities = actor.getSocialIdentities();
		this.socialIdentityRepository.delete(socialIdentities);
		actor.getSocialIdentities().removeAll(socialIdentities);
	}

	public Collection<SocialIdentity> findByPrincipal() {

		Collection<SocialIdentity> result = null;
		final Actor actor = this.actorService.findByPrincipal();
		result = this.findByActor(actor);
		return result;
	}

	public Collection<SocialIdentity> findByActor(final Actor actor) {

		Assert.notNull(actor);

		Collection<SocialIdentity> result = null;
		result = this.socialIdentityRepository.findByActor(actor);
		return result;
	}

	public void checkPrincipal(final SocialIdentity socialIdentity) {

		Assert.notNull(socialIdentity);

		final Actor actor = socialIdentity.getActor();
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(actor.equals(principal));
	}

}
