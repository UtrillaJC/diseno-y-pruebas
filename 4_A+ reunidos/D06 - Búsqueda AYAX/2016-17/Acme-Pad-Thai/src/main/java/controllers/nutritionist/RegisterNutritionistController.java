package controllers.nutritionist;

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
import services.NutritionistService;
import controllers.AbstractController;
import domain.Nutritionist;

@Controller
@RequestMapping("/security")
public class RegisterNutritionistController extends AbstractController {


	// Services ============================================================================

	@Autowired
	private NutritionistService nutritionistService;
	
	// Constructors ============================================================================

	public RegisterNutritionistController() {
		super();
	}
	
	// Creation ============================================================================

	@RequestMapping(value = "/registerNutritionist", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Nutritionist nutritionist;

		nutritionist = nutritionistService.create();

		result = createEditModelAndView(nutritionist);

		return result;
	}

	@RequestMapping(value = "/registerNutritionist", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Nutritionist nutritionist, BindingResult binding) {
		ModelAndView result;
		UserAccount userAccount;
		String password;
		String coded;
		Md5PasswordEncoder encoder;

		if (binding.hasErrors()) {
			result = createEditModelAndView(nutritionist);
		} else {
			try {
				userAccount = nutritionist.getUserAccount();

				password = userAccount.getPassword();

				encoder = new Md5PasswordEncoder();
				coded = encoder.encodePassword(password, null);
				userAccount.setPassword(coded);

				nutritionist.setUserAccount(userAccount);
				
				nutritionistService.save(nutritionist);
				
				result = new ModelAndView("redirect:/security/login.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(nutritionist,
						"security.user.commit.error");
			}
		}
		return result;

	}

	// Ancillary methods ==============================================================================
	
	protected ModelAndView createEditModelAndView(Nutritionist nutritionist) {
		ModelAndView result;
		result = createEditModelAndView(nutritionist, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Nutritionist nutritionist, String message) {
		ModelAndView result;
		result = new ModelAndView("security/registerNutritionist");
		result.addObject("nutritionist", nutritionist);
		result.addObject("message", message);

		return result;
	}
}

