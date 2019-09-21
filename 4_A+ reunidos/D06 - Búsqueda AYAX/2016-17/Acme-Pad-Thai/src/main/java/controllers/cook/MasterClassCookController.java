package controllers.cook;

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

import services.CookService;
import services.MasterClassService;
import controllers.AbstractController;
import domain.Cook;
import domain.MasterClass;


@Controller
@RequestMapping("/masterClass/cook")
public class MasterClassCookController extends AbstractController  {
	
	// Services ============================================================================

	@Autowired
	private MasterClassService masterClassService;
	
	@Autowired
	private CookService cookService;
	
	// Constructors ========================================================================

	public MasterClassCookController() {
		super();
	}
	
	// List ================================================================================
	
	//Listing By Cook ======================================================================
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listByCook(){
		ModelAndView result;
		Collection<MasterClass> masterClasses;
		Cook cook;
		int cookId;
		
		cook = cookService.findByPrincipal();
		cookId = cook.getId();
		masterClasses = masterClassService.findAllByCook(cook);

		result = new ModelAndView("masterClass/list");
		result.addObject("masterClasses", masterClasses);
		result.addObject("requestURI","masterClass/list.do?cookId="+cookId);
		
		return result;
	}
	
	
	// Create=======================
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		MasterClass masterClass;
		Cook cook;
	
		cook = cookService.findByPrincipal();
		masterClass = masterClassService.create(cook);
		
		result = createEditModelAndView(masterClass);
		
		return result;
	}
	
	// Edit=======================
		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int masterClassId){
		ModelAndView result;
		MasterClass masterClass;
		
		masterClass = masterClassService.findOne(masterClassId);
		Assert.notNull(masterClass);
			
		result = createEditModelAndView(masterClass);
			
		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid MasterClass masterClass, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(masterClass);
		}else{
			try{
				
				masterClassService.save(masterClass);
				result = new ModelAndView("redirect:/masterClass/cook/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(masterClass, "masterClass.commit.error");
			}
		}
		return result;
	}
	// Delete=======================
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute MasterClass masterClass, BindingResult bindingResult){
		ModelAndView result;
			try {      
				masterClassService.delete(masterClass);
				
			    result = new ModelAndView("redirect:/masterClass/cook/list.do");
			} catch (Throwable oops) {
			    result = createEditModelAndView(masterClass, "masterClass.commit.error");        
			}

			return result;
				
	}
	
	// Ancillary Methods=======================			
		
	protected ModelAndView createEditModelAndView(MasterClass masterClass) {
		assert masterClass != null;
			    
		ModelAndView result;

		result = createEditModelAndView(masterClass, null);
			    
		return result;
		
	}
			
	
	protected ModelAndView createEditModelAndView(MasterClass masterClass, String message) {
		assert masterClass != null;
			    
		ModelAndView result;        

		result = new ModelAndView("masterClass/edit");
		result.addObject("masterClass", masterClass);
		result.addObject("message", message);
			    
		return result;
	}	
}
