
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
import domain.Explorer;
import domain.Finder;
import domain.Folder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ExplorerServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private ExplorerService	explorerService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private FolderService	folderService;

	@Autowired
	private FinderService	finderService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		Explorer explorer = null;

		explorer = this.explorerService.create();

		Assert.notNull(explorer.getApplications());
		Assert.notNull(explorer.getStories());
		Assert.notNull(explorer.getContacts());
		Assert.notNull(explorer.getFinder());
		Assert.notNull(explorer.getFolders());
		Assert.notNull(explorer.getRecipientMessages());
		Assert.notNull(explorer.getSentMessages());
		Assert.notNull(explorer.getSocialIdentities());
		Assert.isTrue(explorer.getDeactivated() == false);
		Assert.notNull(explorer.getUserAccount());
		Assert.isNull(explorer.getName());
		Assert.isNull(explorer.getSurname());
		Assert.isNull(explorer.getEmail());
		Assert.isNull(explorer.getAddress());
		Assert.isNull(explorer.getPhone());
	}

	@Test
	public void testSave() {

		Explorer explorer = null;
		Explorer saved = null;
		Collection<Actor> actors = null;
		Collection<Folder> folders = null;
		Collection<Finder> finders = null;

		explorer = this.explorerService.create();
		explorer.setName("Explorer 1");
		explorer.setSurname("Explorer 1");
		explorer.setEmail("explorer1@acmeexplorer.com");
		explorer.setAddress("Explorer 1 Street");
		explorer.setPhone("111111111");
		explorer.getUserAccount().setUsername("explorer11");
		explorer.getUserAccount().setPassword("explorer11");

		saved = this.explorerService.save(explorer);
		actors = this.actorService.findAll();
		folders = this.folderService.findAll();
		finders = this.finderService.findAll();

		Assert.isTrue(actors.contains(saved));
		Assert.isTrue(folders.containsAll(saved.getFolders()));
		Assert.isTrue(finders.contains(saved.getFinder()));
	}

	@Test
	public void testDelete() {

		Explorer explorer = null;
		Explorer saved = null;
		Explorer explorerRetrieved = null;
		Finder finder = null;
		Finder finderRetrieved = null;
		Collection<Folder> foldersRetrieved = null;

		explorer = this.explorerService.create();
		explorer.setName("Explorer 1");
		explorer.setSurname("Explorer 1");
		explorer.setEmail("explorerr1@acmeexplorer.com");
		explorer.setAddress("Explorer 1 Street");
		explorer.setPhone("111111111");
		explorer.getUserAccount().setUsername("explorer11");
		explorer.getUserAccount().setPassword("explorer11");

		saved = this.explorerService.save(explorer);
		finder = saved.getFinder();

		this.explorerService.delete(saved);
		explorerRetrieved = this.explorerService.findOne(saved.getId());

		foldersRetrieved = saved.getFolders();
		finderRetrieved = this.finderService.findOne(finder.getId());

		Assert.isNull(explorerRetrieved);
		Assert.isTrue(foldersRetrieved.isEmpty());
		Assert.isNull(finderRetrieved);
	}

	@Test
	public void testFindByPrincipal() {

		super.authenticate("explorer1");

		Explorer explorer = null;

		explorer = this.explorerService.findByPrincipal();
		Assert.isTrue(explorer.getUserAccount().getUsername().equals("explorer1"));

		super.unauthenticate();
	}

}
