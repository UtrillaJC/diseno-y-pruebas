package controllers.tenant;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PropertyService;
import services.RequestService;
import controllers.AbstractController;
import domain.CreditCard;
import domain.Property;
import domain.Request;
import forms.RequestCreditCardForm;

@Controller
@RequestMapping("/request/tenant")
public class RequestTenantController extends AbstractController{
	
	// Services -------------------------------------------------------------------
	
				@Autowired
				private RequestService requestService;
				
				@Autowired
				private PropertyService propertyService;
				
				// Constructors ---------------------------------------------------------------
				
				public RequestTenantController(){
					super();
				}
				
				
				// Listing methods -----------------------------------------------------------

				@RequestMapping(value= "/listOwn", method = RequestMethod.GET)
				public ModelAndView listOwn(){
					ModelAndView result;
					Collection<Request> requests;
					
					requests = requestService.findByPrincipal();
					result = new ModelAndView("request/tenant/listOwn");
					for(Request r:requests){
						CreditCard c = r.getCreditCard();
						String number = c.getNumber();
						String leading = number.substring(0, 4);
						String trailing = number.substring(12, 16);
						c.setNumber(leading + "********" + trailing);
					}
					result.addObject("requestsOwn", requests);
					
					return result;
				}
				
//				@RequestMapping(value="/create", method = RequestMethod.GET)
//				public ModelAndView create(){
//					
//					ModelAndView result;
//					Request request;
//					
//					request = requestService.create();
//					result = createEditModelAndView(request);
//					
//					return result;
//					
//				}
				
				@RequestMapping(value="/create", method = RequestMethod.GET)
				public ModelAndView create(int propertyId){
					
					ModelAndView result;
					RequestCreditCardForm requestCreditCardForm;
					
					requestCreditCardForm = requestService.construct(propertyId);
					
					
					result = createEditModelAndViewForm(requestCreditCardForm);
					
					return result;
					
				}

				
				


				@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
				public ModelAndView save(@Valid RequestCreditCardForm request, BindingResult binding){
					ModelAndView result;
					
					
					if(binding.hasErrors()){
						result = createEditModelAndViewForm(request);
						}else{
							try{
								requestService.reconstruct(request);
								result = new ModelAndView("redirect:listOwn.do");
									
							}catch(Throwable oops){
								result = createEditModelAndViewForm(request, "request.commit.error");
							}
						}
					
					return result;
				}
				
				
				// Ancillary methods ---------------------------------------------------------
				
				protected ModelAndView createEditModelAndView(Request request) {
					ModelAndView result;

					result = createEditModelAndView(request, null);
					
					return result;
				}
				
				protected ModelAndView createEditModelAndView(Request request, String message){
					ModelAndView result;
					Collection<Property> properties;
				
					properties = this.propertyService.findAll();
					result = new ModelAndView("request/tenant/create");
					result.addObject("request", request);
					result.addObject("message", message);
					result.addObject("properties", properties);
					
					return result;
				}
				
				private ModelAndView createEditModelAndViewForm(RequestCreditCardForm requestCreditCardForm) {
					ModelAndView result = createEditModelAndViewForm(requestCreditCardForm, null);
					return result;
				}
				
				protected ModelAndView createEditModelAndViewForm(RequestCreditCardForm requestCreditCardForm, String message){
					ModelAndView result;
					Collection<Property> properties;
				
					properties = this.propertyService.findAll();
					result = new ModelAndView("request/tenant/create");
					result.addObject("requestCreditCardForm", requestCreditCardForm);
					result.addObject("message", message);
					result.addObject("properties", properties);
					
					return result;
				}
}
