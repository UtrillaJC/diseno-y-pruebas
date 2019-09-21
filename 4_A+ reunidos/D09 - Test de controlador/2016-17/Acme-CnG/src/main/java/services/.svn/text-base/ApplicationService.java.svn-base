package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;
import domain.Customer;
import domain.Status;

@Service
@Transactional
public class ApplicationService {

	//Managed Repository =============================================================================

	@Autowired
	private ApplicationRepository	applicationRepository;

	//Supported Services =============================================================================

	@Autowired
	private CustomerService		customerService;
	
	@Autowired
	private ServiceService		serviceService;


	//Constructor methods ============================================================================

	public ApplicationService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	public Application findOne(final int applicationId) {
		Application result;

		result = this.applicationRepository.findOne(applicationId);

		return result;
	}

	public Collection<Application> findAll() {
		Collection<Application> result;

		result = this.applicationRepository.findAll();

		return result;
	}
	
	
	public Application create(int serviceId) {
		Application result;
		Customer principal;
		domain.Service service;
		
		service = serviceService.findOne(serviceId);
		principal = this.customerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.notNull(service);
		Assert.isTrue(!principal.equals(service.getCustomer()));
		result = new Application();

		result.setCustomer(principal);
		result.setStatus(Status.PENDING);
		result.setService(service);
		result = save(result);
		
		//Actualiza la lista de applications del servicio
		
		Collection<Application> apps = service.getApplications();
		apps.add(result);
		serviceService.update(service);
		
		//Actualiza la lista de applications del customer
		
		apps = principal.getApplications();
		apps.add(result);
		customerService.update(principal);
		
		return result;
	}

	public Application save(final Application application) {
		Application result;
		Assert.notNull(application);

		result = this.applicationRepository.save(application);

		return result;
	}

	
	public void delete(final Application application) {
		Customer principal;

		principal = this.customerService.findByPrincipal();
		Assert.isTrue(principal.equals(application.getCustomer()));

		this.applicationRepository.delete(application);
	}
	
	
	//Other Business Methods =========================================================================

	
	public Collection<Application> findAllMyApplicationsByService(int  serviceId) {
		Collection<Application> result;
		Customer principal;
		domain.Service service;
		
		service = serviceService.findOne(serviceId);
		principal = this.customerService.findByPrincipal();
		Assert.isTrue(!principal.equals(service.getCustomer()));

		result = this.applicationRepository.findAllMyApplicationsByServiceId(principal.getId(), serviceId);

		return result;
	}
	
	public void acceptApplication(int applicationId) {
		Application a = findOne(applicationId);
		Customer c = customerService.findByPrincipal();
		Assert.isTrue(a.getService().getCustomer().equals(c));
		a.setStatus(Status.ACCEPTED);
		save(a);


	}

	public void denyApplication(int applicationId) {
		Application a = findOne(applicationId);
		Customer c = customerService.findByPrincipal();
		Assert.isTrue(a.getService().getCustomer().equals(c));
		a.setStatus(Status.DENIED);
		save(a);

	}

	public Collection<Application> findAllApplicationsByServiceCustomer(int serviceId) {
		Collection<Application> result;
		Customer principal;

		principal = this.customerService.findByPrincipal();

		result = this.applicationRepository.findAllApplicationsByServiceCustomerId(principal.getId(), serviceId);

		return result;
	}
	
	
	
	
}