package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.IngredientLine;

@Repository
public interface IngredientLineRepository extends JpaRepository<IngredientLine, Integer>{

	@Query("select r.ingredientLines from Recipe r where r.id = ?1")
	Collection<IngredientLine> findAllByRecipeId(int recipeId);
}
