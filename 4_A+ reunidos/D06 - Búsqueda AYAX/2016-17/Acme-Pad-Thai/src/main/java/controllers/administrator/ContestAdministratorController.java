package controllers.administrator;

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

import services.AdministratorService;
import services.ContestService;
import controllers.AbstractController;
import domain.Contest;

@Controller
@RequestMapping("/contest/administrator")
public class ContestAdministratorController extends AbstractController {

	// Services ============================================================================
	
	@Autowired
	private ContestService contestService;

	@Autowired
	private AdministratorService administratorService;
	
	// Constructors ============================================================================

	public ContestAdministratorController() {
		super();
	}
	
	// List contest closed ============================================================================
	
	@RequestMapping(value = "/contestsFinished", method = RequestMethod.GET)
	public ModelAndView contestsFinished() {
		ModelAndView result;
		Collection<Contest> contests;
		boolean execute;
		
		execute = true;
		contests = contestService.findAllFinished();

		result = new ModelAndView("contest/list");
		result.addObject("contests", contests);
		result.addObject("execute", execute);
		result.addObject("requestURI", "contest/administrator/list.do");

	return result;
	
	} 
	
	@RequestMapping(value = "/execute", method = RequestMethod.GET)
	public ModelAndView excute(@RequestParam int contestId) {
		ModelAndView result;		
			Contest contest;
			
			contest = contestService.findOne(contestId);
			
		try {			
			administratorService.winnersOfContest(contest);
			result = contestsFinished();
			result.addObject("message", "contest.commit.ok");			
		} catch (Throwable oops) {			
			result = contestsFinished();
			result.addObject("message", "contest.commit.error");			
		}
		
		return result;
	}
	
	// Create ======================================================================================
		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Contest contest;
		
		contest = contestService.create();
	
		result = createModelAndView(contest);
		
		return result;
	}
	
	// Edit ======================================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int contestId){
		ModelAndView result;
		Contest contest;
		
		contest = contestService.findOne(contestId);
		Assert.notNull(contest);
	
		result = editModelAndView(contest);
		
		return result;
	}
	
	// Save ======================================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Contest contest, BindingResult bindingResult){
		ModelAndView result;
		Contest contest2;
		

		
		if (bindingResult.hasErrors()) {
			result = createModelAndView(contest);
		} else {
			try {	
				if(contest.getId() != 0){
					contest2 = contestService.findOne(contest.getId());
					Assert.isTrue(!contest.getMomentClosing().before(contest2.getMomentClosing()));
				}
				contestService.save(contest);
				result = new ModelAndView("redirect:/contest/list.do");
			} catch (Throwable oops) {
				result = createModelAndView(contest, "contest.commit.error");				
			}
		}	
		return result;
	}
	
	// Delete ======================================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Contest contest, BindingResult bindingResult){
		ModelAndView result;
		
		if (bindingResult.hasErrors()) {
			result = createModelAndView(contest);
		} else {
			try {			
				contestService.delete(contest);
				result = new ModelAndView("redirect:/contest/list.do");
			} catch (Throwable oops) {
				result = createModelAndView(contest, "contest.commit.error");				
			}
		}	
		return result;
	}
	
	// Ancillary methods =============================================================================
	
	protected ModelAndView createModelAndView(Contest contest) {
		assert contest != null;
		
		ModelAndView result;

		result = createModelAndView(contest, null);
		
		return result;
	}
	
	protected ModelAndView createModelAndView(Contest contest, String message) {
		assert contest != null;
		
		ModelAndView result;				

		result = new ModelAndView("contest/create");
		result.addObject("contest", contest);
		result.addObject("message", message);
		
		return result;
	}
	
	protected ModelAndView editModelAndView(Contest contest) {
		assert contest != null;
		
		ModelAndView result;

		result = editModelAndView(contest, null);
		
		return result;
	}
	
	protected ModelAndView editModelAndView(Contest contest, String message) {
		assert contest != null;
		
		ModelAndView result;				

		result = new ModelAndView("contest/edit");
		result.addObject("contest", contest);
		result.addObject("message", message);
		
		return result;
	}
}
