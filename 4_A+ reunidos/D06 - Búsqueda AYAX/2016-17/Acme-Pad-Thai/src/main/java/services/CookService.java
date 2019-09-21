package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CookRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Cook;
import domain.Folder;
import domain.MasterClass;
import domain.Message;

@Service
@Transactional
public class CookService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private CookRepository cookRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private AdministratorService administratorService;
	
	//Constructor methods ============================================================================
	
	public CookService() {
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Cook findOne(int cookId){
		Cook result;
		
		result = cookRepository.findOne(cookId);
		
		return result;
	}
	
	public Cook create(){
		Cook result;
		UserAccount userAccount;
		Authority authority;
		Collection<Folder> folders;
		Collection<Message> receivedMessages;
		Collection<Message> sentMessages;
		Collection<MasterClass> masterClasses;
		Administrator principal;

		principal = administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		
		authority = new Authority();
		userAccount = new UserAccount();
		folders = new HashSet<Folder>();
		receivedMessages = new HashSet<Message>();
		sentMessages = new HashSet<Message>();
		masterClasses = new HashSet<MasterClass>();
		
		authority.setAuthority("COOK");
		userAccount.addAuthority(authority);

		result = new Cook();

		result.setUserAccount(userAccount);
		result.getUserAccount().setFolders(folders);
		result.getUserAccount().setReceivedMessages(receivedMessages);
		result.getUserAccount().setSentMessages(sentMessages);
		result.setMasterClasses(masterClasses);

		return result;
	}
	
	public Cook save(Cook cook){
		Assert.notNull(cook);
		Assert.notNull(cook.getUserAccount());
		Cook result;
		Cook cookWithoutFolders;
		Folder inbox;
		Folder outbox;
		Folder trashbox;
		Folder spambox;
		Folder inboxSaved;
		Folder outboxSaved;
		Folder trashboxSaved;
		Folder spamboxSaved;
		Administrator principal;

		principal = administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		
		cookWithoutFolders = cookRepository.save(cook);

		inbox = folderService.createFolderCook(cookWithoutFolders);
		inbox.setSystemFolder(true);
		inbox.setName("Inbox");
		inboxSaved = folderService.saveFolderCook(inbox);

		outbox = folderService.createFolderCook(cookWithoutFolders);
		outbox.setSystemFolder(true);
		outbox.setName("Outbox");
		outboxSaved = folderService.saveFolderCook(outbox);

		trashbox = folderService.createFolderCook(cookWithoutFolders);
		trashbox.setSystemFolder(true);
		trashbox.setName("Trashbox");
		trashboxSaved = folderService.saveFolderCook(trashbox);
		
		spambox = folderService.createFolderCook(cookWithoutFolders);
		spambox.setSystemFolder(true);
		spambox.setName("Spambox");
		spamboxSaved = folderService.saveFolderCook(spambox);
		
		cookWithoutFolders.getUserAccount().getFolders().add(outboxSaved);
		cookWithoutFolders.getUserAccount().getFolders().add(inboxSaved);
		cookWithoutFolders.getUserAccount().getFolders().add(trashboxSaved);
		cookWithoutFolders.getUserAccount().getFolders().add(spamboxSaved);
		
		result = cookRepository.save(cookWithoutFolders);

		return result;
	}
	
	public Cook saveProfile(Cook cook) {
		Assert.notNull(cook);
		Cook result;
		
		result = cookRepository.save(cook);
		
		return result;		
	}
	
	//Other Business Methods =========================================================================

	public Cook findByPrincipal() {
		Cook result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Cook findByUserAccount(UserAccount userAccount) {
		Cook result;

		result = cookRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
}
