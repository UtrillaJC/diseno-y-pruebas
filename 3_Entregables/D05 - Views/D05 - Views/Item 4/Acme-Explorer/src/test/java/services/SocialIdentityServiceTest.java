
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.SocialIdentity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SocialIdentityServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private SocialIdentityService	socialIdentityService;

	@Autowired
	private ActorService			actorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		SocialIdentity socialIdentity;

		super.authenticate("admin1");
		final Actor actor = this.actorService.findByPrincipal();
		socialIdentity = this.socialIdentityService.create(actor);
		Assert.isTrue(actor == socialIdentity.getActor());
		Assert.isNull(socialIdentity.getNick());
		Assert.isNull(socialIdentity.getNameSocialNetwork());
		Assert.isNull(socialIdentity.getLink());
		Assert.isNull(socialIdentity.getPhoto());
		super.authenticate(null);
	}

	@Test
	public void testSave() {

		SocialIdentity socialIdentity, saved;

		super.authenticate("admin1");
		final Actor actor = this.actorService.findByPrincipal();
		socialIdentity = this.socialIdentityService.create(actor);
		socialIdentity.setNick("Social identity nick");
		socialIdentity.setNameSocialNetwork("Social identity name social network");
		socialIdentity.setLink("http://www.link.com/");
		socialIdentity.setPhoto("http://www.photo.com/");
		saved = this.socialIdentityService.save(socialIdentity);
		final Collection<SocialIdentity> socialIdentities = this.socialIdentityService.findByPrincipal();
		Assert.isTrue(socialIdentities.contains(saved));
		super.authenticate(null);
	}

	@Test
	public void testDelete() {

		SocialIdentity socialIdentity, saved;

		super.authenticate("admin1");
		final Actor actor = this.actorService.findByPrincipal();
		socialIdentity = this.socialIdentityService.create(actor);
		socialIdentity.setNick("Social identity nick");
		socialIdentity.setNameSocialNetwork("Social identity name social network");
		socialIdentity.setLink("http://www.link.com/");
		socialIdentity.setPhoto("http://www.photo.com/");
		saved = this.socialIdentityService.save(socialIdentity);
		this.socialIdentityService.delete(saved);
		final Collection<SocialIdentity> socialIdentities = this.socialIdentityService.findByPrincipal();
		Assert.isTrue(!socialIdentities.contains(saved));
		super.authenticate(null);
	}

	@Test
	public void testFindOneToEditPositive() {

		SocialIdentity socialIdentity, saved, socialIdentityRetrieved;

		super.authenticate("admin1");
		socialIdentity = this.socialIdentityService.create(this.actorService.findByPrincipal());
		socialIdentity.setNick("Social identity nick");
		socialIdentity.setNameSocialNetwork("Social identity name social network");
		socialIdentity.setLink("http://www.link.com/");
		socialIdentity.setPhoto("http://www.photo.com/");
		saved = this.socialIdentityService.save(socialIdentity);
		socialIdentityRetrieved = this.socialIdentityService.findOneToEdit(saved.getId());
		Assert.isTrue(saved.equals(socialIdentityRetrieved));
		super.authenticate(null);
	}

	@Test
	public void testFindOneToEditNegative() {

		SocialIdentity socialIdentity1;

		super.authenticate("admin1");
		socialIdentity1 = this.socialIdentityService.findOneToEdit(this.socialIdentityService.findByPrincipal().iterator().next().getId());
		super.authenticate(null);
		super.authenticate("auditor1");
		try {
			final SocialIdentity socialIdentity2 = this.socialIdentityService.findOneToEdit(socialIdentity1.getId());
		} catch (final Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void testFindByPrincipal() {

		Collection<SocialIdentity> socialIdentities;

		super.authenticate("admin1");
		final Actor actor = this.actorService.findByPrincipal();
		socialIdentities = this.socialIdentityService.findByPrincipal();
		for (final SocialIdentity s : socialIdentities)
			Assert.isTrue(s.getActor() == actor);
		super.authenticate(null);
	}
}
