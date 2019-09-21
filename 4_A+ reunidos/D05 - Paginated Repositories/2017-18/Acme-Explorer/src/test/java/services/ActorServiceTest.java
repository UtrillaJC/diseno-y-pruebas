
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ActorServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private ActorService	actorService;


	// Tests ----------------------------------------------

	@Test
	public void testFindByPrincipal() {

		Actor actor = null;

		super.authenticate("admin1");

		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor.getUserAccount().getUsername().equals("admin1"));

		super.unauthenticate();
	}
}
