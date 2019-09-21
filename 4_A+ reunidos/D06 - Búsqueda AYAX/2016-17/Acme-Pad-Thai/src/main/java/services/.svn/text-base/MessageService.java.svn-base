package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;
import domain.SpamTerm;

@Service
@Transactional
public class MessageService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private MessageRepository messageRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private SpamTermService spamTermService;
	
	//Constructor methods ============================================================================
	
	public MessageService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Message findOne(int messageId) {
		Message result;
		Actor principal;
			
		principal = actorService.findByPrincipal();

		result = messageRepository.findOne(messageId);
			
		if(result != null) {
			Assert.isTrue(principal.getUserAccount().equals(result.getRecipient()) || principal.getUserAccount().equals(result.getSender()));
		}
			
		return result;
	}
	
	public Message findMessage(int messageId) {
		Message result;
		Actor principal;
		
		principal = actorService.findByPrincipal();

		result = messageRepository.findOne(messageId);
		
		Assert.isTrue(result.getRecipient().equals(principal.getUserAccount()) || result.getSender().equals(principal.getUserAccount()));

		return result;
	}
	
	public Message create(Actor sender) {
		Assert.notNull(sender);
		Assert.isTrue(actorService.findByPrincipal().equals(sender));
		Message result;
		Folder folder;
		Date currentMoment;

		currentMoment = new Date(System.currentTimeMillis());
		folder = new Folder();
		result = new Message();
		folder = folderService.findActorOutboxByUserAccount(sender.getUserAccount());

		result.setSender(sender.getUserAccount());
		result.setFolder(folder);
		result.setMoment(currentMoment);

		return result;
	}
	
	public Message save(Message message) {
		Assert.notNull(message);
		Assert.notNull(message.getSender());
		Assert.isTrue(message.getPriority().toUpperCase().equals("LOW") || message.getPriority().toUpperCase().equals("NEUTRAL") || message.getPriority().toUpperCase().equals("HIGH"));
		Assert.isTrue(!message.getSender().equals(message.getRecipient()));
		Message result;
		Collection<SpamTerm> spamTerms;
		Actor principal;
		Folder inbox;
		Date moment;
		String subject;
		String body;
		boolean spam = false;
			
		principal = actorService.findByPrincipal();
		moment = new Date(System.currentTimeMillis()-1000);
				
		message.setMoment(moment);
			
		if(message.getId() == 0) {
			Assert.isTrue(principal.getUserAccount().equals(message.getSender()));
			spamTerms = spamTermService.findAll();
			subject = message.getSubject().toLowerCase();
			body = message.getBody().toLowerCase();
			
			for(SpamTerm term : spamTerms) {
				for(String keyword : term.getKeywords()){
					if(subject.contains(keyword.toLowerCase()) || body.contains(keyword.toLowerCase())) {
						spam = true;
						break;
					}
				}
				
			}
			
			if(spam) {
				inbox = folderService.findActorSpamboxByUserAccount(message.getRecipient());
			} else {
				inbox = folderService.findActorInboxByUserAccount(message.getRecipient());
			}
				
			result = messageRepository.save(message);
			
			message.setFolder(inbox);
				
			result = messageRepository.save(message);
		} else {
			Assert.isTrue(principal.getUserAccount().equals(message.getSender()) || principal.getUserAccount().equals(message.getRecipient()));
			result = messageRepository.save(message);
		}
			
		return result;
	}
		
	public void delete(Message message){
		Assert.notNull(message);
		Actor principal;
		Folder folder;
			
		principal = actorService.findByPrincipal();
			
		Assert.isTrue(principal.getUserAccount().equals(message.getSender()) || principal.getUserAccount().equals(message.getRecipient()));
			
		folder = message.getFolder();
			
		if(folder.getName().toLowerCase().equals("trashbox")){
			messageRepository.delete(message);
		}else{
			Folder trashbox = folderService.findActorTrashboxByUserAccount(principal.getUserAccount());
			moveMessageToFolder(message, folder, trashbox);
		}
	}
	
	//Other Business Methods =========================================================================

	public Collection<Message> findByFolder(Folder folder) {
		Assert.notNull(folder);
		Assert.isTrue(actorService.findByPrincipal().getUserAccount().equals(folder.getUserAccount()));
		Collection<Message> result;

		result = messageRepository.findByFolderId(folder.getId());

		return result;
	}
		
	public void moveMessageToFolder(Message message, Folder sourceFolder, Folder targetFolder) {
		Assert.notNull(message);
		Assert.notNull(targetFolder);
		Assert.notNull(sourceFolder);
			
		Assert.isTrue(!targetFolder.equals(sourceFolder));
		Assert.isTrue(sourceFolder.getMessages().contains(message));
		Assert.isTrue(!targetFolder.getMessages().contains(message));
			
		Actor principal;
		principal = actorService.findByPrincipal();
		Assert.notNull(principal);

		Assert.isTrue(message.getSender().equals(principal.getUserAccount()) || message.getRecipient().equals(principal.getUserAccount()));
		Assert.isTrue(targetFolder.getUserAccount().equals(principal.getUserAccount()));
		Assert.isTrue(sourceFolder.getUserAccount().equals(principal.getUserAccount()));

		message.setFolder(targetFolder);

		save(message);
	}
}
