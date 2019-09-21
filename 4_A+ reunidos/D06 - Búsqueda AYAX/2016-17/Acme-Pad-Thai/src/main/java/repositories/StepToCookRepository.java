package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.StepToCook;

@Repository
public interface StepToCookRepository extends JpaRepository<StepToCook, Integer>{

	//Dashboard =============================================================================

	@Query("select avg(r.stepsToCook.size) from Recipe r")
	Double avgStepsRecipe();

	@Query("select stddev(r.stepsToCook.size) from Recipe r")
	Double stddevStepsRecipe();
}
