package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{
	
	@Query("select p from Person p where p.userAccount.id = ?1")
	Person findByUserAccountId(int userAccountId);
	
	@Query("select p from Person p where ?1 not member of p.followings")
	Collection<Person> findAllByNotPersonId(int personId);
	
	@Query("select p.followings from Person p where p.id = ?1")
	Collection<Person> findAllByPersonId(int personId);
}
