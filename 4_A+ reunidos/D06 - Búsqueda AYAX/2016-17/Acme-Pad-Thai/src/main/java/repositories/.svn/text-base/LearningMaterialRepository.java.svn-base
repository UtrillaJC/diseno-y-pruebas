package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Cook;
import domain.LearningMaterial;

@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Integer>{
	
	@Query("select lm from LearningMaterial lm where lm.masterClass.id = ?1")
	Collection<LearningMaterial> findAllByMasterClassId(int masterClassId);
	
	//Dashboard =============================================================================
	
	@Query("select avg(mc.learningMaterials.size) from MasterClass mc join mc.learningMaterials lm order by lm")
	Double avgNumberLearningMaterialsGroupedLearningMaterials();
	
	@Query("select count(m) from MasterClass m where m.isPromoted = true")
	Integer numberOfMasterClassPromoted();
	
	@Query("select distinct c from Cook c join c.masterClasses mc where mc.isPromoted=true order by c.masterClasses.size desc")
	Collection<Cook> listOfCookOrderByNumMAsterClassPromoted();
	
	@Query("select count(mc)/(select count(c) from Cook c)*1.0 from MasterClass mc where mc.isPromoted=true")
	Double avgNumberPromotedPerCook();
	
	@Query("select count(mc)/(select count(c) from Cook c)*1.0 from MasterClass mc where mc.isPromoted=false")
	Double avgNumberDemotedPerCook();
	
}
