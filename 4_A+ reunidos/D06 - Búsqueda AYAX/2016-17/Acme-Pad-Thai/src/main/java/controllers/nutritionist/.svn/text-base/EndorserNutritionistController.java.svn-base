package controllers.nutritionist;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.EndorserService;
import services.NutritionistService;
import controllers.AbstractController;
import domain.Curriculum;
import domain.Endorser;
import domain.Nutritionist;

@Controller
@RequestMapping("/endorser/nutritionist")
public class EndorserNutritionistController extends AbstractController{
	
	@Autowired
	private NutritionistService nutritionistService;
	
	@Autowired
	private EndorserService endorserService;
	
	@Autowired
	private CurriculumService curriculumService;
	
	//Listing --------------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int curriculumId){
		ModelAndView result;
		Collection<Endorser> endorsers;
		Nutritionist nutritionist;
		
		nutritionist = nutritionistService.findByPrincipal();
		Assert.notNull(nutritionist);
		endorsers = endorserService.findAllByCurriculum(curriculumId);

		result = new ModelAndView("endorser/list");
		result.addObject("endorsers", endorsers);
		result.addObject("requestURI", "endorser/nutritionist/list.do");
		result.addObject("curriculumId", curriculumId);

		return result;
	}
	
	//Creation ----------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Endorser endorser;
		Nutritionist principal;
		
		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		endorser = endorserService.create();
		
		result = createEditModelAndView(endorser);
		
		return result;
	}
	
	// Edit Endorser
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int endorserId) {
		ModelAndView result;
		Endorser endorser;
		Nutritionist nutritionist;

		nutritionist = nutritionistService.findByPrincipal();
		Assert.notNull(nutritionist);
		endorser = endorserService.findOne(endorserId);

		result = createEditModelAndView(endorser);

		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Endorser endorser, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(endorser);
		}else{
			try{
				
				endorserService.save(endorser);
				result = new ModelAndView("redirect:/curriculum/nutritionist/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(endorser, "endorser.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addEndorser(@RequestParam int endorserId, @RequestParam int curriculumId) {
		ModelAndView result;
		Endorser endorser;
		Curriculum curriculum;

		endorser = endorserService.findOne(endorserId);
		curriculum = curriculumService.findOne(curriculumId);
		
		curriculumService.addEndorser(curriculum, endorser);
		result = new ModelAndView("redirect:/curriculum/nutritionist/showCurriculum.do?curriculumId="+curriculumId);

		return result;

	}
	
	// Ancillary methods
	
	protected ModelAndView createEditModelAndView(Endorser endorser) {
		ModelAndView result;

		result = createEditModelAndView(endorser, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Endorser endorser, String message) {
		ModelAndView result;

		result = new ModelAndView("endorser/edit");

		result.addObject("endorser", endorser);
		result.addObject("message", message);
		result.addObject("requestURI", "endorser/edit.do");

		return result;
	}
	
}
