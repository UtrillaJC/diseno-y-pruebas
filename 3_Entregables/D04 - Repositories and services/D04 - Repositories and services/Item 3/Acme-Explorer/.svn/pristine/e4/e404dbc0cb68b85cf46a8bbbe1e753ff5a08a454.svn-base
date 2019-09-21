
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;
import domain.Trip;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select t from Trip t where t.category.id = ?1")
	Collection<Trip> findAllTripByCategoryID(int categoryId);

	@Query("select c from Category c where c.parentCategory.id = ?1")
	Collection<Category> findCategoryChildrenID(int categoryId);

}
