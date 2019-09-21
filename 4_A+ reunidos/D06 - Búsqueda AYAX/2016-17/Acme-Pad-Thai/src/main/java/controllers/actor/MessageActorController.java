package controllers.actor;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.ActorService;
import services.FolderService;
import services.MessageService;
import services.UserAccountService;
import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Controller
@RequestMapping("/message/actor")
public class MessageActorController extends AbstractController {

	// Services =======================================================================================
	
	@Autowired
	private MessageService messageService;

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private UserAccountService userAccountService;

	// Constructors =======================================================================================
	
	public MessageActorController() {
		super();
	}
	
	//Show message ===========================================================================================
	
	@RequestMapping(value = "/showMessage", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int messageId) {
		ModelAndView result;
		Message message;

		message = messageService.findMessage(messageId);
		
		result = new ModelAndView("message/actor/showMessage");

		result.addObject("men", message);

		return result;
	}
	
	//Create ===========================================================================================
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Message message;
		Actor sender;
		Collection<UserAccount> actors;
		Collection<String> priorities;

		priorities = new HashSet<String>();
		sender = actorService.findByPrincipal();
		message = messageService.create(sender);
		actors = userAccountService.findAll();
		actors.remove(sender.getUserAccount());

		priorities.add("HIGH");
		priorities.add("NEUTRAL");
		priorities.add("LOW");
		
		result = new ModelAndView("message/actor/edit");

		result.addObject("message", message);
		result.addObject("actors", actors);
		result.addObject("priorities", priorities);
		result.addObject("requestURI", "message/actor/create.do");

		return result;
	}
	
	
	//Save ===========================================================================================
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Message message, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(message);
		} else {
			try {
				messageService.save(message);

				result = new ModelAndView("redirect:/mailbox/actor/list.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(message, "message.commit.error");
			}
		}
		return result;

	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Message message, BindingResult binding) {
		ModelAndView result;

		try {
			messageService.delete(message);
			result = new ModelAndView("redirect:/mailbox/actor/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(message, "message.commit.error");
		}

		return result;
	}
	
	@RequestMapping(value = "/moveTo", method = RequestMethod.GET)
	public ModelAndView moveMessageToFolder(@RequestParam int targetFolderId,
			@RequestParam int messageId, @RequestParam int sourceFolderId) {
		ModelAndView result;
		Folder targetFolder;
		Folder sourceFolder;
		Message message;

		targetFolder = folderService.findOneByPrincipal(targetFolderId);
		sourceFolder = folderService.findOneByPrincipal(sourceFolderId);
		message = messageService.findMessage(messageId);

		messageService.moveMessageToFolder(message, sourceFolder, targetFolder);
		result = new ModelAndView("redirect:/mailbox/actor/list.do");

		return result;

	}
	
	// Ancillary methods: Create ===========================================================================================
	
	protected ModelAndView createEditModelAndView(Message message) {
		ModelAndView result;
		result = createEditModelAndView(message, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(Message message, String errorMessage) {
		ModelAndView result;
		Collection<Actor> actors;
		Actor sender;
		Collection<String> priorities;

		priorities = new HashSet<String>();
		sender = actorService.findByPrincipal();
		actors = actorService.findAll();
		actors.remove(sender);
		
		priorities.add("HIGH");
		priorities.add("NEUTRAL");
		priorities.add("LOW");

		result = new ModelAndView("message/actor/edit");

		result.addObject("actors", actors);
		result.addObject("priorities", priorities);
		result.addObject("message", message);
		result.addObject("requestURI", "message/actor/create.do");
		result.addObject("errorMessage", errorMessage);
		
		return result;
	}
	
}
