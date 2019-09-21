
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	@Query("select avg(m.trips.size), min(m.trips.size), max(m.trips.size), sqrt(sum(m.trips.size * m.trips.size)/count(t) - (avg(m.trips.size) * avg(m.trips.size))) from Manager m, Trip t")
	Collection<Double> avgMinMaxDesvTripsPerManager();

	@Query("select concat(100*(select count(m) from Manager m where m.suspicious is true)/count(a), '%') from Manager a")
	String ratioManagersSuspicious();

	@Query("select m from Manager m where m.userAccount.id = ?1")
	Manager findByUserAccounId(int userAccountId);

}
