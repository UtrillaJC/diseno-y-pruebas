
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Folder;
import domain.Message;
import domain.SocialIdentity;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SponsorRepository		sponsorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private FolderService			folderService;

	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private SocialIdentityService	socialIdentityService;

	@Autowired
	private MessageService			messageService;

	@Autowired
	private SponsorshipService		sponsorshipService;


	// Constructors -----------------------------------------------------------

	public SponsorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Sponsor create() {

		Sponsor result = null;
		result = new Sponsor();
		result.setDeactivated(false);
		result.setSponsorships(new ArrayList<Sponsorship>());
		result.setFolders(new ArrayList<Folder>());
		result.setRecipientMessages(new ArrayList<Message>());
		result.setSentMessages(new ArrayList<Message>());
		result.setSocialIdentities(new ArrayList<SocialIdentity>());
		final UserAccount userAccount = this.userAccountService.create("SPONSOR");
		result.setUserAccount(userAccount);
		return result;
	}

	public Sponsor findOne(final int sponsorId) {

		Sponsor result = null;
		result = this.sponsorRepository.findOne(sponsorId);
		return result;
	}

	public Collection<Sponsor> findAll() {

		Collection<Sponsor> result = null;
		result = this.sponsorRepository.findAll();
		return result;
	}

	public Sponsor save(final Sponsor sponsor) {

		Assert.notNull(sponsor);

		Sponsor result = null;
		if (sponsor.getId() == 0) {
			result = this.sponsorRepository.save(sponsor);
			final Collection<Folder> folders = this.folderService.defaultFolders(result);
			result.setFolders(folders);
		} else
			result = this.sponsorRepository.save(sponsor);
		return result;
	}

	public void delete(final Sponsor sponsor) {

		this.folderService.deleteByActor(sponsor);
		this.messageService.deleteByActor(sponsor);
		this.socialIdentityService.deleteByActor(sponsor);
		this.sponsorRepository.delete(sponsor);
		this.sponsorshipService.deleteBySponsor(sponsor);
	}

	// Other business methods -------------------------------------------------

	public Sponsor findByPrincipal() {

		Sponsor result = null;
		final UserAccount userAccount = LoginService.getPrincipal();
		result = this.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Sponsor findByUserAccountId(final int userAccountId) {

		Sponsor result = null;
		result = this.sponsorRepository.findByUserAccountId(userAccountId);
		return result;
	}

}
