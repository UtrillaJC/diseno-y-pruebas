package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>{

	@Query("select r from Recipe r join r.categories c order by c.id desc")
	Collection<Recipe> findAllOrderByCategories();
	
	@Query("select r from Recipe r join r.registrations rg where rg.contest.id = ?1 order by r.likes - r.dislikes desc")
	Collection<Recipe> findAllByContestId(int contestId);
	
	@Query("select r from Recipe r join r.contestsWon cw where cw.id = ?1")
	Collection<Recipe> findAllByContestIdWinners(int contestId);
	
	@Query("select r from Recipe r where r.user.id = ?1")
	Collection<Recipe> findAllByUserId(int userId);
	
	@Query("select r from Recipe r where r.user.id != ?1")
	Collection<Recipe> findAllByNotUserId(int userId);
	
	@Query("select r from Recipe r where r.ticker like %?1% or r.title like %?1% or r.summary like %?1%")
	Collection<Recipe> searchRecipeByWords(String keyWord);
	
	@Query("select r from Recipe r join r.registrations rg where rg.contest.id = ?1 order by r.likes - r.dislikes desc")
	Collection<Recipe> findRecipeOrderDescByLikesMinusDislikes(int contestId);
	
	@Query("select r.ticker from Recipe r order by r.ticker desc")
	List<String> searchLastTicker();

	@Query("select r from Recipe r where month(r.momentAuthored) <= month(CURRENT_DATE) and month(r.momentAuthored) >= month(CURRENT_DATE)-1 and year(r.momentAuthored) = year(CURRENT_DATE)")
	Collection<Recipe> findAllByMomentAuthored();
	
	//Dashboard =============================================================================

	@Query("select avg(u.recipes.size) from User u")
	Double avgRecipesUser();
	
	@Query("select min(u.recipes.size) from User u")
	Double minRecipesUser();

	@Query("select max(u.recipes.size) from User u")
	Double maxRecipesUser();
	
	@Query("select min(c.registrations.size) from Contest c")
	Double minRecipesHaveQualifiedContest();
	
	@Query("select avg(c.registrations.size) from Contest c")
	Double avgRecipesHaveQualifiedContest();
	
	@Query("select max(c.registrations.size) from Contest c")
	Double maxRecipesHaveQualifiedContest();
}
