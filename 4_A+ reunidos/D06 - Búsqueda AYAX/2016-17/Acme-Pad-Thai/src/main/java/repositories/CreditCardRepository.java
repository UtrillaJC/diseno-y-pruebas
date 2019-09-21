package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer>{
	
	@Query("select s.creditCard from Sponsor s where s.id = ?1")
	CreditCard findBySponsorId(int sponsorId);

}
