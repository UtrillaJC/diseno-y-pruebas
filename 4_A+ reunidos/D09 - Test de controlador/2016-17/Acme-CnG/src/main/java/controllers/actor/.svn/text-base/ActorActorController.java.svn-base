package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import controllers.AbstractController;
import domain.Actor;

@Controller
@RequestMapping("/actor/actor")
public class ActorActorController extends AbstractController{
	
	// Services -------------------------------------------------------------------

		@Autowired
		private ActorService	actorService;


		// Constructors ---------------------------------------------------------------

		public ActorActorController() {
			super();
		}

		// CRUD methods -----------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Actor> actors;

		actors = this.actorService.findAll();

		result = new ModelAndView("actor/actor/list");
		result.addObject("actors", actors);
		result.addObject("requestURI", "actors/actor/list.do");

		return result;

	}

}
