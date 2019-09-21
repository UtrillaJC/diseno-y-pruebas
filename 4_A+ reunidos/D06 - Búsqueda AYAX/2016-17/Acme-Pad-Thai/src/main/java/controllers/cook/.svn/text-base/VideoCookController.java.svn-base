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
import services.VideoService;
import controllers.AbstractController;
import domain.MasterClass;
import domain.Video;


@Controller
@RequestMapping("/video/cook")
public class VideoCookController extends AbstractController  {
	
	// Services ============================================================================

	@Autowired
	private LearningMaterialService learningMaterialService;
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private MasterClassService masterClassService;
	
	// Constructors ========================================================================

	public VideoCookController() {
		super();
	}
	
	
	// Edit=======================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int masterClassId){
		ModelAndView result;	
		Video video;
		MasterClass masterClass;
		
		masterClass = masterClassService.findOne(masterClassId);
		video = videoService.create(masterClass);
		result = createEditModelAndView(video);
		
			
		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Video video, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(video);
		}else{
			try{
				
				learningMaterialService.save(video);
				result = new ModelAndView("redirect:/masterClass/cook/list.do");
				result.addObject("requestURI", "video/cook/edit.do");
			}catch(Throwable oops){
				result = createEditModelAndView(video, "video.commit.error");
			}
		}
		return result;
	}
	
	// Ancillary Methods=======================			
	
	protected ModelAndView createEditModelAndView(Video video) {
		assert video != null;
			    
		ModelAndView result;

		result = createEditModelAndView(video, null);
			    
		return result;
		
	}
			
	
	protected ModelAndView createEditModelAndView(Video video, String message) {
		assert video != null;
			    
		ModelAndView result; 

		result = new ModelAndView("learningMaterial/editVideo");
		result.addObject("video", video);
		result.addObject("message", message);
			    
		return result;
	}	
	
	
}