package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PropertyService;
import services.ValueAttributeService;
import domain.Property;
import domain.ValueAttribute;


@Controller
@RequestMapping("/valueAttribute")
public class ValueAttributeController extends AbstractController{

	
	// Services -------------------------------------------------------------------
	
	@Autowired
	private ValueAttributeService valueAttributeService;
	
	@Autowired
	private PropertyService propertyService;
	
	// Constructors ---------------------------------------------------------------
	
	public ValueAttributeController(){
		super();
	}
	
	// List =================================================================
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int propertyId){
		ModelAndView result;
		Collection<ValueAttribute> valueAttributes;
		Property property;
		
		property = propertyService.findOne(propertyId);
		
		
		valueAttributes = valueAttributeService.findAllByProperty(property);
		
		result = new ModelAndView("valueAttribute/list");
		result.addObject("valueAttributes", valueAttributes);
		result.addObject("property", property);
		result.addObject("requestURI","valueAttribute/list.do");
		
		return result;
	}
}
