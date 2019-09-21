package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PersonRepository;
import security.LoginService;
import security.UserAccount;
import domain.Person;

@Service
@Transactional
public class PersonService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private PersonRepository personRepository;
	
	//Supported Services =============================================================================
	
	//Constructor methods ============================================================================
	
	//Simple CRUD methods ============================================================================
	
	public Person findOne(int personId){
		Person result;
		
		result = personRepository.findOne(personId);
		
		return result;
	}
	
	public Collection<Person> findAll(){
		Collection<Person> result;
		
		result = personRepository.findAll();
		
		return result;
	}
	
	//Other Business Methods =========================================================================

	public Collection<Person> findAllByPerson(){
		Collection<Person> result;
		Person principal;
		
		principal = findByPrincipal();
		Assert.notNull(principal);
		
		result = personRepository.findAllByPersonId(principal.getId());
		
		result.remove(principal);
		
		return result;
	}
	
	public Collection<Person> findAllByNotPerson(){
		Collection<Person> result;
		Collection<Person> allPersons;
		Collection<Person> personsFollowings;

		Person principal;
		
		principal = findByPrincipal();
		
		result = findAll();
		allPersons = findAll();
		allPersons.remove(principal);
		personsFollowings = personRepository.findAllByPersonId(principal.getId());
		
		for(Person p : personsFollowings){
				if(allPersons.contains(p)){
					result.remove(p);
				}
			
		}
		result.remove(principal);
		
		return result;
	}
	
	public void follow(int personId) {
		Collection<Person> followers;
		Collection<Person> followings;
		Person principal;
		Person person;
		
		principal = findByPrincipal();
		person = findOne(personId);
		followings = principal.getFollowings();
		followers = person.getFollowers();		
		
		Assert.isTrue(!followings.contains(person));
		
		followings.add(person);
		followers.add(principal);

		personRepository.save(person);
	}

	public void unfollow(int personId) {
		Collection<Person> followings;
		Collection<Person> followers;
		Person person;
		Person principal;
		
		principal = findByPrincipal();
		person = findOne(personId);
		followings = principal.getFollowings();
		followers = person.getFollowers();				
		
		Assert.isTrue(followings.contains(person));
		
		followings.remove(person);
		followers.remove(principal);

		personRepository.save(person);	
	}
	
	public Person findByPrincipal() {
		Person result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Person findByUserAccount(UserAccount userAccount) {
		Person result;

		result = personRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
}
