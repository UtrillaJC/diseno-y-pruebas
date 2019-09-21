
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.CommentClass;

@Repository
public interface CommentClassRepository extends JpaRepository<CommentClass, Integer> {

}
