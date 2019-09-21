
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;
import domain.CreditCard;
import domain.Explorer;
import domain.Manager;
import domain.Status;
import domain.Trip;

@Service
@Transactional
public class ApplicationService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private ApplicationRepository	applicationRepository;

	// Supported Services

	@Autowired
	private ExplorerService			explorerService;

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private MessageService			messageService;


	// Simple CRUD methods ----------------------------------------------------

	public Application create(final Explorer explorer) {
		Application application;

		application = new Application();
		application.setMoment(new Date());
		application.setStatus(Status.PENDING);
		application.setApplicant(explorer);
		application.setComments(new ArrayList<String>());

		return application;
	}

	public Application findOne(final int applicationId) {
		return this.applicationRepository.findOne(applicationId);
	}

	public Application save(final Application application) {
		Assert.notNull(application);
		Assert.notNull(application.getTrip());
		application.setMoment(new Date(System.currentTimeMillis()));
		return this.applicationRepository.save(application);
	}

	public Collection<Application> findAll() {
		return this.applicationRepository.findAll();
	}

	public void delete(final Application application) {
		this.applicationRepository.delete(application);
	}

	public void deleteApplications(final Trip trip) {
		final Collection<Application> applications = trip.getApplications();
		this.applicationRepository.delete(applications);
	}

	// other business methods -------------------------------------------------

	public void deleteByExplorer(final Explorer explorer) {

		Assert.notNull(explorer);

		final Collection<Application> applications = explorer.getApplications();
		this.applicationRepository.delete(applications);
		explorer.getApplications().removeAll(applications);
	}
	public Collection<Application> findByExplorer(final Explorer explorer) {

		Assert.notNull(explorer);

		Collection<Application> result = null;
		result = this.applicationRepository.findByExplorer(explorer);
		return result;
	}

	//TODO: Controlar que la fecha de la application sea antes de la fecha del trip
	public Application rejectApplication(final Application application, final String rejectedReason) {
		Assert.notNull(application);
		Assert.notNull(rejectedReason);
		Assert.isTrue(application.getStatus().equals(Status.PENDING));
		this.checkByPrincipal(application);

		application.setStatus(Status.REJECTED);
		application.setRejectReason(rejectedReason);
		this.applicationRepository.save(application);

		this.messageService.notifyChangeInApplicationStatus(application);

		return application;
	}
	public Application dueApplication(final Application application) {
		Assert.notNull(application);
		Assert.isTrue(application.getStatus().equals(Status.PENDING));
		this.checkByPrincipal(application);

		application.setStatus(Status.DUE);
		this.applicationRepository.save(application);

		this.messageService.notifyChangeInApplicationStatus(application);

		return application;
	}

	public Application acceptApplication(final Application application, final CreditCard creditCard) {
		Assert.notNull(application);
		Assert.notNull(creditCard);
		Assert.isTrue(application.getStatus().equals(Status.DUE));
		//Compobar el Explorer
		this.checkByPrincipalExplorer(application);

		application.setCreditCard(creditCard);
		application.setStatus(Status.ACCEPTED);
		this.applicationRepository.save(application);

		this.messageService.notifyChangeInApplicationStatus(application);

		return application;
	}

	public Application cancelApplication(final Application application) {
		Assert.notNull(application);
		Assert.isTrue(application.getStatus().equals(Status.ACCEPTED));

		final Date currentDate = new Date(System.currentTimeMillis() - 1000);
		Assert.isTrue(currentDate.before(application.getTrip().getStartDateTrip()));

		this.checkByPrincipalExplorer(application);

		application.setStatus(Status.CANCELLED);
		this.save(application);

		this.messageService.notifyChangeInApplicationStatus(application);

		return application;
	}

	public Application applyForTrip(final Trip trip, final Application application) {
		Assert.notNull(trip);
		Assert.notNull(application);
		Assert.isTrue(!this.applicationRepository.findApplicantsToTrip(trip).contains(application.getApplicant()));
		Assert.isTrue(application.getMoment().before(trip.getStartDateTrip()));
		this.checkByPrincipalExplorer(application);
		final Explorer explorer = this.explorerService.findByPrincipal();

		application.setTrip(trip);
		application.setApplicant(explorer);
		this.explorerService.save(explorer);

		Application application2 = this.save(application);

		final List<Application> tripApplications = new ArrayList<>(trip.getApplications());
		tripApplications.add(application);
		trip.setApplications(tripApplications);
		//this.tripService.saveExplorer(trip);

		final List<Application> explorerApplications = new ArrayList<>(explorer.getApplications());
		explorerApplications.add(application);
		explorer.setApplications(explorerApplications);
		this.explorerService.save(explorer);
		application2 = this.save(application);
		return application2;
	}

	public Collection<Explorer> findApplicantsToTrip(final Trip trip) {
		return this.applicationRepository.findApplicantsToTrip(trip);
	}

	public Collection<Application> findApplicationsByExplorerId(final int explorerId) {
		return this.applicationRepository.findApplicationByExplorer(explorerId);
	}

	public Collection<Application> findApplicationsByManagerId(final int managerId) {
		return this.applicationRepository.findApplicationByManager(managerId);
	}

	public Double findRatioOfAcceptedApplications() {
		return this.applicationRepository.acceptedApplications();
	}

	public Double findRatioOfCancellededApplications() {
		return this.applicationRepository.cancelledApplications();
	}
	public Double findRatioOfDueApplications() {
		return this.applicationRepository.dueApplications();
	}
	public Double findRatioOfPendingApplications() {
		return this.applicationRepository.pendingApplications();
	}
	private void checkByPrincipal(final Application application) {
		Manager manager;

		manager = this.managerService.findByPrincipal();
		Assert.isTrue(manager.equals(application.getTrip().getManager()));

	}
	private void checkByPrincipalExplorer(final Application application) {
		Explorer explorer;

		explorer = this.explorerService.findByPrincipal();
		Assert.isTrue(explorer.equals(application.getApplicant()));
	}
}
