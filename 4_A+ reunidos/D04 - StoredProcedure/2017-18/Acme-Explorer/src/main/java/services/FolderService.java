
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class FolderService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FolderRepository	folderRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private MessageService		messageService;


	// Constructors -----------------------------------------------------------

	public FolderService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Folder create(final Actor actor, final boolean predefined, final Folder folder) {

		Assert.notNull(actor);

		Folder result = null;
		result = new Folder();
		result.setPredefined(predefined);
		if (folder != null) {
			result.setParent(folder);
			folder.getChildren().add(result);
		}
		result.setActor(actor);
		result.setChildren(new ArrayList<Folder>());
		result.setMessages(new ArrayList<Message>());
		return result;
	}

	public Folder findOne(final int folderId) {

		Folder result = null;
		result = this.folderRepository.findOne(folderId);
		return result;
	}

	public Folder findOneToEdit(final int folderId) {

		Folder result = null;
		result = this.folderRepository.findOne(folderId);
		this.checkPrincipal(result);
		Assert.isTrue(result.getPredefined() == false);
		return result;
	}

	public Collection<Folder> findAll() {

		Collection<Folder> result = null;
		result = this.folderRepository.findAll();
		return result;
	}

	public Folder save(final Folder folder) {

		Assert.notNull(folder);

		Folder result = null;
		result = this.folderRepository.save(folder);
		result.getActor().getFolders().add(result);
		return result;
	}

	public void delete(final Folder folder) {

		Assert.notNull(folder);

		this.checkPrincipal(folder);
		this.messageService.deleteByFolder(folder);
		this.folderRepository.delete(folder);
	}

	// Other business methods -------------------------------------------------

	public Collection<Folder> defaultFolders(final Actor actor) {

		Collection<Folder> result = null;
		result = new ArrayList<Folder>();
		final Folder inbox = this.create(actor, true, null);
		inbox.setName("In Box");
		final Folder savedInbox = this.save(inbox);
		result.add(savedInbox);
		final Folder outbox = this.create(actor, true, null);
		outbox.setName("Out Box");
		final Folder savedOutbox = this.save(outbox);
		result.add(savedOutbox);
		final Folder notificationbox = this.create(actor, true, null);
		notificationbox.setName("Notification Box");
		final Folder savedNotificationbox = this.save(notificationbox);
		result.add(savedNotificationbox);
		final Folder spambox = this.create(actor, true, null);
		spambox.setName("Spam Box");
		final Folder savedSpambox = this.save(spambox);
		result.add(savedSpambox);
		final Folder trashbox = this.create(actor, true, null);
		trashbox.setName("Trash Box");
		final Folder savedTrashbox = this.save(trashbox);
		result.add(savedTrashbox);
		return result;
	}

	public void deleteByActor(final Actor actor) {

		Assert.notNull(actor);

		final Collection<Folder> folders = actor.getFolders();
		for (final Folder f : folders)
			this.messageService.deleteByFolder(f);
		this.folderRepository.delete(folders);
		actor.getFolders().removeAll(folders);
	}

	public Collection<Folder> findByPrincipal() {

		Collection<Folder> result = null;
		final UserAccount userAccount = LoginService.getPrincipal();
		result = this.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Collection<Folder> findByUserAccountId(final int userAccountId) {

		Collection<Folder> result = null;
		result = this.folderRepository.findByUserAccountId(userAccountId);
		return result;
	}

	public void checkPrincipal(final Folder folder) {

		Assert.notNull(folder);

		final Actor actor = folder.getActor();
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(actor.equals(principal));
	}

}
