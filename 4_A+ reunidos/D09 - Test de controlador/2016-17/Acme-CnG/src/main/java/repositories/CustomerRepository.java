
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where c.userAccount.id = ?1")
	Customer findByUserAccountId(int userAccountId);

	@Query("select c from Customer c join c.services s join s.applications a where a.status = 1 and a.size = (select max(a.size) from Customer c join c.services s join s.applications a where a.status = 1)")
	Collection<Customer> customerMoreAppAcepted();

	@Query("select c from Customer c join c.services s join s.applications a where a.status = 2 and a.size = (select max(a.size) from Customer c join c.services s join s.applications a where a.status = 2)")
	Collection<Customer> customerMoreAppDenied();
}
