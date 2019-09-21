package services;


import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Message;

@Service
@Transactional
public class MessageService {

	//Managed Repository =============================================================================

	@Autowired
	public MessageRepository messageRepository;
	
	//Supported Services ============================================================================

	@Autowired
	public ActorService actorService;

	@Autowired
	public AdministratorService administratorService;

	//Constructor methods ============================================================================

	public MessageService(){
		super();
	}

	// Simple CRUD methods ============================================================================

	public Message findOne(int messageId){
		Message result;
		
		result = messageRepository.findOne(messageId);
		
		return result;
	}
	
	public Message create(){
		Message result;
		Actor principal;
		Date momentActual;
		
		principal = actorService.findByPrincipal();
		momentActual = new Date(System.currentTimeMillis());
		result = new Message();
		
		result.setCreatedMoment(momentActual);
		result.setSender(principal);
		
		return result;
	}
	
	public Message save(Message message){
		Message result;
		Date momentActual;
		
		momentActual = new Date(System.currentTimeMillis() - 1000);
		message.setCreatedMoment(momentActual);
		
		result = messageRepository.saveAndFlush(message);
		
		return result;
	}
	
	public void delete(Message message){
		Assert.notNull(message);
		Actor principal;
			
		principal = actorService.findByPrincipal();
			
		Assert.isTrue(principal.equals(message.getSender()) || principal.equals(message.getRecipient()));
			
		messageRepository.delete(message);

	}

	// Other business methods ============================================================================
	
	public Message createReply(int messageId){
		Message result;
		Message message;
		Actor principal;
		Date momentActual;
		
		principal = actorService.findByPrincipal();
		message = findOne(messageId);
		Assert.isTrue(message.getRecipient().equals(principal));
		momentActual = new Date(System.currentTimeMillis());
		
		result = new Message();
		
		result.setCreatedMoment(momentActual);
		result.setSender(principal);
		result.setRecipient(message.getSender());
		
		return result;
	}
	
	public Message createForward(int messageId){
		Message result;
		Message message;
		Actor principal;
		Date momentActual;
		
		principal = actorService.findByPrincipal();
		message = findOne(messageId);
		Assert.isTrue(message.getSender().equals(principal));
		momentActual = new Date(System.currentTimeMillis());
		
		result = new Message();
		
		result.setCreatedMoment(momentActual);
		result.setSender(principal);
		result.setTitle(message.getTitle());
		result.setText(message.getText());
		result.setAttachments(message.getAttachments());
		
		return result;
	}
	
	public Collection<Message> findSendMessagesByActor(){
		Collection<Message> result;
		Actor principal;
		
		principal = actorService.findByPrincipal();
		result = messageRepository.findSendMessagesByActor(principal.getId());
		
		return result;
	}
	
	public Collection<Message> findReceivedMessagesByActor(){
		Collection<Message> result;
		Actor principal;
		
		principal = actorService.findByPrincipal();
		result = messageRepository.findreceivedMessagesByActor(principal.getId());
		
		return result;
	}
	
	public Double minMessagePerActor() {
		Double result;

		administratorService.checkPrincipal();
		result = this.messageRepository.minMessagePerActor();


		if (result == null)
			result = 0.0;

		result =(double) Math.round(result/2);
		return result;
	}
	
	public Double maxMessagePerActor() {
		Double result;

		administratorService.checkPrincipal();
		result = this.messageRepository.maxMessagePerActor();

		if (result == null)
			result = 0.0;
		result = (double) Math.round(result/2);
		return result;
	}

	public Double avgMessagePerActor() {
		Double result;

		administratorService.checkPrincipal();
		result = this.messageRepository.avgMessagePerActor();

		if (result == null)
			result = 0.0;
		result = result/2;
		return result;
	}

	public Double minMessageReceivedPerActor() {
		Double result;

		administratorService.checkPrincipal();
		result = this.messageRepository.minMessageReceivedPerActor();

		if (result == null)
			result = 0.0;
		result = (double) Math.round(result/2);
		return result;
	}

	public Double maxMessageReceivedPerActor() {
		Double result;

		administratorService.checkPrincipal();
		result = this.messageRepository.maxMessageReceivedPerActor();

		if (result == null)
			result = 0.0;
		result = (double) Math.round(result/2);
		return result;
	}

	public Double avgMessageReceivedPerActor() {
		Double result;

		administratorService.checkPrincipal();
		result = this.messageRepository.avgMessageReceivedPerActor();

		if (result == null)
			result = 0.0;
		result = result/2;
		return result;
	}

	public Collection<Actor> actorMoreSentMessage() {

		administratorService.checkPrincipal();
		Collection<Actor> result = this.messageRepository.actorMoreSentMessage();

		Assert.notNull(result);

		return result;
	}

	public Collection<Actor> actorMoreReceiverMessage() {
		administratorService.checkPrincipal();
		
		Collection<Actor> result = this.messageRepository.actorMoreReceiverMessage();

		Assert.notNull(result);

		return result;
	}
}
