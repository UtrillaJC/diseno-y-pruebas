
package controllers.actor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CommentClassService;
import services.CommentService;
import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;
import domain.Comment;
import domain.CommentClass;
import domain.Customer;

@Controller
@RequestMapping("/comment/actor")
public class CommentActorController extends AbstractController {

	// Services -------------------------------------------------------------------

	@Autowired
	private CommentService	commentService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private CommentClassService	commentClassService;
	// Constructors ---------------------------------------------------------------

	public CommentActorController() {
		super();
	}

	// CRUD methods -----------------------------------------------------------

	//List --------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int commentClassId) {
		ModelAndView result;
		Collection<Comment> comments = new ArrayList<Comment>();
		Actor principal = this.actorService.findByPrincipal();

		if(principal instanceof Administrator){
			comments = this.commentService.findByCommentClassId(commentClassId);
		}else if(principal instanceof Customer){
			comments = this.commentService.findByCommentClassId(commentClassId);
			Iterator<Comment> iter = comments.iterator();

			while (iter.hasNext()) {
				Comment com = iter.next();
				if(com.getAuthor().getId()!=principal.getId() && com.isBanned()==true){
					iter.remove();
				}
			}
		}

		result = new ModelAndView("comment/actor/list");
		result.addObject("comments", comments);
		result.addObject("requestURI", "comment/actor/list.do");

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int commentClassId) {

		ModelAndView result;
		Comment comment;

		comment = this.commentService.create(commentClassId);
		result = this.createEditModelAndView(comment);

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Comment comment, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(comment);
		else
			try {
				Comment commentSaved = this.commentService.save(comment);
				CommentClass receiver = commentClassService.findOne(commentSaved.getReceiverId());
				
				receiver.getReceivedComments().add(commentSaved);
				commentClassService.save(receiver);
				
				result = new ModelAndView("redirect:../../welcome/index.do");

			} catch (Throwable oops) {
				result = this.createEditModelAndView(comment, "comment.commit.error");
			}

		return result;
	}

	// Ancillary methods ---------------------------------------------------------

	protected ModelAndView createEditModelAndView(Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Comment comment, String message) {
		ModelAndView result;

		result = new ModelAndView("comment/actor/create");
		result.addObject("comment", comment);
		result.addObject("message", message);

		return result;
	}

}
