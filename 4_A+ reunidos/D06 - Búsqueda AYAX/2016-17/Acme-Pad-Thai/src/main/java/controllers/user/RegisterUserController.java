package controllers.user;

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
import services.UserService;
import controllers.AbstractController;
import domain.User;

@Controller
@RequestMapping("/security")
public class RegisterUserController extends AbstractController{

	// Services ============================================================================

	@Autowired
	private UserService userService;
	
	// Constructors ============================================================================

	public RegisterUserController() {
		super();
	}
	
	// Creation ============================================================================

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		User user;

		user = userService.create();

		result = createEditModelAndView(user);

		return result;
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute User user, BindingResult binding) {
		ModelAndView result;
		UserAccount userAccount;
		String password;
		String coded;
		Md5PasswordEncoder encoder;

		if (binding.hasErrors()) {
			result = createEditModelAndView(user);
		} else {
			try {
				userAccount = user.getUserAccount();

				password = userAccount.getPassword();

				encoder = new Md5PasswordEncoder();
				coded = encoder.encodePassword(password, null);
				userAccount.setPassword(coded);

				user.setUserAccount(userAccount);
				
				userService.save(user);
				
				result = new ModelAndView("redirect:/security/login.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(user, "security.user.commit.error");
			}
		}
		return result;

	}

	// Ancillary methods ==============================================================================
	
	protected ModelAndView createEditModelAndView(User user) {
		ModelAndView result;
		result = createEditModelAndView(user, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(User user, String message) {
		ModelAndView result;
		result = new ModelAndView("security/registerUser");
		result.addObject("user", user);
		result.addObject("message", message);

		return result;
	}
}
