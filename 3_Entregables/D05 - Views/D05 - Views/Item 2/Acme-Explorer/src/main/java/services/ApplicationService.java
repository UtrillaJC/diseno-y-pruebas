
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

	// Supporting services ----------------------------------------------------

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
		application.setMoment(new Date(System.currentTimeMillis() - 1000));
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
		application.setMoment(new Date(System.currentTimeMillis() - 1000));
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

	// Other business methods -------------------------------------------------

	public void deleteByExplorer(final Explorer explorer) {

		// Comprobamos que los parámetros de entrada son correctos

		Assert.notNull(explorer);

		// Realizamos las modificaciones correspondientes

		Collection<Application> applications = null;

		applications = explorer.getApplications();

		this.applicationRepository.delete(applications);
		explorer.getApplications().removeAll(applications);
	}

	public Collection<Application> findByExplorer(final Explorer explorer) {

		// Comprobamos que los parámetros de entrada son correctos

		Assert.notNull(explorer);

		// Realizamos las modificaciones correspondientes

		Collection<Application> result = null;

		result = this.applicationRepository.findByExplorer(explorer);
		return result;
	}

	public Application dueApplication(final Application application) {

		// Comprobamos que los parámetros de entrada son correctos

		Assert.notNull(application);
		Assert.isTrue(application.getStatus().equals(Status.PENDING));
		this.checkByPrincipal(application);	// Comprobamos que el Manager del Trip contenido en Application 
											// coindice con el Manager logueado 

		// Realizamos las modificaciones correspondientes 
		Application saved = null;

		application.setStatus(Status.DUE);
		saved = this.applicationRepository.save(application);
		this.messageService.notifyChangeInApplicationStatus(saved);	// Creamos un mensaje para notificar que
																	// hay un cambio en application(solicitud)

		return saved;
	}

	public Application rejectApplication(final Application application, final String rejectReason) {

		// Comprobamos que los parámetros de entrada son correctos

		Assert.notNull(application);
		Assert.notNull(rejectReason);
		Assert.isTrue(!rejectReason.isEmpty());
		Assert.isTrue(application.getStatus().equals(Status.PENDING));

		this.checkByPrincipal(application);	// Comprobamos que el Manager del Trip contenido en Application 
											// coindice con el Manager logueado

		// Realizamos las modificaciones correspondientes
		Application saved = null;

		application.setStatus(Status.REJECTED);
		application.setRejectReason(rejectReason);
		saved = this.applicationRepository.save(application);

		this.messageService.notifyChangeInApplicationStatus(saved); // Creamos un mensaje para notificar que
																	// hay un cambio en application(solicitud)

		return application;
	}

	public Application acceptApplication(final Application application, final CreditCard creditCard) {

		// Comprobando que los parámetros de entrada son correctos

		this.checkByPrincipalExplorer(application);	// Comprobamos que el explorer logueado coincide con el
													// applicant(explorer) de Application
		Assert.notNull(application);
		Assert.isTrue(application.getStatus().equals(Status.DUE));
		Assert.notNull(creditCard);

		// Comprobamos los atributos de la creditCard

		Assert.notNull(creditCard.getHolder());
		Assert.notNull(creditCard.getBrand());
		Assert.notNull(creditCard.getExpirationMonth());
		Assert.notNull(creditCard.getExpirationYear());
		Assert.notNull(creditCard.getCvv());

		// Realizamos las modificaciones correspondientes
		Application saved = null;

		application.setCreditCard(creditCard);
		application.setStatus(Status.ACCEPTED);
		saved = this.applicationRepository.save(application);
		this.messageService.notifyChangeInApplicationStatus(saved);	// Creamos un mensaje para notificar que
																	// hay un cambio en application(solicitud)
		return saved;
	}

	public Application cancelApplication(final Application application) {

		// Comprobando que los parámetros de entrada son correctos

		Assert.notNull(application);
		Assert.isTrue(application.getStatus().equals(Status.ACCEPTED));

		final Date currentDate = new Date(System.currentTimeMillis() - 10000);

		// Comprobamos que currentDate sea anterior a la StartDateTrip

		Assert.isTrue(currentDate.before(application.getTrip().getStartDateTrip()));
		this.checkByPrincipalExplorer(application);	// Comprobamos que el explorer logueado coincide 
													// con el applicant(explorer) de Application

		// Realizamos las modificaciones correspondientes

		Application saved = null;

		application.setStatus(Status.CANCELLED);
		saved = this.save(application);
		this.messageService.notifyChangeInApplicationStatus(saved);	// Creamos un mensaje para notificar que
																	// hay un cambio en application(solicitud)
		return saved;
	}

	public Application applyForTrip(final Trip trip, final Application application) {

		// Comprobando que los parámetros de entrada son correctos

		Assert.notNull(trip);
		Assert.notNull(application);
		Assert.isTrue(!this.applicationRepository.findApplicantsToTrip(trip).contains(application.getApplicant()));
		Assert.isTrue(application.getMoment().before(trip.getStartDateTrip()));
		this.checkByPrincipalExplorer(application);

		// Realizamos las modificaciones correspondientes

		Explorer explorer = null;
		Application saved = null;
		List<Application> tripApplications = null;
		List<Application> explorerApplications = null;

		explorer = this.explorerService.findByPrincipal();

		application.setTrip(trip);
		application.setApplicant(explorer);
		this.explorerService.save(explorer);

		saved = this.save(application);

		tripApplications = new ArrayList<Application>();
		tripApplications.addAll(trip.getApplications());
		tripApplications.add(application);
		trip.setApplications(tripApplications);
		//this.tripService.saveExplorer(trip);

		explorerApplications = new ArrayList<Application>();
		explorerApplications.addAll(explorer.getApplications());
		explorerApplications.add(application);
		explorer.setApplications(explorerApplications);

		this.explorerService.save(explorer);						// Persistimos el explorer...
		saved = this.save(application);								// ...y luego application

		return saved;
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

	public Collection<Application> findApplicationsByStatus(final Status status) {
		return this.applicationRepository.findApplicationsByStatus(status);
	}

	private void checkByPrincipal(final Application application) {

		Manager manager = null;

		manager = this.managerService.findByPrincipal();
		Assert.isTrue(manager.equals(application.getTrip().getManager()));

	}

	private void checkByPrincipalExplorer(final Application application) {

		Explorer explorer = null;

		explorer = this.explorerService.findByPrincipal();
		Assert.isTrue(explorer.equals(application.getApplicant()));

	}

}
