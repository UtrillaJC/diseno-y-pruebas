package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MasterClassService;
import services.UserService;
import controllers.AbstractController;
import domain.MasterClass;
import domain.User;

@Controller
@RequestMapping("/masterClass/user")
public class MasterClassUserController extends AbstractController{

	// Services ============================================================================

	@Autowired
	private MasterClassService masterClassService;
	
	@Autowired
	private UserService userService;

	// Constructors ============================================================================

	public MasterClassUserController() {
		super();
	}
	
	// List ============================================================================
	
	@RequestMapping("/list-registered")
	public ModelAndView listRegistered() {
		ModelAndView result;
		Collection<MasterClass> masterClasses;
		User user;
		boolean registered;
		
		registered = true;
		user = userService.findByPrincipal();
		masterClasses = masterClassService.findAllByUser(user);
		
		result = new ModelAndView("masterClass/list");
		result.addObject("masterClasses", masterClasses);
		result.addObject("registered", registered);
		result.addObject("requestURI", "masterClass/user/list-registered.do");
		
		return result;
	}
	
	@RequestMapping("/list-not-registered.do")
	public ModelAndView listNotRegistered() {
		ModelAndView result;
		Collection<MasterClass> masterClasses;
		User user;
		boolean registered;
		
		registered = false;
		user = userService.findByPrincipal();
		masterClasses = masterClassService.findAllByNotUser(user);
		
		result = new ModelAndView("masterClass/list");
		result.addObject("masterClasses", masterClasses);
		result.addObject("registered", registered);
		result.addObject("requestURI", "masterClass/user/list-not-registered.do");
		
		return result;
	}

	// Registration -----------------------------------------------------------
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam int masterClassId) {
		ModelAndView result;		
				
		try {			
			masterClassService.register(masterClassId);
			result = listNotRegistered();
			result.addObject("message", "masterClass.commit.ok");			
		} catch (Throwable oops) {			
			result = listNotRegistered();
			result.addObject("message", "masterClass.commit.error");			
		}
		
		return result;
	}
	
	@RequestMapping(value = "/unregister", method = RequestMethod.GET)
	public ModelAndView unregister(@RequestParam int masterClassId) {
		ModelAndView result;		
				
		try {
			masterClassService.unregister(masterClassId);
			result = listRegistered();			
			result.addObject("message", "masterClass.commit.ok");
		} catch (Throwable oops) {
			result = listRegistered();
			result.addObject("message", "masterClass.commit.error");			
		}
		
		return result;
	}
}
