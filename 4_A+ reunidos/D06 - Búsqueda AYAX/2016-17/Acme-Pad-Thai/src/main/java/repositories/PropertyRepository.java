package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer>{

	@Query("select p from Property p where ?1 not member of p.ingredients")
	Collection<Property> findAllByNotIngredientId(int ingredientId);
	
	@Query("select p from Property p where ?1 member of p.ingredients")
	Collection<Property> findAllByIngredientId(int ingredientId);

}
