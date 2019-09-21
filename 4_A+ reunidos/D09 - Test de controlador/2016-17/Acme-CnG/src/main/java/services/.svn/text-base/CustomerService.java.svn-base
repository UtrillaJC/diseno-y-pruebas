
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Comment;
import domain.Customer;
import domain.Message;
import forms.RegisterForm;

@Service
@Transactional
public class CustomerService {

	//Managed Repository =============================================================================

	@Autowired
	private CustomerRepository customerRepository;

	//Supported Services =============================================================================
	
	@Autowired
	private AdministratorService administratorService;
	//Constructor methods ============================================================================

	public CustomerService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	public Customer findOne(final int customerId) {
		Customer result;

		result = this.customerRepository.findOne(customerId);

		return result;
	}

	public Collection<Customer> findAll() {
		Collection<Customer> result;

		result = this.customerRepository.findAll();

		return result;
	}

	public Customer create() {
		Customer result;
		UserAccount userAccount;
		Authority authority;

		Collection<Message> sentMessages;
		Collection<Message> receivedMessages;
		Collection<Comment> createdComments;
		Collection<Application> applications;

		authority = new Authority();
		userAccount = new UserAccount();
		sentMessages = new HashSet<Message>();
		receivedMessages = new HashSet<Message>();
		createdComments = new HashSet<Comment>();
		applications = new HashSet<Application>();

		authority.setAuthority("CUSTOMER");
		userAccount.addAuthority(authority);

		result = new Customer();

		result.setUserAccount(userAccount);
		result.setSentMessages(sentMessages);
		result.setReceivedMessages(receivedMessages);
		result.setCreatedComments(createdComments);
		result.setApplications(applications);

		return result;
	}

	public Customer save(final Customer customer) {
		Assert.notNull(customer);
		Customer result;

		String password = customer.getUserAccount().getPassword();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		customer.getUserAccount().setPassword(password);

		result = this.customerRepository.saveAndFlush(customer);

		return result;
	}

	public Customer update(final Customer customer) {
		Assert.notNull(customer);

		Customer result;

		result = this.customerRepository.saveAndFlush(customer);

		return result;

	}

	//Other Business Methods =========================================================================

	public Customer findByPrincipal() {
		Customer result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Customer findByUserAccount(final UserAccount userAccount) {
		Customer result;

		result = this.customerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public void checkPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		final Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		final Authority auth = new Authority();
		auth.setAuthority("CUSTOMER");

		Assert.isTrue(authorities.contains(auth));

	}

	public Customer reconstruct(final RegisterForm form) {
		Customer customer;
		
		customer = this.create();

		customer.getUserAccount().setUsername(form.getUsername());
		customer.getUserAccount().setPassword(form.getPassword());
		customer.setName(form.getName());
		customer.setEmail(form.getEmail());
		customer.setPhone(form.getPhone());

		return customer;

	}

	public Collection<Customer> customerMoreAppAcepted() {
		administratorService.checkPrincipal();
		Collection<Customer> result;

		result = this.customerRepository.customerMoreAppAcepted();
		Assert.notNull(result);

		return result;
	}

	public Collection<Customer> customerMoreAppDenied() {
		administratorService.checkPrincipal();
		Collection<Customer> result;

		result = this.customerRepository.customerMoreAppDenied();
		Assert.notNull(result);

		return result;
	}
}
