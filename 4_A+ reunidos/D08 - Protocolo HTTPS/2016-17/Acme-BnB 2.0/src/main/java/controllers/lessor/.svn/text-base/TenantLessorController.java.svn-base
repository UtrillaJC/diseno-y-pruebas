package controllers.lessor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.RequestService;
import controllers.AbstractController;
import domain.Comment;
import domain.Request;
import domain.SocialIdentity;
import domain.Tenant;

@Controller
@RequestMapping("/tenant/lessor")
public class TenantLessorController extends AbstractController{
	
	// Services -------------------------------------------------------------------

		@Autowired
		private CommentService commentService;
		
		@Autowired
		private RequestService requestService;

		// Constructors ---------------------------------------------------------------

		public TenantLessorController() {
			super();
		}

		// Listing methods -----------------------------------------------------------

		//ShowUser --------------------------------------------------------------------

		@RequestMapping(value = "/showTenant", method = RequestMethod.GET)
		public ModelAndView showTenant(@RequestParam int requestId) {
			ModelAndView result;
			Tenant tenant;
			Collection<Comment> comments;
			Collection<SocialIdentity> socialIdentities;
			Request request = this.requestService.findOne(requestId);

			tenant = request.getTenant();
			comments = this.commentService.findByTenantId(tenant.getId());
			socialIdentities = tenant.getSocialIdentities();

			result = new ModelAndView("tenant/lessor/showTenant");
			result.addObject("tenant", tenant);
			result.addObject("commentsTenant", comments);
			result.addObject("socialIdentitiesTenant", socialIdentities);

			return result;
		}

}
