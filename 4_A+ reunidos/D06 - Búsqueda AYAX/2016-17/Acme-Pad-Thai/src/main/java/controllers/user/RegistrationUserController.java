package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import services.RecipeService;
import services.RegistrationService;
import services.UserService;
import domain.Contest;
import domain.Recipe;
import domain.Registration;
import domain.User;


@Controller
@RequestMapping("/registration/user")
public class RegistrationUserController {

	// Services ================================================================================	

	@Autowired
	private ContestService contestService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private RecipeService recipeService;
	
	// Constructors ============================================================================

	public RegistrationUserController() {
		super();
	}
	
	// List ====================================================================================
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Registration> registrations;
		User user;
		
		user = userService.findByPrincipal();
		registrations = registrationService.findAllByUser(user);
		
		result = new ModelAndView("registration/user/list");
		result.addObject("registrations", registrations);
		result.addObject("requestURI", "registration/user/list.do");
		
		return result;
	}	
		
	// Register ============================================================================
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam int contestId){
		ModelAndView result;
		Registration registration;
		Collection<Recipe> recipes;
		Contest contest;
		User user;
		int userId;
		
		contest = contestService.findOne(contestId);
		user = userService.findByPrincipal();
		userId = user.getId();
		recipes = recipeService.findAllByUser(userId);
		
		for(Registration reg : contest.getRegistrations()){
			if(recipes.contains(reg.getRecipe())){
				recipes.remove(reg.getRecipe());
			}
		}
		
		registration = registrationService.create(contest);	
		
		result = new ModelAndView("registration/user/register");
		result.addObject("registration", registration);
		result.addObject("recipes", recipes);

		return result;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Registration registration, BindingResult bindingResult){
		ModelAndView result;
			
		if (bindingResult.hasErrors()) {
			result = createEditModelAndView(registration);
		} else {
			try {			
				registrationService.save(registration);
				result = new ModelAndView("redirect: /list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(registration, "registration.commit.error");	
			}
		}	
		return result;
	}
	
	// Ancillary methods ==============================================================================
	
	protected ModelAndView createEditModelAndView(Registration registration) {
		ModelAndView result;
		result = createEditModelAndView(registration, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Registration registration, String message) {
		ModelAndView result;
		
		result = new ModelAndView("registration/user/register");
		result.addObject("registration", registration);
		result.addObject("message", message);

		return result;
	}
}
