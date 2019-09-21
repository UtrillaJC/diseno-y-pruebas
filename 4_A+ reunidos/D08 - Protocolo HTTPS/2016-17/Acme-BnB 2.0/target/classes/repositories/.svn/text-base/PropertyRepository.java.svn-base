
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

	@Query("select p from Property p where p.lessor.id = ?1")
	Collection<Property> findAllByLessorId(int lessorId);

	@Query("select p from Property p where p.lessor.id =?1 order by p.audits.size desc")
	Collection<Property> findAllByLessorOrderAuditsSizeId(int lessorId);

	@Query("select p from Property p where p.lessor.id =?1 order by p.requests.size desc")
	Collection<Property> findAllByLessorOrderRequestsSizeId(int lessorId);

	@Query("select p.id from Property p left join p.audits s where s.auditor.id = ?1 and s.draft=0")
	Collection<Integer> findByAuditorAudits(int auditorId);

	@Query("select count(f) from Finder f")
	Integer numFinder();

	@Query("select avg(p.audits.size) from Property p")
	Double avgAuditPerProperty();

	@Query("select max(p.audits.size) from Property p")
	Integer maxAuditPerProperty();

	@Query("select min(p.audits.size) from Property p")
	Integer minAuditPerProperty();

	@Query("select avg(p.requests.size) from Property p where p.audits is not empty")
	Double avgRequestPerProperty();

}
