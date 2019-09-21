
package controllers.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import controllers.AbstractController;
import domain.Application;

@Controller
@RequestMapping("/application/customer")
public class ApplicationCustomerController extends AbstractController {

	// Services ============================================================================

	@Autowired
	private ApplicationService	applicationService;

	// Constructors ========================================================================

	public ApplicationCustomerController() {
		super();
	}

	//List My Applications--------------------------------------------------------------------

	@RequestMapping(value = "/listMyApplications", method = RequestMethod.GET)
	public ModelAndView listMyApplications(@RequestParam int serviceId) {
		ModelAndView result;
		Collection<Application> applications;
		
		applications = this.applicationService.findAllMyApplicationsByService(serviceId);

		result = new ModelAndView("application/listApplicationsJoin");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/customer/listMyApplications.do");
										

		return result;

	}
	
	@RequestMapping(value = "/listApplicationNotOwn", method = RequestMethod.GET)
	public ModelAndView listApplicationNotOwn(@RequestParam int serviceId) {
		ModelAndView result;
		Collection<Application> applications;

		applications = this.applicationService.findAllApplicationsByServiceCustomer(serviceId);

		result = new ModelAndView("application/listMyApplications");
		result.addObject("applications", applications);

		result.addObject("requestURI", "applications/customer/listApplicationsJoin.do");

		return result;

	}
	
	//Accept My Applications--------------------------------------------------------------------

	@RequestMapping(value= "/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam int applicationId){
		ModelAndView result;
		
		Application application;
		domain.Service service;
				
		application = applicationService.findOne(applicationId);
		service = application.getService();
		try{
			applicationService.acceptApplication(applicationId);
			result = new ModelAndView("redirect:/application/customer/listApplicationNotOwn.do?serviceId=" + service.getId());
		}catch(Throwable oops){
			result = createEditModelAndView(application, "application.commit.error");
		}
		
		return result;
	}

	
	//Deny My Applications--------------------------------------------------------------------

	@RequestMapping(value= "/deny", method = RequestMethod.GET)
	public ModelAndView deny(@RequestParam int applicationId){
		ModelAndView result;
		
		Application application;
		domain.Service service;
				
		application = applicationService.findOne(applicationId);
		service = application.getService();
		try{
			applicationService.denyApplication(applicationId);
			result = new ModelAndView("redirect:/application/customer/listApplicationNotOwn.do?serviceId=" + service.getId());
		}catch(Throwable oops){
			result = createEditModelAndView(application, "application.commit.error");
		}
		
		return result;
		
	}
	
	
	//Create--------------------------------------------------------------------

	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int serviceId){
		
		ModelAndView result;
				
		applicationService.create(serviceId);	
				
		result = new ModelAndView("redirect:/application/customer/listMyApplications.do?serviceId=" + serviceId);

		
		return result;
		
	}
	

	// Ancillary Methods============================================================		

	protected ModelAndView createEditModelAndView(final Application application) {
		assert application != null;

		ModelAndView result;

		result = this.createEditModelAndView(application, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Application application, final String message) {
		assert application != null;
		ModelAndView result;

		result = new ModelAndView("application/edit");
		result.addObject("application", application);
		result.addObject("message", message);

		return result;
	}

}
