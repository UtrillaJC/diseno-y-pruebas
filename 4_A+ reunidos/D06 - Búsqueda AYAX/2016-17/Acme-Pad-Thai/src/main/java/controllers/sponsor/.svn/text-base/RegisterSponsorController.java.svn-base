package controllers.sponsor;

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
import services.SponsorService;
import controllers.AbstractController;
import domain.Sponsor;

@Controller
@RequestMapping("/security")
public class RegisterSponsorController extends AbstractController {


	// Services ============================================================================

	@Autowired
	private SponsorService sponsorService;
	
	// Constructors ============================================================================

	public RegisterSponsorController() {
		super();
	}
	
	// Creation ============================================================================

	@RequestMapping(value = "/registerSponsor", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Sponsor sponsor;

		sponsor = sponsorService.create();

		result = createEditModelAndView(sponsor);

		return result;
	}

	@RequestMapping(value = "/registerSponsor", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Sponsor sponsor, BindingResult binding) {
		ModelAndView result;
		UserAccount userAccount;
		String password;
		String coded;
		Md5PasswordEncoder encoder;

		if (binding.hasErrors()) {
			result = createEditModelAndView(sponsor);
		} else {
			try {
				userAccount = sponsor.getUserAccount();

				password = userAccount.getPassword();

				encoder = new Md5PasswordEncoder();
				coded = encoder.encodePassword(password, null);
				userAccount.setPassword(coded);

				sponsor.setUserAccount(userAccount);
				
				sponsorService.save(sponsor);
				
				result = new ModelAndView("redirect:/security/login.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(sponsor,
						"security.user.commit.error");
			}
		}
		return result;

	}

	// Ancillary methods ==============================================================================
	
	protected ModelAndView createEditModelAndView(Sponsor sponsor) {
		ModelAndView result;
		result = createEditModelAndView(sponsor, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Sponsor sponsor, String errorMessage) {
		ModelAndView result;
		result = new ModelAndView("security/registerSponsor");
		result.addObject("sponsor", sponsor);
		result.addObject("errorMessage", errorMessage);

		return result;
	}
}

