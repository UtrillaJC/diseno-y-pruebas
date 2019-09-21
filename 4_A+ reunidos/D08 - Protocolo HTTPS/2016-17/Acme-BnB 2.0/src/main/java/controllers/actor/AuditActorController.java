package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import controllers.AbstractController;
import domain.Audit;

@Controller
@RequestMapping("/audit/actor")
public class AuditActorController extends AbstractController{
	
	// Services -------------------------------------------------------------------

		@Autowired
		private AuditService auditService;


		// Constructors ---------------------------------------------------------------

		public AuditActorController() {
			super();
		}

		//List --------------------------------------------------------------------
		
			@RequestMapping(value = "/list", method = RequestMethod.GET)
			public ModelAndView list(@RequestParam int propertyId) {
				ModelAndView result;
				Collection<Audit> audits;

				audits = this.auditService.findNoDraftsByPropertyId(propertyId);

				result = new ModelAndView("audit/actor/list");
				result.addObject("audits", audits);
				result.addObject("requestURI", "audit/actor/list.do");

			return result;
			
			} 

}
