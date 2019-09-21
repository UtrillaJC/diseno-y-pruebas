
package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Attribute;
import domain.Lessor;
import domain.Property;
import domain.Request;
import domain.Status;
import domain.Tenant;
import services.ActorService;
import services.AttributeService;
import services.InvoiceService;
import services.LessorService;
import services.PropertyService;
import services.TenantService;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashBoardAdministratorController {

	@Autowired
	private LessorService		lessorService;

	@Autowired
	private TenantService		tenantService;

	@Autowired
	private PropertyService		propertyService;

	@Autowired
	private AttributeService	attributeService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private InvoiceService		invoiceService;


	public DashBoardAdministratorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView result = new ModelAndView("dashboard/list");

		//The min the recipes per user.
		Double dashb1 = lessorService.avgRequestAcceptedForLessor();
		Double dashb2 = lessorService.avgRequestDeniedForLessor();
		Double dashb3 = tenantService.avgRequestAcceptedForTenant();
		Double dashb4 = tenantService.avgRequestDeniedForTenant();
		Collection<Lessor> dashb5 = lessorService.lessorMoreRequestApproved();
		Collection<Lessor> dashb6 = lessorService.lessorMoreRequestDenied();
		Collection<Lessor> dashb7 = lessorService.lessorMoreRequestPending();
		Collection<Tenant> dashb8 = tenantService.tenantMoreRequestApproved();
		Collection<Tenant> dashb9 = tenantService.tenantMoreRequestDenied();
		Collection<Tenant> dashb10 = tenantService.tenantMoreRequestPending();
		Collection<Lessor> dashb11 = lessorService.lessorRatioMaxApproved();
		Collection<Lessor> dashb12 = lessorService.lessorRatioMinApproved();
		Collection<Tenant> dashb13 = tenantService.tenantRatioMaxApproved();
		Collection<Tenant> dashb14 = tenantService.tenantRatioMinApproved();
		Double dashb15 = propertyService.avgResultPerFinder();
		Integer dashb16 = propertyService.maxResultPerFinder();
		Integer dashb17 = propertyService.minResultPerFinder();
		Double dashb18 = propertyService.avgAuditPerProperty();
		Integer dashb19 = propertyService.maxAuditPerProperty();
		Integer dashb20 = propertyService.minAuditPerProperty();
		Collection<Attribute> dashb21 = attributeService.AttribueOrderUseId();
		Double dashb22 = actorService.avgSocialIdentityPerActor();
		Integer dashb23 = actorService.maxSocialIdentityPerActor();
		Integer dashb24 = actorService.minSocialIdentityPerActor();
		Double dashb25 = invoiceService.avgInvoiceIssued();
		Integer dashb26 = invoiceService.maxInvoiceIssued();
		Integer dashb27 = invoiceService.minInvoiceIssued();
		Double dashb28 = invoiceService.totalAmount();
		Double dashb29 = propertyService.avgRequestPerProperty();

		result.addObject("dashb1", dashb1);
		result.addObject("dashb2", dashb2);
		result.addObject("dashb3", dashb3);
		result.addObject("dashb4", dashb4);
		result.addObject("dashb5", dashb5);
		result.addObject("dashb6", dashb6);
		result.addObject("dashb7", dashb7);
		result.addObject("dashb8", dashb8);
		result.addObject("dashb9", dashb9);
		result.addObject("dashb10", dashb10);
		result.addObject("dashb11", dashb11);
		result.addObject("dashb12", dashb12);
		result.addObject("dashb13", dashb13);
		result.addObject("dashb14", dashb14);
		result.addObject("dashb15", dashb15);
		result.addObject("dashb16", dashb16);
		result.addObject("dashb17", dashb17);
		result.addObject("dashb18", dashb18);
		result.addObject("dashb19", dashb19);
		result.addObject("dashb20", dashb20);
		result.addObject("dashb21", dashb21);
		result.addObject("dashb22", dashb22);
		result.addObject("dashb23", dashb23);
		result.addObject("dashb24", dashb24);
		result.addObject("dashb25", dashb25);
		result.addObject("dashb26", dashb26);
		result.addObject("dashb27", dashb27);
		result.addObject("dashb28", dashb28);
		result.addObject("dashb29", dashb29);

		result.addObject("requestURI", "dashboard/administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/listByLessor", method = RequestMethod.GET)
	public ModelAndView dashboardByLessor(@RequestParam int lessorId) {
		ModelAndView result = new ModelAndView("dashboard/listByLessor");

		Collection<Property> dashb1 = propertyService.findAllByLessorOrderAuditsSizeId(lessorId);
		Collection<Property> dashb2 = propertyService.findAllByLessorOrderRequestsSizeId(lessorId);
		List<Property> dashb3 = (List<Property>) propertyService.findAllByLessorOrderRequestsAcceptedSizeId(lessorId);
		List<Property> dashb4 = (List<Property>) propertyService.findAllByLessorOrderRequestsDeniedSizeId(lessorId);
		List<Property> dashb5 = (List<Property>) propertyService.findAllByLessorOrderRequestsPendingSizeId(lessorId);
		
		Collections.sort(dashb3, new Comparator<Property>(){
			public int compare(final Property propertyA, Property propertyB){
				Collection<Request> acceptedB = new ArrayList<Request>();
				Collection<Request> acceptedA = new ArrayList<Request>();
				for(Request rB:propertyB.getRequests()){
					if(rB.getStatus()==Status.ACCEPTED){
						acceptedB.add(rB);
					}
				}
				for(Request rA:propertyA.getRequests()){
					if(rA.getStatus()==Status.ACCEPTED){
						acceptedA.add(rA);
					}
				}
				return acceptedB.size() - acceptedA.size();
			}
		});
		
		Collections.sort(dashb4, new Comparator<Property>(){
			public int compare(final Property propertyA, Property propertyB){
				Collection<Request> deniedB = new ArrayList<Request>();
				Collection<Request> deniedA = new ArrayList<Request>();
				for(Request rB:propertyB.getRequests()){
					if(rB.getStatus()==Status.DENIED){
						deniedB.add(rB);
					}
				}
				for(Request rA:propertyA.getRequests()){
					if(rA.getStatus()==Status.DENIED){
						deniedA.add(rA);
					}
				}
				return deniedB.size() - deniedA.size();
			}
		});
		
		Collections.sort(dashb5, new Comparator<Property>(){
			public int compare(final Property propertyA, Property propertyB){
				Collection<Request> pendingB = new ArrayList<Request>();
				Collection<Request> pendingA = new ArrayList<Request>();
				for(Request rB:propertyB.getRequests()){
					if(rB.getStatus()==Status.PENDING){
						pendingB.add(rB);
					}
				}
				for(Request rA:propertyA.getRequests()){
					if(rA.getStatus()==Status.PENDING){
						pendingA.add(rA);
					}
				}
				return pendingB.size() - pendingA.size();
			}
		});

		result.addObject("dashb1", dashb1);
		result.addObject("dashb2", dashb2);
		result.addObject("dashb3", dashb3);
		result.addObject("dashb4", dashb4);
		result.addObject("dashb5", dashb5);

		return result;
	}

	@RequestMapping(value = "/listLessor", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Lessor> lessors;

		lessors = lessorService.findAll();

		result = new ModelAndView("dashboard/listLessor");
		result.addObject("lessors", lessors);

		return result;
	}
}
