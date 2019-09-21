package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ValueAttribute;

@Repository
public interface ValueAttributeRepository extends JpaRepository<ValueAttribute, Integer>{

	@Query("select va from ValueAttribute va where va.property.id = ?1")
	Collection<ValueAttribute> findAllByPropertyId(int propertyId);
	
}
