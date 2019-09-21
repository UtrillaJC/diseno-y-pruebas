package controllers.tenant;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.InvoiceService;
import services.RequestService;
import services.TenantService;
import controllers.AbstractController;
import domain.CreditCard;
import domain.Invoice;
import domain.Request;
import domain.Tenant;

@Controller
@RequestMapping("/invoice/tenant")
public class InvoiceTenantController extends AbstractController{

	// Services -------------------------------------------------------------------
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private TenantService tenantService;
	
	@Autowired
	private RequestService requestService;
	
	
	// Constructors ---------------------------------------------------------------
	
	public InvoiceTenantController(){
		super();
	}
	
	
	// Listing methods -----------------------------------------------------------
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView list(){

		ModelAndView result;
		Tenant tenant;
		Collection<Invoice> invoices;
	
		tenant = tenantService.findByPrincipal();
		invoices = invoiceService.findAllByTenant(tenant);
		
		result = new ModelAndView("invoice/tenant/list");
		result.addObject("invoices", invoices);

		return result;
	}
	
	@RequestMapping(value="/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int invoiceId){

		ModelAndView result;
		Invoice invoice;
	
		invoice = invoiceService.findOneToShow(invoiceId);
		
		result = new ModelAndView("invoice/tenant/display");
		
		CreditCard c = invoice.getCreditCard();
		String number = c.getNumber();
		String leading = number.substring(0, 4);
		String trailing = number.substring(12, 16);
		c.setNumber(leading + "********" + trailing);
		
		result.addObject("invoice", invoice);

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int requestId) {
		ModelAndView result;
		Request request;

		request = requestService.findOne(requestId);
		try{
			invoiceService.issueInvoice(request);
			result = new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = new ModelAndView("redirect:list.do");
		}

		
		return result;
	}
	
	

}
