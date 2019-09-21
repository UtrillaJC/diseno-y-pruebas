
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Folder;
import domain.Manager;
import domain.Message;
import domain.Priority;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MessageServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private MessageService	messageService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private ManagerService	managerService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {
		final Message message;
		final Actor sender;

		super.authenticate("manager1");

		sender = this.actorService.findByPrincipal();
		message = this.messageService.create(sender);
		Assert.isTrue(message.getSender().equals(sender));

		final List<Folder> folders_sender = new ArrayList<Folder>(sender.getFolders());
		Assert.isTrue(message.getFolder().equals(folders_sender.get(1)));

		super.unauthenticate();
	}

	@Test
	public void testSave() {
		Actor sender, reciever;
		Message message, saved;

		super.authenticate("manager1");
		sender = this.actorService.findByPrincipal();
		reciever = this.actorService.findOne(7276);
		message = this.messageService.create(sender);
		message.setSubject("Trip information");
		message.setBody("This is the body of the message to explorer1 from manager1.");
		message.getRecipients().add(reciever);
		message.setPriority(Priority.HIGH);
		saved = this.messageService.save(message);

		Assert.notNull(this.messageService.findOne(saved.getId()));

	}

	@Test
	public void testSend_Message() {
		Actor sender, reciever;
		Message messageToSend, sentMessage;

		super.authenticate("admin1");
		sender = this.actorService.findByPrincipal();
		reciever = this.actorService.findOne(7277);
		messageToSend = this.messageService.create(sender);
		messageToSend.setSubject("Trip information");
		messageToSend.setBody("This is the body of the message to explorer1 from manager1.");
		final Collection<Actor> recipients = new ArrayList<>();
		recipients.add(reciever);
		messageToSend.setRecipients(recipients);
		messageToSend.setPriority(Priority.HIGH);
		sentMessage = this.messageService.sendMessage(messageToSend);

		final List<Folder> folders_sender = new ArrayList<Folder>(sender.getFolders());
		Assert.isTrue(sender.getSentMessages().contains(sentMessage));
		Assert.isTrue(!folders_sender.isEmpty());
		final List<Folder> folders_reciever = new ArrayList<Folder>(reciever.getFolders());

		Assert.isTrue(!folders_reciever.get(0).getMessages().isEmpty());

		super.unauthenticate();
	}
	@Test
	public void testSend_Spam_Message() {
		Actor sender, reciever;
		Message messageToSend;
		Folder spamBox;
		Manager manager;

		super.authenticate("manager1");
		sender = this.actorService.findByPrincipal();
		reciever = this.actorService.findOne(7276);
		messageToSend = this.messageService.create(sender);
		messageToSend.setSubject("Trip information ");
		messageToSend.setBody("This is the body of the SeX message to explorer1 from manager1.");
		final Collection<Actor> recipients = new ArrayList<>();
		recipients.add(reciever);
		messageToSend.setRecipients(recipients);
		messageToSend.setPriority(Priority.HIGH);
		this.messageService.sendMessage(messageToSend);
		final List<Folder> folders_reciever = new ArrayList<Folder>(reciever.getFolders());
		spamBox = folders_reciever.get(4);

		Assert.isTrue(sender.getDeactivated());
		Assert.isTrue(!spamBox.getMessages().isEmpty());

		manager = this.managerService.findByUserAccountId(sender.getUserAccount().getId());
		Assert.isTrue(manager.getSuspicious());

	}

	@Test
	public void test_Send_Notification() {
		Actor sender, reciever;
		Message messageToSend;
		Folder notificationBox;

		super.authenticate("admin1");
		sender = this.actorService.findByPrincipal();
		reciever = this.actorService.findOne(7276);
		messageToSend = this.messageService.create(sender);
		messageToSend.setSubject("Notification to manager1 ");
		messageToSend.setBody("This is the body of the notification message to manager1 from admin1.");
		final Collection<Actor> recipients = new ArrayList<>();
		recipients.add(reciever);
		messageToSend.setRecipients(recipients);
		messageToSend.setPriority(Priority.HIGH);

		final List<Folder> folders_reciever = new ArrayList<Folder>(reciever.getFolders());
		notificationBox = folders_reciever.get(2);

		Assert.isTrue(notificationBox.getMessages().isEmpty());

		this.messageService.notifyMessage(messageToSend);

		Assert.isTrue(!notificationBox.getMessages().isEmpty());
	}

	@Test
	public void testMoveMessage() {

		super.authenticate("ranger1");

		final Actor actor = this.actorService.findByPrincipal();

		final List<Folder> actorFolders = new ArrayList<>(actor.getFolders());
		final Folder inbox = actorFolders.get(0);
		final List<Message> messages = new ArrayList<>(inbox.getMessages());
		final Message messageToMove = messages.get(0);
		final Folder trashBox = actorFolders.get(3);

		this.messageService.moveMessageToFolder(messageToMove, trashBox);

		Assert.isTrue(!inbox.getMessages().contains(messageToMove));
		Assert.isTrue(trashBox.getMessages().contains(messageToMove));
	}
}
