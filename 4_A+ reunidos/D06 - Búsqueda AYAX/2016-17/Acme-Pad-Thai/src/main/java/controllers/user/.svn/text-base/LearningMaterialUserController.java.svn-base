package controllers.user;

import java.net.URL;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LearningMaterialService;
import services.MasterClassService;
import controllers.AbstractController;
import domain.LearningMaterial;
import domain.MasterClass;

@Controller
@RequestMapping("/learningMaterial/user")
public class LearningMaterialUserController extends AbstractController {

	// Services ================================================================================

	@Autowired
	private LearningMaterialService learningMaterialService;
	
	@Autowired
	private MasterClassService masterClassService;
	
	// Constructors ============================================================================

	public LearningMaterialUserController() {
		super();
	}
	
	// List ====================================================================================
		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int masterClassId){
		ModelAndView result;
		Collection<LearningMaterial> learningMaterials;
		MasterClass masterClass;
		boolean registered;
		
		registered = true;
		masterClass = masterClassService.findOneByUserPrincipal(masterClassId);
	
		learningMaterials = learningMaterialService.findAllByMasterClass(masterClass);
		
		result = new ModelAndView("learningMaterial/list");
		result.addObject("learningMaterials", learningMaterials);
		result.addObject("registered", registered);
		result.addObject("requestURI", "learningMaterial/user/list.do");
		
		return result;
	}	
	
	// Show ====================================================================================

	@RequestMapping(value = "/showLearningMaterial", method = RequestMethod.GET)
	public ModelAndView showItem(@RequestParam int learningMaterialId) {
		ModelAndView result;
		LearningMaterial learningMaterial;
		Collection<URL> attachments;
		
		learningMaterial = learningMaterialService.findOne(learningMaterialId);
		attachments = learningMaterial.getAttachments();
		
		result = new ModelAndView();
		
		switch (learningMaterial.getType()) {
			case "Video":
				result = new ModelAndView("learningMaterial/user/showVideo");
				result.addObject("learningMaterial", learningMaterial);
				result.addObject("attachments", attachments);
				break;
				
			case "Text":
				result = new ModelAndView("learningMaterial/user/showText");
				result.addObject("learningMaterial", learningMaterial);
				result.addObject("attachments", attachments);
				break;
			
			case "Presentation":
				result = new ModelAndView("learningMaterial/user/showPresentation");
				result.addObject("learningMaterial", learningMaterial);
				result.addObject("attachments", attachments);
				break;
	      }

		return result;
	}
}
