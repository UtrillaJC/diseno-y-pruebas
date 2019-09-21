
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TripRepository;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.AuditRecord;
import domain.Auditor;
import domain.Category;
import domain.Explorer;
import domain.Finder;
import domain.HasValue;
import domain.Manager;
import domain.Money;
import domain.Note;
import domain.Sponsorship;
import domain.Stage;
import domain.Story;
import domain.SurvivalClass;
import domain.Trip;

@Service
public class TripService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private TripRepository			tripRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CategoryService			categoryService;

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private ExplorerService			explorerService;

	//@Autowired
	//private ActorService		actorService;

	//@Autowired
	//private RangerService		rangerService;

	@Autowired
	private NoteService				noteService;

	@Autowired
	private AuditRecordService		auditRecordService;

	@Autowired
	private StoryService			storyService;

	@Autowired
	private AuditorService			auditorService;

	@Autowired
	private ApplicationService		applicationService;

	@Autowired
	private SurvivalClassService	survivalClassService;

	@Autowired
	private SponsorshipService		sponsorshipService;

	@Autowired
	private HasValueService			hasValueService;

	@Autowired
	private FinderService			finderService;


	// Constructors -----------------------------------------------------------

	public TripService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Trip create() {

		Manager manager = null;

		Collection<Note> notes = null;
		Collection<AuditRecord> auditRecords = null;
		Collection<Story> stories = null;
		Collection<Application> applications = null;
		Collection<Finder> finders = null;
		Collection<HasValue> hasValues = null;
		Collection<Sponsorship> sponsorships = null;
		Collection<SurvivalClass> survivalClasses = null;
		Collection<Stage> stages = null;
		manager = this.managerService.findByPrincipal();

		stages = new ArrayList<Stage>();
		notes = new ArrayList<Note>();
		auditRecords = new ArrayList<AuditRecord>();
		stories = new ArrayList<Story>();
		applications = new ArrayList<Application>();
		finders = new ArrayList<Finder>();
		hasValues = new ArrayList<HasValue>();
		sponsorships = new ArrayList<Sponsorship>();
		survivalClasses = new ArrayList<SurvivalClass>();

		Trip res = new Trip();

		res = this.generateTicker(res);

		res.setRequirements(new ArrayList<String>());

		res.setNotes(notes);
		res.setAuditRecords(auditRecords);
		res.setStories(stories);
		res.setApplications(applications);
		res.setManager(manager);
		res.setSurvivalClasses(survivalClasses);
		res.setSponsorships(sponsorships);
		res.setStages(stages);
		res.setHasValues(hasValues);

		return res;

	}

	public Trip save(final Trip trip) {

		Assert.notNull(trip);

		Trip res = null;

		if (this.checkPrincipalAuditor(trip) || this.checkPrincipalExplorer(trip) || this.checkPrincipalManager(trip)) {

			res = new Trip();

			res.setPrice(this.getPrice(trip));
			res = this.tripRepository.save(trip);

		}

		return res;
	}

	public Collection<Trip> findAll() {

		Collection<Trip> res = null;
		res = this.tripRepository.findAll();
		return res;
	}

	public Trip findOne(final int tripId) {

		Trip result = null;
		result = this.tripRepository.findOne(tripId);
		return result;
	}

	public Trip findOneToEdit(final int tripId) {

		Trip result = null;
		result = this.tripRepository.findOne(tripId);
		Assert.isTrue(this.checkPrincipalManager(result) || this.checkPrincipalAuditor(result) || this.checkPrincipalExplorer(result));
		return result;
	}

	public void delete(final Trip trip) {

		Assert.notNull(trip);
		Assert.isTrue(this.checkPrincipalManager(trip));
		Assert.isTrue(this.checkDeleteByPublicationDate(trip));

		this.applicationService.deleteApplications(trip);
		this.auditRecordService.deleteAuditRecords(trip);
		this.storyService.deleteStories(trip);
		this.survivalClassService.deleteSurvivalClasses(trip);
		this.finderService.deleteReferenceTrip(trip);
		this.hasValueService.deleteHasValues(trip);
		this.sponsorshipService.deleteSponsorShips(trip);
		this.noteService.deleteNotes(trip);

		this.tripRepository.delete(trip);

	}
	// Other business methods -------------------------------------------------

	public Trip assignedCategoryToTrip(final Trip trip, final Category category) {

		Assert.notNull(trip);
		Assert.notNull(category);

		Assert.isTrue(this.checkPrincipalManager(trip));

		Trip res = null;
		Collection<Trip> trips = null;

		res = new Trip();
		trips = new ArrayList<Trip>();

		trips.add(trip);

		category.setTrips(trips);
		res.setCategory(category);

		this.categoryService.save(category);
		this.save(res);

		return res;
	}

	public Trip unassignedStageOfTrip(final Trip trip, final Stage stage) {

		Assert.notNull(trip);
		Assert.notNull(stage);

		this.checkPrincipalManager(trip);

		Trip res = null;

		trip.getStages().remove(stage);
		res = this.save(trip);

		return res;
	}

	public boolean checkPrincipalManager(final Trip trip) {

		boolean res = false;

		final Manager manager = this.managerService.findByPrincipal();
		if (manager.equals(trip.getManager()))
			res = true;
		return res;
	}

	public boolean checkPrincipalExplorer(final Trip trip) {

		boolean res = false;

		final Collection<Explorer> explorerers = this.tripRepository.explorerPerTrip(trip.getId());
		final Explorer login = this.explorerService.findByPrincipal();
		if (explorerers.contains(login))
			res = true;
		return res;

	}
	public boolean checkPrincipalAuditor(final Trip trip) {

		boolean res = false;

		final Collection<Auditor> auditors = this.tripRepository.auditorPerTrip(trip.getId());
		final Auditor login = this.auditorService.findByPrincipal();
		if (auditors.contains(login))
			res = true;
		return res;
	}

	public void deleteByManager(final Manager manager) {

		Assert.notNull(manager);

		final Collection<Trip> trips = this.findByManager(manager);
		for (final Trip t : trips) {
			this.noteService.deleteNotes(t);
			this.auditRecordService.deleteAuditRecords(t);
			this.storyService.deleteStories(t);
			this.applicationService.deleteApplications(t);
			this.survivalClassService.deleteSurvivalClasses(t);
			this.sponsorshipService.deleteSponsorShips(t);
			this.hasValueService.deleteHasValues(t);
		}
		this.tripRepository.delete(trips);
	}

	public Collection<Trip> findByManager(final Manager manager) {

		Collection<Trip> result = null;
		result = this.tripRepository.listTripPerManager(manager.getId());
		return result;
	}

	public boolean checkDeleteByPublicationDate(final Trip trip) {

		final Date date = new Date();
		return date.before(trip.getPublicationDate());

	}

	public Trip findOneToCancel(final Trip trip) {

		final Date date = new Date();
		Assert.isNull(trip.getCancelledReason());
		Assert.isTrue(date.before(trip.getStartDateTrip()));

		return trip;
	}

	public Manager findByPrincipal() {

		Manager result = null;
		final UserAccount userAccount = LoginService.getPrincipal();
		result = this.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Manager findByUserAccountId(final int userAccountId) {

		Manager result = null;
		result = this.managerService.findByUserAccountId(userAccountId);
		return result;
	}

	public Collection<Trip> listTripPerManager(final int managerId) {

		Collection<Trip> res = null;
		res = this.tripRepository.listTripPerManager(managerId);
		return res;

	}

	public Collection<Double> avgMinMaxDevApplicationsPerTrip() {
		Collection<Double> res = null;
		res = this.tripRepository.avgMinMaxDevApplicationsPerTrip();
		return res;
	}

	public Collection<Double> avgMinMaxDevPriceOfTheTrips() {
		Collection<Double> res = null;
		res = this.tripRepository.avgMinMaxDevPriceOfTheTrips();
		return res;
	}

	public String ratioOfTripsCancelledVsTotalTripsOrganized() {
		String res = null;
		res = this.tripRepository.ratioOfTripsCancelledVsTotalTripsOrganized();
		return res;
	}

	public Collection<String> listingTrips10PercentMoraApplicantionsThanAvg() {
		Collection<String> res = null;
		res = this.tripRepository.listingTrips10PercentMoraApplicantionsThanAvg();
		return res;
	}

	public Collection<Double> minMaxAvgDevNotesPerTrip() {
		Collection<Double> res = null;
		res = this.tripRepository.minMaxAvgDevNotesPerTrip();
		return res;
	}

	public Collection<Double> minMaxAvgDevAuditRecordPerTrip() {
		Collection<Double> res = null;
		res = this.tripRepository.minMaxAvgDevAuditRecordPerTrip();
		return res;
	}

	public Collection<Auditor> auditorPerTrip(final int tripId) {
		Collection<Auditor> res = null;
		res = this.tripRepository.auditorPerTrip(tripId);
		return res;
	}

	public String ratioOfTripsWithAnyAuditRecord() {
		String res = null;
		res = this.tripRepository.ratioOfTripsWithAnyAuditRecord();
		return res;
	}

	public Trip generateTicker(final Trip trip) {

		String ticker = new String();
		Trip newTrip = null;
		boolean newTicker = false;

		while (!newTicker) {
			final Integer year = Calendar.getInstance().get(Calendar.YEAR) % 100;
			final Integer month = Calendar.getInstance().get(Calendar.MONTH);
			final Integer day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

			ticker = year.toString() + month.toString() + day.toString() + "-";

			final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			final int N = alphabet.length();

			final Random r = new Random();

			for (int i = 0; i < 4; i++)
				ticker += alphabet.charAt(r.nextInt(N));

			newTrip = this.tripRepository.findByTicker(ticker);

			if (newTrip == null)
				newTicker = true;

			trip.setTicker(ticker);
		}
		return trip;
	}

	public Money getPrice(final Trip trip) {
		final Money price = new Money();
		Double amount = 0.0;

		for (final Stage s : trip.getStages())
			amount += s.getPrice().getAmount();
		price.setAmount(amount);

		return price;
	}

}
