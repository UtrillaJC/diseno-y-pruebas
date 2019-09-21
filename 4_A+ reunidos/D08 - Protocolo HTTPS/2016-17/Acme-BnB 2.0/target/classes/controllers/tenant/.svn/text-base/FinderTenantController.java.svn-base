package controllers.tenant;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import services.PropertyService;
import controllers.AbstractController;
import domain.Finder;
import domain.Property;

@Controller
@RequestMapping("/finder/tenant")
public class FinderTenantController extends AbstractController{
	
	// Services -------------------------------------------------------------------
	
	@Autowired
	private FinderService finderService;
	
	@Autowired
	private PropertyService propertyService;
	
	// Constructors ---------------------------------------------------------------
	
	public FinderTenantController(){
		super();
	}
	
	
	// Listing methods -----------------------------------------------------------
	
	@RequestMapping(value="/display", method = RequestMethod.GET)
	public ModelAndView display(){

		ModelAndView result;
		Finder finder;
		Collection<Property> properties;
	
		finder = finderService.findByPrincipal();
		properties = propertyService.findAllByFinder(finder);
		
		result = new ModelAndView("finder/tenant/display");
		result.addObject("finderDisplay", finder);
		result.addObject("properties", properties);

		return result;
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public ModelAndView searchFinder(){

		ModelAndView result;
		Finder finder;
	
		finder = finderService.findByPrincipal();
		propertyService.search(finder);
		
		result = new ModelAndView("finder/tenant/display");
		result.addObject("finderDisplay", finder);
		result.addObject("properties", finder.getProperties());

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Finder finder;
		
		finder = finderService.findByPrincipal();
		Assert.notNull(finder);

		result = createEditModelAndView(finder);

		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@ModelAttribute @Valid Finder finder, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(finder);
		}else{
			try{
				finderService.saveEdit(finder);
				result = new ModelAndView("redirect:display.do");
			}catch(Throwable oops){
				result = createEditModelAndView(finder, "finder.commit.error");
			}
		}
		return result;
	}
	
	// Ancillary Methods============================================================		
	
		protected ModelAndView createEditModelAndView(Finder finder) {
			ModelAndView result;
			
			result = createEditModelAndView(finder, null);
				    
			return result;
			
		}		
		
		protected ModelAndView createEditModelAndView(Finder finder, String message) {
			ModelAndView result;       

			result = new ModelAndView("finder/tenant/edit");
			result.addObject("finder", finder);
			result.addObject("message", message);
				    
			return result;
		}	

}
