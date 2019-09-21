
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Invoice;
import domain.Property;
import domain.Request;
import domain.Tenant;
import repositories.InvoiceRepository;

@Service
@Transactional
public class InvoiceService {

	//Managed Repository =============================================================================

	@Autowired
	private InvoiceRepository	invoiceRepository;

	//Supported Services =============================================================================

	@Autowired
	private TenantService		tenantService;


	//Constructor methods ============================================================================

	public InvoiceService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	public Invoice findOne(int invoiceId) {
		Invoice result;

		result = invoiceRepository.findOne(invoiceId);

		return result;
	}

	public Collection<Invoice> findAll() {
		Collection<Invoice> result;

		result = invoiceRepository.findAll();

		return result;
	}

	public Invoice create(Request request) {
		Assert.notNull(request);
		Invoice result;
		Tenant principal;
		Date moment;
		Double totalAmountDue;
		Property property;
		String tenantInformation;
		String details;

		principal = tenantService.findByPrincipal();
		property = request.getProperty();
		moment = new Date(System.currentTimeMillis());
		result = new Invoice();
		totalAmountDue = this.generateTotalAmountDue(property, principal);
		tenantInformation = principal.getSurname() + ", " + principal.getName();
		details = request.getProperty().getName() + ", " + request.getProperty().getAddress();

		result.setRequest(request);
		result.setMoment(moment);
		result.setTenantInformation(tenantInformation);
		result.setDetails(details);
		result.setTotalAmountDue(totalAmountDue);
		result.setCreditCard(request.getCreditCard());

		return result;
	}

	public Invoice save(Invoice invoice) {
		Assert.notNull(invoice);
		Invoice result;
		Date moment;
		Tenant principal = this.tenantService.findByPrincipal();
		
		moment = new Date(System.currentTimeMillis() - 1000);

		Assert.isTrue(principal.equals(invoice.getRequest().getTenant()));
		invoice.setMoment(moment);

		result = invoiceRepository.save(invoice);

		return result;
	}

	//Other Business Methods =========================================================================

	public Double generateTotalAmountDue(Property property, Tenant tenant) {
		Double result;
		Double ratePerDay = property.getRatePerDay();
		Collection<Request> rp = property.getRequests();
		Collection<Request> rt = tenant.getRequests();
		rt.retainAll(rp);
		List<Request> requests = (List<Request>) rt;
		Request request = requests.get(0);

		Date checkin = request.getCheckInDate();
		Date checkout = request.getCheckOutDate();

		int diffInDays = (int) ((checkout.getTime() - checkin.getTime()) / (1000 * 60 * 60 * 24));

		result = diffInDays * ratePerDay;

		return result;
	}

	public Collection<Invoice> findAllByTenant(Tenant tenant) {
		Assert.notNull(tenant);
		Collection<Invoice> result;

		result = invoiceRepository.findAllByTenantId(tenant.getId());

		return result;
	}

	public Invoice findOneToShow(int invoiceId) {
		Invoice result;
		Tenant principal;

		principal = tenantService.findByPrincipal();
		result = invoiceRepository.findOne(invoiceId);
		Assert.isTrue(result.getRequest().getTenant().equals(principal));

		return result;
	}

	public void issueInvoice(Request request) {
		Assert.notNull(request);
		Invoice invoice;
		Tenant principal;

		principal = tenantService.findByPrincipal();
		Assert.isTrue(principal.equals(request.getTenant()));
		invoice = create(request);
		Assert.notNull(invoice);

		save(invoice);
	}

	public Double avgInvoiceIssued() {
		Double result;

		result = this.invoiceRepository.avgInvoiceIssued();

		if (result == null) {
			result = 0.0;
		}

		return result;
	}

	public Integer maxInvoiceIssued() {
		Integer result;

		result = this.invoiceRepository.maxInvoiceIssued();

		if (result == null) {
			result = 0;
		}

		return result;
	}

	public Integer minInvoiceIssued() {
		Integer result;

		result = this.invoiceRepository.minInvoiceIssued();

		if (result == null) {
			result = 0;
		}

		return result;
	}

	public Double totalAmount() {
		Double result;

		result = this.invoiceRepository.totalAmount();

		if (result == null) {
			result = 0.0;
		}

		return result;
	}
}
