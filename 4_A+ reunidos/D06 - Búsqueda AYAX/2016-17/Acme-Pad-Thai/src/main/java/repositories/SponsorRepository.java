package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Sponsor;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer>{
	
	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findByUserAccountId(int userAccountId);
	
	@Query("select s from Sponsor s join s.campaigns c join c.bills b where b.momentPaid is null and DATEDIFF(CURRENT_DATE, b.momentCreated) >= 30")
	Collection<Sponsor> findAllByUnpaidLastMonth();

	//Dashboard =============================================================================

	@Query("select s.nameCompany from Sponsor s order by s.campaigns.size desc")
	Collection<String> rankingsOfCompaniesForCampaigns();
	
	@Query("select s.nameCompany from Sponsor s join s.campaigns c order by c.bills.size desc")
	Collection<String> rankingsOfCompaniesForBills();
	
	@Query("select distinct s from Sponsor s join s.campaigns camp where (day(camp.dateEnd) < day(CURRENT_DATE) and month(camp.dateEnd) <= month(CURRENT_DATE)-3 and year(camp.dateEnd) = year(CURRENT_DATE)) or (month(camp.dateStart) >= month(CURRENT_DATE) and year(camp.dateStart) >= year(CURRENT_DATE)) and (month(camp.dateEnd) >= month(CURRENT_DATE) and year(camp.dateEnd) >= year(CURRENT_DATE))")
	Collection<Sponsor> sponsorsWhoNotManagedCampaignLastThreeMonths();
	
	@Query("select distinct s.nameCompany from Sponsor s join s.campaigns c join c.bills b where b.cost <= (select avg(b.cost) from Campaign c join c.bills b)")
	Collection<String> companiesSpentLessAvgCampaigns();
	
	@Query("select distinct s.nameCompany from Sponsor s join s.campaigns c join c.bills b where b.cost >= (select max(b.cost) from Sponsor s join s.campaigns c join c.bills b)*0.9")
	Collection<String> companiesSpentLeast90PerCent();
}
