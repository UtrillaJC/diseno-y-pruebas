
package controllers.customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;
import domain.Customer;
import domain.Service;
import domain.Type;
import forms.ServiceCoordinatesForm;
import services.ActorService;
import services.CustomerService;
import services.ServiceService;

@Controller
@RequestMapping("/service/customer")
public class ServiceCustomerController extends AbstractController {

	// Services ============================================================================

	@Autowired
	private ServiceService	serviceService;

	@Autowired
	private CustomerService	customerService;
	
	@Autowired
	private ActorService actorService;


	// Constructors ========================================================================

	public ServiceCustomerController() {
		super();
	}

	//List All Offers--------------------------------------------------------------------

	@RequestMapping(value = "/listOffers", method = RequestMethod.GET)
	public ModelAndView listOffers() {
		ModelAndView result;
		Collection<domain.Service> services = new ArrayList<domain.Service>() ;
		Actor principal = this.actorService.findByPrincipal();
		boolean ownerService;
		
		ownerService = true;
		
		if(principal instanceof Administrator){
			services = this.serviceService.findAllOffers();
		}else if(principal instanceof Customer){
			services = this.serviceService.findAllOffers();
			Iterator<domain.Service> iter = services.iterator();

			while (iter.hasNext()) {
				domain.Service s = iter.next();
				if(s.getCustomer().getId()!=principal.getId() && s.isBanned()==true){
					iter.remove();
				}
			}
		}

		result = new ModelAndView("service/listOffers");
		result.addObject("services", services);
		result.addObject("ownerService", ownerService);
		result.addObject("requestURI", "service/customer/listOffers.do");

		return result;

	}

	//List All Requests--------------------------------------------------------------------

	@RequestMapping(value = "/listRequests", method = RequestMethod.GET)
	public ModelAndView listRequests() {
		ModelAndView result;
		Collection<domain.Service> services = new ArrayList<domain.Service>();
		Actor principal = this.actorService.findByPrincipal();
		boolean ownerService;
		
		ownerService = true;
		
		if(principal instanceof Administrator){
			services = this.serviceService.findAllRequests();
		}else if(principal instanceof Customer){
			services = this.serviceService.findAllRequests();
			Iterator<domain.Service> iter = services.iterator();

			while (iter.hasNext()) {
				domain.Service s = iter.next();
				if(s.getCustomer().getId()!=principal.getId() && s.isBanned()==true){
					iter.remove();
				}
			}
		}

		result = new ModelAndView("service/listRequests");
		result.addObject("services", services);
		result.addObject("ownerService", ownerService);
		result.addObject("requestURI", "service/customer/listRequests.do");

		return result;

	}

	//List My Offers--------------------------------------------------------------------

	@RequestMapping(value = "/listMyOffers", method = RequestMethod.GET)
	public ModelAndView listMyOffers() {
		ModelAndView result;
		Collection<domain.Service> services;
		Customer customer;
		boolean ownerService;
		boolean applicable = true;

		ownerService = true;

		customer = this.customerService.findByPrincipal();
		services = this.serviceService.findAllOffersByCustomer(customer);

		result = new ModelAndView("service/listMyOffers");
		result.addObject("services", services);
		result.addObject("ownerService", ownerService);
		result.addObject("applicable", applicable);
		result.addObject("requestURI", "service/customer/listMyOffers.do");

		return result;

	}

	//List My Requests--------------------------------------------------------------------

	@RequestMapping(value = "/listMyRequests", method = RequestMethod.GET)
	public ModelAndView listMyRequests() {
		ModelAndView result;
		Collection<domain.Service> services;
		Customer customer;
		boolean ownerService;
		boolean applicable = true;
		
		ownerService = true;


		customer = this.customerService.findByPrincipal();
		services = this.serviceService.findAllRequestsByCustomer(customer);

		result = new ModelAndView("service/listMyRequests");
		result.addObject("services", services);
		result.addObject("ownerService", ownerService);
		result.addObject("applicable", applicable);
		result.addObject("requestURI", "service/customer/listMyRequests.do");

		return result;

	}
	
	//List All Offers By Not Customer--------------------------------------------------------------------

	@RequestMapping(value = "/listRequestsByNotCustomer", method = RequestMethod.GET)
	public ModelAndView listRequestsByNotCustomer() {
		ModelAndView result;
		Collection<domain.Service> services;
		boolean ownerService;
		
		boolean createApplication = true;
		ownerService = false;

		services = this.serviceService.findAllRequestsByNotCustomer();

		result = new ModelAndView("service/listRequests");
		result.addObject("services", services);
		result.addObject("ownerService", ownerService);
		result.addObject("createApplication", createApplication);
		result.addObject("requestURI", "service/customer/listRequestsByNotCustomer.do");

		return result;

	}
	
	@RequestMapping(value = "/listOffersByNotCustomer", method = RequestMethod.GET)
	public ModelAndView listOffersByNotCustomer() {
		ModelAndView result;
		Collection<domain.Service> services;
		boolean ownerService;
		boolean createApplication;
		
		ownerService = false;
		createApplication = true;

		services = this.serviceService.findAllOffersByNotCustomer();

		result = new ModelAndView("service/listOffers");
		result.addObject("services", services);
		result.addObject("createApplication", createApplication);
		result.addObject("ownerService", ownerService);

		result.addObject("requestURI", "service/customer/listRequestsByNotCustomer.do");

		return result;

	}
	
	//List Services Applicables--------------------------------------------------------------------

	@RequestMapping(value = "/listServicesApplicables", method = RequestMethod.GET)
	public ModelAndView listServicesAplicables() {
		ModelAndView result;
		Collection<domain.Service> services;
		Customer customer;

		customer = this.customerService.findByPrincipal();
		services = this.serviceService.findAllServicesToDoApplication(customer);

		result = new ModelAndView("service/customer/listServicesApplicables");
		result.addObject("services", services);
		result.addObject("requestURI", "service/customer/listServicesApplicables.do");

		return result;

	}

	//SearchForm ==============================================================================

	@RequestMapping(value = "/searchForm", method = RequestMethod.GET)
	public ModelAndView listSearch() {
		ModelAndView result;
		Collection<domain.Service> services;
		Boolean firstTime;

		services = this.serviceService.findAll();
		firstTime = true;

		result = new ModelAndView("service/customer/search");
		result.addObject("services", services);
		result.addObject("requestURI", "service/searchForm.do");
		result.addObject("firstTime", firstTime);

		return result;
	}

	//Search ==============================================================================	

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("keyword") String keyword) {
		ModelAndView result;
		Collection<domain.Service> services;
		Customer principal;
		services = new ArrayList<domain.Service>();

		
		principal = customerService.findByPrincipal();
		
		services = this.serviceService.getServiceByKeyWord(keyword);
		Iterator<domain.Service> iter = services.iterator();

		while (iter.hasNext()) {
			domain.Service s = iter.next();
			if(s.getCustomer().getId()!=principal.getId() && s.isBanned()==true){
				iter.remove();
			}
		}


		result = new ModelAndView("service/customer/search");
		result.addObject("services", services);

		return result;
	}
	// ShowCoordinates methods ===================================================================
	
	@RequestMapping(value="/showCoordinates", method = RequestMethod.GET)
	public ModelAndView showCoordinates(@RequestParam int serviceId){
		
		ModelAndView result;
		Service service;
		
		service = serviceService.findOne(serviceId);
		
		
		result = showModelAndView(service);

		result.addObject("service", service);		
		return result;
		
	}

	// Create methods ===================================================================
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		ServiceCoordinatesForm serviceCoordinatesForm;
		
		serviceCoordinatesForm = serviceService.construct();
		
		
		result = createEditModelAndViewForm(serviceCoordinatesForm);
		
		return result;
		
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ServiceCoordinatesForm service, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndViewForm(service);
		else
			try {
				this.serviceService.reconstruct(service);
				result = new ModelAndView("redirect:../../welcome/index.do");

			} catch (Throwable oops) {
				result = this.createEditModelAndViewForm(service, "service.commit.error");
			}

		return result;
	}

	// Ancillary Methods============================================================	

	protected ModelAndView createEditModelAndView(domain.Service service) {
		assert service != null;

		ModelAndView result;

		result = this.createEditModelAndView(service, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(domain.Service service, String message) {
		assert service != null;
		ModelAndView result;
		List<Type> types = Arrays.asList(Type.values());

		result = new ModelAndView("service/customer/create");
		result.addObject("service", service);
		result.addObject("message", message);
		result.addObject("types", types);

		return result;
	}

	protected ModelAndView createEditModelAndViewForm(ServiceCoordinatesForm serviceCoordinatesForm) {
		ModelAndView result = this.createEditModelAndViewForm(serviceCoordinatesForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndViewForm(ServiceCoordinatesForm serviceCoordinatesForm, String message) {
		ModelAndView result;
		List<Type> types = Arrays.asList(Type.values());

		result = new ModelAndView("service/customer/create");
		result.addObject("serviceCoordinatesForm", serviceCoordinatesForm);
		result.addObject("message", message);
		result.addObject("types", types);

		return result;
	}
	protected ModelAndView showModelAndView(domain.Service service) {
		assert service != null;

		ModelAndView result;

		result = this.showModelAndView(service, null);

		return result;

	}

	protected ModelAndView showModelAndView(domain.Service service, String message) {
		assert service != null;
		ModelAndView result;

		result = new ModelAndView("service/customer/showCoordinates");
		result.addObject("service", service);
		result.addObject("message", message);

		return result;
	}
}
