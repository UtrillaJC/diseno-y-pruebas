
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

	@Query("select count (r) from Request r where r.status = 1")
	Double requestAccepted();

	@Query("select count (r) from Request r where r.status = 2")
	Double requestDenied();

	@Query("select count (r) from Request r")
	Double requestAll();
}
