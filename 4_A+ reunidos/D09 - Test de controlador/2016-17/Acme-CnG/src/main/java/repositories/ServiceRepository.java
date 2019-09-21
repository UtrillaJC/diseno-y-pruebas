
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

	@Query("select s from Service s where s.applications is empty")
	Collection<Service> findAllWithoutApplications();
	
	@Query("select s from Service s where s.customer.id=?1")
	Collection<Service> findServicesByCustomer(int customerId);
	
	@Query("select a.service from Application a where a.customer.id=?1")
	Collection<Service> findServiceWithMyApplication(int customerId);
	
	@Query("select s from Service s join s.applications a where a.customer.id !=?1 and s.customer.id != ?1")
	Collection<Service> findAllServicesApplicables(int customerId);

	@Query("select s from Service s where s.type = 0")
	Collection<Service> findAllOffers();

	@Query("select s from Service s where s.type = 1")
	Collection<Service> findAllRequests();

	@Query("select s from Service s where s.customer.id = ?1 and s.type = 0")
	Collection<Service> findAllOffersByCustomerId(int customerId);

	@Query("select s from Service s where s.customer.id = ?1 and s.type = 1")
	Collection<Service> findAllRequestsByCustomerId(int customerId);
	
	@Query("select s from Service s where s.customer.id != ?1 and s.type = 0 and s.banned = 0")
	Collection<Service> findAllOffersByNotCustomerId(int customerId);

	@Query("select s from Service s where s.customer.id != ?1 and s.type = 1 and s.banned = 0")
	Collection<Service> findAllRequestsByNotCustomerId(int customerId);

	@Query("select s from Service s where s.title like %?1% or s.description like %?1% or s.originPlace like %?1% or s.destinationPlace like %?1%")
	Collection<Service> searchServiceByWords(String keyWord);

	@Query("select (count(a)+0.0)/((select count(s) from Service s where s.type = 0)+0.0) from Application a")
	Double ratioOffers();

	@Query("select (count(r)+0.0)/((select count(s) from Service s)+0.0) from Service r where r.type = 1")
	Double ratioRequests();

	@Query("select avg(c.services.size) from Customer c")
	Double avgServicesPerCustomer();

	@Query("select avg(s.applications.size) from Service s where s.type = 0")
	Double avgApplicationsPerOffer();

	@Query("select avg(s.applications.size) from Service s where s.type = 1")
	Double avgApplicationsPerRequest();
}
