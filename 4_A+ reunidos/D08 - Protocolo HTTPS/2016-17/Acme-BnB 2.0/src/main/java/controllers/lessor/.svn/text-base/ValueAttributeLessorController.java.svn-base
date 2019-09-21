package controllers.lessor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AttributeService;
import services.LessorService;
import services.PropertyService;
import services.ValueAttributeService;
import controllers.AbstractController;
import domain.Attribute;
import domain.Lessor;
import domain.Property;
import domain.ValueAttribute;

@Controller
@RequestMapping("/valueAttribute/lessor")
public class ValueAttributeLessorController extends AbstractController{

	// Services -------------------------------------------------------------------
	
	@Autowired
	private ValueAttributeService valueAttributeService;
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private AttributeService attributeService;
	
	@Autowired
	private LessorService lessorService;
	
	// Constructors ---------------------------------------------------------------
	
	public ValueAttributeLessorController(){
		super();
	}
	
	// List =================================================================
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int propertyId){
		ModelAndView result;
		Collection<ValueAttribute> valueAttributes;
		Property property;
		Lessor principal;
		boolean owner;
		
		principal = lessorService.findByPrincipal();
		owner= false;
		property = propertyService.findOne(propertyId);
		
		if(principal.equals(property.getLessor())){
			owner = true;
		}
		
		valueAttributes = valueAttributeService.findAllByProperty(property);
		
		result = new ModelAndView("valueAttribute/list");
		result.addObject("valueAttributes", valueAttributes);
		result.addObject("owner", owner);
		result.addObject("property", property);
		result.addObject("requestURI","valueAttribute/lessor/list.do");
		
		return result;
	}

	
	// Creation ==============================================================
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int propertyId){
		ModelAndView result;
		ValueAttribute valueAttribute;
		Property property;
		
		property = propertyService.findOne(propertyId);
		
		valueAttribute = valueAttributeService.create(property);
		
		result = createEditModelAndView(valueAttribute);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int valueAttributeId) {
		ModelAndView result;
		ValueAttribute valueAttribute;
		
		valueAttribute = valueAttributeService.findOne(valueAttributeId);
		Assert.notNull(valueAttribute);

		result = createEditModelAndView(valueAttribute);

		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@ModelAttribute @Valid ValueAttribute valueAttribute, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(valueAttribute);
		}else{
			try{
				valueAttributeService.save(valueAttribute);
				result = new ModelAndView("redirect:/property/lessor/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(valueAttribute, "valueAttribute.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute ValueAttribute valueAttribute, BindingResult binding){
		ModelAndView result;
		Property property;
		
		property = valueAttribute.getProperty();
		
		try{
			valueAttributeService.delete(valueAttribute);
			result = new ModelAndView("redirect:/valueAttribute/lessor/list.do?propertyId="+property.getId());
		}catch(Throwable oops){
			result = createEditModelAndView(valueAttribute, "valueAttribute.commit.error");
		}
		return result;
	}
	
	// Ancillary Methods============================================================		
	
	protected ModelAndView createEditModelAndView(ValueAttribute valueAttribute) {
		assert valueAttribute != null;
			    
		ModelAndView result;
		
		result = createEditModelAndView(valueAttribute, null);
			    
		return result;
		
	}		
	
	protected ModelAndView createEditModelAndView(ValueAttribute valueAttribute, String message) {
		assert valueAttribute != null;
		ModelAndView result;     
		Collection<Attribute> attributes;
		
		attributes = attributeService.findAll();

		result = new ModelAndView("valueAttribute/edit");
		result.addObject("valueAttribute", valueAttribute);
		result.addObject("attributes", attributes);
		result.addObject("message", message);
			    
		return result;
	}
}
