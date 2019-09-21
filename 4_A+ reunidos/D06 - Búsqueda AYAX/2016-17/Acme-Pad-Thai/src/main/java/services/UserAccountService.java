package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import security.UserAccount;
import security.UserAccountRepository;

@Service
@Transactional
public class UserAccountService {
	//Managed Repository =============================================================================
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	//Supported Services =============================================================================
	
	
	//Constructor methods ============================================================================
	
	public UserAccountService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Collection<UserAccount> findAll(){
		Collection<UserAccount> result;
		
		result = userAccountRepository.findAll();
		
		return result;
	}

	//Other Business Methods =========================================================================


}
