package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Endorser;

@Repository
public interface EndorserRepository extends JpaRepository<Endorser, Integer>{
	
	@Query("select e from Endorser e where ?1 not member of e.curricula")
	Collection<Endorser> findAllByNotCurriculumId(int curriculumId);
	
	@Query("select e from Endorser e join e.curricula c where c.id = ?1")
	Collection<Endorser> findAllByCurriculumId(int curriculumId);
	
}
