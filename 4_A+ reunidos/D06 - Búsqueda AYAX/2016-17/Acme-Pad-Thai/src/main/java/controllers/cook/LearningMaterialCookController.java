package controllers.cook;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LearningMaterialService;
import controllers.AbstractController;
import domain.LearningMaterial;


@Controller
@RequestMapping("/learningMaterial/cook")
public class LearningMaterialCookController extends AbstractController  {
	
	// Services ============================================================================

	@Autowired
	private LearningMaterialService learningMaterialService;
	
	// Constructors ========================================================================

		public LearningMaterialCookController() {
			super();
		}
		
		// List ================================================================================
		
		
		// Edit=======================
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int learningMaterialId){
			ModelAndView result;
			LearningMaterial learningMaterial;
			
			learningMaterial = learningMaterialService.findOne(learningMaterialId);
			Assert.notNull(learningMaterial);
				
			result = createEditModelAndView(learningMaterial);
				
			return result;
		}
		
		@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
		public ModelAndView save(@Valid LearningMaterial learningMaterial, BindingResult binding){
			ModelAndView result;
			
			if(binding.hasErrors()){
				result = createEditModelAndView(learningMaterial);
			}else{
				try{
					
					learningMaterialService.save(learningMaterial);
					result = new ModelAndView("redirect:/masterClass/cook/list.do");
				}catch(Throwable oops){
					result = createEditModelAndView(learningMaterial, "learningMaterial.commit.error");
				}
			}
			return result;
		}
		
		// Ancillary Methods=======================			
		
		protected ModelAndView createEditModelAndView(LearningMaterial learningMaterial) {
			assert learningMaterial != null;
				    
			ModelAndView result;

			result = createEditModelAndView(learningMaterial, null);
				    
			return result;
			
		}
				
		
		protected ModelAndView createEditModelAndView(LearningMaterial learningMaterial, String message) {
			assert learningMaterial != null;
				    
			ModelAndView result;        

			result = new ModelAndView("learningMaterial/edit");
			result.addObject("learningMaterial", learningMaterial);
			result.addObject("message", message);
				    
			return result;
		}	
}