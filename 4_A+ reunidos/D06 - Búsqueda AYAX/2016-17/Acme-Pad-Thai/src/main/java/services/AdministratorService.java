package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Bill;
import domain.Contest;
import domain.Folder;
import domain.Message;
import domain.Recipe;
import domain.Sponsor;

@Service
@Transactional
public class AdministratorService {

	//Managed Repository =============================================================================
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private FolderService folderService;
	
	//Constructor methods ============================================================================
	
	public AdministratorService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Administrator create() {
		Administrator result;
		UserAccount userAccount;
		Authority authority;
		Collection<Folder> folders;
		Collection<Message> receivedMessages;
		Collection<Message> sentMessages;
		
		authority = new Authority();
		userAccount = new UserAccount();
		folders = new HashSet<Folder>();
		receivedMessages = new HashSet<Message>();
		sentMessages = new HashSet<Message>();
		
		authority.setAuthority("ADMINISTRATOR");
		userAccount.addAuthority(authority);
		
		result = new Administrator();
		
		result.setUserAccount(userAccount);
		result.getUserAccount().setFolders(folders);
		result.getUserAccount().setReceivedMessages(receivedMessages);
		result.getUserAccount().setSentMessages(sentMessages);
		
		return result;
	}
	
	public Administrator save(Administrator administrator) {
		Assert.notNull(administrator);
		Assert.notNull(administrator.getUserAccount());
		Administrator result;
		Administrator administratorWithoutFolders;
		Folder inbox;
		Folder outbox;
		Folder trashbox;
		Folder spambox;
		Folder inboxSaved;
		Folder outboxSaved;
		Folder trashboxSaved;
		Folder spamboxSaved;
		
		administratorWithoutFolders = administratorRepository.save(administrator);
		
		inbox = folderService.create(administratorWithoutFolders);
		inbox.setSystemFolder(true);
		inbox.setName("Inbox");
		inboxSaved = folderService.saveBySystem(inbox);
		
		outbox = folderService.create(administratorWithoutFolders);
		outbox.setSystemFolder(true);
		outbox.setName("Outbox");
		outboxSaved = folderService.saveBySystem(outbox);
		
		trashbox = folderService.create(administratorWithoutFolders);
		trashbox.setSystemFolder(true);
		trashbox.setName("Trashbox");
		trashboxSaved = folderService.saveBySystem(trashbox);		
		
		spambox = folderService.create(administratorWithoutFolders);
		spambox.setSystemFolder(true);
		spambox.setName("Spambox");
		spamboxSaved = folderService.saveBySystem(spambox);
		
		administratorWithoutFolders.getUserAccount().getFolders().add(outboxSaved);
		administratorWithoutFolders.getUserAccount().getFolders().add(inboxSaved);
		administratorWithoutFolders.getUserAccount().getFolders().add(trashboxSaved);
		administratorWithoutFolders.getUserAccount().getFolders().add(spamboxSaved);
		
		result = administratorRepository.save(administratorWithoutFolders);
	
		return result;
	}
	
	public Administrator saveProfile(Administrator administrator) {
		Assert.notNull(administrator);
		Administrator result;
		
		result = administratorRepository.save(administrator);
		
		return result;		
	}
	
	//Other Business Methods =========================================================================

	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Administrator findByUserAccount(UserAccount userAccount) {
		Administrator result;

		result = administratorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
	
	public Double getCostTotal(int sponsorId){
		Collection<Bill> bill;
		Sponsor sponsor;
		Double costTotal;
		costTotal = 0.0;
		
		sponsor = sponsorService.findOne(sponsorId);
		bill = billService.findAllBySponsorNotPaidToAdministrator(sponsor);

		for(Bill b: bill){
			costTotal = costTotal + b.getCost();
		}
		return costTotal;
	}	
	
	public void winnersOfContest(Contest contest){
		Assert.isTrue(contest.getWinners().isEmpty());
		Assert.notNull(contest);
		Administrator administrator;
		Date moment;
		Collection<Recipe> recipes;
		Collection<Recipe> recipesWinners;
		Collection<Contest> contestWon;
		int cont = 0;
		
		recipesWinners = new ArrayList<Recipe>();
		
		contestWon = new ArrayList<Contest>();
		administrator = findByPrincipal();
		Assert.notNull(administrator);
		Assert.isInstanceOf(Administrator.class, administrator);
		moment = new Date(System.currentTimeMillis());
		Assert.isTrue(moment.after(contest.getMomentOpening()) && moment.after(contest.getMomentClosing()));
		recipes = recipeService.findRecipeOrderDescByLikesMinusDislikes(contest);	
		
		contestWon.add(contest);
			
		for(Recipe recipe : recipes){
			recipesWinners.add(recipe);
			recipe.setContestsWon(contestWon);
			recipeService.saveByAdministrator(recipe);

			cont++;	
			if(cont == 3){
				break;
			}
		}	
		
		contest.setWinners(recipesWinners);
		contestService.save(contest);
	}
	
	public void sendABulk(){
		Administrator administrator;
		Collection<Sponsor> sponsors;
		Message message;
		
		administrator = findByPrincipal();
		sponsors = sponsorService.findAllByUnpaidLastMonth();
		
		for(Sponsor sponsor : sponsors){
			message = messageService.create(administrator);
			
			message.setSubject("Bills unpadid/Facturas impagadas");
			message.setBody("You have unpaid bills, is advised him to put in order his payments/Usted tiene facturas impagadas, se le aconseja poner en orden sus pagos");
			message.setPriority("HIGH");
			message.setRecipient(sponsor.getUserAccount());
			
			messageService.save(message);		
		}
	}
}
