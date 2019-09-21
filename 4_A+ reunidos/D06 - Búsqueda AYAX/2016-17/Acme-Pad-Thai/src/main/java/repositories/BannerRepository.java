package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer>{

	@Query("select b from Banner b where b.campaign.id = ?1")
	Collection<Banner> findAllByCampaignId(int campaignId);
	
	@Query("select sum(b.numDisplayed) from Banner b where b.campaign.id = ?1")
	Integer sumNumDisplayedOfBannerForCampaignId(int campaignId);

	@Query("select b from Banner b where b.campaign.isStar = true")
	Collection<Banner> findBannerIsStar();
	
}
