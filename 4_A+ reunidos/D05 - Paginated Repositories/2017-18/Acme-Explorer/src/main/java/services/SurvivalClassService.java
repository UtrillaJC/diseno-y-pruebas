
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurvivalClassRepository;
import domain.Explorer;
import domain.Manager;
import domain.SurvivalClass;
import domain.Trip;

@Service
@Transactional
public class SurvivalClassService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SurvivalClassRepository	survivalClassRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ExplorerService			explorerService;

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private TripService				tripService;


	// Constructors -----------------------------------------------------------

	public SurvivalClassService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public SurvivalClass create() {

		SurvivalClass result = null;
		Manager manager = null;

		manager = this.managerService.findByPrincipal();
		result = new SurvivalClass();

		result.setManager(manager);
		result.setExplorers(new ArrayList<Explorer>());

		return result;
	}

	public SurvivalClass save(final SurvivalClass survivalClass) {

		Assert.notNull(survivalClass);

		SurvivalClass result = null;
		Explorer explorer = null;
		Boolean isExplorer = null;
		Boolean isManager = null;

		isManager = this.checkByPrincipalManager(survivalClass);
		isExplorer = this.checkByPrincipalExplorer();

		Assert.isTrue(isManager || isExplorer);

		if (isManager)
			result = this.survivalClassRepository.save(survivalClass);
		else if (isExplorer) {

			explorer = this.explorerService.findByPrincipal();
			Assert.isTrue(this.checkEnrolAnExplorerToSurvivalClass(explorer, survivalClass));

			explorer.getSurvivalClasses().add(survivalClass);
			survivalClass.getExplorers().add(explorer);

			this.explorerService.save(explorer);
			result = this.survivalClassRepository.save(survivalClass);
		}

		return result;
	}
	public void delete(final SurvivalClass survivalClass) {

		Assert.notNull(survivalClass);
		Assert.isTrue(this.checkByPrincipalManager(survivalClass));
		this.survivalClassRepository.delete(survivalClass);
	}

	public SurvivalClass findOne(final int id) {
		return this.survivalClassRepository.findOne(id);
	}

	public Collection<SurvivalClass> findAll() {
		return this.survivalClassRepository.findAll();
	}

	// Other business methods -------------------------------------------------

	public void deleteSurvivalClasses(final Trip trip) {
		final Collection<SurvivalClass> survivalClasses = trip.getSurvivalClasses();
		this.survivalClassRepository.delete(survivalClasses);
	}

	public Collection<SurvivalClass> findByManager(final Manager manager) {

		Collection<SurvivalClass> result = null;
		result = this.survivalClassRepository.findByManager(manager.getId());
		return result;
	}

	public Collection<SurvivalClass> findByExplorer(final Explorer explorer) {

		Collection<SurvivalClass> result = null;
		result = this.survivalClassRepository.findByExplorer(explorer.getId());
		return result;
	}

	public SurvivalClass findOneToEdit(final int id) {

		Assert.notNull(id);

		SurvivalClass result = null;

		result = this.findOne(id);
		this.checkByPrincipalManager(result);

		return result;
	}

	public Boolean checkByPrincipalManager(final SurvivalClass survivalClass) {

		Manager manager = null;
		Boolean result = false;

		manager = this.managerService.findByPrincipal();

		if (manager != null && survivalClass.getManager().equals(manager))
			result = true;

		return result;
	}

	public Boolean checkByPrincipalExplorer() {

		Explorer explorer = null;
		Boolean result = false;

		explorer = this.explorerService.findByPrincipal();

		if (explorer != null && explorer instanceof Explorer)
			result = true;

		return result;
	}

	public void assignSurvivalClassToTrip(final int tripId, final int survivalClassId) {

		SurvivalClass survivalClass = null;
		Trip trip = null;

		trip = this.tripService.findOne(tripId);
		survivalClass = this.findOne(survivalClassId);
		this.checkByPrincipalManager(survivalClass);
		trip.getSurvivalClasses().add(survivalClass);
		survivalClass.setTrip(trip);
		this.tripService.save(trip);
		this.save(survivalClass);
	}

	public void deleteByManager(final Manager manager) {
		Collection<SurvivalClass> survivalClasses = null;

		survivalClasses = this.findByManager(manager);
		this.survivalClassRepository.delete(survivalClasses);
		manager.getSurvivalClasses().removeAll(survivalClasses);
	}

	public void deleteByExplorer(final Explorer explorer) {
		Collection<SurvivalClass> survivalClasses = null;

		survivalClasses = this.findByExplorer(explorer);
		this.survivalClassRepository.delete(survivalClasses);
		explorer.getSurvivalClasses().removeAll(survivalClasses);
	}

	public SurvivalClass enrolAnExplorerToSurvivalClass(final Explorer explorer, final SurvivalClass survivalClass) {
		Assert.notNull(explorer);
		Assert.notNull(survivalClass);
		Assert.isTrue(this.checkEnrolAnExplorerToSurvivalClass(explorer, survivalClass));

		SurvivalClass result = null;

		explorer.getSurvivalClasses().add(survivalClass);
		survivalClass.getExplorers().add(explorer);

		this.explorerService.save(explorer);
		result = this.survivalClassRepository.save(survivalClass);

		return result;

	}

	public SurvivalClass disenrolAnExplorerToSurvivalClass(final Explorer explorer, final SurvivalClass survivalClass) {
		Assert.notNull(explorer);
		Assert.notNull(survivalClass);
		Assert.isTrue(this.checkDisenrolAnExplorerToSurvivalClass(explorer, survivalClass));

		SurvivalClass result = null;

		explorer.getSurvivalClasses().remove(survivalClass);
		survivalClass.getExplorers().remove(explorer);

		this.explorerService.save(explorer);
		result = this.survivalClassRepository.save(survivalClass);

		return result;
	}

	private boolean checkEnrolAnExplorerToSurvivalClass(final Explorer explorer, final SurvivalClass survivalClass) {

		Assert.notNull(explorer);
		Assert.notNull(survivalClass);

		Date moment = null;
		Boolean tripFinished = null;
		Boolean isExplorerAssignedOnSurvivalClass = null;
		Boolean canExplorerEnrolOnSurvivalClass = null;

		moment = new Date(System.currentTimeMillis());
		tripFinished = moment.after(survivalClass.getTrip().getStartDateTrip());
		isExplorerAssignedOnSurvivalClass = this.survivalClassRepository.isEnrolExplorerToSurvivalClass(survivalClass.getId(), explorer.getId());
		canExplorerEnrolOnSurvivalClass = this.survivalClassRepository.checkEnrolExplorerToSurvivalClass(survivalClass.getId(), explorer.getId());

		return !tripFinished && !isExplorerAssignedOnSurvivalClass && canExplorerEnrolOnSurvivalClass;
	}

	private boolean checkDisenrolAnExplorerToSurvivalClass(final Explorer explorer, final SurvivalClass survivalClass) {

		Assert.notNull(explorer);
		Assert.notNull(survivalClass);

		Date moment = null;
		Boolean tripFinished = null;
		Boolean isExplorerAssignedOnSurvivalClass = null;

		moment = new Date(System.currentTimeMillis());
		tripFinished = moment.after(survivalClass.getTrip().getStartDateTrip());
		isExplorerAssignedOnSurvivalClass = this.survivalClassRepository.isEnrolExplorerToSurvivalClass(survivalClass.getId(), explorer.getId());

		return !tripFinished && isExplorerAssignedOnSurvivalClass;

	}

	public SurvivalClass findByTrip(final int tripID) {
		return this.survivalClassRepository.findByTrip(tripID);
	}
}
