
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import security.LoginService;
import security.UserAccount;
import domain.Admin;
import domain.Folder;
import domain.Message;
import domain.SocialIdentity;

@Service
@Transactional
public class AdminService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AdminRepository			adminRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private FolderService			folderService;

	@Autowired
	private SocialIdentityService	socialIdentityService;


	// Constructors -----------------------------------------------------------

	public AdminService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Admin create() {

		Admin result = null;
		result = new Admin();
		result.setDeactivated(false);
		result.setFolders(new ArrayList<Folder>());
		result.setRecipientMessages(new ArrayList<Message>());
		result.setSentMessages(new ArrayList<Message>());
		result.setSocialIdentities(new ArrayList<SocialIdentity>());
		final UserAccount userAccount = this.userAccountService.create("ADMIN");
		result.setUserAccount(userAccount);
		return result;
	}

	public Admin findOne(final int adminId) {

		Admin result = null;
		result = this.adminRepository.findOne(adminId);
		return result;
	}

	public Collection<Admin> findAll() {

		Collection<Admin> result = null;
		result = this.adminRepository.findAll();
		return result;
	}

	public Admin save(final Admin admin) {

		Assert.notNull(admin);

		Admin result = null;
		if (admin.getId() == 0) {
			result = this.adminRepository.save(admin);
			final Collection<Folder> folders = this.folderService.defaultFolders(result);
			result.setFolders(folders);
		} else
			result = this.adminRepository.save(admin);
		return result;
	}

	public void delete(final Admin admin) {

		this.folderService.deleteByActor(admin);
		this.socialIdentityService.deleteByActor(admin);
		this.adminRepository.delete(admin);
	}

	// Other business methods -------------------------------------------------

	public Admin findByPrincipal() {

		Admin result = null;
		final UserAccount userAccount = LoginService.getPrincipal();
		result = this.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Admin findByUserAccountId(final int userAccountId) {

		Admin result = null;
		result = this.adminRepository.findByUserAccountId(userAccountId);
		return result;
	}

}
