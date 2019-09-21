
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
import domain.Ranger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RangerServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private RangerService	rangerService;

	@Autowired
	private FolderService	folderService;

	@Autowired
	private ActorService	actorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		Ranger ranger = null;

		ranger = this.rangerService.create();

		Assert.isNull(ranger.getCurriculum());
		Assert.notNull(ranger.getTrips());
		Assert.notNull(ranger.getFolders());
		Assert.notNull(ranger.getRecipientMessages());
		Assert.notNull(ranger.getSentMessages());
		Assert.notNull(ranger.getSocialIdentities());
		Assert.isTrue(ranger.getDeactivated() == false);
		Assert.notNull(ranger.getUserAccount());
		Assert.isNull(ranger.getName());
		Assert.isNull(ranger.getSurname());
		Assert.isNull(ranger.getEmail());
		Assert.isNull(ranger.getAddress());
		Assert.isNull(ranger.getPhone());
	}
	@Test
	public void testSave() {

		Ranger ranger = null;
		Ranger saved = null;
		Collection<Actor> actors = null;
		Collection<Folder> folders = null;

		ranger = this.rangerService.create();
		ranger.setName("Ranger 1");
		ranger.setSurname("Ranger 1");
		ranger.setEmail("ranger1@acmeexplorer.com");
		ranger.setAddress("Ranger 1 Street");
		ranger.setPhone("111111111");
		ranger.getUserAccount().setUsername("ranger11");
		ranger.getUserAccount().setPassword("ranger11");

		saved = this.rangerService.save(ranger);
		actors = this.actorService.findAll();
		folders = this.folderService.findAll();

		Assert.isTrue(actors.contains(saved));
		Assert.isTrue(folders.containsAll(saved.getFolders()));
	}

	@Test
	public void testDelete() {

		Ranger ranger = null;
		Ranger saved = null;
		Ranger rangerRetrieved = null;
		Collection<Folder> foldersRetrieved = null;

		ranger = this.rangerService.create();
		ranger.setName("Ranger 1");
		ranger.setSurname("Ranger 1");
		ranger.setEmail("ranger1@acmeexplorer.com");
		ranger.setAddress("Ranger 1 Street");
		ranger.setPhone("111111111");
		ranger.getUserAccount().setUsername("ranger11");
		ranger.getUserAccount().setPassword("ranger11");

		saved = this.rangerService.save(ranger);
		this.rangerService.delete(saved);

		rangerRetrieved = this.rangerService.findOne(saved.getId());
		foldersRetrieved = saved.getFolders();

		Assert.isNull(rangerRetrieved);
		Assert.isTrue(foldersRetrieved.isEmpty());
	}

	@Test
	public void testAvgMinMaxDesvTripsPerRanger() {

		super.authenticate("admin1");

		Collection<Double> result = null;

		result = this.rangerService.avgMinMaxDesvTripsPerRanger();
		Assert.notNull(result);

		super.unauthenticate();
	}

	@Test
	public void testRatioRangersWithCurriculum() {

		super.authenticate("admin1");

		String result = null;

		result = this.rangerService.ratioRangersWithCurriculum();
		Assert.notNull(result);

		super.unauthenticate();
	}

	@Test
	public void testRatioRangersCurriculumWithEndorserRecords() {

		super.authenticate("admin1");

		String result = null;

		result = this.rangerService.ratioRangersCurriculumWithEndorserRecords();
		Assert.notNull(result);

		super.unauthenticate();
	}

	@Test
	public void testRatioRangerSuspicious() {

		super.authenticate("admin1");

		String result = null;

		result = this.rangerService.ratioRangersSuspicious();
		Assert.notNull(result);

		super.unauthenticate();
	}

	@Test
	public void testFindByPrincipal() {

		super.authenticate("ranger1");

		Ranger ranger = null;

		ranger = this.rangerService.findByPrincipal();
		Assert.isTrue(ranger.getUserAccount().getUsername().equals("ranger1"));

		super.unauthenticate();
	}

}
