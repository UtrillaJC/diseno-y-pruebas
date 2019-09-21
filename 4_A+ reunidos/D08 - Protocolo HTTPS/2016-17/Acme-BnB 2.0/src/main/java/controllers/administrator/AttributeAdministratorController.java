package controllers.administrator;

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
import controllers.AbstractController;
import domain.Attribute;

@Controller
@RequestMapping("/attribute/administrator")
public class AttributeAdministratorController extends AbstractController {

	// Services ============================================================================
	
	@Autowired
	private AttributeService attributeService;


	
	// Constructors ============================================================================

	public AttributeAdministratorController() {
		super();
	}
	
	//Listing --------------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Attribute> attributes;
		
		attributes = attributeService.findAll();

		result = new ModelAndView("attribute/administrator/list");
		result.addObject("attributes", attributes);
		result.addObject("requestURI", "attribute/administrator/list.do");

		return result;
	}
	
	// Create=======================
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Attribute attribute;
		
	
		attribute = attributeService.create();
		
		result = createEditModelAndView(attribute);
		
		return result;
	}
	
	// Edit=======================
		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int attributeId){
		ModelAndView result;
		Attribute attribute;
		
		attribute = attributeService.findOne(attributeId);
		Assert.notNull(attribute);
			
		result = createEditModelAndView(attribute);
			
		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Attribute attribute, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(attribute);
		}else{
			try{
				
				attributeService.save(attribute);
				result = new ModelAndView("redirect:/attribute/administrator/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(attribute, "attribute.commit.error");
			}
		}
		return result;
	}
	
	// Delete=======================
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Attribute attribute, BindingResult bindingResult){
		ModelAndView result;
			try {      
				attributeService.delete(attribute);
				
			    result = new ModelAndView("redirect:/attribute/administrator/list.do");
			} catch (Throwable oops) {
			    result = createEditModelAndView(attribute, "attribute.commit.error");        
			}

			return result;
				
	}
	
	// Ancillary Methods=======================			
		
	protected ModelAndView createEditModelAndView(Attribute attribute) {
		assert attribute != null;
			    
		ModelAndView result;

		result = createEditModelAndView(attribute, null);
			    
		return result;
		
	}
			
	
	protected ModelAndView createEditModelAndView(Attribute attribute, String message) {
		assert attribute != null;
			    
		ModelAndView result;        

		result = new ModelAndView("attribute/edit");
		result.addObject("attribute", attribute);
		result.addObject("message", message);
			    
		return result;
	}	
}
