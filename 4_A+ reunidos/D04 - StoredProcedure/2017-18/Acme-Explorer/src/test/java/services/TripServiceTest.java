
package services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Category;
import domain.Stage;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TripServiceTest extends AbstractTest {

	@Autowired
	private TripService		tripService;

	@Autowired
	private CategoryService	categoryService;

	@Autowired
	private ManagerService	mangerService;

	@Autowired
	private StageService	stageService;


	@Test
	public void testCreate() {

		super.authenticate("manager1");

		Trip trip = null;

		trip = this.tripService.create();

		Assert.notNull(trip.getTicker());
		Assert.isNull(trip.getTitle());
		Assert.isNull(trip.getDescription());
		Assert.notNull(trip.getRequirements());
		Assert.isNull(trip.getPublicationDate());
		Assert.isNull(trip.getStartDateTrip());
		Assert.isNull(trip.getEndDateTrip());
		Assert.isNull(trip.getCancelledReason());
		Assert.isNull(trip.getPrice());
		Assert.notNull(trip.getNotes());
		Assert.notNull(trip.getAuditRecords());
		Assert.notNull(trip.getStories());
		Assert.notNull(trip.getApplications());
		Assert.notNull(trip.getSurvivalClasses());
		Assert.notNull(trip.getSponsorships());
		Assert.notNull(trip.getStages());
		Assert.notNull(trip.getHasValues());

		super.unauthenticate();
	}

	@Test
	public void testSave() {

		super.authenticate("manager1");

		Trip trip = null;
		Trip tripSaved = null;
		Collection<Trip> trips = null;

		trip = this.tripService.findOne(7279);
		trips = new ArrayList<Trip>();

		trip.setDescription("Description TEST");
		tripSaved = this.tripService.save(trip);
		Assert.isTrue(tripSaved.getDescription().equals("Description TEST"));
		trips = this.tripService.findAll();

		Assert.isTrue(trips.contains(tripSaved));

		super.unauthenticate();
	}
	@Test
	public void testDelete() {

		super.authenticate("manager1");

		Trip trip = null;
		Trip tripSaved = null;
		Collection<Trip> trips = null;

		trips = new ArrayList<Trip>();

		trip = this.tripService.findOne(7279);

		trip.setDescription("Description TEST");
		trip.setPublicationDate(new Timestamp(2020, 10, 20, 20, 10, 00, 00));
		tripSaved = this.tripService.save(trip);

		this.tripService.delete(tripSaved);

		trips = this.tripService.findAll();

		Assert.isTrue(!trips.contains(tripSaved));

		super.unauthenticate();
	}

	@Test
	public void assignedCategoryToTrip() {

		super.authenticate("manager1");

		final Category category = this.categoryService.findOne(7158);
		final Trip trip = this.tripService.findOne(7278);

		final Trip tripSave = this.tripService.assignedCategoryToTrip(trip, category);

		Assert.isTrue(tripSave.getCategory().equals(category));
		Assert.isTrue(category.getTrips().contains(trip));

		super.unauthenticate();
	}

	@Test
	public void unassignedStageOfTrip() {

		super.authenticate("manager1");

		final Stage stage = this.stageService.findOne(7173);
		final Trip trip = this.tripService.findOne(7278);

		final Trip tripSave = this.tripService.unassignedStageOfTrip(trip, stage);

		Assert.isTrue(!tripSave.getStages().contains(stage));

		super.unauthenticate();
	}

	@Test
	public void listTripPerManager() {

		super.authenticate("manager1");
		Collection<Trip> trips = null;
		trips = new ArrayList<Trip>();
		int managerId;

		managerId = this.mangerService.findByPrincipal().getId();
		trips = this.tripService.listTripPerManager(managerId);

		super.authenticate("manager1");
	}

	@Test
	public void avgMinMaxDevApplicationsPerTrip() {

		this.tripService.avgMinMaxDevApplicationsPerTrip();
	}

	@Test
	public void avgMinMaxDevPriceOfTheTrips() {

		this.tripService.avgMinMaxDevPriceOfTheTrips();
	}

	@Test
	public void ratioOfTripsCancelledVsTotalTripsOrganized() {

		this.tripService.ratioOfTripsCancelledVsTotalTripsOrganized();
	}

	@Test
	public void listingTrips10PercentMoraApplicantionsThanAvg() {

		this.tripService.listingTrips10PercentMoraApplicantionsThanAvg();
	}

	@Test
	public void minMaxAvgDevNotesPerTrip() {
		this.tripService.minMaxAvgDevNotesPerTrip();
	}

	@Test
	public void minMaxAvgDevAuditRecordPerTrip() {
		this.tripService.minMaxAvgDevAuditRecordPerTrip();
	}

	@Test
	public void ratioOfTripsWithAnyAuditRecord() {
		this.tripService.ratioOfTripsWithAnyAuditRecord();
	}

	@Test
	public void auditorPerTrip() {
		super.authenticate("manager1");

		final Trip trip = this.tripService.findOne(7279);
		this.tripService.auditorPerTrip(trip.getId());

		super.unauthenticate();
	}
}
