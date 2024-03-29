
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
import domain.Auditor;
import domain.Folder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AuditorServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private AuditorService	auditorService;

	@Autowired
	private FolderService	folderService;

	@Autowired
	private ActorService	actorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		Auditor auditor;

		auditor = this.auditorService.create();
		Assert.notNull(auditor.getAuditRecords());
		Assert.notNull(auditor.getNotes());
		Assert.notNull(auditor.getFolders());
		Assert.notNull(auditor.getRecipientMessages());
		Assert.notNull(auditor.getSentMessages());
		Assert.notNull(auditor.getSocialIdentities());
		Assert.isTrue(auditor.getDeactivated() == false);
		Assert.notNull(auditor.getUserAccount());
		Assert.isNull(auditor.getName());
		Assert.isNull(auditor.getSurname());
		Assert.isNull(auditor.getEmail());
		Assert.isNull(auditor.getAddress());
		Assert.isNull(auditor.getPhone());
	}

	@Test
	public void testSave() {

		Auditor auditor, saved;

		auditor = this.auditorService.create();
		auditor.setName("Auditor 1");
		auditor.setSurname("Auditor 1");
		auditor.setEmail("auditor1@acmeexplorer.com");
		auditor.setAddress("Auditor 1 Street");
		auditor.setPhone("111111111");
		auditor.getUserAccount().setUsername("auditor11");
		auditor.getUserAccount().setPassword("auditor11");
		saved = this.auditorService.save(auditor);
		final Collection<Actor> actors = this.actorService.findAll();
		final Collection<Folder> folders = this.folderService.findAll();
		Assert.isTrue(actors.contains(saved));
		Assert.isTrue(folders.containsAll(saved.getFolders()));
	}

	@Test
	public void testDelete() {

		Auditor auditor, saved;

		auditor = this.auditorService.create();
		auditor.setName("Auditor 1");
		auditor.setSurname("Auditor 1");
		auditor.setEmail("auditor1@acmeexplorer.com");
		auditor.setAddress("Auditor 1 Street");
		auditor.setPhone("111111111");
		auditor.getUserAccount().setUsername("auditor11");
		auditor.getUserAccount().setPassword("auditor11");
		saved = this.auditorService.save(auditor);
		this.auditorService.delete(saved);
		final Collection<Actor> actors = this.actorService.findAll();
		final Collection<Folder> folders = this.folderService.findByUserAccountId(saved.getUserAccount().getId());
		Assert.isTrue(!actors.contains(saved));
		Assert.isTrue(folders.isEmpty());
	}

	@Test
	public void testFindByPrincipal() {

		Auditor auditor;

		super.authenticate("auditor1");
		auditor = this.auditorService.findByPrincipal();
		Assert.isTrue(auditor.getUserAccount().getUsername().equals("auditor1"));
		super.authenticate(null);
	}

}
