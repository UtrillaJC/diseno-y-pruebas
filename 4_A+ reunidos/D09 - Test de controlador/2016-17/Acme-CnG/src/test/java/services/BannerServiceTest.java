
package services;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Banner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class BannerServiceTest extends AbstractTest {

	// The SUT ====================================================================================

	@Autowired
	private BannerService	bannerService;


	// Tests =======================================================================================

	@Test
	public void driverFindOneToEdit() {
		final Object testingData[][] = {
			{
				"admin", this.bannerService.findBanner().getId(), null //POSITIVO Usuario logueado como admin llama al fomr de eidtar banner.
			}, {
				"customer1", this.bannerService.findBanner().getId(), IllegalArgumentException.class //NEGATIVO Usuario logueado que no puede editar Banner llama al form de Banner.
			}, {
				null, this.bannerService.findBanner().getId(), IllegalArgumentException.class //NEGATIVO Usuario no autenticado llama al form de Banner.
			}, {
				"admin", 144, IllegalArgumentException.class //NEGATIVO Usuario logueado como admin llama al form de Banner co un id que no pertenece a un objeto Banner
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateFindOneToEdit((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	public void templateFindOneToEdit(final String username, final int bannerId, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.bannerService.findOneToEdit(bannerId);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	@Test
	public void driverSave() {
		final Object testingData[][] = {
			{
				"admin", this.bannerService.findBanner().getId(), "http://image.com", null //POSITIVO Usuario logueado como admin llama al metodo guardar de Banner con todos los datos de Banner rellenos correctamente.
			}, {
				"admin", this.bannerService.findBanner().getId(), null, ConstraintViolationException.class //NEGATIVO Usuario logueado como admin llama al metodo guardar con el campo "text" a null
			}, {
				null, this.bannerService.findBanner().getId(), "http://image.com", IllegalArgumentException.class //NEGATIVO Usuario no autenticado llama al método guardar de Banner con todos los datos de Banner rellenos correctamente.
			}, {
				"customer1", this.bannerService.findBanner().getId(), "http://image.com", IllegalArgumentException.class //NEGATIVO Usuario autenticado que no debe de guardar un Banner llama al método de guardar Banner con todos us datos rellenos correctamente.
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateSave((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	public void templateSave(final String username, final int bannerId, String text, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			Banner banner;
			banner = bannerService.findOneToEdit(bannerId);
			banner.setText(text);
			this.bannerService.save(banner);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
