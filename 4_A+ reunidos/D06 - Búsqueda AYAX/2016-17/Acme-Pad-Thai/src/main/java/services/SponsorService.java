package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Campaign;
import domain.CreditCard;
import domain.Folder;
import domain.Message;
import domain.Sponsor;

@Service
@Transactional
public class SponsorService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private SponsorRepository sponsorRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private AdministratorService administratorService;
	
	//Constructor methods ============================================================================
	
	public SponsorService(){
		super();
	}
	
	
	//Simple CRUD methods ============================================================================
	
	public Sponsor findOne(int sponsorId){
		Sponsor result;
		
		result = sponsorRepository.findOne(sponsorId);
		
		return result;
	}
	
	public Sponsor create(){
		Sponsor result;
		UserAccount userAccount;
		Authority authority;
		Collection<Folder> folders;
		Collection<Message> receivedMessages;
		Collection<Message> sentMessages;
		Collection<Campaign> campaigns;
		CreditCard creditCard;
		
		userAccount = new UserAccount();
		authority = new Authority();
		folders = new HashSet<Folder>();
		receivedMessages = new HashSet<Message>();
		sentMessages = new HashSet<Message>();
		creditCard = new CreditCard();
		
		authority.setAuthority("SPONSOR");
		userAccount.addAuthority(authority);
		
		result = new Sponsor();
		
		campaigns = new ArrayList<Campaign>();
		
		result.setUserAccount(userAccount);
		result.getUserAccount().setFolders(folders);
		result.getUserAccount().setReceivedMessages(receivedMessages);
		result.getUserAccount().setSentMessages(sentMessages);
		result.setCampaigns(campaigns);
		result.setCreditCard(creditCard);
		
		return result;
	}

	@SuppressWarnings("deprecation")
	public Sponsor save(Sponsor sponsor){
		Assert.notNull(sponsor);
		Assert.notNull(sponsor.getUserAccount());
		Sponsor result;
		Sponsor sponsorWithoutFolders;
		Folder inbox;
		Folder outbox;
		Folder trashbox;
		Folder spambox;
		Folder inboxSaved;
		Folder outboxSaved;
		Folder trashboxSaved;
		Folder spamboxSaved;
		Date moment;
		
		moment = new Date(System.currentTimeMillis()-1000);
			
		if (sponsor.getId() == 0) {
			Assert.isTrue((sponsor.getCreditCard().getExpirationYear() - 1900) >= moment
					.getYear());
			if ((sponsor.getCreditCard().getExpirationYear() - 1900) == moment
					.getYear()) {
				Assert.isTrue((sponsor.getCreditCard().getExpirationMonth() - 1) > moment
						.getMonth());
			}		
		}
		
		sponsorWithoutFolders = sponsorRepository.save(sponsor);

		inbox = folderService.create(sponsorWithoutFolders);
		inbox.setSystemFolder(true);
		inbox.setName("Inbox");
		inboxSaved = folderService.saveBySystem(inbox);

		outbox = folderService.create(sponsorWithoutFolders);
		outbox.setSystemFolder(true);
		outbox.setName("Outbox");
		outboxSaved = folderService.saveBySystem(outbox);
		trashbox = folderService.create(sponsorWithoutFolders);
		trashbox.setSystemFolder(true);
		trashbox.setName("Trashbox");
		trashboxSaved = folderService.saveBySystem(trashbox);
			
		spambox = folderService.create(sponsorWithoutFolders);
		spambox.setSystemFolder(true);
		spambox.setName("Spambox");
		spamboxSaved = folderService.saveBySystem(spambox);
			
		sponsorWithoutFolders.getUserAccount().getFolders().add(outboxSaved);
		sponsorWithoutFolders.getUserAccount().getFolders().add(inboxSaved);
		sponsorWithoutFolders.getUserAccount().getFolders().add(trashboxSaved);
		sponsorWithoutFolders.getUserAccount().getFolders().add(spamboxSaved);
		
		result = sponsorRepository.save(sponsorWithoutFolders);

		return result;
	}
	
	public Sponsor saveProfile(Sponsor sponsor) {
		Assert.notNull(sponsor);
		Sponsor result;
		
		result = sponsorRepository.save(sponsor);
		
		return result;		
	}
	
	//Other Business Methods =========================================================================

	public Sponsor findByPrincipal() {
		Sponsor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Sponsor findByUserAccount(UserAccount userAccount) {
		Sponsor result;

		result = sponsorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
	
	public Collection<Sponsor> findAllByUnpaidLastMonth(){
		Collection<Sponsor> result;
		Administrator principal;
		
		principal = administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		
		result = sponsorRepository.findAllByUnpaidLastMonth();
		
		return result;
	}
	
	//Dashboard =============================================================================
	
	public Collection<String> rankingsOfCompaniesForCampaigns(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Collection<String> result;
		
		result = sponsorRepository.rankingsOfCompaniesForCampaigns();
	
		return result;
	}
	
	public Collection<String> rankingsOfCompaniesForBills(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Collection<String> result;
		
		result = sponsorRepository.rankingsOfCompaniesForBills();
	
		return result;
	}
	
	public Collection<Sponsor> sponsorsWhoNotManagedCampaignLastThreeMonths(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Collection<Sponsor> result;
		
		result = sponsorRepository.sponsorsWhoNotManagedCampaignLastThreeMonths();
		
		return result;
	}
	
	public Collection<String> companiesSpentLessAvgCampaigns(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Collection<String> result;
		
		result = sponsorRepository.companiesSpentLessAvgCampaigns();
		
		return result;
	}
	
	public Collection<String> companiesSpentLeast90PerCent(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Collection<String> result;
		
		result = sponsorRepository.companiesSpentLeast90PerCent();
		
		return result;
	}
}
