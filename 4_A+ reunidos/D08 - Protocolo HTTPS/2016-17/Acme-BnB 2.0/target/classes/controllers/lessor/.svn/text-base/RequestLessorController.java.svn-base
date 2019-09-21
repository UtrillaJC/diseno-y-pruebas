package controllers.lessor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RequestService;
import controllers.AbstractController;
import domain.CreditCard;
import domain.Property;
import domain.Request;

@Controller
@RequestMapping("/request/lessor")
public class RequestLessorController extends AbstractController{
	
	// Services -------------------------------------------------------------------
	
	@Autowired
	private RequestService requestService;
	
	// Constructors ---------------------------------------------------------------
	
	public RequestLessorController(){
		super();
	}
	
	
	// Listing methods -----------------------------------------------------------

	@RequestMapping(value= "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int propertyId){
		ModelAndView result;
		Collection<Request> requests;

		requests = requestService.findByPropertyIdAndPrincipal(propertyId);
		result = new ModelAndView("request/lessor/list");
		for(Request r:requests){
			CreditCard c = r.getCreditCard();
			String number = c.getNumber();
			String leading = number.substring(0, 4);
			String trailing = number.substring(12, 16);
			c.setNumber(leading + "********" + trailing);
		}
		result.addObject("requests", requests);

		return result;
	}
	
	@RequestMapping(value= "/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam int requestId){
		ModelAndView result;
		
		Request request;
		int id;
		
		request = requestService.findOne(requestId);
		id = request.getProperty().getId();
		
		try{
			requestService.acceptRequest(requestId);
			result = new ModelAndView("redirect:list.do?propertyId="+id);
		}catch(Throwable oops){
			result = createEditModelAndView(request, "request.commit.error");
		}
		
		return result;
	}

	@RequestMapping(value= "/deny", method = RequestMethod.GET)
	public ModelAndView deny(@RequestParam int requestId){
		requestService.denyRequest(requestId);
		int id = requestService.findOne(requestId).getProperty().getId();
		ModelAndView result = new ModelAndView("redirect:list.do?propertyId="+id);
		
		return result;
		
	}
	
	protected ModelAndView createEditModelAndView(Request request, String message) {
		assert request != null;
		ModelAndView result;       
		Property property;
		Collection<Request> requests;
		
		property = request.getProperty();
		requests = requestService.findByPropertyIdAndPrincipal(property.getId());
		
		result = new ModelAndView("request/lessor/list");
		result.addObject("requests", requests);
		result.addObject("message", message);
			    
		return result;
	}	

}
