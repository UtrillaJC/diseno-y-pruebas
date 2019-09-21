package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PersonService;
import domain.Person;

@Controller
@RequestMapping("/person")
public class PersonController extends AbstractController{

	//Services ===============================================================================
	
	@Autowired
	private PersonService personService;
	
	//Constructor ============================================================================
	
	public PersonController(){
		super();
	}
	
	
	//List ============================================================================
	
	// List ============================================================================
	
	@RequestMapping("/list-follow")
	public ModelAndView listFollow() {
		ModelAndView result;
		Collection<Person> persons;
		boolean following;
		
		following = true;
		persons = personService.findAllByPerson();
		
		result = new ModelAndView("person/list");
		result.addObject("persons", persons);
		result.addObject("following", following);
		result.addObject("requestURI", "person/list-follow.do");
		
		return result;
	}
	
	@RequestMapping("/list-unfollow")
	public ModelAndView listUnfollow() {
		ModelAndView result;
		Collection<Person> persons;
		boolean following;
		
		following = false;
		persons = personService.findAllByNotPerson();
		
		result = new ModelAndView("person/list");
		result.addObject("persons", persons);
		result.addObject("following", following);
		result.addObject("requestURI", "person/list-unfollow.do");
		
		return result;
	}

	// Follow -----------------------------------------------------------
	
	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam int personId) {
		ModelAndView result;		
				
		try {			
			personService.follow(personId);
			result = listUnfollow();
			result.addObject("message", "person.commit.ok");			
		} catch (Throwable oops) {			
			result = listUnfollow();
			result.addObject("message", "person.commit.error");			
		}
		
		return result;
	}
	
	@RequestMapping(value = "/unfollow", method = RequestMethod.GET)
	public ModelAndView unregister(@RequestParam int personId) {
		ModelAndView result;		
				
		try {
			personService.unfollow(personId);
			result = listFollow();			
			result.addObject("message", "person.commit.ok");
		} catch (Throwable oops) {
			result = listFollow();			
			result.addObject("message", "person.commit.error");			
		}
		
		return result;
	}
	
	// List ============================================================================
	
	@RequestMapping("/list-followNutritionist")
	public ModelAndView listFollowN() {
		ModelAndView result;
		Collection<Person> persons;
		boolean following;
		
		following = true;
		persons = personService.findAllByPerson();
		
		result = new ModelAndView("person/list");
		result.addObject("persons", persons);
		result.addObject("following", following);
		result.addObject("requestURI", "person/list-followN.do");
		
		return result;
	}
	
	@RequestMapping("/list-unfollowNutritionist")
	public ModelAndView listUnfollowN() {
		ModelAndView result;
		Collection<Person> persons;
		boolean following;
		
		following = false;
		persons = personService.findAllByNotPerson();
		
		result = new ModelAndView("person/list");
		result.addObject("persons", persons);
		result.addObject("following", following);
		result.addObject("requestURI", "person/list-unfollowN.do");
		
		return result;
	}

	// Follow -----------------------------------------------------------
	
	@RequestMapping(value = "/followNutritionist", method = RequestMethod.GET)
	public ModelAndView registerN(@RequestParam int personId) {
		ModelAndView result;		
				
		try {			
			personService.follow(personId);
			result = listUnfollow();
			result.addObject("message", "person.commit.ok");			
		} catch (Throwable oops) {			
			result = listUnfollow();
			result.addObject("message", "person.commit.error");			
		}
		
		return result;
	}
	
	@RequestMapping(value = "/unfollowNutritionist", method = RequestMethod.GET)
	public ModelAndView unregisterN(@RequestParam int personId) {
		ModelAndView result;		
				
		try {
			personService.unfollow(personId);
			result = listFollow();			
			result.addObject("message", "person.commit.ok");
		} catch (Throwable oops) {
			result = listFollow();			
			result.addObject("message", "person.commit.error");			
		}
		
		return result;
	}
}
