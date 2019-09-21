package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MasterClassService;
import domain.MasterClass;

@Controller
@RequestMapping("/masterClass")
public class MasterClassController extends AbstractController{

	//Services ===============================================================================
	
	@Autowired
	private MasterClassService masterClassService;
	
	//Constructor ============================================================================
	
	public MasterClassController(){
		super();
	}
	
	
	//List
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<MasterClass> masterClasses;
		
		masterClasses = masterClassService.findAll();

		result = new ModelAndView("masterClass/list");
		result.addObject("masterClasses", masterClasses);
		result.addObject("requestURI","masterClass/list.do");
		
		return result;
	}
		
	}