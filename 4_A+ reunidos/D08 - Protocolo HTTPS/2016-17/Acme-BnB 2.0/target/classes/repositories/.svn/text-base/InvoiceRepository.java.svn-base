
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	@Query("select i from Invoice i where i.request.tenant.id = ?1")
	Collection<Invoice> findAllByTenantId(int tenantId);

	@Query("select avg(t.requests.size) from Tenant t join t.requests r where r.invoice is not null")
	Double avgInvoiceIssued();

	@Query("select min(t.requests.size) from Tenant t join t.requests r where r.invoice is not null")
	Integer minInvoiceIssued();

	@Query("select max(t.requests.size) from Tenant t join t.requests r where r.invoice is not null")
	Integer maxInvoiceIssued();

	@Query("select sum(i.totalAmountDue) from Invoice i")
	Double totalAmount();

}
