
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.Lessor;
import domain.Property;
import domain.Request;
import domain.Status;
import domain.Tenant;
import forms.RequestCreditCardForm;
import repositories.RequestRepository;

@Service
@Transactional
public class RequestService {

	//Managed Repository =============================================================================

	@Autowired
	private RequestRepository	requestRepository;

	//Supported Services =============================================================================

	@Autowired
	private TenantService		tenantService;

	@Autowired
	private PropertyService		propertyService;

	@Autowired
	private LessorService		lessorService;


	//Constructor methods ============================================================================

	public RequestService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	public Request findOne(int requestId) {
		Request result;

		result = requestRepository.findOne(requestId);

		return result;
	}

	public Collection<Request> findAll() {
		Collection<Request> result;

		result = requestRepository.findAll();

		return result;
	}

	public Request create(Tenant tenant, Property property) {
		Assert.notNull(tenant);
		Assert.notNull(property);
		Request result;
		Tenant principal;
		CreditCard creditCard;

		principal = tenantService.findByPrincipal();
		Assert.isTrue(principal.equals(tenant));
		creditCard = new CreditCard();
		result = new Request();

		result.setTenant(tenant);
		result.setCreditCard(creditCard);
		result.setStatus(Status.PENDING);

		return result;
	}

	public Request create() {
		Request result;
		Tenant principal;
		CreditCard creditCard;

		principal = tenantService.findByPrincipal();
		creditCard = new CreditCard();
		result = new Request();

		result.setTenant(principal);
		result.setCreditCard(creditCard);
		result.setStatus(Status.PENDING);

		return result;
	}

	@SuppressWarnings("deprecation")
	public Request save(Request request) {
		Assert.notNull(request);
		Tenant tenant = request.getTenant();
		Assert.notNull(tenant);
		Property property = request.getProperty();
		Assert.notNull(property);
		Request result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1000);

		if (request.getId() == 0) {
			Assert.isTrue((request.getCreditCard().getExpirationYear() - 1900) >= moment.getYear());
			if ((request.getCreditCard().getExpirationYear() - 1900) == moment.getYear()) {
				Assert.isTrue((request.getCreditCard().getExpirationMonth() - 1) > moment.getMonth());
			}
		}
		long diff = request.getCheckOutDate().getTime() - request.getCheckInDate().getTime();
		long days = TimeUnit.MILLISECONDS.toDays(diff);
		Assert.isTrue(request.getCheckInDate().before(request.getCheckOutDate()) && days >= 1);

		result = requestRepository.save(request);

		//Actualizar las listas de las relaciones
		Collection<Request> r;

		//Tenant

		r = tenant.getRequests();
		r.add(result);
		tenant.setRequests(r);
		tenantService.update(tenant);

		//Property
		r = property.getRequests();
		r.add(result);
		property.setRequests(r);
		propertyService.save(property);

		return result;
	}

	public Request reconstruct(RequestCreditCardForm requestForm) {
		Request result;
		Tenant principal;
		CreditCard creditCard;

		principal = tenantService.findByPrincipal();
		creditCard = new CreditCard();
		creditCard.setExpirationMonth(requestForm.getExpirationMonth());
		creditCard.setExpirationYear(requestForm.getExpirationYear());
		creditCard.setBrandName(requestForm.getBrandName());
		creditCard.setcvvCode(requestForm.getcvvCode());
		creditCard.setHolderName(requestForm.getHolderName());
		creditCard.setNumber(requestForm.getNumber());

		result = new Request();

		result.setProperty(propertyService.findOne(requestForm.getPropertyId()));
		result.setTenant(principal);
		result.setCreditCard(creditCard);
		result.setCheckInDate(requestForm.getCheckInDate());
		result.setCheckOutDate(requestForm.getCheckOutDate());
		result.setSmoker(requestForm.isSmoker());
		result.setStatus(Status.PENDING);

		Request savedResult = save(result);
		return savedResult;

	}

	public void acceptRequest(int requestId) {
		Request r = findOne(requestId);
		Lessor l = lessorService.findByPrincipal();
		Assert.isTrue(lessorService.checkCreditCard(l));
		Assert.isTrue(r.getProperty().getLessor().equals(l));
		r.setStatus(Status.ACCEPTED);
		save(r);
		lessorService.increaseAmount();

	}

	public void denyRequest(int requestId) {
		Request r = findOne(requestId);
		Lessor l = lessorService.findByPrincipal();
		Assert.isTrue(r.getProperty().getLessor().equals(l));
		r.setStatus(Status.DENIED);
		save(r);

	}

	//Other Business Methods =========================================================================

	public Collection<Request> findByPrincipal() {
		Collection<Request> result;
		Tenant tenant;

		tenant = this.tenantService.findByPrincipal();
		result = tenant.getRequests();
		return result;
	}

	public Collection<Request> findByPropertyId(int propertyId) {
		Collection<Request> result;
		Property property;

		property = this.propertyService.findOne(propertyId);
		this.propertyService.checkPrincipal(property);
		result = property.getRequests();
		return result;
	}

	public Collection<Request> findByPropertyIdAndPrincipal(int propertyId) {
		Collection<Request> result = new ArrayList<Request>();
		Property property;
		Lessor lessor;

		lessor = this.lessorService.findByPrincipal();
		property = this.propertyService.findOne(propertyId);
		for (Property p : lessor.getProperties()) {
			Collection<Request> r = p.getRequests();
			result.addAll(r);
		}

		result.retainAll(property.getRequests());
		return result;
	}

	public RequestCreditCardForm construct(int propertyId) {
		RequestCreditCardForm result = new RequestCreditCardForm();
		result.setPropertyId(propertyId);
		result.setTenantId(tenantService.findByPrincipal().getId());
		return result;
	}

	public Double requestAccepted() {
		Double result;

		result = requestRepository.requestAccepted();

		return result;
	}

	public Double requestDenied() {
		Double result;

		result = requestRepository.requestDenied();

		return result;
	}
	public Double requestAll() {
		Double result;

		result = requestRepository.requestAll();

		return result;
	}
}
