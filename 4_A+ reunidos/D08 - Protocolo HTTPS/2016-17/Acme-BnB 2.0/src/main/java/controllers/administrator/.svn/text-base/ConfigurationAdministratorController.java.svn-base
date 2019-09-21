
package controllers.administrator;

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

import controllers.AbstractController;
import domain.Configuration;
import services.ConfigurationService;

@Controller
@RequestMapping("/configuration/administrator")
public class ConfigurationAdministratorController extends AbstractController {

	// Services ============================================================================

	@Autowired
	private ConfigurationService configurationService;


	// Constructors ============================================================================

	public ConfigurationAdministratorController() {
		super();
	}

	@RequestMapping(value = "/showConfiguration", method = RequestMethod.GET)
	public ModelAndView showConfiguration() {
		ModelAndView result;
		Configuration configuration;

		configuration = configurationService.findOne(1);

		result = new ModelAndView("configuration/administrator/showConfiguration");
		result.addObject("row", configuration);

		return result;
	}

	// Edit ======================================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int configurationId) {
		ModelAndView result;
		Configuration configuration;

		configuration = configurationService.findOne(configurationId);
		Assert.notNull(configuration);

		result = createEditModelAndView(configuration);

		return result;
	}

	// Edit ======================================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Configuration configuration, BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors()) {
			result = createEditModelAndView(configuration);
		} else {
			try {
				configurationService.save(configuration);
				result = new ModelAndView("redirect:showConfiguration.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(configuration, "configuration.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods =============================================================================

	protected ModelAndView createEditModelAndView(Configuration configuration) {
		assert configuration != null;

		ModelAndView result;

		result = createEditModelAndView(configuration, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Configuration configuration, String message) {
		assert configuration != null;

		ModelAndView result;

		result = new ModelAndView("configuration/administrator/edit");
		result.addObject("configuration", configuration);
		result.addObject("message", message);

		return result;
	}
}
