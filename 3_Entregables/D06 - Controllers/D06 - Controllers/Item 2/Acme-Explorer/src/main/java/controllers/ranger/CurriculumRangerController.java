
package controllers.ranger;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.RangerService;
import controllers.AbstractController;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.PersonalRecord;
import domain.ProfessionalRecord;

@Controller
@RequestMapping("/curriculum/ranger")
public class CurriculumRangerController extends AbstractController {

	// Services ------------------------------------------------------------

	@Autowired
	private CurriculumService	curriculumService;

	@Autowired
	private RangerService		rangerService;


	// Constructors --------------------------------------------------------

	public CurriculumRangerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	// Display --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result = null;
		PersonalRecord personalRecord = null;
		Curriculum curriculum = null;
		Collection<EducationRecord> educationRecords = null;
		Collection<EndorserRecord> endorserRecords = null;
		Collection<MiscellaneousRecord> miscellaneousRecords = null;
		Collection<ProfessionalRecord> professionalRecords = null;

		curriculum = this.curriculumService.findByPrincipal();

		personalRecord = curriculum.getPersonalRecord();
		educationRecords = curriculum.getEducationRecords();
		endorserRecords = curriculum.getEndorserRecords();
		miscellaneousRecords = curriculum.getMiscellaneousRecords();
		professionalRecords = curriculum.getProfessionalRecords();

		result = new ModelAndView("curriculum/display");
		result.addObject("curriculum", curriculum);
		result.addObject("personalRecord", personalRecord);
		result.addObject("educationRecords", educationRecords);
		result.addObject("endorserRecords", endorserRecords);
		result.addObject("miscellaneousRecords", miscellaneousRecords);
		result.addObject("professionalRecords", professionalRecords);

		return result;
	}

	// Creation  --------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result = null;
		Curriculum curriculum = null;

		curriculum = this.curriculumService.create(this.rangerService.findByPrincipal());
		result = this.createEditModelAndView(curriculum);

		return result;
	}

	// Edition    --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int curriculumId) {
		ModelAndView result = null;
		Curriculum curriculum = null;

		curriculum = this.curriculumService.findOne(curriculumId);
		Assert.notNull(curriculum);
		result = this.createEditModelAndView(curriculum);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Curriculum curriculum, final BindingResult bindingResult) {
		ModelAndView result = null;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(curriculum);
		else
			try {
				this.curriculumService.save(curriculum);
				result = new ModelAndView("redirect:../curriculum/display.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(curriculum, "application.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Curriculum curriculum, final BindingResult bindingResult) {
		ModelAndView result = null;

		try {
			this.curriculumService.delete(curriculum);
			result = new ModelAndView("redirect:../curriculum/ranger/display.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(curriculum, "curriculum.commit.error");
		}

		return result;
	}

	// Ancillary methods ----------------------------------------------------

	protected ModelAndView createEditModelAndView(final Curriculum curriculum) {
		ModelAndView result = null;
		result = this.createEditModelAndView(curriculum, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Curriculum curriculum, final String messageCode) {
		ModelAndView result = null;

		result = new ModelAndView("curriculum/edit");
		result.addObject("curriculum", curriculum);
		result.addObject("message", messageCode);
		result.addObject("educationRecords", curriculum.getEducationRecords());
		result.addObject("endorserRecords", curriculum.getEndorserRecords());
		result.addObject("miscellaneousRecords", curriculum.getMiscellaneousRecords());
		result.addObject("professionalRecords", curriculum.getProfessionalRecords());

		return result;
	}
}
