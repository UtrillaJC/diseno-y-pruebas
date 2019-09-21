
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.CreditCard;
import domain.Lessor;
import domain.Property;
import domain.Request;
import domain.SocialIdentity;
import domain.Tenant;
import forms.CreditCardForm;
import forms.LessorRegistrationForm;
import repositories.LessorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class LessorService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private LessorRepository		lessorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private RequestService			requestService;

	@Autowired
	private ConfigurationService	configurationService;

	@Autowired
	private TenantService			tenantService;


	// Constructors-------------------------------------------------------
	public LessorService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public Lessor create() {
		Lessor result;

		UserAccount userAccount;
		Authority authority;

		result = new Lessor();
		userAccount = new UserAccount();
		authority = new Authority();
		Collection<Property> properties = new ArrayList<Property>();
		Collection<Comment> commentReceivers;
		Collection<Comment> commentCreates;
		Collection<SocialIdentity> socialIdentities;

		commentReceivers = new HashSet<Comment>();
		commentCreates = new HashSet<Comment>();
		socialIdentities = new HashSet<SocialIdentity>();

		result.setProperties(properties);
		result.setAmount(0.0);
		result.setCommentCreates(commentCreates);
		result.setCommentReceivers(commentReceivers);
		result.setSocialIdentities(socialIdentities);

		authority.setAuthority(Authority.LESSOR);
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		return result;
	}

	public Collection<Lessor> findAll() {
		Collection<Lessor> lessors;

		lessors = this.lessorRepository.findAll();
		Assert.notNull(lessors);

		return lessors;
	}

	public Lessor findOne(int lessorId) {
		Lessor lessor;

		lessor = this.lessorRepository.findOne(lessorId);
		Assert.notNull(lessor);

		return lessor;
	}

	public Lessor save(Lessor lessor) {
		Lessor newLessor;
		Assert.notNull(lessor);

		String password = lessor.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		lessor.getUserAccount().setPassword(password);

		int actualMonth, actualYear;

		actualMonth = DateTime.now().getMonthOfYear();
		actualYear = DateTime.now().getYear();

		Assert.isTrue(lessor.getCreditCard().getExpirationYear() > actualYear || ((lessor.getCreditCard().getExpirationYear() == actualYear) && (lessor.getCreditCard().getExpirationMonth() >= actualMonth)));

		newLessor = this.lessorRepository.save(lessor);
		return newLessor;
	}

	public Lessor update(Lessor lessor) {
		Lessor newLessor;
		Assert.notNull(lessor);

		newLessor = lessorRepository.saveAndFlush(lessor);

		return newLessor;
	}

	public void delete(Lessor lessor) {
		Assert.notNull(lessor);

		this.lessorRepository.delete(lessor);
	}

	public void increaseAmount() {
		Lessor l = findByPrincipal();
		Double amount = l.getAmount();
		l.setAmount(amount + configurationService.findFee());
		update(l);
	}

	// Other business methods
	// -----------------------------------------------------------

	public Lessor findByPrincipal() {
		Lessor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	private Lessor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Lessor result;

		result = lessorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public void checkPrincipal() {
		UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		Authority auth = new Authority();
		auth.setAuthority("LESSOR");

		Assert.isTrue(authorities.contains(auth));

	}

	public Lessor reconstruct(LessorRegistrationForm form) {
		Lessor lessor;

		lessor = this.create();

		lessor.getUserAccount().setUsername(form.getUsername());
		lessor.getUserAccount().setPassword(form.getPassword());
		lessor.setName(form.getName());
		lessor.setSurname(form.getSurname());
		lessor.setEmail(form.getEmail());
		lessor.setPhone(form.getPhone());
		lessor.setPicture(form.getPicture());

		CreditCard creditCard = new CreditCard();
		creditCard.setBrandName(form.getCreditCard().getBrandName());
		creditCard.setHolderName(form.getCreditCard().getHolderName());
		creditCard.setNumber(form.getCreditCard().getNumber());
		creditCard.setExpirationMonth(form.getCreditCard().getExpirationMonth());
		creditCard.setExpirationYear(form.getCreditCard().getExpirationYear());
		creditCard.setcvvCode(form.getCreditCard().getcvvCode());

		lessor.setCreditCard(creditCard);

		return lessor;

	}

	public Double avgRequestAcceptedForLessor() {
		Double result;
		Double requestAccepted = requestService.requestAccepted();
		if (requestAccepted != 0.0) {
			result = this.lessorRepository.avgRequestAcceptedForLessor();
		} else {
			result = 0.0;
		}

		return result;
	}

	public Double avgRequestDeniedForLessor() {
		Double result;
		Double requestDenied = requestService.requestDenied();
		if (requestDenied != 0.0) {
			result = this.lessorRepository.avgRequestDeniedForLessor();
		} else {
			result = 0.0;
		}

		return result;
	}

	public Collection<Lessor> lessorMoreRequestApproved() {
		Collection<Lessor> result;

		result = this.lessorRepository.lessorMoreRequestApproved();
		Assert.notNull(result);

		return result;
	}

	public Collection<Lessor> lessorMoreRequestDenied() {
		Collection<Lessor> result;

		result = this.lessorRepository.lessorMoreRequestDenied();
		Assert.notNull(result);

		return result;
	}

	public Collection<Lessor> lessorMoreRequestPending() {
		Collection<Lessor> result;

		result = this.lessorRepository.lessorMoreRequestPending();
		Assert.notNull(result);

		return result;
	}

	public Collection<Lessor> findByTenantPrincipal() {
		Tenant tenant;
		Collection<Lessor> result = new ArrayList<Lessor>();
		Collection<Lessor> lessors = this.findAll();

		tenant = this.tenantService.findByPrincipal();
		for (Request r : tenant.getRequests()) {
			for (Lessor l : lessors) {
				for (Property p : l.getProperties()) {
					if (p.getRequests().contains(r) && !result.contains(l)) {
						result.add(l);
					}
				}
			}
		}

		return result;
	}

	public boolean checkCreditCard(Lessor l) {
		
		//its number must be run through Luhn's algorithm to checksum it;
		CreditCard cc = l.getCreditCard();
		String number = cc.getNumber();
		number = number.replace(" ","");
		int length = number.length();
		int sum = 0;
		int checksum = Integer.parseInt(number.substring(length - 1));
		boolean aux = true;
		String workNumber = number.substring(0, length - 1);
		for (int i = workNumber.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(number.substring(i, i + 1));
			if (aux) {
				n *= 2;
				if (n > 9) {
					n -= 9;
				}
			}
			sum += n;
			aux = !aux;

		}

		Boolean b1 = (sum % 10 == 10 - checksum);

		//Check the expiry date must be confirmed to be at least seven days in future.
		Calendar expirationdate = Calendar.getInstance();
		expirationdate.set(cc.getExpirationYear(), cc.getExpirationMonth(), 1);
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, 6);
		Boolean b2 = now.before(expirationdate);

		return (b1 && b2);
	}

	//	public Collection<Lessor> lessorRatioMaxApproved() {
	//		Collection<Lessor> result;
	//
	//		result = this.lessorRepository.lessorRatioMaxApproved();
	//		Assert.notNull(result);
	//
	//		return result;
	//	}

	//The lessor/s whose ratio of requested versus approved request/s 
	public Double lessorRatioApproved(Lessor lessor) {
		Double d = 0.0;
		Integer numRequest = lessorRepository.request(lessor.getId()).size();
		Double numReq = numRequest.doubleValue();
		Integer numRequestApproved = lessorRepository.requestApproved(lessor.getId()).size();
		Double numReqApp = numRequestApproved.doubleValue();
		if (numReqApp.compareTo(0.0) == 0) {
			d = 0.0;
		} else {
			d = (numReqApp / numReq);
		}

		return d;
	}

	public Collection<Lessor> lessorRatioMaxApproved() {
		Collection<Lessor> lessorAll = findAll();
		Collection<Lessor> result1 = new ArrayList<Lessor>();
		Collection<Lessor> result2 = new ArrayList<Lessor>();
		Double d = 0.0;

		for (Lessor l : lessorAll) {
			double ratio = lessorRatioApproved(l);
			if (ratio >= d) {
				result1.add(l);
				d = ratio;
			}
		}

		for (Lessor l : result1) {

			double ratio = lessorRatioApproved(l);
			if (ratio >= d) {
				result2.add(l);
			}
		}

		return result2;
	}

	public Collection<Lessor> lessorRatioMinApproved() {
		Collection<Lessor> lessorAll = findAll();
		Collection<Lessor> result1 = new ArrayList<Lessor>();
		Collection<Lessor> result2 = new ArrayList<Lessor>();
		Double d = 1.0;

		for (Lessor l : lessorAll) {
			double ratio = lessorRatioApproved(l);
			if (ratio <= d) {
				result1.add(l);
				d = ratio;
			}
		}

		for (Lessor l : result1) {

			double ratio = lessorRatioApproved(l);
			if (ratio <= d) {
				result2.add(l);
			}
		}

		return result2;
	}

	public void updateCreditCard(Lessor lessorUpdate, CreditCardForm creditCard) {
		CreditCard c = new CreditCard();
		
		c.setBrandName(creditCard.getBrandName());
		c.setcvvCode(creditCard.getcvvCode());
		c.setExpirationMonth(creditCard.getExpirationMonth());
		c.setExpirationYear(creditCard.getExpirationYear());
		c.setHolderName(creditCard.getHolderName());
		c.setNumber(creditCard.getNumber());
		
		lessorUpdate.setCreditCard(c);
		Assert.isTrue(checkCreditCard(lessorUpdate));
	
	}
}
