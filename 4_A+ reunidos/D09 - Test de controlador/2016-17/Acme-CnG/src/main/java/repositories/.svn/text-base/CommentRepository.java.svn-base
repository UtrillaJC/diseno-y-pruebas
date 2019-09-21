

package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {


	@Query("select avg(a.receivedComments.size) from Actor a")
	Double avgCommentPerActor();

	@Query("select avg(s.receivedComments.size) from Service s where s.type = 0")
	Double avgCommentPerOffer();

	@Query("select avg(s.receivedComments.size) from Service s where s.type = 1")
	Double avgCommentPerRequest();

	@Query("select avg(a.createdComments.size) from Actor a")
	Double avgCommentPostedActor();
	
	@Query("select c from Comment c where c.id = ?1")
	Comment findOne(int commentId);
	
	@Query("select c.receivedComments from CommentClass c where c.id = ?1")
	Collection<Comment> findByCommentClass(int commentClassId);

	
	//The actors who have posted ±10% the average number of comments per actor
	@Query("select a from Actor a where a.createdComments.size > 0.9*(select avg(a.createdComments.size) from Actor a) and a.createdComments.size < 1.1*(select avg(a.createdComments.size) from Actor a)")
	Collection<Actor> postedActor10Avg();	
}
