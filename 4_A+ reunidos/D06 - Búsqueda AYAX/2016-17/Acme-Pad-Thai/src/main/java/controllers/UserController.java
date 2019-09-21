package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;


@Controller
@RequestMapping("/user")
public class UserController extends AbstractController{
	
	@Autowired
	private UserService userService;
	
	//Listing --------------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<User> users;
		
		users = userService.findAll();

		result = new ModelAndView("user/list");
		result.addObject("users", users);
		result.addObject("requestURI", "user/list.do");

		return result;
	}
	
	//ShowUser --------------------------------------------------------------------
	
	@RequestMapping(value = "/showUser", method = RequestMethod.GET)
	public ModelAndView showItem(@RequestParam int userId) {
		ModelAndView result;
		User user;
	
		user = userService.findOne(userId);
		
		result = new ModelAndView("user/showUser");
		result.addObject("user", user);

		return result;
	}
	
	//SearchForm ==============================================================================
	
	@RequestMapping(value = "/searchForm", method = RequestMethod.GET)
	public ModelAndView listSearch(){
		ModelAndView result;
		Collection<User> users;
		Boolean firstTime;
		
		users = userService.findAll();
		firstTime = true;
		
		result = new ModelAndView("user/search");
		result.addObject("users", users);
		result.addObject("requestURI", "user/searchForm.do");
		result.addObject("firstTime", firstTime);

		return result;
	}
	
	
	//Search ==============================================================================	
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ModelAndView search(@RequestParam("keyword") String keyword){
		ModelAndView result;
		Collection<User> users;
		
		users = userService.getUserByKeyWord(keyword);
		
		result = new ModelAndView("user/search");
		result.addObject("users", users);

		return result;
	}
}
