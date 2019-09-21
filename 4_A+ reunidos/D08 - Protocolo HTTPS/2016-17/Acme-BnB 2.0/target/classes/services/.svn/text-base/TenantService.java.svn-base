
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Finder;
import domain.Lessor;
import domain.Property;
import domain.Request;
import domain.SocialIdentity;
import domain.Tenant;
import forms.RegistrationForm;
import repositories.TenantRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class TenantService {

	//Managed Repository =============================================================================

	@Autowired
	private TenantRepository	tenantRepository;

	//Supported Services =============================================================================

	@Autowired
	private FinderService		finderService;

	@Autowired
	private LessorService		lessorService;

	@Autowired
	private RequestService		requestService;


	//Constructor methods ============================================================================

	public TenantService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	public Tenant findOne(int tenantId) {
		Tenant result;

		result = tenantRepository.findOne(tenantId);

		return result;
	}

	public Collection<Tenant> findAll() {
		Collection<Tenant> result;

		result = tenantRepository.findAll();

		return result;
	}

	public Tenant create() {
		Tenant result;
		UserAccount userAccount;
		Authority authority;
		Collection<Request> requests;
		Collection<Comment> commentCreates;
		Collection<Comment> commentReceivers;
		Collection<SocialIdentity> socialIdentities;

		authority = new Authority();
		userAccount = new UserAccount();
		requests = new HashSet<Request>();
		commentCreates = new HashSet<Comment>();
		commentReceivers = new HashSet<Comment>();
		socialIdentities = new HashSet<SocialIdentity>();

		authority.setAuthority("TENANT");
		userAccount.addAuthority(authority);

		result = new Tenant();

		result.setUserAccount(userAccount);
		result.setRequests(requests);
		result.setCommentCreates(commentCreates);
		result.setCommentReceivers(commentReceivers);
		result.setSocialIdentities(socialIdentities);

		return result;
	}

	public Tenant save(Tenant tenant) {
		Assert.notNull(tenant);
		Tenant result;

		String password = tenant.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		tenant.getUserAccount().setPassword(password);

		result = tenantRepository.save(tenant);

		return result;
	}

	public Tenant update(Tenant tenant) {
		Assert.notNull(tenant);

		Tenant result;

		result = tenantRepository.saveAndFlush(tenant);

		return result;

	}

	//Other Business Methods =========================================================================

	public Tenant findByPrincipal() {
		Tenant result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Tenant findByUserAccount(UserAccount userAccount) {
		Tenant result;

		result = tenantRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public void checkPrincipal() {
		UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		Authority auth = new Authority();
		auth.setAuthority("TENANT");

		Assert.isTrue(authorities.contains(auth));

	}

	public Tenant reconstruct(RegistrationForm form) {
		Tenant tenant;

		Finder finder = finderService.create();
		Finder finderSaved = finderService.save(finder);

		tenant = this.create();

		tenant.getUserAccount().setUsername(form.getUsername());
		tenant.getUserAccount().setPassword(form.getPassword());
		tenant.setName(form.getName());
		tenant.setSurname(form.getSurname());
		tenant.setEmail(form.getEmail());
		tenant.setPhone(form.getPhone());
		tenant.setPicture(form.getPicture());
		tenant.setFinder(finderSaved);

		return tenant;

	}

	public Double avgRequestAcceptedForTenant() {
		Double result;

		Double requestAccepted = requestService.requestAccepted();
		if (requestAccepted != 0.0) {
			result = this.tenantRepository.avgRequestDeniedForTenant();
		} else {
			result = 0.0;
		}

		return result;
	}
	public Double avgRequestDeniedForTenant() {
		Double result;
		Double requestDenied = requestService.requestDenied();
		if (requestDenied != 0.0) {
			result = this.tenantRepository.avgRequestDeniedForTenant();
		} else {
			result = 0.0;
		}

		return result;
	}

	public Collection<Tenant> tenantMoreRequestApproved() {
		Collection<Tenant> result;

		result = this.tenantRepository.tenantMoreRequestApproved();
		Assert.notNull(result);

		return result;
	}

	public Collection<Tenant> tenantMoreRequestDenied() {
		Collection<Tenant> result;

		result = this.tenantRepository.tenantMoreRequestDenied();
		Assert.notNull(result);

		return result;
	}

	public Collection<Tenant> tenantMoreRequestPending() {
		Collection<Tenant> result;

		result = this.tenantRepository.tenantMoreRequestPending();
		Assert.notNull(result);

		return result;
	}

	public Collection<Tenant> findByLessorPrincipal() {
		Lessor lessor;
		Collection<Tenant> result = new ArrayList<Tenant>();
		Collection<Tenant> tenants = this.findAll();

		lessor = this.lessorService.findByPrincipal();
		for (Property p : lessor.getProperties()) {
			for (Tenant t : tenants) {
				for (Request r : t.getRequests()) {
					if (p.getRequests().contains(r) && !result.contains(t)) {
						result.add(t);
					}
				}
			}
		}
		return result;
	}

	public Collection<Tenant> tenantRatioMaxApproved() {
		Collection<Tenant> tenants;
		Collection<Tenant> result1 = new ArrayList<Tenant>();
		Collection<Tenant> result2 = new ArrayList<Tenant>();
		Double numReqAccept = 0.0;
		Double numReq = 0.0;

		Double max = 0.0;
		Double actual = 0.0;

		tenants = tenantRepository.findAll();

		for (Tenant t : tenants) {
			numReqAccept = tenantRepository.numRequestTenantAccepted(t.getId());
			numReq = tenantRepository.numRequestTenant(t.getId());
			if (numReqAccept.compareTo(0.0) == 0) {
				actual = 0.0;
			} else {
				actual = numReqAccept / numReq;
			}
			if (max <= actual) {
				max = actual;
				result1.add(t);
			}
		}
		for (Tenant t : result1) {
			numReqAccept = tenantRepository.numRequestTenantAccepted(t.getId());
			numReq = tenantRepository.numRequestTenant(t.getId());
			if (numReqAccept.compareTo(0.0) == 0) {
				actual = 0.0;
			} else {
				actual = numReqAccept / numReq;
			}
			if (max.compareTo(actual) == 0) {
				result2.add(t);
			}
		}

		return result2;
	}

	public Collection<Tenant> tenantRatioMinApproved() {
		Collection<Tenant> tenants;
		Collection<Tenant> result1 = new ArrayList<Tenant>();
		Collection<Tenant> result2 = new ArrayList<Tenant>();
		Double numReqAccept = 0.0;
		Double numReq = 0.0;
		Double min = 1.0;
		Double actual = 0.0;

		tenants = tenantRepository.findAll();

		for (Tenant t : tenants) {
			numReqAccept = tenantRepository.numRequestTenantAccepted(t.getId());
			numReq = tenantRepository.numRequestTenant(t.getId());
			if (numReqAccept.compareTo(0.0) == 0) {
				actual = 0.0;
			} else {
				actual = numReqAccept / numReq;
			}
			if (min >= actual) {
				min = actual;
				result1.add(t);
			}
		}
		for (Tenant t : result1) {
			numReqAccept = tenantRepository.numRequestTenantAccepted(t.getId());
			numReq = tenantRepository.numRequestTenant(t.getId());
			if (numReqAccept.compareTo(0.0) == 0) {
				actual = 0.0;
			} else {
				actual = numReqAccept / numReq;
			}
			if (min.compareTo(actual) == 0) {
				result2.add(t);
			}
		}

		return result2;
	}
}
