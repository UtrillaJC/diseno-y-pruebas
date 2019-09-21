package controllers.auditor;

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

import controllers.AbstractController;
import domain.Audit;
import domain.Auditor;
import services.AuditService;
import services.AuditorService;

@Controller
@RequestMapping("/audit/auditor")
public class AuditAuditorController extends AbstractController {

	@Autowired
	private AuditService auditService;
	
	@Autowired
	private AuditorService auditorService;

	public AuditAuditorController() {
		super();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int propertyId) {
		ModelAndView result;
		Collection<Audit> audits;
		Auditor a = auditorService.findByPrincipal();
		audits = auditService.findByPropertyAndAuditor(propertyId,a.getId());

		result = new ModelAndView("audit/auditor/list");
		result.addObject("audits", audits);
		result.addObject("requestURI", "audit/auditor/list.do");

	return result;
	
	} 

	

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int propertyId) {

		ModelAndView m;

		Audit audit = auditService.create(propertyId);

		m = createEditModelAndView(audit);

		return m;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam String id) {

		ModelAndView m;
		Audit audit;

		try {
			audit = auditService.findOne(new Integer(id));
			Assert.notNull(audit);
			Assert.isTrue(audit.isDraft());
			m = createEditModelAndView(audit);
		} catch (Throwable e) {
			m = new ModelAndView("redirect:list.do?propertyId="+
					this.auditService.findOne(new Integer(id)).getProperty().getId());
		}

		return m;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Audit audit, BindingResult binding) {

		ModelAndView m;

		if (binding.hasErrors()) {
			m = createEditModelAndView(audit);
		} else {
			try {
				auditService.save(audit);
				m = new ModelAndView("redirect:list.do?propertyId="+audit.getProperty().getId());
			} catch (Throwable e) {

				m = createEditModelAndView(audit, "audit.commit.error");
			}
		}

		return m;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveAsDraft")
	public ModelAndView saveAsDraft(@Valid Audit audit, BindingResult binding) {

		ModelAndView m;

		if (binding.hasErrors()) {
			m = createEditModelAndView(audit);
		} else {
			try {
				auditService.saveAsDraft(audit);
				m = new ModelAndView("redirect:list.do?propertyId="+audit.getProperty().getId());
			} catch (Throwable e) {

				m = createEditModelAndView(audit, "audit.commit.error");
			}
		}

		return m;
	}
	
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Audit object, BindingResult binding) {

		ModelAndView m;

		try {
			
			auditService.delete(object);
			m = new ModelAndView("redirect:list.do?propertyId="+object.getProperty().getId());
		} catch (Throwable e) {

			m = createEditModelAndView(object, "audit.delete.error");
		}

		return m;
	}


	protected ModelAndView createEditModelAndView(Audit audit) {

		ModelAndView m;

		m = createEditModelAndView(audit, null);

		return m;
	}

	protected ModelAndView createEditModelAndView(Audit audit, String message) {

		ModelAndView m;

		m = new ModelAndView("audit/auditor/edit");

		m.addObject("audit", audit);
		m.addObject("message", message);

		return m;
	}
}