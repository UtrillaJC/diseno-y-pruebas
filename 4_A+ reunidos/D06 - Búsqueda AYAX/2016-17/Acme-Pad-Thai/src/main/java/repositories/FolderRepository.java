package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer>{
	
	@Query("select f from Folder f where f.userAccount.id = ?1")
	Collection<Folder> findByUserAccountId(int userAccountd);
	
	@Query("select f from Folder f where f.userAccount.id=?1 and f.name='Inbox'")
	Folder findActorInboxByUserAccountId(int userAccount);
	
	@Query("select f from Folder f where f.userAccount.id=?1 and f.name='Outbox'")
	Folder findActorOutboxByUserAccountId(int userAccountId);
	
	@Query("select f from Folder f where f.userAccount.id=?1 and f.name='Trashbox'")
	Folder findActorTrashboxByUserAccountId(int userAccountId);
	
	@Query("select f from Folder f where f.userAccount.id=?1 and f.name='Spambox'")
	Folder findActorSpamboxByUserAccountId(int userAccountId);
	
	@Query("select f from Folder f where f.userAccount.id=?1 and f.name=?2")
	Folder findUserAccountFolderByName(int userAccountId, String folderName);
	
	@Query("select f from Folder f where f.userAccount.id=?1 and f.parent = null")
	Collection<Folder> findByUserAccountIdWithoutParent(int userAccountId);
	
	@Query("select f from Folder f where f.userAccount.id=?1 and f.parent.parent = null")
	Collection<Folder> findByUserAccountIdWithoutGrandparent(int userAccountId);
	
	@Query("select f from Folder f where f.userAccount.id=?1 and f.parent.parent != null")
	Collection<Folder> findByUserAccountIdWithGrandparent(int userAccountd);

}
