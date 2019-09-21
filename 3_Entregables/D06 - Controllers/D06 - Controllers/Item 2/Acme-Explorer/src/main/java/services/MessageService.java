
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.Authority;
import domain.Actor;
import domain.Application;
import domain.Explorer;
import domain.Folder;
import domain.Manager;
import domain.Message;
import domain.Priority;
import domain.Ranger;

@Service
@Transactional
public class MessageService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private MessageRepository		messageRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private FolderService			folderService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ConfigurationService	configurationService;

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private RangerService			rangerService;

	@Autowired
	private AdminService			adminService;


	// Constructors -----------------------------------------------------------

	public MessageService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Message create() {

		Message message;
		Actor actor;
		Folder folder;

		message = new Message();
		message.setMoment(new Date(System.currentTimeMillis() - 1000));

		actor = this.actorService.findByPrincipal();
		message.setSender(actor);

		folder = this.folderService.findByFolderName(actor.getUserAccount().getId(), "Out Box");
		message.setFolder(folder);

		return message;
	}

	public void delete(final Message message) {

		this.checkByPrincipal(message);

		Message saved;
		final Actor actor;
		Folder trashbox;

		actor = this.actorService.findByPrincipal();
		trashbox = this.folderService.findByFolderName(actor.getUserAccount().getId(), "Trash Box");

		if (message.getFolder() != trashbox) {
			message.setFolder(trashbox);
			saved = this.messageRepository.save(message);
			trashbox.getMessages().add(saved);
		} else
			this.messageRepository.delete(message);
	}

	public Message findOne(final int id) {

		Message result;

		result = this.messageRepository.findOne(id);

		return result;
	}

	public Message findOneToEdit(final int id) {

		Message result;

		result = this.findOne(id);
		this.checkByPrincipal(result);

		return result;
	}

	public Collection<Message> findAll() {
		return this.messageRepository.findAll();
	}

	public Message save(final Message message) {

		Assert.notNull(message);

		Message saved, copy, savedCopy;
		Folder outboxSender, inboxRecipient, spamboxRecipient;

		if (message.getId() == 0) {
			final Date newMoment = new Date(System.currentTimeMillis() - 1000);
			copy = this.copy(message);
			if (this.isSpamMessage(message)) {
				spamboxRecipient = this.folderService.findByFolderName(copy.getRecipient().getUserAccount().getId(), "Spam Box");
				copy.setFolder(spamboxRecipient);
				savedCopy = this.messageRepository.save(copy);
				savedCopy.setMoment(newMoment);
				spamboxRecipient.getMessages().add(savedCopy);
			} else {
				inboxRecipient = this.folderService.findByFolderName(copy.getRecipient().getUserAccount().getId(), "In Box");
				copy.setFolder(inboxRecipient);
				savedCopy = this.messageRepository.save(copy);
				savedCopy.setMoment(newMoment);
				inboxRecipient.getMessages().add(savedCopy);
			}

			outboxSender = message.getFolder();
			saved = this.messageRepository.save(message);
			saved.setMoment(newMoment);
			outboxSender.getMessages().add(saved);
		} else
			saved = this.messageRepository.save(message);

		return saved;
	}

	public Message notify(final Message message) {

		Assert.isTrue(!this.isSpamMessage(message));
		Assert.notNull(message);

		Message saved = null, copy, savedCopy;
		Folder outboxSender, notificationboxRecipient;

		message.setMoment(new Date(System.currentTimeMillis() - 1000));
		final Collection<Actor> recipients = this.actorService.findAll();
		recipients.remove(this.actorService.findByPrincipal());
		for (final Actor recipient : recipients) {
			message.setRecipient(recipient);
			copy = this.copy(message);
			notificationboxRecipient = this.folderService.findByFolderName(recipient.getUserAccount().getId(), "Notification Box");
			copy.setFolder(notificationboxRecipient);
			savedCopy = this.messageRepository.save(copy);
			notificationboxRecipient.getMessages().add(savedCopy);
			outboxSender = message.getFolder();
			saved = this.messageRepository.save(message);
			outboxSender.getMessages().add(saved);
		}

		return saved;
	}

	// Other business methods -------------------------------------------------

	public Message copy(final Message message) {

		Assert.notNull(message);

		Message result;

		result = this.create();
		result.setSubject(message.getSubject());
		result.setBody(message.getBody());
		result.setMoment(message.getMoment());
		result.setPriority(message.getPriority());
		result.setRecipient(message.getRecipient());
		result.setSender(message.getSender());

		return result;
	}

	public Collection<Message> findByFolderId(final int folderId) {

		Collection<Message> result;

		final Folder folder = this.folderService.findOne(folderId);
		this.folderService.checkPrincipal(folder);
		result = folder.getMessages();

		return result;
	}

	public void deleteByFolder(final Folder folder) {

		final Collection<Message> messages = folder.getMessages();
		this.messageRepository.delete(messages);
	}

	public void deleteByActor(final Actor actor) {
		Assert.notNull(actor);

		//final Collection<Message> messages = this.messageRepository.findMessagesByActor(actor);

		//this.messageRepository.deleteInBatch(messages);
	}

	public Message sendMessage(final Message message) {
		Assert.notNull(message);

		Actor sender = null;
		Actor recipient = null;
		Folder inboxReceiver = null;
		Folder outboxSender = null;

		// TODO: a�adir comentario
		sender = message.getSender();
		final List<Folder> foldersSender = new ArrayList<Folder>(sender.getFolders());
		outboxSender = foldersSender.get(1);

		//  SPAM Control

		if (this.isSpamMessage(message))
			return this.sendMessageSpam(message, sender);

		recipient = message.getRecipient();
		//final ArrayList<Folder> test = receiver.getFolders();

		// Mandamos los mensajes a los receivers...

		final List<Folder> foldersReceiver = new ArrayList<Folder>(recipient.getFolders());
		inboxReceiver = foldersReceiver.get(0);
		final Message copyOfmessage = this.messageRepository.save(message);

		copyOfmessage.setFolder(inboxReceiver);
		//			receiver.getRecipientMessages().add(message);
		inboxReceiver.getMessages().add(copyOfmessage);
		this.actorService.save(recipient);
		this.folderService.save(inboxReceiver);
		this.save(copyOfmessage);

		//		sender.getSentMessages().add(message);
		//		sender.getSentMessages().add(message);
		outboxSender.getMessages().add(message);
		this.actorService.save(sender);
		this.folderService.save(outboxSender);
		this.save(message);

		return message;
	}

	private Message sendMessageSpam(final Message message, final Actor sender) {
		Actor recipient = null;
		Folder spamBoxReceiver = null;
		Folder outboxSender = null;

		recipient = message.getRecipient();
		final List<Folder> foldersReceiver = new ArrayList<Folder>(recipient.getFolders());
		spamBoxReceiver = foldersReceiver.get(4);
		final Message copyOfmessage = this.messageRepository.save(message);

		copyOfmessage.setFolder(spamBoxReceiver);
		//receiver.getRecipientMessages().add(copyOfmessage);
		spamBoxReceiver.getMessages().add(copyOfmessage);
		this.actorService.save(recipient);
		this.folderService.save(spamBoxReceiver);
		this.save(copyOfmessage);

		// desactivar la cuenta del sender y marcarlo como suspicious si el sender es Manager o Ranger.

		final Collection<Authority> test = sender.getUserAccount().getAuthorities();
		final String authority = test.toArray()[0].toString();

		if (authority.equals("MANAGER")) {
			final Manager manager = this.managerService.findByUserAccountId(sender.getUserAccount().getId());
			manager.setSuspicious(true);
			this.managerService.save(manager);
		} else if (authority.equals("RANGER")) {
			final Ranger ranger = this.rangerService.findByPrincipal();
			ranger.setSuspicious(true);
			this.rangerService.save(ranger);
		}

		final List<Folder> folders_sender = new ArrayList<Folder>(sender.getFolders());
		outboxSender = folders_sender.get(1);

		//		sender.setDeactivated(true);
		//		sender.getSentMessages().add(message);
		outboxSender.getMessages().add(message);
		// save del copy y del sender en Repo
		this.actorService.save(sender);
		this.folderService.save(outboxSender);
		this.save(message);

		return message;
	}

	public Message notifyMessage(final Message message) {
		Assert.notNull(message);
		// TODO: Control de SPAM?

		Actor sender = null;
		Actor recipient = null;
		Folder notificationBox = null;
		Folder outboxSender = null;

		// TODO: Notificar message a mas de un actor. 

		sender = message.getSender();
		recipient = message.getRecipient();

		final List<Folder> foldersReceiver = new ArrayList<Folder>(recipient.getFolders());
		notificationBox = foldersReceiver.get(2);
		final Message copyOfmessage = this.save(message);

		copyOfmessage.setFolder(notificationBox);
		//			receiver.getRecipientMessages().add(copyOfmessage);
		notificationBox.getMessages().add(copyOfmessage);

		final List<Folder> folders_sender = new ArrayList<Folder>(sender.getFolders());
		outboxSender = folders_sender.get(1);

		message.setFolder(outboxSender);
		//		sender.getSentMessages().add(message);
		//		sender.getSentMessages().add(message);

		return message;
	}
	public void moveMessageToFolder(final Message message, final Folder folder) {
		Assert.notNull(folder);
		Assert.notNull(message);
		this.folderService.checkPrincipal(folder);
		Assert.isTrue(!folder.getMessages().contains(message));

		final Actor actor = this.actorService.findByPrincipal();   //folder.getActor();

		//		Assert.isTrue(actor.getRecipientMessages().contains(message) || actor.getSentMessages().contains(message));
		Assert.isTrue(actor.getFolders().contains(message.getFolder()));

		final List<Message> messages = new ArrayList<Message>(folder.getMessages());
		final Folder folderSource = message.getFolder();
		final List<Message> messages2 = new ArrayList<Message>(folderSource.getMessages());

		messages.add(message);
		folder.setMessages(messages);
		messages2.remove(message);
		folderSource.setMessages(messages2);

		this.folderService.save(folder);
		message.setFolder(folder);
		this.save(message);
	}

	public void checkByPrincipal(final Message message) {

		Assert.notNull(message);

		final Actor actor = this.actorService.findByPrincipal();
		Assert.isTrue(message.getRecipient().equals(actor) || message.getSender().equals(actor));
	}

	private boolean isSpamMessage(final Message message) {
		boolean result = false;
		Pattern p;
		Matcher isAnyMatcherBody;
		Matcher isAnyMatcherSubject;

		p = this.spamWords();
		isAnyMatcherBody = p.matcher(message.getBody());
		isAnyMatcherSubject = p.matcher(message.getSubject());

		if (isAnyMatcherBody.find() || isAnyMatcherSubject.find())
			result = true;

		return result;
	}

	public Pattern spamWords() {
		Pattern result;
		List<String> spamWords;

		final Collection<String> spamlist = this.configurationService.findAll().iterator().next().getSpamWords();
		spamWords = new ArrayList<>(spamlist);

		String str = ".*\\b(";
		for (int i = 0; i <= spamWords.size(); i++)
			if (i < spamWords.size())
				str += spamWords.get(i) + "|";
			else
				str += spamWords.iterator().next() + ")\\b.*";

		result = Pattern.compile(str, Pattern.CASE_INSENSITIVE);

		return result;
	}

	public void notifyChangeInApplicationStatus(final Application application) {
		final Manager manager = application.getTrip().getManager();
		final Explorer applicant = application.getApplicant();
		final List<Actor> recipients = new ArrayList<>();
		final Date actualDate = new Date(System.currentTimeMillis() - 1000);
		recipients.add(manager);
		recipients.add(applicant);

		for (final Actor recipient : recipients) {
			final Message message = this.create();
			message.setRecipient(recipient);
			message.setSubject("Application status has changed");
			message.setBody("The application in reference to the Trip:" + application.getTrip().getTitle() + " has changed to " + application.getStatus());
			message.setPriority(Priority.HIGH);
			message.setMoment(actualDate);
			final Message copy = this.copy(message);
			final Folder inboxRecipient = this.folderService.findByFolderName(copy.getRecipient().getUserAccount().getId(), "In Box");
			copy.setFolder(inboxRecipient);
			final Message savedCopy = this.messageRepository.save(copy);
			inboxRecipient.getMessages().add(savedCopy);
			final Folder outboxSender = this.folderService.findByFolderName(message.getSender().getUserAccount().getId(), "Out Box");
			final Message saved = this.messageRepository.save(message);
			outboxSender.getMessages().add(saved);
		}

	}

}
