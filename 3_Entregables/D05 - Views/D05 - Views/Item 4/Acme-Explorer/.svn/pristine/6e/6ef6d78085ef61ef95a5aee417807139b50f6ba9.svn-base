
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ExplorerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Contact;
import domain.Explorer;
import domain.Finder;
import domain.Folder;
import domain.Message;
import domain.SocialIdentity;
import domain.Story;
import domain.SurvivalClass;

@Service
@Transactional
public class ExplorerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ExplorerRepository		explorerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private FolderService			folderService;

	@Autowired
	private FinderService			finderService;

	@Autowired
	private SocialIdentityService	socialIdentityService;

	@Autowired
	private ApplicationService		applicationService;

	@Autowired
	private StoryService			storyService;

	@Autowired
	private SurvivalClassService	survivalClassService;


	// Constructors -----------------------------------------------------------

	public ExplorerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Explorer create() {

		Explorer result = null;

		result = new Explorer();

		result.setDeactivated(false);
		result.setApplications(new ArrayList<Application>());
		result.setContacts(new ArrayList<Contact>());
		result.setStories(new ArrayList<Story>());
		result.setFolders(new ArrayList<Folder>());
		result.setRecipientMessages(new ArrayList<Message>());
		result.setSentMessages(new ArrayList<Message>());
		result.setSocialIdentities(new ArrayList<SocialIdentity>());
		result.setSurvivalClasses(new ArrayList<SurvivalClass>());

		final UserAccount userAccount = this.userAccountService.create("EXPLORER");
		result.setUserAccount(userAccount);
		this.finderService.create(result);

		return result;
	}

	public Explorer findOne(final int explorerId) {

		Explorer result = null;
		result = this.explorerRepository.findOne(explorerId);
		return result;
	}

	public Collection<Explorer> findAll() {

		Collection<Explorer> result = null;
		result = this.explorerRepository.findAll();
		return result;
	}

	public Explorer save(final Explorer explorer) {

		Assert.notNull(explorer);

		Explorer result = null;

		if (explorer.getId() == 0) {
			final Finder finder = this.finderService.save(explorer.getFinder());
			explorer.setFinder(finder);

			result = this.explorerRepository.save(explorer);
			finder.setExplorer(result);

			final Collection<Folder> folders = this.folderService.defaultFolders(result);
			result.setFolders(folders);
		} else
			result = this.explorerRepository.save(explorer);

		return result;
	}

	public void delete(final Explorer explorer) {

		this.survivalClassService.deleteByExplorer(explorer);
		this.explorerRepository.delete(explorer);
		this.folderService.deleteByActor(explorer);
		this.socialIdentityService.deleteByActor(explorer);
		this.finderService.delete(explorer.getFinder());
		this.applicationService.deleteByExplorer(explorer);
		this.storyService.deleteByExplorer(explorer);

	}

	// Other business methods -------------------------------------------------

	public Explorer findByPrincipal() {

		Explorer result = null;
		final UserAccount userAccount = LoginService.getPrincipal();
		result = this.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Explorer findByUserAccountId(final int userAccountId) {

		Explorer result = null;
		result = this.explorerRepository.findByUserAccountId(userAccountId);
		return result;
	}

}
