package repositories;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("select u from User u join u.recipes r where r.id = ?1")
	User findOneByRecipeId(int recipeId);
	
	@Query("select u from User u where u.userAccount.id = ?1")
	User findByUserAccountId(int userAccountId);
	
	@Query("select u from User u where u.name like %?1%")
	Collection<User> searchUserByWords(String keyWord);
	
	//Dashboard =============================================================================

	@Query("select u from User u where u.recipes.size = (select max(u.recipes.size)from User u)")
	Collection<User> usersAuthoredMoreRecipes();
	
	@Query("select u from User u order by u.followers.size desc")
	Collection<User> listUsersDescendingPopularity();
	
	@Query("select distinct u from User u join u.recipes r order by r.likes/u.recipes.size desc")
	Collection<User> listUsersLikes();
	
	@Query("select distinct u from User u join u.recipes r order by r.dislikes/u.recipes.size desc")
	Collection<User> listUsersDislikes();
	
}
