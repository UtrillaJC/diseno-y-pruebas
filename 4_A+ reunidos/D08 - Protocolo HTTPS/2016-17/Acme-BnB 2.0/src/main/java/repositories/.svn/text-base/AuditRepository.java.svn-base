
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {
	
	@Query("select a from Audit a where a.draft is false and a.property.id = ?1")
	Collection<Audit> findNoDraftsByPropertyId(int propertyId);
	
	@Query("select a from Audit a where a.property.id = ?1 and a.auditor.id=?2")
	Collection<Audit> findByPropertyAndAuditor(int propertyId, int auditorId);
	
	@Query("select a from Audit a where a.property.id = ?1 and a.auditor.id=?2 and a.draft is true")
	Collection<Audit> findByDraftPropertyAndAuditor(int propertyId, int auditorId);

}
