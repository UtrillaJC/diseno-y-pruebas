
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
import domain.Folder;
import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SponsorServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private SponsorService	sponsorService;

	@Autowired
	private FolderService	folderService;

	@Autowired
	private ActorService	actorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		Sponsor sponsor;

		sponsor = this.sponsorService.create();
		Assert.notNull(sponsor.getSponsorships());
		Assert.notNull(sponsor.getFolders());
		Assert.notNull(sponsor.getRecipientMessages());
		Assert.notNull(sponsor.getSentMessages());
		Assert.notNull(sponsor.getSocialIdentities());
		Assert.isTrue(sponsor.getDeactivated() == false);
		Assert.notNull(sponsor.getUserAccount());
		Assert.isNull(sponsor.getName());
		Assert.isNull(sponsor.getSurname());
		Assert.isNull(sponsor.getEmail());
		Assert.isNull(sponsor.getAddress());
		Assert.isNull(sponsor.getPhone());
	}
	@Test
	public void testSave() {

		Sponsor sponsor, saved;

		sponsor = this.sponsorService.create();
		sponsor.setName("Sponsor 1");
		sponsor.setSurname("Sponsor 1");
		sponsor.setEmail("sponsor1@acmeexplorer.com");
		sponsor.setAddress("Sponsor 1 Street");
		sponsor.setPhone("111111111");
		sponsor.getUserAccount().setUsername("sponsor11");
		sponsor.getUserAccount().setPassword("sponsor11");
		saved = this.sponsorService.save(sponsor);
		final Collection<Actor> actors = this.actorService.findAll();
		final Collection<Folder> folders = this.folderService.findAll();
		Assert.isTrue(actors.contains(saved));
		Assert.isTrue(folders.containsAll(saved.getFolders()));
	}

	@Test
	public void testDelete() {

		Sponsor sponsor, saved;

		sponsor = this.sponsorService.create();
		sponsor.setName("Sponsor 1");
		sponsor.setSurname("Sponsor 1");
		sponsor.setEmail("sponsor1@acmeexplorer.com");
		sponsor.setAddress("Sponsor 1 Street");
		sponsor.setPhone("111111111");
		sponsor.getUserAccount().setUsername("sponsor11");
		sponsor.getUserAccount().setPassword("sponsor11");
		saved = this.sponsorService.save(sponsor);
		this.sponsorService.delete(saved);
		final Actor actorRetrieved = this.actorService.findOne(saved.getId());
		final Collection<Folder> foldersRetrieved = saved.getFolders();
		Assert.isNull(actorRetrieved);
		Assert.isTrue(foldersRetrieved.isEmpty());
	}

	@Test
	public void testFindByPrincipal() {

		Sponsor sponsor;

		super.authenticate("sponsor1");
		sponsor = this.sponsorService.findByPrincipal();
		Assert.isTrue(sponsor.getUserAccount().getUsername().equals("sponsor1"));
		super.authenticate(null);
	}

}
