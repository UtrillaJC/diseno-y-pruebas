package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import controllers.AbstractController;
import domain.Contest;

@Controller
@RequestMapping("/contest/user")
public class ContestUserController extends AbstractController {
	
	// Services ================================================================================

	@Autowired
	private ContestService contestService;
	
	// Constructors ============================================================================

	public ContestUserController() {
		super();
	}
	
	// List ====================================================================================
		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Contest> contests;
		
		contests = contestService.findAllActive();
		
		result = new ModelAndView("contest/list");
		result.addObject("contests", contests);
		
		return result;
	}	
}
