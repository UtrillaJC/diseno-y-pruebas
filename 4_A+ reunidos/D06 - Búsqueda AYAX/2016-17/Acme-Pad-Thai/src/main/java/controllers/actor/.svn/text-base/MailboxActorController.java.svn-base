package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Folder;
import domain.Message;

import services.ActorService;
import services.FolderService;
import services.MessageService;

@Controller
@RequestMapping("/mailbox/actor")
public class MailboxActorController {

	//Services =======================================================================================
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;
	
	//Constructors =======================================================================================
	
	public MailboxActorController(){
		super();
	}
	
	//Mailbox ========================================================================================
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Message> messages;
		Collection<Folder> folders;
		Folder inbox;
		Actor actor;
		String folderTitle;
		int sourceFolderId;
		
		actor = actorService.findByPrincipal();
		folders = folderService.findAllByUserAccount(actor.getUserAccount());
		inbox = folderService.findActorInboxByUserAccount(actor.getUserAccount());
		messages = messageService.findByFolder(inbox);

		folderTitle = inbox.getName();
		sourceFolderId = inbox.getId();

		result = new ModelAndView("mailbox/main");
		result.addObject("folders", folders);
		result.addObject("messages", messages);
		result.addObject("folderTitle", folderTitle);
		result.addObject("requestURI", "mailbox/actor/list.do");
		result.addObject("sourceFolderId", sourceFolderId);

		return result;

	}
	
	@RequestMapping(value = "/list2", method = RequestMethod.GET)
	public ModelAndView list2(@RequestParam int folderId) {
		ModelAndView result;
		Collection<Message> messages;
		Collection<Folder> folders;
		Folder actualFolder;
		Actor actor;
		String folderTitle;
		int sourceFolderId;
		
		actor = actorService.findByPrincipal();
		folders = folderService.findAllByUserAccount(actor.getUserAccount());
		actualFolder = folderService.findOneByPrincipal(folderId);
		messages = messageService.findByFolder(actualFolder);
		folderTitle = actualFolder.getName();
		sourceFolderId = folderId;

		result = new ModelAndView("mailbox/main");
		result.addObject("folders", folders);
		result.addObject("messages", messages);
		result.addObject("folderTitle", folderTitle);
		result.addObject("requestURI", "mailbox/actor/list2.do");
		result.addObject("sourceFolderId", sourceFolderId);
		
		return result;

	}
}
