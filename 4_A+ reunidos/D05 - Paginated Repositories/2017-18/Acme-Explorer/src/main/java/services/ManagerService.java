
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Folder;
import domain.Manager;
import domain.Message;
import domain.SocialIdentity;
import domain.SurvivalClass;
import domain.Trip;

@Service
@Transactional
public class ManagerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ManagerRepository		managerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private FolderService			folderService;

	@Autowired
	private SocialIdentityService	socialIdentityService;

	@Autowired
	private TripService				tripService;

	@Autowired
	private SurvivalClassService	survivalClassService;


	// Constructors -----------------------------------------------------------

	public ManagerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Manager create() {

		Manager result = null;
		result = new Manager();
		result.setSuspicious(false);
		result.setDeactivated(false);
		result.setSurvivalClasses(new ArrayList<SurvivalClass>());
		result.setTrips(new ArrayList<Trip>());
		result.setFolders(new ArrayList<Folder>());
		result.setRecipientMessages(new ArrayList<Message>());
		result.setSentMessages(new ArrayList<Message>());
		result.setSocialIdentities(new ArrayList<SocialIdentity>());
		final UserAccount userAccount = this.userAccountService.create("MANAGER");
		result.setUserAccount(userAccount);
		return result;
	}

	public Manager findOne(final int managerId) {

		Manager result = null;
		result = this.managerRepository.findOne(managerId);
		return result;
	}

	public Collection<Manager> findAll() {

		Collection<Manager> result = null;
		result = this.managerRepository.findAll();
		return result;
	}

	public Manager save(final Manager manager) {

		Assert.notNull(manager);

		Manager result = null;
		if (manager.getId() == 0) {
			result = this.managerRepository.save(manager);
			final Collection<Folder> folders = this.folderService.defaultFolders(result);
			result.setFolders(folders);
		} else
			result = this.managerRepository.save(manager);
		return result;
	}

	public void delete(final Manager manager) {

		this.tripService.deleteByManager(manager);
		this.survivalClassService.deleteByManager(manager);
		this.socialIdentityService.deleteByActor(manager);
		this.folderService.deleteByActor(manager);
		this.managerRepository.delete(manager);
	}

	// Other business methods -------------------------------------------------

	public Collection<Double> avgMinAvgMinMaxDesvTripsPerManager() {

		Collection<Double> result = null;
		result = this.managerRepository.avgMinMaxDesvTripsPerManager();
		return result;
	}

	public String ratioManagersSuspicious() {

		String result = null;
		result = this.managerRepository.ratioManagersSuspicious();
		return result;
	}

	public Manager findByPrincipal() {

		Manager result = null;
		final UserAccount userAccount = LoginService.getPrincipal();
		result = this.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Manager findByUserAccountId(final int userAccountId) {

		Manager result = null;
		result = this.managerRepository.findByUserAccounId(userAccountId);
		return result;
	}

}
