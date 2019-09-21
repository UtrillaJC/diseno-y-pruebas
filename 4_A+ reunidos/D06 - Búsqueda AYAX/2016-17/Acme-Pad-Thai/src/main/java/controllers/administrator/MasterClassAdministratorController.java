package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MasterClassService;
import controllers.AbstractController;
import domain.MasterClass;

@Controller
@RequestMapping("/masterClass/administrator")
public class MasterClassAdministratorController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private MasterClassService masterClassService;

	// Constructors
	// ============================================================================

	public MasterClassAdministratorController() {
		super();
	}

	// List
	// ============================================================================

	@RequestMapping("/list-promoted")
	public ModelAndView listPromoted() {
		ModelAndView result;
		Collection<MasterClass> masterClasses;
		boolean promoted;

		promoted = true;
		masterClasses = masterClassService.findAllPromoted();

		result = new ModelAndView("masterClass/list");
		result.addObject("masterClasses", masterClasses);
		result.addObject("promoted", promoted);
		result.addObject("requestURI",
				"masterClass/administrator/list-promoted.do");

		return result;
	}

	@RequestMapping("/list-demoted.do")
	public ModelAndView listDemoted() {
		ModelAndView result;
		Collection<MasterClass> masterClasses;
		boolean promoted;

		promoted = false;

		masterClasses = masterClassService.findAllDemoted();

		result = new ModelAndView("masterClass/list");
		result.addObject("masterClasses", masterClasses);
		result.addObject("promoted", promoted);
		result.addObject("requestURI",
				"masterClass/administrator/list-demoted.do");

		return result;
	}

	// Promotion -----------------------------------------------------------

	@RequestMapping(value = "/promote", method = RequestMethod.GET)
	public ModelAndView promote(@RequestParam int masterClassId) {
		ModelAndView result;

		try {
			masterClassService.promote(masterClassId);
			result = listDemoted();
			result.addObject("message", "masterClass.commit.ok");
		} catch (Throwable oops) {
			result = listDemoted();
			result.addObject("message", "masterClass.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/demote", method = RequestMethod.GET)
	public ModelAndView demote(@RequestParam int masterClassId) {
		ModelAndView result;

		try {
			masterClassService.demote(masterClassId);
			result = listPromoted();
			result.addObject("message", "masterClass.commit.ok");
		} catch (Throwable oops) {
			result = listPromoted();
			result.addObject("message", "masterClass.commit.error");
		}

		return result;
	}
}
