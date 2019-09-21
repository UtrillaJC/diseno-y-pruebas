/*
 * CustomerController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.auditor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuditorService;
import services.PropertyService;
import controllers.AbstractController;
import domain.Auditor;
import domain.Property;

@Controller
@RequestMapping("/property/auditor")
public class PropertyAuditorController extends AbstractController {

	// Services -------------------------------------------------------------------

	@Autowired
	private PropertyService propertyService;
	@Autowired
	private AuditorService auditorService;


	// Constructors ---------------------------------------------------------------

	public PropertyAuditorController() {
		super();
	}

	//List --------------------------------------------------------------------
	
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			List<Property> properties;

			properties = (List<Property>) propertyService.findAll();
			
			Collections.sort(properties, new Comparator<Property>(){
				public int compare(final Property propertyA, Property propertyB){
					return propertyB.getRequests().size() - propertyA.getRequests().size();
				}
			});

			result = new ModelAndView("property/list");
			result.addObject("properties", properties);
			result.addObject("requestURI", "property/list.do");
			Auditor a = auditorService.findByPrincipal();
			result.addObject("done", propertyService.findByAuditorAudits(a.getId()));
			
		return result;
		
		} 
	// Create methods --------------------------------------------------------------

	

}
