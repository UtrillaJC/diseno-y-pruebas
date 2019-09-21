
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	// The SUT ====================================================================================

	@Autowired
	private ApplicationService	applicationService;


	// Tests =======================================================================================

	// Test Create =======================================================================================

	@Test
	public void driverCreate() {
		final Object testingData[][] = {
			{
				"customer3", 108, null //POSITIVO. un cliente realiza una aplicación.
		}, {
				"admin", 107, IllegalArgumentException.class //NEGATIVO. Usuario logueado como admin raliza un aplicación
		}, {
				"customer3", 107, IllegalArgumentException.class //NEGATIVO. Customer3 raliza una aplicación en uno de sus servicios
		}, {
				null, 108, IllegalArgumentException.class //NEGATIVO. Cliente no autenticado realiza una aplicación.
		}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreate((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	public void templateCreate(final String username, final int serviceId, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.applicationService.create(serviceId);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	
	// Test Accept =======================================================================================

	@Test
	public void driverAccept() {
		final Object testingData[][] = {
			{
				"customer3", 110, null	//POSITIVO. un cliente acepta una aplicación.
			}, 
			{
				"admin", 110, IllegalArgumentException.class	//NEGATIVO. Usuario logueado como admin acepta una aplicación.
			}, 
			{
				"customer3", 2, NullPointerException.class		//NEGATIVO. El identificador que se le pasa no es el de un servicio
			}, 
			{
				null, 106, IllegalArgumentException.class		//NEGATIVO. Cliente no autenticado acepta una aplicación.
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateAccept((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	public void templateAccept(final String username, final int applicationId, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.applicationService.acceptApplication(applicationId);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	// Test Deny =======================================================================================

		@Test
		public void driverDeny() {
			final Object testingData[][] = {
				{
					"customer3", 110, null		//POSITIVO. un cliente deniega una aplicación.
				}, 
				{
					"admin", 110, IllegalArgumentException.class	//NEGATIVO. Usuario logueado como admin deniega una aplicación.
				}, 
				{
					"customer3", 2, NullPointerException.class		//NEGATIVO. El identificador que se le pasa no es el de un servicio
				}, 
				{
					null, 106, IllegalArgumentException.class		//NEGATIVO. Cliente no autenticado deniega una aplicación.
				}

			};

			for (int i = 0; i < testingData.length; i++)
				this.templateAccept((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
		}

		public void templateDeny(final String username, final int applicationId, final Class<?> expected) {
			Class<?> caught;

			caught = null;

			try {
				this.authenticate(username);
				this.applicationService.denyApplication(applicationId);
				this.unauthenticate();

			} catch (final Throwable oops) {
				caught = oops.getClass();
			}
			this.checkExceptions(expected, caught);
		}

	

}
