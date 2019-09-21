package controllers.cook;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LearningMaterialService;
import services.MasterClassService;
import services.PresentationService;
import controllers.AbstractController;
import domain.MasterClass;
import domain.Presentation;


@Controller
@RequestMapping("/presentation/cook")
public class PresentationCookController extends AbstractController  {
	
	// Services ============================================================================

	@Autowired
	private LearningMaterialService learningMaterialService;
	
	@Autowired
	private PresentationService presentationService;
	
	@Autowired
	private MasterClassService masterClassService;
	
	// Constructors ========================================================================

	public PresentationCookController() {
		super();
	}
	
	
	// Edit=======================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int masterClassId){
		ModelAndView result;	
		Presentation presentation;
		MasterClass masterClass;
		
		masterClass = masterClassService.findOne(masterClassId);
		presentation = presentationService.create(masterClass);
		result = createEditModelAndView(presentation);
		
			
		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Presentation presentation, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(presentation);
		}else{
			try{
				
				learningMaterialService.save(presentation);
				result = new ModelAndView("redirect:/masterClass/cook/list.do");
				result.addObject("requestURI", "presentation/cook/edit.do");
			}catch(Throwable oops){
				result = createEditModelAndView(presentation, "presentation.commit.error");
			}
		}
		return result;
	}
	
	// Ancillary Methods=======================			
	
	protected ModelAndView createEditModelAndView(Presentation presentation) {
		assert presentation != null;
			    
		ModelAndView result;

		result = createEditModelAndView(presentation, null);
			    
		return result;
		
	}
			
	
	protected ModelAndView createEditModelAndView(Presentation presentation, String message) {
		assert presentation != null;
			    
		ModelAndView result; 
		
		result = new ModelAndView("learningMaterial/editPresentation");
		result.addObject("presentation", presentation);
		result.addObject("message", message);
			    
		return result;
	}	
	
	
}