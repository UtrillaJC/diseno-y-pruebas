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

import services.SpamTermService;
import controllers.AbstractController;
import domain.SpamTerm;

@Controller
@RequestMapping("/spamTerm/administrator")
public class SpamTermAdministratorController extends AbstractController{

	// Services ============================================================================
	
	@Autowired
	private SpamTermService spamTermService;
	
	// Constructors ============================================================================

	public SpamTermAdministratorController() {
		super();
	}
	
	@RequestMapping(value = "/showSpamTerm", method = RequestMethod.GET)
	public ModelAndView showSpamTerm(){
		ModelAndView result;
		SpamTerm spamTerm;
		Collection<String> keywords;
		
		spamTerm = spamTermService.findOne(127);
		keywords = spamTerm.getKeywords();
		
		result = new ModelAndView("spamTerm/administrator/showSpamTerm");
		result.addObject("spamTerm", spamTerm);
		result.addObject("keywords", keywords);
		result.addObject("spamTermId", spamTerm.getId());
		
		return result;
	}
	
	// Edit ======================================================================================
		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int spamTermId){
		ModelAndView result;
		SpamTerm spamTerm;
		
		spamTerm = spamTermService.findOne(spamTermId);
		Assert.notNull(spamTerm);
	
		result = createEditModelAndView(spamTerm);
		
		return result;
	}
	
	// Edit ======================================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute SpamTerm spamTerm, BindingResult bindingResult){
		ModelAndView result;
		
		if (bindingResult.hasErrors()) {
			result = createEditModelAndView(spamTerm);
		} else {
			try {			
				spamTermService.save(spamTerm);
				result = new ModelAndView("redirect:showSpamTerm.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(spamTerm, "spamTerm.commit.error");				
			}
		}	
		return result;
	}
	
	// Ancillary methods =============================================================================
	
	protected ModelAndView createEditModelAndView(SpamTerm spamTerm) {
		assert spamTerm != null;
		
		ModelAndView result;

		result = createEditModelAndView(spamTerm, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(SpamTerm spamTerm, String message) {
		assert spamTerm != null;
		
		ModelAndView result;				

		result = new ModelAndView("spamTerm/administrator/edit");
		result.addObject("spamTerm", spamTerm);
		result.addObject("message", message);
		
		return result;
	}
}
