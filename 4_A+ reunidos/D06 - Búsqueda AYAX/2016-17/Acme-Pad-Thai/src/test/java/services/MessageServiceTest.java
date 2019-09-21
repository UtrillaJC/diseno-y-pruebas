package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Actor;
import domain.Folder;
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class MessageServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	MessageService messageService;
	
	// Tests =========================================================================================
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private FolderService folderService;
	
	// Tests ----------------------------------------------------
	
	/**
	* ################################################################
	*
	* TEST POSITIVOS
	*
	* #################################################################
	*/
	
	@Test
	public void findOneTest(){
		System.out.println("----------------------------FindOne-----------------------------");
		authenticate("admin");
		int messageId = 109;
		System.out.println(messageService.findOne(messageId).getId() == messageId? 
				"Success" : "Fail");
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void findByFolderTest(){
		System.out.println("--------------------------FindByFolder--------------------------");
		authenticate("admin");
		int folderId = 57;
		Folder folder = folderService.findOneByPrincipal(folderId);
		Collection<Message> result = messageService.findByFolder(folder);
		for (Message message : result) {
			System.out.println("Message ID: " + message.getId() + 
								"Recipient: " + message.getRecipient());
		}
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}

	@Test
	public void createTest(){
		System.out.println("-----------------------------Create-----------------------------");
		authenticate("admin");
		Actor sender = actorService.findByPrincipal();
		Message message = messageService.create(sender);
		System.out.println("Subject: " + message.getSubject());
		System.out.println("Body: " + message.getBody());
		System.out.println("Moment: " + message.getMoment());
		System.out.println("Sender: " + message.getSender());
		System.out.println("Recipient: " + message.getRecipient());
		System.out.println("Priority: " + message.getPriority());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	
	@Test
	public void saveTest(){
		System.out.println("------------------------------Save------------------------------");
		authenticate("admin");
		Actor sender = actorService.findByPrincipal();
		Actor recipient = actorService.findByUsername("user1");
		Message result = messageService.create(sender);
		result.setSubject("Prueba de guardado");
		result.setBody("Mensaje de prueba");
		result.setPriority("LOW");
		result.setRecipient(recipient.getUserAccount());
		result = messageService.save(result);
		System.out.println("ID: " + result.getId());
		System.out.println("Subject: " + result.getSubject());
		System.out.println("Body: " + result.getBody());
		System.out.println("Moment: " + result.getMoment());
		System.out.println("Sender: " + result.getSender());
		System.out.println("Recipient: " + result.getRecipient());
		System.out.println("Priority: " + result.getPriority());

		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void deleteTest(){
		System.out.println("-----------------------------Delete-----------------------------");
		authenticate("admin");
		Actor actor = actorService.findByPrincipal();
		Folder actorTrashbox = folderService.findActorTrashboxByUserAccount(actor.getUserAccount());
		int messageId = 109;
		Message result = messageService.findOne(messageId);
		System.out.println("Original folder of the message: " + result.getFolder().getId() +
				" - " + result.getFolder().getName());
		messageService.delete(result);
		result = messageService.findOne(messageId);
		System.out.println("Actor's trashbox: " + actorTrashbox.getId());
		System.out.println("Folder of the message after \"deleting\" it: " + result.getFolder().getId() +
				" - " + result.getFolder().getName());
		messageService.delete(result);
		result = messageService.findOne(messageId);
		System.out.println(result == null? "Message successfully deleted" : "Fail");
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void moveMessageToFolderTest() {
		System.out.println("----------------------MoveMessageToFolder-----------------------");
		authenticate("admin");
		int messageId = 109;
		Message result = messageService.findOne(messageId);
		
		System.out.println("Original folder of the message: " + result.getFolder().getId() +
				" - " + result.getFolder().getName());
		
		Folder sourceFolder = result.getFolder();
		Folder targetFolder = folderService.findOneByPrincipal(57);
		
		messageService.moveMessageToFolder(result, sourceFolder, targetFolder);
		
		System.out.println("Folder of the message after moving it: " + result.getFolder().getId() +
				" - " + result.getFolder().getName());
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}


	/**
	* ################################################################
	*
	* TEST NEGATIVOS
	*
	* #################################################################
	*/
	
	@Test
	public void findOneTestNegative(){
		try{
			System.out.println("----------------------------FindOne Negative-----------------------------");
			authenticate("admin");
			int messageId = 15;
			messageId = messageService.findOne(messageId).getId();
			authenticate(null);
		}
		catch(Exception e){System.out.println("No existe un mensaje con el id introducido"); }
		finally{System.out.println("----------------------------------------------------------------"); }

		
	}
	
	@Test
	public void findByFolderTestNegative(){
		try{
		System.out.println("--------------------------Find By Folder Negative--------------------------");
		authenticate("admin");
		int folderId = 10;
		Folder folder = folderService.findOneByPrincipal(folderId);
		Collection<Message> result = messageService.findByFolder(folder);
		System.out.println("Number of messages from Folder " + folder.getId() + " - " + result.size());
		for (Message message : result) {
			System.out.println("Message ID: " + message.getId());
		}
		authenticate(null);
		}
		catch(Exception e){System.out.println("No existe una carpeta con el id introducido"); }
		finally{System.out.println("----------------------------------------------------------------"); }
	}
	
	@Test
	public void createTestNegative(){
		try{
		System.out.println("-----------------------------Create Negative-----------------------------");
		authenticate("nutritionist");
		Actor sender = actorService.findByPrincipal();
		Message message = messageService.create(sender);
		System.out.println("Subject: " + message.getSubject());
		System.out.println("Body: " + message.getBody());
		System.out.println("Moment: " + message.getMoment());
		System.out.println("Sender: " + message.getSender());
		System.out.println("Recipient: " + message.getRecipient());
		System.out.println("Priority: " + message.getPriority());
		
		authenticate(null);
		}
		catch(Exception e){System.out.println("No puede crearse un mensaje correctamente"); }
		finally{System.out.println("----------------------------------------------------------------"); }
	}

	@Test
	public void saveTestNegative(){
		try {
			System.out.println("------------------------------Save Negative------------------------------");
			authenticate("admin1");
			Actor sender = actorService.findByPrincipal();
			Actor recipient = actorService.findByUsername("user1");
			Message result = messageService.create(sender);
			result.setSubject("Prueba de guardado");
			result.setBody("Mensaje de prueba");
			result.setPriority("LOW");
			result.setRecipient(recipient.getUserAccount());
			result = messageService.save(result);
			System.out.println("ID: " + result.getId());
			System.out.println("Subject: " + result.getSubject());
			System.out.println("Body: " + result.getBody());
			System.out.println("Moment: " + result.getMoment());
			System.out.println("Sender: " + result.getSender());
			System.out.println("Recipient: " + result.getRecipient());
			System.out.println("Priority: " + result.getPriority());

			authenticate(null);
		}
		catch(Exception e){System.out.println("No puede guardarse un mensaje correctamente"); }
		finally{System.out.println("----------------------------------------------------------------"); }
	}
	
	@Test
	public void deleteTestNegative(){
		try {
			System.out.println("-----------------------------Delete Negative-----------------------------");
			authenticate("admin1");
			Actor actor = actorService.findByPrincipal();
			Folder actorTrashbox = folderService.findActorInboxByUserAccount(actor.getUserAccount());
			int messageId = 106;
			Message result = messageService.findOne(messageId);
			System.out.println("Original folder of the message: " + result.getFolder().getId() +
					" - " + result.getFolder().getName());
			messageService.delete(result);
			result = messageService.findOne(messageId);
			System.out.println("Actor's trashbox: " + actorTrashbox.getId());
			System.out.println("Folder of the message after \"deleting\" it: " + result.getFolder().getId() +
					" - " + result.getFolder().getName());
			messageService.delete(result);
			result = messageService.findOne(messageId);
			System.out.println(result == null? "Message successfully deleted" : "Fail");
			authenticate(null);
		}
		catch(Exception e){System.out.println("No puede borrarse un mensaje correctamente"); }
		finally{System.out.println("----------------------------------------------------------------"); }
	}
	
	@Test
	public void moveMessageToFolderTestNegative() {
		try {
			System.out.println("----------------------Move Message To Folder Negative-----------------------");
			authenticate("admin1");
			int messageId = 106;
			Message result = messageService.findOne(messageId);
			System.out.println("Original folder of the message: " + result.getFolder().getId() +
					" - " + result.getFolder().getName());
			Folder sourceFolder = result.getFolder();
			Folder targetFolder = folderService.findOneByPrincipal(56);
			messageService.moveMessageToFolder(result, sourceFolder, targetFolder);
			System.out.println("Folder of the message after moving it: " + result.getFolder().getId() +
					" - " + result.getFolder().getName());
			authenticate(null);
		}
		catch(Exception e){System.out.println("No es posible mover el mensaje hacia otra carpeta"); }
		finally{System.out.println("----------------------------------------------------------------"); }
		
	}
}