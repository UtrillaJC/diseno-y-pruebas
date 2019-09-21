
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

import services.BannerService;
import controllers.AbstractController;
import domain.Banner;

@Controller
@RequestMapping("/banner/administrator")
public class BannerAdministratorController extends AbstractController {

	// Services ============================================================================

	@Autowired
	private BannerService	bannerService;


	// Constructors ========================================================================

	public BannerAdministratorController() {
		super();
	}

	//Show banner ============================================================

	@RequestMapping(value = "/showBanner", method = RequestMethod.GET)
	public ModelAndView showBanner() {
		ModelAndView result;
		Banner banner;

		banner = this.bannerService.findBanner();

		result = new ModelAndView("banner/administrator/showBanner");
		result.addObject("row", banner);

		return result;
	}

	// Edit ======================================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int bannerId) {
		ModelAndView result;
		final Banner banner;

		banner = this.bannerService.findOneToEdit(bannerId);
		Assert.notNull(banner);

		result = this.createEditModelAndView(banner);

		return result;
	}

	// Edit ======================================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute final Banner banner, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(banner);
		else
			try {
				this.bannerService.save(banner);
				result = new ModelAndView("redirect:showBanner.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(banner, "banner.commit.error");
			}
		return result;
	}

	// Ancillary methods =============================================================================

	protected ModelAndView createEditModelAndView(final Banner banner) {
		assert banner != null;

		ModelAndView result;

		result = this.createEditModelAndView(banner, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Banner banner, final String message) {
		assert banner != null;

		ModelAndView result;

		result = new ModelAndView("banner/administrator/edit");
		result.addObject("banner", banner);
		result.addObject("message", message);

		return result;
	}
}
