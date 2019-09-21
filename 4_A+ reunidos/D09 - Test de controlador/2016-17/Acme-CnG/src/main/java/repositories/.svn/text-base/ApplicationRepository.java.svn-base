
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	

	@Query("select a from Application a where a.customer.id = ?1 and a.service.id = ?2")
	Collection<Application> findAllMyApplicationsByServiceId(int customerId, int serviceId);

	@Query("select a from Application a where a.service.customer.id = ?1 and a.service.id = ?2")
	Collection<Application> findAllApplicationsByServiceCustomerId(int customerId, int serviceId);
	
}
