/*
 * AbstractController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AbstractController {

	// Panic handler ----------------------------------------------------------

	@ExceptionHandler(Throwable.class)
	public ModelAndView panic(final Throwable oops) {

		final ModelAndView result;

		result = new ModelAndView("redirect:/");

		//		result = new ModelAndView("misc/panic");
		//		result.addObject("name", ClassUtils.getShortName(oops.getClass()));
		//		result.addObject("exception", oops.getMessage());
		//		result.addObject("stackTrace", ExceptionUtils.getStackTrace(oops));

		return result;

	}

}
