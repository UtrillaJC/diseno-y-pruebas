package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MasterClass;

@Repository
public interface MasterClassRepository extends JpaRepository<MasterClass, Integer>{

	@Query("select mc from MasterClass mc where mc.cook.id = ?1")
	Collection<MasterClass> findAllByCookId(int cookId);
	
	@Query("select m from MasterClass m where ?1 not member of m.users")
	Collection<MasterClass> findAllByNotUserId(int userId);
	
	@Query("select m from MasterClass m where ?1 member of m.users")
	Collection<MasterClass> findAllByUserId(int userId);
	
	@Query("select m from MasterClass m where m.isPromoted = true")
	Collection<MasterClass> findAllPromoted();
	
	@Query("select m from MasterClass m where m.isPromoted = false")
	Collection<MasterClass> findAllDemoted();
		
	//Dashboard =============================================================================

	@Query("select min(c.masterClasses.size) from Cook c")
	Double minMasterClassesCook();
	
	@Query("select max(c.masterClasses.size) from Cook c")
	Double maxMasterClassesCook();
	
	@Query("select avg(c.masterClasses.size) from Cook c")
	Double avgMasterClassesCook();
	
	@Query("select stddev(c.masterClasses.size) from Cook c")
	Double stddevMasterClassesCook();
}
