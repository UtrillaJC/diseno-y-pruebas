
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Ranger;

@Repository
public interface RangerRepository extends JpaRepository<Ranger, Integer> {

	@Query("select avg(r.trips.size), min(r.trips.size), max(r.trips.size), sqrt(sum(r.trips.size * r.trips.size)/count(t) - (avg(r.trips.size) * avg(r.trips.size))) from Ranger r, Trip t")
	Collection<Double> avgMinMaxDesvTripsPerRanger();

	@Query("select concat(100*(select count(r) from Ranger r where r.curriculum is not null)/count(a), '%') from Ranger a")
	String ratioRangersWithCurriculum();

	@Query("select concat(100*(select count(r) from Ranger r where r.curriculum.endorserRecords is not empty)/count(a), '%') from Ranger a")
	String ratioRangersCurriculumWithEndorserRecords();

	@Query("select concat(100*(select count(r) from Ranger r where r.suspicious is true)/count(a), '%') from Ranger a")
	String ratioRangersSuspicious();

	@Query("select r from Ranger r where r.userAccount.id = ?1")
	Ranger findByUserAccountId(int userAccountId);
}
