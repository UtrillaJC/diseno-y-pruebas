package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer>{
	
	@Query("select c from Campaign c where c.sponsor.id = ?1")
	Collection<Campaign> findAllBySponsorId(int sponsorId);
	
	@Query("select c from Campaign c where CURRENT_TIMESTAMP > c.dateEnd and c.bills is empty")
	Collection<Campaign> findAllFinished();
	
	//Dashboard =============================================================================
	
	@Query("select min(s.campaigns.size) from Sponsor s")
	Double findMinNumber();
	
	@Query("select avg(s.campaigns.size) from Sponsor s") 
	Double findAvgNumber();
	
	@Query("select max(s.campaigns.size) from Sponsor s")
	Double findMaxNumber();
	
	@Query("select min(s.campaigns.size) from Sponsor s join s.campaigns c where CURRENT_TIMESTAMP between c.dateStart and c.dateEnd")
	Double findMinNumberActiveCampaign();
	
	@Query("select count(c)/(select count(s) from Sponsor s)*1.0 from Campaign c where CURRENT_TIMESTAMP between c.dateStart and c.dateEnd")
	Double findAvgNumberActiveCampaign();
	
	@Query("select max(s.campaigns.size) from Sponsor s join s.campaigns c where CURRENT_TIMESTAMP between c.dateStart and c.dateEnd")
	Double findMaxNumberActiveCampaign();
}
