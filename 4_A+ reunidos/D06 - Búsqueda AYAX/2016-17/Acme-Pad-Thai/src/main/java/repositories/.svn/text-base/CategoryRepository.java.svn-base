package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c where ?1 not member of c.recipes")
	Collection<Category> findAllByNotRecipeId(int recipeId);
	
	@Query("select c from Category c where ?1 member of c.recipes")
	Collection<Category> findAllByRecipeId(int recipeId);
	
	@Query("select c from Category c where c.parent is null or c.parent.id != ?1")
	Collection<Category> findAllParentCategory(int categoryId);
}
