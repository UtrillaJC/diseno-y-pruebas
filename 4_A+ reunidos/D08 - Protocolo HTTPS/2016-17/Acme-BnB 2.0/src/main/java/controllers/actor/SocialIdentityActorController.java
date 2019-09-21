package controllers.actor;
import java.util.Collection;

import javax.validation.Valid;
import javax.xml.bind.ParseConversionEvent;

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
import domain.Actor;
import domain.SocialIdentity;
import services.ActorService;
import services.SocialIdentityService;

@Controller
@RequestMapping("socialidentity")
public class SocialIdentityActorController extends AbstractController {

	@Autowired
	private SocialIdentityService socialIdentityService;

	@Autowired
	private ActorService actorService;

	public SocialIdentityActorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Actor actor = actorService.findByPrincipal();

		Collection<SocialIdentity> socialIdentitys = socialIdentityService.findAllSocialIdentityByActor(actor.getId());

		result = new ModelAndView("socialidentity/list");

		result.addObject("socialIdentitys", socialIdentitys);
		result.addObject("requestURI", "socialidentity/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView m;

		SocialIdentity si = socialIdentityService.create();

		m = createModelAndView(si, "create", false, null);

		return m;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView create( @RequestParam String si_id) {

		ModelAndView m;
		try{
				
		SocialIdentity si = socialIdentityService.findOne(new Integer(si_id));
		Assert.isTrue(si.getActor().equals(actorService.findByPrincipal()));
		m = createModelAndView(si, "edit", false, null);
		}catch (Exception e) {
			m = new ModelAndView("redirect:../actor/display.do");
		}
		return m;
	}
	
	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute("socialidentity") @Valid SocialIdentity si) {

		ModelAndView m = null;

		
			
			socialIdentityService.deleteSocialIdentityToActor(si.getId());
			m = new ModelAndView("redirect:../actor/display.do");
		

		return m;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("socialidentity") @Valid SocialIdentity si, BindingResult binding) {

		ModelAndView m = null;
		boolean error = false;

		if (binding.hasErrors()) {
			error = true;
			m = createModelAndView(si, "edit", error, null);
		} else {
			try {
				socialIdentityService.assignSocialIdentityToActor(si);
				m = new ModelAndView("redirect:../actor/display.do");
			} catch (Throwable e) {
				m = createModelAndView(si, "edit", false, "socialidentity.commit.error");
			}
		}

		return m;

	}

	protected ModelAndView createModelAndViewAndShowMessage() {

		ModelAndView result;

		result = new ModelAndView("register/register");
		result.addObject("show", true);

		return result;

	}

	protected ModelAndView createModelAndView(SocialIdentity si, String action, boolean error, String message) {

		ModelAndView result = null;
		if (action.equals("edit")) {
			result = new ModelAndView("socialidentity/edit");
			result.addObject("message", message);
			result.addObject("socialidentity", si);
			result.addObject("error", error);

		} else if (action.equals("create")) {
			result = new ModelAndView("socialidentity/create");
			result.addObject("socialidentity", si);

		}

	

		return result;
	}

}
