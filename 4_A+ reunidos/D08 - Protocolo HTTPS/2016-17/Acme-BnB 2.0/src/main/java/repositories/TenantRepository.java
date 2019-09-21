
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {

	@Query("select t from Tenant t where t.userAccount.id = ?1")
	Tenant findByUserAccountId(int userAccountId);

	@Query("select count (r)/(select count(t) from Tenant t)*1.0 from Request r where r.status = 1")
	Double avgRequestAcceptedForTenant();

	@Query("select count (r)/(select count(t) from Tenant t)*1.0 from Request r where r.status = 2")
	Double avgRequestDeniedForTenant();

	@Query("select distinct t from Tenant t join t.requests r where r.status = 1 and r.size = (select max(r.size) from Tenant t join t.requests r where r.status = 1)")
	Collection<Tenant> tenantMoreRequestApproved();

	@Query("select distinct t from Tenant t join t.requests r where r.status = 2 and r.size = (select max(r.size) from Tenant t join t.requests r where r.status = 2)")
	Collection<Tenant> tenantMoreRequestDenied();

	@Query("select distinct t from Tenant t join t.requests r where r.status = 0 and r.size = (select max(r.size) from Tenant t join t.requests r where r.status = 0)")
	Collection<Tenant> tenantMoreRequestPending();

	@Query("select l from Lessor l join l.properties p where ((p.requests.size)*1.0/(select p.requests.size from Property p join p.requests r where r.status = 1)*1.0) = (select max(p.requests.size) from Property p join p.requests r where r.status = 1)")
	Collection<Tenant> tenantRatioMaxApproved();

	@Query("select l from Lessor l join l.properties p where ((p.requests.size)*1.0/(select p.requests.size from Property p join p.requests r where r.status = 1)*1.0) = (select min(p.requests.size) from Property p join p.requests r where r.status = 1)")
	Collection<Tenant> tenantRatioMinApproved();

	@Query("select count(r) from Request r where r.tenant.id = ?1")
	Double numRequestTenant(int tenantId);

	@Query("select count(r) from Request r where r.tenant.id = ?1 and r.status = 1")
	Double numRequestTenantAccepted(int tenantId);

}
