
package services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Actor;
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class MessageServiceTest extends AbstractTest {

	// The SUT ====================================================================================

	@Autowired
	private MessageService	messageService;
	
	@Autowired
	private ActorService	actorService;


	// Tests =======================================================================================

	@Test
	public void driverMinMessagePerActor() {
		final Object testingData[][] = {
		{
			"admin", null //logueado como un administrador. Positivo
		},{
			"customer1", IllegalArgumentException.class //logueado como un customer. Negativo
		},{
			null, IllegalArgumentException.class	//Sin loguear. Negativo
	}

};

		for (int i = 0; i < testingData.length; i++)
			this.templateMinMessagePerActor((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateMinMessagePerActor(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.messageService.minMessagePerActor();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverMaxMessagePerActor() {
		final Object testingData[][] = {
		{
			"admin", null //logueado como un administrador. Positivo
		},{
			"customer1", IllegalArgumentException.class //logueado como un customer. Negativo
		},{
			null, IllegalArgumentException.class	//Sin loguear. Negativo
	}

};

		for (int i = 0; i < testingData.length; i++)
			this.templateMaxMessagePerActor((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateMaxMessagePerActor(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.messageService.maxMessagePerActor();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	

	@Test
	public void driverAvgMessagePerActor() {
		final Object testingData[][] = {
		{
			"admin", null //logueado como un administrador. Positivo
		},{
			"customer1", IllegalArgumentException.class //logueado como un customer. Negativo
		},{
			null, IllegalArgumentException.class	//Sin loguear. Negativo
	}

};

		for (int i = 0; i < testingData.length; i++)
			this.templateAvgMessagePerActor((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateAvgMessagePerActor(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.messageService.avgMessagePerActor();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverMinMessageReceivedPerActor() {
		final Object testingData[][] = {
		{
			"admin", null //logueado como un administrador. Positivo
		},{
			"customer1", IllegalArgumentException.class //logueado como un customer. Negativo
		},{
			null, IllegalArgumentException.class	//Sin loguear
	}

};

		for (int i = 0; i < testingData.length; i++)
			this.templateMinMessageReceivedPerActor((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateMinMessageReceivedPerActor(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.messageService.minMessageReceivedPerActor();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	@Test
	public void driverMaxMessageReceivedPerActor() {
		final Object testingData[][] = {
		{
			"admin", null //logueado como un administrador. Positivo
		},{
			"customer1", IllegalArgumentException.class //logueado como un customer. Negativo
		},{
			null, IllegalArgumentException.class	//Sin loguear. Negativo
	}

};

		for (int i = 0; i < testingData.length; i++)
			this.templateMaxMessageReceivedPerActor((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateMaxMessageReceivedPerActor(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.messageService.maxMessageReceivedPerActor();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	@Test
	public void driverAvgMessageReceivedPerActor() {
		final Object testingData[][] = {
		{
			"admin", null //logueado como un administrador. Positivo
		},{
			"customer1", IllegalArgumentException.class //logueado como un customer. Negativo
		},{
			null, IllegalArgumentException.class	//Sin loguear. Negativo
	}

};

		for (int i = 0; i < testingData.length; i++)
			this.templateAvgMessageReceivedPerActor((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateAvgMessageReceivedPerActor(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.messageService.avgMessageReceivedPerActor();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverActorMoreSentMessage() {
		final Object testingData[][] = {
		{
			"admin", null //logueado como un administrador. Positivo
		},{
			"customer1", IllegalArgumentException.class //logueado como un customer. Negativo
		},{
			null, IllegalArgumentException.class	//Sin loguear. Negativo
	}

};

		for (int i = 0; i < testingData.length; i++)
			this.templateActorMoreSentMessage((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateActorMoreSentMessage(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.messageService.actorMoreSentMessage();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverActorMoreReceiverMessage() {
		final Object testingData[][] = {
		{
			"admin", null //logueado como un administrador. Positivo
		},{
			"customer1", IllegalArgumentException.class //logueado como un customer. Negativo
		},{
			null, IllegalArgumentException.class	//Sin loguear. Negativo
	}

};

		for (int i = 0; i < testingData.length; i++)
			this.templateActorMoreReceiverMessage((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateActorMoreReceiverMessage(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.messageService.actorMoreReceiverMessage();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void driverSaveMessage() throws MalformedURLException {
		Actor recipient;
		recipient = actorService.findOne(92);
		Collection<URL> attachments1;
		attachments1 = new HashSet<URL>();
		URL url1  = new URL("http://nereida.deioc.ull.es/~cleon/in.dat");
		attachments1.add(url1);
		
		final Object testingData[][] = {
			{
				"customer1", "Title", "Text", attachments1, recipient, null //POSITIVO Usuario logueado manda un mensaje correctamente a otro usuario.
			},{
				"admin", null, "Text", attachments1, recipient, ConstraintViolationException.class //NEGATIVO Usuario logueado manda un mensaje con el campo "title" en blanco a otro usuario.
			},{
				"admin", "Title", null, attachments1, recipient, ConstraintViolationException.class//NEGATIVO Usuario logueado manda un mensaje con el campo "text" en blanco a otro usuario.
			},{
				"admin", "Title", "Text", attachments1, null, ConstraintViolationException.class //NEGATIVO Usuario logueado manda un mensaje a un usuario que está a null.
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateSaveMessage((String) testingData[i][0], (String) testingData[i][1],
				(String) testingData[i][2], (Collection<URL>) testingData[i][3], (Actor) testingData[i][4],
				(Class<?>) testingData[i][5]);
	}

	public void templateSaveMessage(String username, String title, String text, Collection<URL> attachments, Actor recipient, Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			Message message;
			message = messageService.create();
			message.setTitle(title);
			message.setText(text);
			message.setAttachments(attachments);
			message.setRecipient(recipient);
			message.setRecipientCopy(false);
			this.messageService.save(message);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	@Test
	public void driverDeleteMessage(){
		
		final Object testingData[][] = {
			{
				"admin", null //POSITIVO Usuario logueado borra un mensaje suyo correctamente.
			},{
				"customer3", IllegalArgumentException.class //NEGATIVO Usuario logueado borra mensaje que no ha enviado ni recibido, es decir, que no es suyo.
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteMessage((String) testingData[i][0],
				(Class<?>) testingData[i][1]);
	}

	public void templateDeleteMessage(String username, Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			Message message;
			message = messageService.findOne(99);
			this.messageService.delete(message);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
