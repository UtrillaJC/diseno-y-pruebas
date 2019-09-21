
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Coordinates;
import domain.Customer;
import forms.ServiceCoordinatesForm;
import repositories.ServiceRepository;

@Service
@Transactional
public class ServiceService {

	//Managed Repository =============================================================================

	@Autowired
	private ServiceRepository	serviceRepository;

	//Supported Services =============================================================================

	@Autowired
	private CustomerService	customerService;
	@Autowired
	private AdministratorService administratorService;


	//Constructor methods ============================================================================

	public ServiceService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	public domain.Service findOne(int serviceId) {
		domain.Service result;

		result = this.serviceRepository.findOne(serviceId);

		return result;
	}

	public Collection<domain.Service> findAll() {
		Collection<domain.Service> result;

		result = this.serviceRepository.findAll();

		return result;
	}

	public domain.Service create() {
		domain.Service result;
		Customer principal;

		principal = this.customerService.findByPrincipal();
		Assert.notNull(principal);

		result = new domain.Service();

		result.setCustomer(principal);
		result.setBanned(false);

		return result;
	}

	public domain.Service save(domain.Service service) {
		domain.Service result;
		Assert.notNull(service);
		Date today = new Date(System.currentTimeMillis()-10);	
		
		Assert.isTrue(today.before(service.getMoment()));
		if(service.getOriginCoordinates().getLatitude() == null && service.getOriginCoordinates().getLongitude() != null){
			result = new domain.Service();
			Assert.isTrue(service.getOriginCoordinates().getLatitude() != null && service.getOriginCoordinates().getLongitude() == null);
		}else if(service.getOriginCoordinates().getLatitude() != null && service.getOriginCoordinates().getLongitude() == null){
			result = new domain.Service();
			Assert.isTrue(service.getOriginCoordinates().getLatitude() == null && service.getOriginCoordinates().getLongitude() != null);

		}else if(service.getDestinationCoordinates().getLatitude() == null && service.getDestinationCoordinates().getLongitude() != null){
			result = new domain.Service();
			Assert.isTrue(service.getDestinationCoordinates().getLatitude() != null && service.getDestinationCoordinates().getLongitude() == null);
		}else if(service.getDestinationCoordinates().getLatitude() != null && service.getDestinationCoordinates().getLongitude() == null){
			result = new domain.Service();
			Assert.isTrue(service.getDestinationCoordinates().getLatitude() == null && service.getDestinationCoordinates().getLongitude() != null);

		}else{
			result = this.serviceRepository.saveAndFlush(service);

		}
		return result;
	}

	public void delete(domain.Service service) {
		Customer principal;

		principal = this.customerService.findByPrincipal();
		Assert.isTrue(principal.equals(service.getCustomer()));

		this.serviceRepository.delete(service);
	}

	//Other Business Methods =========================================================================
	
	public Collection<domain.Service> findAllServicesToDoApplication(Customer customer) {
				
		Assert.notNull(customer);
		Collection<domain.Service> result;
		Customer principal;

		principal = this.customerService.findByPrincipal();
		Assert.isTrue(customer.equals(principal));
		
		result = findAll();
		result.removeAll(findServiceWithMyApplication(customer));
		result.removeAll(findServicesByCustomer(customer));

		return result;
	}
	
	

	private Collection<?> findServicesByCustomer(Customer principal) {
		
		return serviceRepository.findServicesByCustomer(principal.getId());
	}

	private Collection<domain.Service> findServiceWithMyApplication(Customer principal) {
		
		return serviceRepository.findServiceWithMyApplication(principal.getId());
	}

	public Collection<domain.Service> findAllOffers() {
		Collection<domain.Service> result;

		result = this.serviceRepository.findAllOffers();

		return result;
	}

	public Collection<domain.Service> findAllRequests() {
		Collection<domain.Service> result;

		result = this.serviceRepository.findAllRequests();

		return result;
	}

	public Collection<domain.Service> findAllOffersByCustomer(Customer customer) {
		Assert.notNull(customer);
		Collection<domain.Service> result;
		Customer principal;

		principal = this.customerService.findByPrincipal();
		Assert.isTrue(customer.equals(principal));

		result = this.serviceRepository.findAllOffersByCustomerId(customer.getId());

		return result;
	}
	
	public Collection<domain.Service> findAllRequestsByCustomer(Customer customer) {
		Assert.notNull(customer);
		Collection<domain.Service> result;
		Customer principal;

		principal = this.customerService.findByPrincipal();
		Assert.isTrue(customer.equals(principal));

		result = this.serviceRepository.findAllRequestsByCustomerId(customer.getId());

		return result;
	}


	public Collection<domain.Service> findAllRequestsByNotCustomer() {
		Collection<domain.Service> result;
		Customer principal;

		principal = this.customerService.findByPrincipal();

		result = this.serviceRepository.findAllRequestsByNotCustomerId(principal.getId());

		return result;
	}
	
	public Collection<domain.Service> findAllOffersByNotCustomer() {
		Collection<domain.Service> result;
		Customer principal;

		principal = this.customerService.findByPrincipal();

		result = this.serviceRepository.findAllOffersByNotCustomerId(principal.getId());

		return result;
	}

	public Collection<domain.Service> getServiceByKeyWord(String keyWord) {
		Assert.notNull(keyWord);
		Collection<domain.Service> result;
		Customer principal;

		principal = this.customerService.findByPrincipal();
		Assert.isInstanceOf(Customer.class, principal);
		result = this.serviceRepository.searchServiceByWords(keyWord);

		return result;
	}

	public Double ratioOffers() {
		administratorService.checkPrincipal();
		Double result;

		result = this.serviceRepository.ratioOffers();
		if (result == null)

			result = 0.0;

		return result;
	}

	public Double ratioRequests() {
		administratorService.checkPrincipal();
		Double result;

		result = this.serviceRepository.ratioRequests();
		if (result == null)
			result = 0.0;

		return result;
	}

	public Double avgServicesPerCustomer() {
		administratorService.checkPrincipal();
		Double result;

		result = this.serviceRepository.avgServicesPerCustomer();
		if (result == null)
			result = 0.0;

		return result;
	}

	public Double avgApplicationsPerOffer() {
		administratorService.checkPrincipal();
		Double result;

		result = this.serviceRepository.avgApplicationsPerOffer();
		if (result == null)
			result = 0.0;

		return result;
	}
	public Double avgApplicationsPerRequest() {
		administratorService.checkPrincipal();
		Double result;

		result = this.serviceRepository.avgApplicationsPerRequest();
		if (result == null)
			result = 0.0;

		return result;
	}

	public domain.Service reconstruct(ServiceCoordinatesForm serviceForm) {
		domain.Service result;
		Customer principal;
		Coordinates originCoordinates;
		Coordinates destinationCoordinates;


		principal = this.customerService.findByPrincipal();
		originCoordinates = new Coordinates();
		destinationCoordinates = new Coordinates();

		originCoordinates.setLatitude(serviceForm.getOriginLatitude());
		originCoordinates.setLongitude(serviceForm.getOriginLongitude());
		destinationCoordinates.setLatitude(serviceForm.getDestinationLatitude());
		destinationCoordinates.setLongitude(serviceForm.getDestinationLongitude());

		result = new domain.Service();

		result.setCustomer(principal);
		result.setOriginCoordinates(originCoordinates);
		result.setDestinationCoordinates(destinationCoordinates);
		result.setTitle(serviceForm.getTitle());
		result.setDescription(serviceForm.getDescription());
		result.setMoment(serviceForm.getMoment());
		result.setOriginPlace(serviceForm.getOriginPlace());
		result.setDestinationPlace(serviceForm.getDestinationPlace());
		result.setType(serviceForm.getType());
		result.setBanned(false);

		domain.Service savedResult = this.save(result);
		return savedResult;

	}
	
	public ServiceCoordinatesForm construct() {
		ServiceCoordinatesForm result = new ServiceCoordinatesForm();
		result.setCustomerId(this.customerService.findByPrincipal().getId());
		return result;
	}

	public domain.Service banService(int serviceId) {
		Assert.notNull(administratorService.findByPrincipal());
		
		domain.Service service = findOne(serviceId);
		service.setBanned(true);
		return update(service);
		
	}

	public domain.Service update(domain.Service service) {
		
		return serviceRepository.save(service);
	}


}
