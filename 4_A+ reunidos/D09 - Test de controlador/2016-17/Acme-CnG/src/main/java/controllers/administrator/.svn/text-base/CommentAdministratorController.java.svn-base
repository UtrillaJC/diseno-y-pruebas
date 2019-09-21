
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("/comment/administrator")
public class CommentAdministratorController extends AbstractController {

	// Services ============================================================================

	@Autowired
	private CommentService			commentService;



	// Constructors ========================================================================

	public CommentAdministratorController() {
		super();
	}

	//List All Comments--------------------------------------------------------------------

	@RequestMapping(value = "/listAllComments", method = RequestMethod.GET)
	public ModelAndView listAllComments() {
		ModelAndView result;
		Collection<Comment> comments;
	

		comments = this.commentService.findAll();

		result = new ModelAndView("comment/administrator/listAllComments");
		result.addObject("comments", comments);
		result.addObject("requestURI", "comment/administrator/listAllComments.do");

		return result;

	}
	
	//Ban comment--------------------------------------------------------------------

	@RequestMapping(value= "/banComment", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam int commentId){
		ModelAndView result;
		
			commentService.banComment(commentId);
			result = new ModelAndView("redirect:/comment/administrator/listAllComments.do");

		
		return result;
	}

	
	

	// Ancillary Methods============================================================		

	protected ModelAndView createEditModelAndView(Comment comment) {
		assert comment != null;

		ModelAndView result;

		result = this.createEditModelAndView(comment, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(Comment comment, String message) {
		assert comment != null;
		ModelAndView result;
		Collection<Comment> comments;

		comments = this.commentService.findAll();

		result = new ModelAndView("comment/administrator/listAllComments");
		result.addObject("comment", comment);
		result.addObject("message", message);
		result.addObject("comments", comments);

		return result;
	}

}
