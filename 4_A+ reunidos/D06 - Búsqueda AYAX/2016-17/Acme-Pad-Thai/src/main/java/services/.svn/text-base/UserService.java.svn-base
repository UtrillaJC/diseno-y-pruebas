package services;


import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Comment;
import domain.Folder;
import domain.MasterClass;
import domain.Message;
import domain.Person;
import domain.Recipe;
import domain.Taste;
import domain.User;

@Service
@Transactional
public class UserService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private UserRepository userRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private AdministratorService administratorService;
	
	//Constructor methods ============================================================================
	
	public UserService() {
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public User findOne(int userId){
		User result;
		
		result = userRepository.findOne(userId);
		
		return result;
	}
	
	public Collection<User> findAll(){
		Collection<User> result;
		
		result = userRepository.findAll();
		
		return result;
	}
	
	public User create() {
		User result;
		UserAccount userAccount;
		Authority authority;
		Collection<Folder> folders;
		Collection<Message> receivedMessages;
		Collection<Message> sentMessages;
		Collection<Recipe> recipes;
		Collection<Comment> comments;
		Collection<Person> followers;
		Collection<Person> followings;
		Collection<MasterClass> masterClasses;
		Collection<Taste> tastes;
		
		authority = new Authority();
		userAccount = new UserAccount();
		folders = new HashSet<Folder>();
		receivedMessages = new HashSet<Message>();
		sentMessages = new HashSet<Message>();
		recipes = new HashSet<Recipe>();
		comments = new HashSet<Comment>();
		followers = new HashSet<Person>();
		followings = new HashSet<Person>();
		tastes = new HashSet<Taste>();	 
		masterClasses = new HashSet<MasterClass>();
		
		authority.setAuthority("USER");
		userAccount.addAuthority(authority);
		
		result = new User();
		
		result.setUserAccount(userAccount);
		result.getUserAccount().setFolders(folders);
		result.getUserAccount().setReceivedMessages(receivedMessages);
		result.getUserAccount().setSentMessages(sentMessages);
		result.setRecipes(recipes);
		result.setComments(comments);
		result.setFollowers(followers);
		result.setFollowings(followings);
		result.setTastes(tastes);
		result.setMasterClasses(masterClasses);
		
		return result;
	}
	
	public User save(User user) {
		Assert.notNull(user);
		Assert.notNull(user.getUserAccount());
		User result;
		User userWithoutFolders;
		Folder inbox;
		Folder outbox;
		Folder trashbox;
		Folder spambox;
		Folder inboxSaved;
		Folder outboxSaved;
		Folder trashboxSaved;
		Folder spamboxSaved;
		
		userWithoutFolders = userRepository.save(user);
		
		inbox = folderService.create(userWithoutFolders);
		inbox.setSystemFolder(true);
		inbox.setName("Inbox");
		inboxSaved = folderService.saveBySystem(inbox);
		
		outbox = folderService.create(userWithoutFolders);
		outbox.setSystemFolder(true);
		outbox.setName("Outbox");
		outboxSaved = folderService.saveBySystem(outbox);
		
		trashbox = folderService.create(userWithoutFolders);
		trashbox.setSystemFolder(true);
		trashbox.setName("Trashbox");
		trashboxSaved = folderService.saveBySystem(trashbox);		
		
		spambox = folderService.create(userWithoutFolders);
		spambox.setSystemFolder(true);
		spambox.setName("Spambox");
		spamboxSaved = folderService.saveBySystem(spambox);
		
		userWithoutFolders.getUserAccount().getFolders().add(outboxSaved);
		userWithoutFolders.getUserAccount().getFolders().add(inboxSaved);
		userWithoutFolders.getUserAccount().getFolders().add(trashboxSaved);
		userWithoutFolders.getUserAccount().getFolders().add(spamboxSaved);
		
		result = userRepository.save(userWithoutFolders);
	
		return result;
	}
	

	public User saveProfile(User user) {
		Assert.notNull(user);
		User result;
		
		result = userRepository.save(user);
		
		return result;		
	}
	//Other Business Methods =========================================================================
	
	public User findOneByRecipe(Recipe recipe){
		User result;
		
		result = userRepository.findOneByRecipeId(recipe.getId());
		
		return result;
	}
	
	public User findByPrincipal() {
		User result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public User findByUserAccount(UserAccount userAccount) {
		User result;

		result = userRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
	
	public Collection<User> getUserByKeyWord(String keyWord){
		Assert.notNull(keyWord);
		
		Collection<User> result;
		
		result = userRepository.searchUserByWords(keyWord);
		
		return result;
	}
	
	//Dashboard =============================================================================
	
	public Collection<User> usersAuthoredMoreRecipes(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Collection<User> result;
		
		result = userRepository.usersAuthoredMoreRecipes();
		
		return result;
	}
	
	
	public Collection<User> listUsersDescendingPopularity(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Collection<User> result;
		
		result = userRepository.listUsersDescendingPopularity();
		
		return result;
	}

	public Collection<User> listUsersLikes(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Collection<User> result;
		
		result = userRepository.listUsersLikes();
		
		return result;
	}
	
	public Collection<User> listUsersDislikes(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Collection<User> result;
		
		result = userRepository.listUsersDislikes();
		
		return result;
	}
	
}
