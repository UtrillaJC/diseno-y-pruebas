package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NutritionistRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Curriculum;
import domain.Folder;
import domain.Message;
import domain.Nutritionist;
import domain.Person;

@Service
@Transactional
public class NutritionistService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private NutritionistRepository nutritionistRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private FolderService folderService;
	
	//Constructor methods ============================================================================
	
	public NutritionistService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Nutritionist findOne(int nutritionistId){
		Nutritionist result;
		
		result = nutritionistRepository.findOne(nutritionistId);
		
		return result;
	}
	
	public Nutritionist create(){
		Nutritionist result;
		UserAccount userAccount;
		Authority authority;
		Collection<Folder> folders;
		Collection<Message> receivedMessages;
		Collection<Message> sentMessages;
		Collection<Comment> comments;
		Collection<Person> followers;
		Collection<Person> followings;
		Collection<Curriculum> curricula;
		
		authority = new Authority();
		userAccount = new UserAccount();
		folders = new HashSet<Folder>();
		receivedMessages = new HashSet<Message>();
		sentMessages = new HashSet<Message>();
		comments = new HashSet<Comment>();
		followers = new HashSet<Person>();
		followings = new HashSet<Person>();
		curricula = new ArrayList<Curriculum>();
		
		authority.setAuthority("NUTRITIONIST");
		userAccount.addAuthority(authority);
		
		result = new Nutritionist();

		result.setUserAccount(userAccount);
		result.getUserAccount().setFolders(folders);
		result.getUserAccount().setReceivedMessages(receivedMessages);
		result.getUserAccount().setSentMessages(sentMessages);
		result.setComments(comments);
		result.setFollowers(followers);
		result.setFollowings(followings);
		result.setCurricula(curricula);
		
		return result;
	}
	
	public Nutritionist save(Nutritionist nutritionist){
		Assert.notNull(nutritionist);
		Assert.notNull(nutritionist.getUserAccount());
		Nutritionist result;
		Nutritionist nutritionistWithoutFolders;
		Folder inbox;
		Folder outbox;
		Folder trashbox;
		Folder spambox;
		Folder inboxSaved;
		Folder outboxSaved;
		Folder trashboxSaved;
		Folder spamboxSaved;
		
		nutritionistWithoutFolders = nutritionistRepository.save(nutritionist);
		
		inbox = folderService.create(nutritionistWithoutFolders);
		inbox.setSystemFolder(true);
		inbox.setName("Inbox");
		inboxSaved = folderService.saveBySystem(inbox);
		
		outbox = folderService.create(nutritionistWithoutFolders);
		outbox.setSystemFolder(true);
		outbox.setName("Outbox");
		outboxSaved = folderService.saveBySystem(outbox);
		
		trashbox = folderService.create(nutritionistWithoutFolders);
		trashbox.setSystemFolder(true);
		trashbox.setName("Trashbox");
		trashboxSaved = folderService.saveBySystem(trashbox);		
		
		spambox = folderService.create(nutritionistWithoutFolders);
		spambox.setSystemFolder(true);
		spambox.setName("Spambox");
		spamboxSaved = folderService.saveBySystem(spambox);
		
		nutritionistWithoutFolders.getUserAccount().getFolders().add(outboxSaved);
		nutritionistWithoutFolders.getUserAccount().getFolders().add(inboxSaved);
		nutritionistWithoutFolders.getUserAccount().getFolders().add(trashboxSaved);
		nutritionistWithoutFolders.getUserAccount().getFolders().add(spamboxSaved);
		
		result = nutritionistRepository.save(nutritionistWithoutFolders);
	
		return result;
	}
	
	public Nutritionist saveProfile(Nutritionist nutritionist) {
		Assert.notNull(nutritionist);
		Nutritionist result;
		
		result = nutritionistRepository.save(nutritionist);
		
		return result;		
	}
	
	//Other Business Methods =========================================================================

	public Nutritionist findByPrincipal() {
		Nutritionist result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Nutritionist findByUserAccount(UserAccount userAccount) {
		Nutritionist result;

		result = nutritionistRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
}
