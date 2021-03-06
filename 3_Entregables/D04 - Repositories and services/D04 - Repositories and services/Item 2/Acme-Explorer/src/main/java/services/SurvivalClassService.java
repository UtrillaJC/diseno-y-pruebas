
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurvivalClassRepository;
import domain.Manager;
import domain.SurvivalClass;
import domain.Trip;

@Service
@Transactional
public class SurvivalClassService {

	// Managed Repository -----------------------------------------------
	@Autowired
	private SurvivalClassRepository	survivalClassRepository;

	// Supported Services -------------------------------------------------------
	@Autowired
	private ManagerService			managerService;
	@Autowired
	private TripService				tripService;


	// CRUD Methods -----------------------------------------------------------

	public SurvivalClass create() {
		SurvivalClass result;
		Manager manager = null;
		//GPSCoordinates location = new GPSCoordinates();

		manager = this.managerService.findByPrincipal();
		result = new SurvivalClass();
		result.setManager(manager);
		//result.setLocation(location);

		return result;
	}
	public void delete(final SurvivalClass survivalClass) {
		Assert.notNull(survivalClass);
		this.checkByPrincipalManager(survivalClass);
		this.survivalClassRepository.delete(survivalClass);
	}

	public SurvivalClass save(final SurvivalClass survivalClass) {
		Assert.notNull(survivalClass);
		this.checkByPrincipalManager(survivalClass);
		return this.survivalClassRepository.save(survivalClass);
	}
	public SurvivalClass findOne(final int id) {
		return this.survivalClassRepository.findOne(id);
	}

	public Collection<SurvivalClass> findAll() {
		return this.survivalClassRepository.findAll();
	}

	// Other Business methods ----------------------------------------------

	public void deleteSurvivalClasses(final Trip trip) {
		final Collection<SurvivalClass> survivalClasses = trip.getSurvivalClasses();
		this.survivalClassRepository.delete(survivalClasses);
	}

	public Collection<SurvivalClass> findByManager(final Manager manager) {

		Collection<SurvivalClass> result = null;
		result = this.survivalClassRepository.findByManager(manager.getId());
		return result;
	}

	public SurvivalClass findOneToEdit(final int id) {
		Assert.notNull(id);
		SurvivalClass result;

		result = this.findOne(id);
		this.checkByPrincipalManager(result);
		return result;
	}

	public void checkByPrincipalManager(final SurvivalClass survivalClass) {
		Manager manager;

		manager = this.managerService.findByPrincipal();
		Assert.isTrue(survivalClass.getManager().equals(manager));
	}

	public void assignSurvivalClassToTrip(final int tripId, final int survivalClassId) {
		SurvivalClass survivalClass;
		Trip trip;

		trip = this.tripService.findOne(tripId);
		survivalClass = this.findOne(survivalClassId);
		this.checkByPrincipalManager(survivalClass);
		trip.getSurvivalClasses().add(survivalClass);
		survivalClass.setTrip(trip);
		this.tripService.save(trip);
		this.save(survivalClass);
	}

	public void deleteByManager(final Manager manager) {
		final Collection<SurvivalClass> survivalClasses = this.findByManager(manager);
		this.survivalClassRepository.delete(survivalClasses);
		manager.getSurvivalClasses().removeAll(survivalClasses);
	}

	public void findByTrip(final int tripID) {
		this.survivalClassRepository.findByTrip(tripID);
	}

}
