
package services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.CreditCard;
import domain.Explorer;
import domain.Folder;
import domain.Status;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private ExplorerService		explorerService;

	@Autowired
	private TripService			tripService;

	@Autowired
	private FolderService		folderService;


	@Test
	public void testCreate() {

		super.authenticate("explorer1");

		Application application = null;
		Explorer explorer = null;

		explorer = this.explorerService.findByPrincipal();
		application = this.applicationService.create(explorer);

		Assert.notNull(application.getApplicant());
		Assert.notNull(application.getMoment());
		Assert.isTrue(application.getStatus().equals(Status.PENDING));

		super.unauthenticate();
	}

	@Test
	public void testSave() {

		super.authenticate("explorer1");

		Application application = null;
		Application saved = null;
		Trip trip = null;
		Explorer explorer = null;

		trip = this.tripService.findTripPerKeywordR("AAAB").iterator().next();

		explorer = this.explorerService.findByPrincipal();
		application = this.applicationService.create(explorer);

		application.setComments("This is a modified comment");

		trip.setApplications(new ArrayList<Application>());
		application.setTrip(trip);

		saved = this.applicationService.save(application);

		Assert.isTrue(this.applicationService.findAll().contains(saved));

		super.unauthenticate();
	}

	@Test
	public void testCancelApplicationWithNotification() {

		super.authenticate("explorer1");

		Application application = null;
		Folder notificationBox = null;
		Explorer explorer = null;
		Integer numberOfMessages = null;

		explorer = this.explorerService.findByPrincipal();
		application = this.applicationService.findApplicationsByStatus(Status.ACCEPTED).iterator().next();

		notificationBox = this.folderService.findByFolderName(this.explorerService.findByPrincipal().getUserAccount().getId(), "Notification Box");
		numberOfMessages = notificationBox.getMessages().size();

		this.applicationService.cancelApplication(application);

		Assert.isTrue(application.getStatus().equals(Status.CANCELLED));
		Assert.isTrue(this.applicationService.findApplicationsByExplorerId(explorer.getId()).contains(application));
		Assert.isTrue(notificationBox.getMessages().size() > numberOfMessages);

		super.unauthenticate();
	}
	@Test
	public void testDueApplication() {

		super.authenticate("manager1");

		Application application = null;

		application = this.applicationService.findApplicationsByStatus(Status.PENDING).iterator().next();
		this.applicationService.dueApplication(application);

		Assert.isTrue(application.getStatus().equals(Status.DUE));

		super.unauthenticate();

	}

	@Test
	public void testRejectApplication() {

		super.authenticate("manager1");

		Application application = null;
		String rejectReason = null;

		application = this.applicationService.findApplicationsByStatus(Status.PENDING).iterator().next();
		rejectReason = "This is the rejectReason";
		this.applicationService.rejectApplication(application, rejectReason);

		Assert.isTrue(application.getStatus().equals(Status.REJECTED));

		super.unauthenticate();

	}

	@Test
	public void testAcceptApplication() {

		super.authenticate("explorer2");

		Application application = null;
		CreditCard creditCard = null;

		application = this.applicationService.findApplicationsByStatus(Status.DUE).iterator().next();

		creditCard = new CreditCard();
		creditCard.setBrand("VISA");
		creditCard.setCvv(612);
		creditCard.setExpirationMonth(5);
		creditCard.setExpirationYear(2022);
		creditCard.setHolder("Holder1");
		creditCard.setNumber("4539009782278447");

		this.applicationService.acceptApplication(application, creditCard);

		Assert.isTrue(application.getStatus().equals(Status.ACCEPTED));

		super.unauthenticate();
	}

}
