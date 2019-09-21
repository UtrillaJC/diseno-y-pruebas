package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("select c from Comment c order by c.momentCreated desc")
	Collection<Comment> findAllByMomentCreatedDesc();
	
	@Query("select c from Comment c where c.nameRecipe = ?1")
	Collection<Comment> findAllByNameRecipe(String nameRecipe);
}
