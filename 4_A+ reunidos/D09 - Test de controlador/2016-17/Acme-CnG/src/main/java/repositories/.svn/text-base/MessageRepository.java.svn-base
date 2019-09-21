
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select m from Message m where m.sender.id = ?1 and m.recipientCopy = false")
	Collection<Message> findSendMessagesByActor(int actorId);
	
	@Query("select m from Message m where m.recipient.id = ?1 and m.recipientCopy = true")
	Collection<Message> findreceivedMessagesByActor(int actorId);

//	The minimum, the average, and the maximum number of messages sent per actor.
	@Query("select min(a.sentMessages.size) from Actor a")
	Double minMessagePerActor();
	
	@Query("select max(a.sentMessages.size) from Actor a")
	Double maxMessagePerActor();

	@Query("select avg(a.sentMessages.size) from Actor a")
	Double avgMessagePerActor();


//The minimum, the average, and the maximum number of messages received per actor.
	@Query("select min(a.receivedMessages.size) from Actor a")
	Double minMessageReceivedPerActor();
	
	@Query("select max(a.receivedMessages.size) from Actor a")
	Double maxMessageReceivedPerActor();

	@Query("select avg(a.receivedMessages.size) from Actor a")
	Double avgMessageReceivedPerActor();

//The actors who have sent more messages.
	@Query("select a from Actor a where a.sentMessages.size = (select max(a.sentMessages.size ) from Actor a)")
	Collection<Actor> actorMoreSentMessage();
	
//The actors who have got more messages.
	@Query("select a from Actor a where a.receivedMessages.size = (select max(a.receivedMessages.size) from Actor a)")
	Collection<Actor> actorMoreReceiverMessage();
}
