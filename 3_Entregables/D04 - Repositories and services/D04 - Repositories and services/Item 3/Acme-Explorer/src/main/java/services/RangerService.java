
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RangerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Folder;
import domain.Message;
import domain.Ranger;
import domain.SocialIdentity;
import domain.Trip;

@Service
@Transactional
public class RangerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RangerRepository		rangerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private FolderService			folderService;

	@Autowired
	private SocialIdentityService	socialIdentityService;

	@Autowired
	private MessageService			messageService;

	@Autowired
	private CurriculumService		curriculumService;


	// Constructors -----------------------------------------------------------

	public RangerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Ranger create() {

		Ranger result = null;
		result = new Ranger();
		result.setSuspicious(false);
		result.setDeactivated(false);
		result.setTrips(new ArrayList<Trip>());
		result.setFolders(new ArrayList<Folder>());
		result.setRecipientMessages(new ArrayList<Message>());
		result.setSentMessages(new ArrayList<Message>());
		result.setSocialIdentities(new ArrayList<SocialIdentity>());
		final UserAccount userAccount = this.userAccountService.create("RANGER");
		result.setUserAccount(userAccount);
		return result;
	}

	public Ranger findOne(final int rangerId) {

		Ranger result = null;
		result = this.rangerRepository.findOne(rangerId);
		return result;
	}

	public Collection<Ranger> findAll() {

		Collection<Ranger> result = null;
		result = this.rangerRepository.findAll();
		return result;
	}

	public Ranger save(final Ranger ranger) {

		Assert.notNull(ranger);

		Ranger result = null;
		if (ranger.getId() == 0) {
			result = this.rangerRepository.save(ranger);
			final Collection<Folder> folders = this.folderService.defaultFolders(result);
			result.setFolders(folders);
		} else
			result = this.rangerRepository.save(ranger);
		return result;
	}

	public void delete(final Ranger ranger) {

		this.folderService.deleteByActor(ranger);
		this.messageService.deleteByActor(ranger);
		this.socialIdentityService.deleteByActor(ranger);
		this.rangerRepository.delete(ranger);
		if (ranger.getCurriculum() != null)
			this.curriculumService.deleteByRanger(ranger);
	}

	// Other business methods -------------------------------------------------

	public Collection<Double> avgMinMaxDesvTripsPerRanger() {

		Collection<Double> result = null;
		result = this.rangerRepository.avgMinMaxDesvTripsPerRanger();
		return result;
	}

	public String ratioRangersWithCurriculum() {

		String result = null;
		result = this.rangerRepository.ratioRangersWithCurriculum();
		return result;
	}

	public String ratioRangersCurriculumWithEndorserRecords() {

		String result = null;
		result = this.rangerRepository.ratioRangersCurriculumWithEndorserRecords();
		return result;
	}

	public String ratioRangersSuspicious() {

		String result = null;
		result = this.rangerRepository.ratioRangersSuspicious();
		return result;
	}

	public Ranger findByPrincipal() {

		Ranger result = null;
		final UserAccount userAccount = LoginService.getPrincipal();
		result = this.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Ranger findByUserAccountId(final int userAccountId) {

		Ranger result = null;
		result = this.rangerRepository.findByUserAccountId(userAccountId);
		return result;
	}

}
