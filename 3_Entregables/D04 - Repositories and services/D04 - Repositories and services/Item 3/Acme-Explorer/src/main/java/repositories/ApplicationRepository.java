
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.Explorer;
import domain.Trip;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select e.applications from Explorer e where e.id = ?1")
	Collection<Application> findApplicationByExplorer(int id);

	@Query("select a from Application a where a.trip.manager.id=?1")
	Collection<Application> findApplicationByManager(int id);

	@Query("select a from Application a where a.applicant = ?1")
	Collection<Application> findByExplorer(Explorer explorer);

	@Query("select a.applicant from Application a where a.trip = ?1 ")
	Collection<Explorer> findApplicantsToTrip(Trip t);

	@Query("select concat( 100 * (select count(a) from Application a where a.status = 'PENDING') / count(b), '%')from Application b")
	Double pendingApplications();//C5

	@Query("select concat( 100 * (select count(a) from Application a where a.status = 'DUE') / count(b), '%')from Application b")
	Double dueApplications();//C6

	@Query("select concat( 100 * (select count(a) from Application a where a.status = 'ACCEPTED') / count(b), '%')from Application b")
	Double acceptedApplications();//C7

	@Query("select concat( 100 * (select count(a) from Application a where a.status = 'CANCELLED') / count(b), '%')from Application b")
	Double cancelledApplications();//C8
}
