package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FeeRepository;
import domain.Administrator;
import domain.Fee;

@Service
@Transactional
public class FeeService {

	//Managed Repository =============================================================================
	
	@Autowired
	private FeeRepository feeRepository;
	
	//Supported Services =============================================================================

	@Autowired
	private AdministratorService administratorService;
	
	//Constructor methods ============================================================================
	
	public FeeService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Collection<Fee> findAll(){
		Collection<Fee> result;
		
		result = feeRepository.findAll();
		
		return result;
	}
	
	public Fee findOne(int feeId){
		Fee result;
		
		result = feeRepository.findOne(feeId);		
		
		return result;
	}
	
	public Fee findOneToEdit(int feeId){
		Fee result;
		Administrator principal;
		
		principal = administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		result = feeRepository.findOne(feeId);		
		
		return result;
	}
	
	public Fee create(){
		Fee result;
		Administrator principal;
		
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		
		result = new Fee();
		
		return result;
	}
	
	public Fee save(Fee fee){
		Assert.notNull(fee);
		Fee result;
		Administrator principal;
		
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		
		result = feeRepository.save(fee);
		
		return result;
	}
		
	//Other Business Methods =========================================================================
	

}
