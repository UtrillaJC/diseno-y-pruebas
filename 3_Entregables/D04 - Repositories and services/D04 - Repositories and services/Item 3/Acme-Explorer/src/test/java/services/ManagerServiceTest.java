
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
import domain.Manager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ManagerServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private FolderService	folderService;

	@Autowired
	private ActorService	actorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		Manager manager;

		manager = this.managerService.create();
		Assert.notNull(manager.getSurvivalClasses());
		Assert.notNull(manager.getTrips());
		Assert.notNull(manager.getFolders());
		Assert.notNull(manager.getRecipientMessages());
		Assert.notNull(manager.getSentMessages());
		Assert.notNull(manager.getSocialIdentities());
		Assert.isTrue(manager.getDeactivated() == false);
		Assert.notNull(manager.getUserAccount());
		Assert.isNull(manager.getName());
		Assert.isNull(manager.getSurname());
		Assert.isNull(manager.getEmail());
		Assert.isNull(manager.getAddress());
		Assert.isNull(manager.getPhone());
	}
	@Test
	public void testSave() {

		Manager manager, saved;

		manager = this.managerService.create();
		manager.setName("Manager 1");
		manager.setSurname("Manager 1");
		manager.setEmail("manager1@acmeexplorer.com");
		manager.setAddress("Manager 1 Street");
		manager.setPhone("111111111");
		manager.getUserAccount().setUsername("manager11");
		manager.getUserAccount().setPassword("manager11");
		saved = this.managerService.save(manager);
		final Collection<Actor> actors = this.actorService.findAll();
		final Collection<Folder> folders = this.folderService.findAll();
		Assert.isTrue(actors.contains(saved));
		Assert.isTrue(folders.containsAll(saved.getFolders()));
	}

	@Test
	public void testDelete() {

		Manager manager, saved;

		manager = this.managerService.create();
		manager.setName("Manager 1");
		manager.setSurname("Manager 1");
		manager.setEmail("manager1@acmeexplorer.com");
		manager.setAddress("Manager 1 Street");
		manager.setPhone("111111111");
		manager.getUserAccount().setUsername("manager11");
		manager.getUserAccount().setPassword("manager11");
		saved = this.managerService.save(manager);
		this.managerService.delete(saved);
		final Collection<Actor> actors = this.actorService.findAll();
		final Collection<Folder> folders = this.folderService.findAll();
		Assert.isTrue(!actors.contains(saved));
		Assert.isTrue(!folders.contains(saved.getFolders()));
	}

	@Test
	public void testAvgMinAvgMinMaxDesvTripsPerManager() {

		Collection<Double> result;

		super.authenticate("admin1");
		result = this.managerService.avgMinAvgMinMaxDesvTripsPerManager();
		Assert.notNull(result);
		super.authenticate(null);
	}

	@Test
	public void testRatioManagersSuspicious() {

		String result;

		super.authenticate("admin1");
		result = this.managerService.ratioManagersSuspicious();
		Assert.notNull(result);
		super.authenticate(null);
	}

	@Test
	public void testFindByPrincipal() {

		Manager manager;

		super.authenticate("manager1");
		manager = this.managerService.findByPrincipal();
		Assert.isTrue(manager.getUserAccount().getUsername().equals("manager1"));
		super.authenticate(null);
	}

}
