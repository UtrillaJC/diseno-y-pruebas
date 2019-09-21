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
@RequestMapping("/curriculum/nutritionist")
public class CurriculumNutritionistController extends AbstractController{
	
	@Autowired
	private NutritionistService nutritionistService;
	
	@Autowired
	private CurriculumService curriculumService;
	
	@Autowired
	private EndorserService endorserService;
	
	//Listing --------------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Curriculum> curricula;
		Nutritionist nutritionist;
		
		nutritionist = nutritionistService.findByPrincipal();
		
		curricula = curriculumService.findAllByNutritionist(nutritionist);

		result = new ModelAndView("curriculum/list");
		result.addObject("curricula", curricula);
		result.addObject("requestURI", "curriculum/nutritionist/list.do");

		return result;
	}
	
	//Creation ----------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Curriculum curriculum;
		Nutritionist principal;
		
		principal = nutritionistService.findByPrincipal();
		curriculum = curriculumService.create(principal);
		
		result = createEditModelAndView(curriculum);
		
		return result;
	}
	// Edit Curriculum

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int curriculumId) {
		ModelAndView result;
		Curriculum curriculum;
		Nutritionist nutritionist;

		nutritionist = nutritionistService.findByPrincipal();
		Assert.notNull(nutritionist);
		curriculum = curriculumService.findOne(curriculumId);

		result = createEditModelAndView(curriculum, "curriculum/edit");

		return result;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Curriculum curriculum, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(curriculum);
		}else{
			try{
				
				curriculumService.save(curriculum);
				result = new ModelAndView("redirect:/curriculum/nutritionist/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(curriculum, "curriculum.commit.error");
			}
		}
		return result;
	}
	
	//Deleting -------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Curriculum curriculum, BindingResult binding){
		ModelAndView result;
		
		try{
			curriculumService.delete(curriculum);
			result = new ModelAndView("redirect:/curriculum/nutritionist/list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(curriculum, "curriculum.commit.error");
		}
		return result;
	}
	
	//ShowCurriculum --------------------------------------------------------------------
	
	@RequestMapping(value = "/showCurriculum", method = RequestMethod.GET)
	public ModelAndView showCurriculum(@RequestParam int curriculumId) {
		ModelAndView result;
		Curriculum curriculum;
		Collection<Endorser> endorsers;

		curriculum = curriculumService.findOne(curriculumId);
		endorsers = endorserService.findAllByCurriculum(curriculumId);
		
		result = new ModelAndView("curriculum/showCurriculum");
		result.addObject("curriculum", curriculum);
		result.addObject("endorsers",endorsers);

		return result;
	}
	// Ancillary methods
	
	protected ModelAndView createEditModelAndView(Curriculum curriculum) {
		ModelAndView result;

		result = createEditModelAndView(curriculum, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Curriculum curriculum, String errorMessage) {
		ModelAndView result;

		result = new ModelAndView("curriculum/edit");

		result.addObject("curriculum", curriculum);
		result.addObject("errorMessage", errorMessage);
		result.addObject("requestURI", "curriculum/edit.do");

		return result;
	}
	
	@RequestMapping(value = "/addEndorser", method = RequestMethod.GET)
	public ModelAndView addEndorser(@RequestParam int curriculumId) {
		ModelAndView result;
		Collection<Endorser> endorsers;
		Curriculum curriculum;


		curriculum = curriculumService.findOne(curriculumId);
		endorsers = endorserService.findAllByNotCurriculum(curriculum);

		result = new ModelAndView("endorser/list");
		result.addObject("endorsers", endorsers);
		result.addObject("requestURI", "endorser/nutritionist/list.do?curriculumId="+ curriculumId);
		result.addObject("curriculumId", curriculumId);

		return result;

	}


}
