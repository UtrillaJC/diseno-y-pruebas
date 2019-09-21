package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.CookService;
import controllers.AbstractController;
import domain.Cook;

@Controller
@RequestMapping("/security/administrator")
public class RegisterCookAdministratorController extends AbstractController {


	// Services ============================================================================

	@Autowired
	private CookService cookService;
	
	// Constructors ============================================================================

	public RegisterCookAdministratorController() {
		super();
	}
	
	// Creation ============================================================================

	@RequestMapping(value = "/registerCook", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Cook cook;

		cook = cookService.create();

		result = createEditModelAndView(cook);

		return result;
	}

	@RequestMapping(value = "/registerCook", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Cook cook, BindingResult binding) {
		ModelAndView result;
		UserAccount userAccount;
		String password;
		String coded;
		Md5PasswordEncoder encoder;

		if (binding.hasErrors()) {
			result = createEditModelAndView(cook);
		} else {
			try {
				userAccount = cook.getUserAccount();

				password = userAccount.getPassword();

				encoder = new Md5PasswordEncoder();
				coded = encoder.encodePassword(password, null);
				userAccount.setPassword(coded);

				cook.setUserAccount(userAccount);
				
				cookService.save(cook);
				
				result = new ModelAndView("redirect:/security/login.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(cook,
						"security.user.commit.error");
			}
		}
		return result;

	}

	// Ancillary methods ==============================================================================
	
	protected ModelAndView createEditModelAndView(Cook cook) {
		ModelAndView result;
		result = createEditModelAndView(cook, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Cook cook, String errorMessage) {
		ModelAndView result;
		result = new ModelAndView("security/administrator/registerCook");
		result.addObject("cook", cook);
		result.addObject("errorMessage", errorMessage);

		return result;
	}
}

