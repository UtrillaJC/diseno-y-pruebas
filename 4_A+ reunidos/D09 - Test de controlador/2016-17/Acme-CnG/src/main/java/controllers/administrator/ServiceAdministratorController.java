
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Service;
import services.ServiceService;

@Controller
@RequestMapping("/service/administrator")
public class ServiceAdministratorController extends AbstractController {

	// Services ============================================================================

	@Autowired
	private ServiceService	serviceService;


	// Constructors ========================================================================

	public ServiceAdministratorController() {
		super();
	}

	//List My Services--------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listMyServices() {
		ModelAndView result;
		Collection<Service> services;
			
		services = this.serviceService.findAll();

		result = new ModelAndView("service/administrator/list");
		result.addObject("services", services);
		result.addObject("requestURI", "service/administrator/list.do");

		return result;

	}
	
	//Accept My Services--------------------------------------------------------------------

	@RequestMapping(value= "/ban", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam int serviceId){
		ModelAndView result;
		result = new ModelAndView("redirect:/service/administrator/list.do");
			try{
				serviceService.banService(serviceId);
				
			}catch (Exception e) {
				
				result.addObject("message", "service.commit.error");
			}
			
		
		
		return result;
	}

	
	// Ancillary Methods============================================================		

	protected ModelAndView createEditModelAndView(final Service service) {
		assert service != null;

		ModelAndView result;

		result = this.createEditModelAndView(service, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Service service, final String message) {
		assert service != null;
		ModelAndView result;

		result = new ModelAndView("service/edit");
		result.addObject("service", service);
		result.addObject("message", message);

		return result;
	}

}
