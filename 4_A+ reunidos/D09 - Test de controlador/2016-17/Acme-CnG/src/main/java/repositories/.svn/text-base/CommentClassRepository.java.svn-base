
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CommentClass;

@Repository
public interface CommentClassRepository extends JpaRepository<CommentClass, Integer> {

	@Query("select c from CommentClass c where c.id = ?1")
	CommentClass findOne(int commentClassId);

}
