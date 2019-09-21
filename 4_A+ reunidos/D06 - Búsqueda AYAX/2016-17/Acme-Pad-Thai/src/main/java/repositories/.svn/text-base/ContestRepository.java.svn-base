package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contest;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Integer>{

	@Query("select c from Contest c where CURRENT_TIMESTAMP between c.momentOpening and c.momentClosing")
	Collection<Contest> findAllActive();
	
	@Query("select c from Contest c where CURRENT_TIMESTAMP >= c.momentClosing and c.winners is empty")
	Collection<Contest> findAllFinished();
	
	//Dashboard =============================================================================
	
	@Query("select c from Contest c where c.registrations.size = (select max(c.registrations.size)from Contest c)")
	Collection<Contest> contestswhichMoreRecipesQualified();
}
