package controllers.lessor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

import services.LessorService;
import services.PropertyService;
import controllers.AbstractController;
import domain.Lessor;
import domain.Property;

@Controller
@RequestMapping("/property/lessor")
public class PropertyLessorController extends AbstractController{
	
	// Services ============================================================================
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private LessorService lessorService;

	// Constructors ========================================================================

	public PropertyLessorController() {
		super();
	}
		
	//Listing By Lessor ======================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		List<Property> properties;
		Lessor lessor;
		
		lessor = lessorService.findByPrincipal();
		properties = (List<Property>) propertyService.findAllByLessor(lessor);
		
		Collections.sort(properties, new Comparator<Property>(){
			public int compare(final Property propertyA, Property propertyB){
				return propertyB.getRequests().size() - propertyA.getRequests().size();
			}
		});

		result = new ModelAndView("property/list");
		result.addObject("properties", properties);
		result.addObject("requestURI","property/lessor/list.do");
		
		return result;
	}
	
	// Creation ==============================================================
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Property property;
		
		property = propertyService.create();
		
		result = createEditModelAndView(property);
		
		return result;
	}

	// Edition =================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int propertyId) {
		ModelAndView result;
		Property property;
		
		property = propertyService.findOne(propertyId);
		Assert.notNull(property);

		result = createEditModelAndView(property);

		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@ModelAttribute @Valid Property property, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(property);
		}else{
			try{
				propertyService.save(property);
				result = new ModelAndView("redirect:/property/lessor/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(property, "property.commit.error");
			}
		}
		return result;
	}
	
	//Delete ======================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Property property, BindingResult binding){
		ModelAndView result;
		
		try{
			propertyService.delete(property);
			result = new ModelAndView("redirect:/property/lessor/list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(property, "property.commit.error");
		}
		return result;
	}
	
	// Ancillary Methods============================================================		
	
	protected ModelAndView createEditModelAndView(Property property) {
		assert property != null;
			    
		ModelAndView result;
		
		result = createEditModelAndView(property, null);
			    
		return result;
		
	}		
	
	protected ModelAndView createEditModelAndView(Property property, String message) {
		assert property != null;
		ModelAndView result;       

		result = new ModelAndView("property/edit");
		result.addObject("property", property);
		result.addObject("message", message);
			    
		return result;
	}	
}
