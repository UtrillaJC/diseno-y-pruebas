package controllers.actor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Message;

@Controller
@RequestMapping("/message/actor")
public class MessageActorController extends AbstractController {
	
	// Services ============================================================================

	@Autowired
	private MessageService	messageService;

	@Autowired
	private ActorService actorService;

	// Constructors ========================================================================

	public MessageActorController() {
		super();
	}
	
	//Show message ============================================================

	@RequestMapping(value = "/showMessage", method = RequestMethod.GET)
	public ModelAndView showMessage(@RequestParam int messageId) {
		ModelAndView result;
		Message row;

		row = messageService.findOne(messageId);

		result = new ModelAndView("message/actor/showMessage");
		result.addObject("row", row);

		return result;
	}
	
	//List my sendMessages ========================================================================================
	
	@RequestMapping(value = "/listSendMessages", method = RequestMethod.GET)
	public ModelAndView listSendMessages() {
		ModelAndView result;
		Collection<Message> messages;
		boolean ownerReply;
		boolean ownerForward;
		
		ownerReply = false;
		ownerForward = true;
		messages = messageService.findSendMessagesByActor();

		result = new ModelAndView("message/actor/list");
		result.addObject("messages", messages);
		result.addObject("requestURI", "message/actor/listSendMessages.do");
		result.addObject("ownerReply", ownerReply);
		result.addObject("ownerForward", ownerForward);


		return result;
	}
	
	@RequestMapping(value = "/listReceivedMessages", method = RequestMethod.GET)
	public ModelAndView listReceivedMessages() {
		ModelAndView result;
		Collection<Message> messages;
		boolean ownerReply;
		boolean ownerForward;
		
		ownerReply = true;
		ownerForward = false;
		messages = messageService.findReceivedMessagesByActor();

		result = new ModelAndView("message/actor/list");
		result.addObject("messages", messages);
		result.addObject("requestURI", "message/actor/listReceivedMessages.do");
		result.addObject("ownerReply", ownerReply);
		result.addObject("ownerForward", ownerForward);


		return result;
	}

	//Create ===========================================================================================
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Message messageSender;
		Actor sender;
		Collection<Actor> actors;

		sender = actorService.findByPrincipal();
		messageSender = messageService.create();
		actors = actorService.findAll();
		actors.remove(sender);
		
		result = new ModelAndView("message/actor/edit");

		result.addObject("messageSender", messageSender);
		result.addObject("actors", actors);

		return result;
	}
	
	//Reply ===========================================================================================

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public ModelAndView reply(@RequestParam int messageId) {
		ModelAndView result;
		Message messageSender;

		messageSender = messageService.createReply(messageId);

		
		result = new ModelAndView("message/actor/editReply");

		result.addObject("messageSender", messageSender);

		return result;
	}
	
	//Forward ===========================================================================================

	@RequestMapping(value = "/forward", method = RequestMethod.GET)
	public ModelAndView forward(@RequestParam int messageId) {
		ModelAndView result;
		Message messageSender;
		Actor sender;
		Collection<Actor> actors;

		sender = actorService.findByPrincipal();
		messageSender = messageService.createForward(messageId);
		actors = actorService.findAll();
		actors.remove(sender);
		
		result = new ModelAndView("message/actor/edit");
		result.addObject("messageSender", messageSender);
		result.addObject("actors", actors);

		return result;
	}
	
	
	//Save ===========================================================================================
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("messageSender") @Valid Message messageSender, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(messageSender);
		} else {
			try {
				messageSender.setRecipientCopy(false);
				messageService.save(messageSender);
				messageSender.setRecipientCopy(true);
				messageService.save(messageSender);

				result = new ModelAndView("redirect:listSendMessages.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(messageSender, "messageSender.commit.error");
			}
		}
		return result;

	}
	
	//Delete ===========================================================================================
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Message row, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(row);
		} else {
			try {
				messageService.delete(row);

				result = new ModelAndView("redirect:listSendMessages.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(row, "messageSender.commit.error");
			}
		}
		return result;

	}
	
	// Ancillary methods: Create ===========================================================================================
	
	protected ModelAndView createEditModelAndView(Message messageSender) {
		ModelAndView result;
		result = createEditModelAndView(messageSender, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(Message messageSender, String message) {
		ModelAndView result;
		Collection<Actor> actors;
		Actor sender;

		sender = actorService.findByPrincipal();
		actors = actorService.findAll();
		actors.remove(sender);
		

		result = new ModelAndView("message/actor/edit");

		result.addObject("actors", actors);
		result.addObject("messageSender", messageSender);
		result.addObject("message", message);
		
		return result;
	}
	
}
