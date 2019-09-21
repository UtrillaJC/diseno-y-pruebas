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
import services.TextService;
import controllers.AbstractController;
import domain.MasterClass;
import domain.Text;


@Controller
@RequestMapping("/text/cook")
public class TextCookController extends AbstractController  {
	
	// Services ============================================================================

	@Autowired
	private LearningMaterialService learningMaterialService;
	
	@Autowired
	private TextService textService;
	
	@Autowired
	private MasterClassService masterClassService;
	
	// Constructors ========================================================================

	public TextCookController() {
		super();
	}
	
	
	// Edit=======================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int masterClassId){
		ModelAndView result;	
		Text text;
		MasterClass masterClass;
		
		masterClass = masterClassService.findOne(masterClassId);
		text = textService.create(masterClass);
		result = createEditModelAndView(text);
		
			
		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Text text, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(text);
		}else{
			try{
				
				learningMaterialService.save(text);
				result = new ModelAndView("redirect:/masterClass/cook/list.do");
				result.addObject("requestURI", "text/cook/edit.do");
			}catch(Throwable oops){
				result = createEditModelAndView(text, "text.commit.error");
			}
		}
		return result;
	}
	
	// Ancillary Methods=======================			
	
	protected ModelAndView createEditModelAndView(Text text) {
		assert text != null;
			    
		ModelAndView result;

		result = createEditModelAndView(text, null);
			    
		return result;
		
	}
			
	
	protected ModelAndView createEditModelAndView(Text text, String message) {
		assert text != null;
			    
		ModelAndView result; 

		result = new ModelAndView("learningMaterial/editText");
		result.addObject("text", text);
		result.addObject("message", message);

    
		return result;
	}	
	
	
}