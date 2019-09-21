
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccountId(int userAccountId);

	@Query("select a from Actor a where a.userAccount.username = ?1")
	Actor findByUsername(String username);

	@Query("select avg(a.socialIdentities.size) from Actor a")
	Double avgSocialIdentityPerActor();

	@Query("select min(a.socialIdentities.size) from Actor a")
	Integer minSocialIdentityPerActor();

	@Query("select max(a.socialIdentities.size) from Actor a")
	Integer maxSocialIdentityPerActor();
}
