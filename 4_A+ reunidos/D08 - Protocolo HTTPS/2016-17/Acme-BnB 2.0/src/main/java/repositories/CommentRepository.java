
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("select c from Comment c where c.author.id = ?1")
	Collection<Comment> findCommentCreatesLessorId(int lessorId);

	@Query("select c from Comment c where c.receiver.id = ?1")
	Collection<Comment> findCommentReceiverLessorId(int lessorId);

}
