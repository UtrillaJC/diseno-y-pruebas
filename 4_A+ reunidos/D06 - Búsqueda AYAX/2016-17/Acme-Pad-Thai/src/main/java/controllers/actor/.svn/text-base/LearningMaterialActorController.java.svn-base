package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.LearningMaterialService;
import controllers.AbstractController;
import domain.LearningMaterial;


@Controller
@RequestMapping("/learningMaterial")
public class LearningMaterialActorController extends AbstractController  {
	
	// Services ============================================================================

	@Autowired
	private LearningMaterialService learningMaterialService;

	// Constructors ============================================================================

		public LearningMaterialActorController() {
			super();
		}
		
	// List ============================================================================
			
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<LearningMaterial> learningMaterials;
		boolean registered;
		
		registered = false;
		learningMaterials = learningMaterialService.findAll();

		result = new ModelAndView("learningMaterial/list");
		result.addObject("learningMaterials", learningMaterials);
		result.addObject("registered", registered);
		result.addObject("requestURI","learningMaterial/list.do");
		
		return result;
	}
}
