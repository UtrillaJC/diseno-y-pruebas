package repositories;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{

	@Query("select i from Ingredient i join i.ingredientLines il where il.recipe.id = ?1")
	Collection<Ingredient> findAllByRecipeId(int recipeId);
	
	//Dashboard =============================================================================

	@Query("select stddev(r.ingredientLines.size) from Recipe r")
	Double sttdevIngredientsRecipe();
	
	@Query("select avg(r.ingredientLines.size) from Recipe r) from Recipe r")
	Double avgIngredientsRecipe();
}
