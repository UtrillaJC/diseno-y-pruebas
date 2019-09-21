package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import security.UserAccount;
import domain.Actor;
import domain.Administrator;
import domain.Cook;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class FolderService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private FolderRepository folderRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private AdministratorService administratorService;
	
	//Constructor methods ============================================================================
	
	public FolderService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Folder findOneByPrincipal(int folderId){
		Folder result;
		Actor principal;
		
		result = folderRepository.findOne(folderId);
		principal = actorService.findByPrincipal();
		Assert.notNull(principal);

		Assert.isTrue(result.getUserAccount().equals(principal.getUserAccount()));

		return result;
	}
	
	public Folder create(Actor actor){
		Assert.notNull(actor);
		Folder result;
		Collection<Message> messages;
		Collection<Folder> children;
		
		children = new HashSet<Folder>();
		messages = new HashSet<Message>();
		
		result = new Folder();
		
		result.setUserAccount(actor.getUserAccount());
		result.setSystemFolder(true);
		result.setMessages(messages);
		result.setChildren(children);
		actor.getUserAccount().getFolders().add(result);
		
		return result;
	}
	
	public Folder createByActor(Actor actor){
		Assert.notNull(actor);
		Folder result;
		Actor principal;
		Collection<Message> messages;
		Collection<Folder> children;
		
		principal = actorService.findByPrincipal();
		Assert.isTrue(principal.equals(actor));
		children = new HashSet<Folder>();
		messages = new HashSet<Message>();
		
		result = new Folder();
		
		result.setUserAccount(actor.getUserAccount());
		result.setSystemFolder(false);
		result.setMessages(messages);
		result.setChildren(children);
		
		return result;
	}
	
	public Folder createChildFolder(Actor actor, Folder parent){
		Assert.notNull(actor);
		Assert.notNull(parent);
		Folder result;
		Actor principal;
		Collection<Message> messages;
		Collection<Folder> children;
		
		principal = actorService.findByPrincipal();
		Assert.isTrue(principal.equals(actor));
		Assert.isTrue(principal.getUserAccount().getFolders().contains(parent));
		children = new HashSet<Folder>();
		messages = new HashSet<Message>();
		
		result = new Folder();
		
		result.setSystemFolder(false);
		result.setUserAccount(actor.getUserAccount());
		result.setMessages(messages);
		result.setChildren(children);
		result.setParent(parent);
		
		return result;
	}
	
	public Folder saveBySystem(Folder folder){
		 Assert.isTrue(folder.getName().equals("Inbox") || folder.getName().equals("Outbox") ||folder.getName().equals("Trashbox") || folder.getName().equals("Spambox"));
		 Assert.isTrue(folder.getSystemFolder().equals(true));
		 Assert.notNull(folder);
		 Assert.notNull(folder.getUserAccount());
		 Folder result;
		 		 
		 result = folderRepository.save(folder);
		 
		 return result;
	 }
	
	public Folder saveFolderByActor(Folder folder){
		 Assert.isTrue(!folder.getName().equals("Inbox") && !folder.getName().equals("Outbox") && !folder.getName().equals("Trashbox") && !folder.getName().equals("Spambox"));
		 Assert.notNull(folder);
		 Assert.notNull(folder.getUserAccount());
		 Folder result;
		 Actor principal;
		 Collection<Folder> grandchildren;
		 
		 principal = actorService.findByPrincipal();
		 
		 Assert.isTrue(folder.getUserAccount().equals(principal.getUserAccount()));
		 Assert.isTrue(folder.getSystemFolder() == false);
		 
		 if(folder.getParent() != null) {
			 Assert.isTrue(folder.getParent().getUserAccount().equals(principal.getUserAccount()));
			 grandchildren = findByUserAccountWithGrandparent(principal.getUserAccount());
			 Assert.isTrue(!grandchildren.contains(folder.getParent()));
		 }
		 
		 result = folderRepository.save(folder);
		 
		 return result;
	 }
	
	public void delete(Folder folder) {
		Assert.notNull(folder);

		Actor principal;

		principal = actorService.findByPrincipal();

		Assert.isTrue(folder.getUserAccount().equals(principal.getUserAccount()));
		Assert.isTrue(!folder.getSystemFolder());
		Assert.isTrue(folder.getChildren().isEmpty());
		Assert.isTrue(folder.getMessages().isEmpty());

		folderRepository.delete(folder);
	}
	 
	//Other Business Methods =========================================================================
	
	public Collection<Folder> findAllByUserAccount(UserAccount userAccount){
		Assert.notNull(userAccount);
		Collection<Folder> result;
		Actor principal;

		principal = actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(userAccount.equals(principal.getUserAccount()));

		result = folderRepository.findByUserAccountId(userAccount.getId());
		
		return result;
	}
	
	public Collection<Folder> findByUserAccountWithoutParent(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Collection<Folder> result;
		Actor principal;
		
		principal = actorService.findByPrincipal();
		Assert.isTrue(principal.equals(userAccount));
		
		result = folderRepository.findByUserAccountIdWithoutParent(userAccount.getId());
	
		return result;
	}
	
	public Collection<Folder> findByUserAccountWithoutGrandparent(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Collection<Folder> result;
		Actor principal;
		
		principal = actorService.findByPrincipal();
		Assert.isTrue(principal.getUserAccount().equals(userAccount));
		
		result = folderRepository.findByUserAccountIdWithoutGrandparent(userAccount.getId());
	
		return result;
	}
	
	public Collection<Folder> findByUserAccountWithGrandparent(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Collection<Folder> result;
		Actor principal;
		
		principal = actorService.findByPrincipal();
		Assert.isTrue(principal.getUserAccount().equals(userAccount));
		
		result = folderRepository.findByUserAccountIdWithGrandparent(userAccount.getId());
	
		return result;
	}
	
	public Folder findActorInboxByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Folder result;

		result = folderRepository.findActorInboxByUserAccountId(userAccount.getId());

		return result;
	}

	public Folder findActorOutboxByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Folder result;

		result = folderRepository.findActorOutboxByUserAccountId(userAccount.getId());

		return result;
	}
	
	public Folder findActorTrashboxByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Folder result;

		result = folderRepository.findActorTrashboxByUserAccountId(userAccount.getId());

		return result;
	}
	
	public Folder findActorSpamboxByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Folder result;

		result = folderRepository.findActorSpamboxByUserAccountId(userAccount.getId());

		return result;
	}
	
	public Folder findActorFolderByName(UserAccount userAccount, String folderName) {
		Assert.notNull(userAccount);
		Folder result;
		
		result = folderRepository.findUserAccountFolderByName(userAccount.getId(), folderName);
		
		return result;
	}
	
	//Only Cook
	
	public Folder createFolderCook(Cook cook){
		Assert.notNull(cook);
		Folder result;
		Administrator principal;
		Collection<Message> messages;
		Collection<Folder> children;
		
		principal = administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		
		children = new HashSet<Folder>();
		messages = new HashSet<Message>();
		
		result = new Folder();
		
		result.setUserAccount(cook.getUserAccount());
		result.setSystemFolder(true);
		result.setMessages(messages);
		result.setChildren(children);
		cook.getUserAccount().getFolders().add(result);
		
		return result;
	}
	
	 public Folder saveFolderCook(Folder folder){
		 Assert.isTrue(folder.getName().equals("Inbox") || folder.getName().equals("Outbox") ||folder.getName().equals("Trashbox") || folder.getName().equals("Spambox"));
		 Assert.isTrue(folder.getSystemFolder().equals(true));
		 Assert.notNull(folder);
		 Assert.notNull(folder.getUserAccount());
		 Folder result;
		 Administrator principal;
		 		 
		 principal = administratorService.findByPrincipal();
		 Assert.isInstanceOf(Administrator.class, principal);
		 result = folderRepository.save(folder);
		 
		 return result;
	 }
}
