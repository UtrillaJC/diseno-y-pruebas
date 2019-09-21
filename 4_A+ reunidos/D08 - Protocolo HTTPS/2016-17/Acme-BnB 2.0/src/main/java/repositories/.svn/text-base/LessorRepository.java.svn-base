
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CreditCard;
import domain.Lessor;
import domain.Request;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Integer> {

	@Query("select l from Lessor l where l.userAccount.id = ?1")
	Lessor findByUserAccountId(int userAccountId);

	@Query("select distinct l.creditCard from Lessor l where l.creditCard.number=?1")
	CreditCard findCreditCardByNumber(String number);

	@Query("select count (r)/(select count(l) from Lessor l)*1.0 from Request r where r.status = 1")
	Double avgRequestAcceptedForLessor();

	@Query("select count (r)/(select count(l) from Lessor l)*1.0 from Request r where r.status = 2")
	Double avgRequestDeniedForLessor();

	@Query("select distinct l from Lessor l join l.properties p join p.requests r where r.status = 1 and r.size = (select max(r.size) from Lessor l join l.properties p join p.requests r where r.status = 1)")
	Collection<Lessor> lessorMoreRequestApproved();

	@Query("select distinct l from Lessor l join l.properties p join p.requests r where r.status = 2 and r.size = (select max(r.size) from Lessor l join l.properties p join p.requests r where r.status = 2)")
	Collection<Lessor> lessorMoreRequestDenied();

	@Query("select distinct l from Lessor l join l.properties p join p.requests r where r.status = 0 and r.size = (select max(r.size) from Lessor l join l.properties p join p.requests r where r.status = 0)")
	Collection<Lessor> lessorMoreRequestPending();

	@Query("select t1 from Tenant t1 join t1.requests r1 where r1.status = '1' and (select count(r2) from Request r2 join r2.tenant t2 where r2.status = '2' AND t2.id = t1.id group by t2) >= ALL (select count(r3) from Request r3 join r3.tenant t4 where r3.status = 2 group by t4 ) group by t1")
	Collection<Lessor> lessorRatioMaxApproved();

	@Query("select p.requests from Property p where p.lessor.id = ?1 ")
	Collection<Request> request(int lessorId);

	@Query("select distinct p.requests from Property p join p.requests r where r.status = 1 and p.lessor.id = ?1")
	Collection<Request> requestApproved(int lessorId);

	@Query("select l from Lessor l join l.properties p where ((p.requests.size)*1.0/(select p.requests.size from Property p join p.requests r where r.status = 'ACCEPTED')*1.0) = (select min(p.requests.size) from Property p join p.requests r where r.status = 'ACCEPTED')")
	Collection<Lessor> lessorRatioMinApproved();
}
