
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.Trip;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select f from Finder f where f.explorer.userAccount.id = ?1")
	Finder findByUserAccountId(int userAccountId);

	@Query("select f from Finder f join f.trips t where t = ?1")
	Collection<Finder> findFinderByTrip(Trip trip);

}
