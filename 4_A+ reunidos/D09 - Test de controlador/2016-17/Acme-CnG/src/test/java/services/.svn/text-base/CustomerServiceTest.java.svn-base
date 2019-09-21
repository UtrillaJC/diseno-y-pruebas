
package services;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import forms.RegisterForm;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CustomerServiceTest extends AbstractTest {

	// The SUT ====================================================================================

	@Autowired
	private CustomerService	customerService;


	// Tests =======================================================================================

	@Test
	public void driverCustomerMoreAppAcepted() {
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
			this.templateCustomerMoreAppAcepted((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateCustomerMoreAppAcepted(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.customerService.customerMoreAppAcepted();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverCustomerMoreAppDenied() {
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
			this.templateCustomerMoreAppDenied((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateCustomerMoreAppDenied(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.customerService.customerMoreAppDenied();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}


	@Test
	public void driverCustomerRegisterForm() {
		final Object testingData[][] = {
			 // Register to the system as a user
			{
				true, "namePrueba", "userNamePrueba", "passwordPrueba", "passwordPrueba", "+34 636435557", "email@gmail.es", null //Nos registramos en el sistema con todos los datos correctos.Positivo.	

			},
			{
				true, null, "userNamePrueba2", "passwordPrueba2", "passwordPrueba2", "+34 636435557", "email@gmail.es", ConstraintViolationException.class //Nombre = null. NEGATIVO.			
			}	
};

		for (int i = 0; i < testingData.length; i++)
			this.templateCustomerRegisterForm((Boolean) testingData[i][0], (String) testingData[i][1],
				(String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4],
				(String) testingData[i][5], (String) testingData[i][6], (Class<?>) testingData[i][7]);
	}

	public void templateCustomerRegisterForm(Boolean contractAccepted, String name, String username, String password,
		String verifyPassword, String phone, String email, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			RegisterForm urf = new RegisterForm();
			urf.setContractAccepted(contractAccepted);
			urf.setName(name);
			urf.setUsername(username);
			urf.setPassword(password);
			urf.setVerifyPassword(verifyPassword);
			urf.setPhone(phone);
			urf.setEmail(email);

			customerService.save(this.customerService.reconstruct(urf));
			


		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
	