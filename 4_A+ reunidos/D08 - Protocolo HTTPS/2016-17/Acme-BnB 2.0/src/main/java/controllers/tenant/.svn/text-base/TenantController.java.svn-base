///*
// * CustomerController.java
// *
// * Copyright (C) 2013 Universidad de Sevilla
// * 
// * The use of this project is hereby constrained to the conditions of the
// * TDG Licence, a copy of which you may download from
// * http://www.tdg-seville.info/License.html
// * 
// */
//
//package controllers.tenant;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import domain.Tenant;
//import services.TenantService;
//
//@Controller
//@RequestMapping("/tenant")
//public class TenantController extends AbstractController {
//
//	// Services -------------------------------------------------------------------
//
//	@Autowired
//	private TenantService tenantService;
//
//
//	// Constructors ---------------------------------------------------------------
//
//	public TenantController() {
//		super();
//	}
//
//	// Listing methods -----------------------------------------------------------
//
//	// Create methods --------------------------------------------------------------
//
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView edit() {
//		ModelAndView result;
//		Tenant tenant;
//
//		tenant = tenantService.findByPrincipal();
//		result = editModelAndView(tenant);
//
//		return result;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
//	public ModelAndView update(@ModelAttribute("tenant") @Valid Tenant tenant, BindingResult binding) {
//
//		ModelAndView result;
//		Tenant tenantUpdate;
//
//		if (binding.hasErrors()) {
//			result = editModelAndView(tenant);
//		} else {
//			try {
//				tenantUpdate = tenantService.findByPrincipal();
//				tenantUpdate.setName(tenant.getName());
//				tenantUpdate.setSurname(tenant.getSurname());
//				tenantUpdate.setEmail(tenant.getEmail());
//				tenantUpdate.setPhone(tenant.getPhone());
//				tenantUpdate.setPicture(tenant.getPhone());
//				tenantService.update(tenant);
//				result = new ModelAndView("redirect:../welcome/index.do");
//
//			} catch (Throwable oops) {
//				result = editModelAndView(tenant, "edit.commit.error");
//			}
//		}
//		return result;
//
//	}
//
//	// Ancillary methods ---------------------------------------------------------
//
//	protected ModelAndView editModelAndView(Tenant tenant) {
//
//		ModelAndView result;
//
//		result = editModelAndView(tenant, null);
//
//		return result;
//	}
//
//	protected ModelAndView editModelAndView(Tenant tenant, String message) {
//
//		ModelAndView result;
//
//		result = new ModelAndView("tenant/edit");
//		result.addObject("tenant", tenant);
//		result.addObject("message", message);
//
//		return result;
//	}
//
//}
