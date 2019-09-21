package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>{
	
	@Query("select b from Bill b where month(b.momentCreated) = month(current_date) and year(b.momentCreated) = year(current_date)")
	Collection<Bill> findAllByMonth();
	
	@Query("select b from Bill b where b.momentPaid = null")
	Collection<Bill> findAllBillsUnpaid();
	
	@Query("select b from Bill b where b.campaign.sponsor.id = ?1")
	Collection<Bill> findAllBySponsorId(int sponsorId);	
	
	@Query("select b from Bill b where b.campaign.sponsor.id = ?1 and b.momentPaid != null")
	Collection<Bill> findAllBySponsorIdPaid(int sponsorId);	
	
	@Query("select b from Bill b where b.campaign.sponsor.id = ?1 and b.momentPaid = null")
	Collection<Bill> findAllBySponsorIdNotPaid(int sponsorId);	
	
	//Dashboard =============================================================================

	@Query("select count(b1)/(select count(b) from Bill b)*1.0 from Bill b1 where b1.momentPaid is not null")
	Double avgBillsPaid();	
	
	@Query("select count(b1)/(select count(b) from Bill b)*1.0 from Bill b1 where b1.momentPaid is null")
	Double avgBillsNotPaid();	
	
	@Query("select stddev(c.bills.size) from Campaign c join c.bills b where b.momentPaid is not null")
	Double stddevBillsPaid();
	
	@Query("select stddev(c.bills.size) from Campaign c join c.bills b where b.momentPaid is null")
	Double stddevBillsNotPaid();
	
	@Query("select count(b) from Bill b where b.momentPaid is not null") 
	Collection<Bill> getBillsPaid(int id, Date date);
}
