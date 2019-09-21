package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.CreditCard;
import domain.Sponsor;

@Service
@Transactional
public class CreditCardService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private SponsorService sponsorService;
	
	//Constructor methods ============================================================================
	
	public CreditCardService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public CreditCard findOne(int creditCardId){
		CreditCard result;
		
		result = creditCardRepository.findOne(creditCardId);
		
		return result;
	}

	
	public CreditCard create(){
		CreditCard result;
		Sponsor principal;
		
		principal = sponsorService.findByPrincipal();
		Assert.isInstanceOf(Sponsor.class, principal);
		
		result = new CreditCard();
		
		return result;
	}

	public CreditCard save(CreditCard creditCard){
		Assert.notNull(creditCard);
		CreditCard result;
		Sponsor principal;
		
		principal = sponsorService.findByPrincipal();
		Assert.isInstanceOf(Sponsor.class, principal);
		
		result = creditCardRepository.save(creditCard);
		
		return result;
	}
		
	//Other Business Methods =========================================================================
	
	public CreditCard findBySponsorId(int sponsorId){
		CreditCard result;
		
		result = creditCardRepository.findBySponsorId(sponsorId);
		
		return result;
	}
	
	
}
