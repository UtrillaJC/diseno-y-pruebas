
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SurvivalClass;

@Repository
public interface SurvivalClassRepository extends JpaRepository<SurvivalClass, Integer> {

	@Query("select s from SurvivalClass s where s.trip.id = ?1")
	SurvivalClass findByTrip(int tripID);

	@Query("select m.survivalClasses from Manager m where m.id=?1")
	Collection<SurvivalClass> findByManager(int id);

}
