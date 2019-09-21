
package services;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Coordinates;
import domain.Type;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ServiceServiceTest extends AbstractTest {

	// The SUT ====================================================================================

	@Autowired
	private ServiceService	serviceService;
	
	@Autowired
	private CustomerService customerService;


	// Tests =======================================================================================

	@Test
	public void driverGetServiceByKeyword() {
		final Object testingData[][] = {
			{
				"customer1", "request", null
			}, {
				"admin", "request", IllegalArgumentException.class
			}, {
				null, "request", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateGetServiceByKeyword((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	public void templateGetServiceByKeyword(final String username, final String keyword, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.serviceService.getServiceByKeyWord(keyword);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	@Test
	public void driverPostService() {
		Coordinates destinationCoordinates1 = new Coordinates();
		Coordinates originCoordinates1 = new Coordinates();
		destinationCoordinates1.setLatitude(30.0);
		destinationCoordinates1.setLongitude(-30.0);
		originCoordinates1.setLatitude(30.0);
		originCoordinates1.setLongitude(-30.0);
		Coordinates destinationCoordinates2 = new Coordinates();
		Coordinates originCoordinates2 = new Coordinates();
		@SuppressWarnings("deprecation")
		final Object testingData[][] = {
			{
				"customer1", Type.OFFER, "titulo", "descripcion", new Date("11/11/2017"), "origen", "destino", originCoordinates1, destinationCoordinates1, null //positivo
			},{
				"admin", Type.OFFER, "titulo", "descripcion", new Date("11/11/2017"), "origen", "destino",originCoordinates1, destinationCoordinates2, IllegalArgumentException.class //logueado como admin. NEGATIVO1
			},{
				"customer2", Type.REQUEST, "titulo", "descripcion", new Date("11/11/2017"), "origen", null,originCoordinates2, destinationCoordinates2, ConstraintViolationException.class //destino nulo. NEGATIVO2
			},{
				"customer2", Type.REQUEST, "titulo", "descripcion", new Date("11/11/2017"), null,"destino" ,originCoordinates2, destinationCoordinates2, ConstraintViolationException.class //origen nulo. NEGATIVO3
			},{
				"customer2", Type.REQUEST, "titulo", "descripcion", null,"origen","destino" ,originCoordinates2, destinationCoordinates2, NullPointerException.class //fecha nulo. NEGATIVO4
			},{
				"customer2", Type.REQUEST, "titulo", "descripcion", new Date("11/11/2000"), "origen","destino" ,originCoordinates2, destinationCoordinates2, IllegalArgumentException.class //fecha pasada. NEGATIVO5
			},{
				"customer2", Type.REQUEST, "titulo", null, new Date("11/11/2020"), "origen","destino" ,originCoordinates2, destinationCoordinates2, ConstraintViolationException.class //descripción nula. NEGATIVO6
			},{
				"customer2", Type.REQUEST, null, "descripcion", new Date("11/11/2020"), "origen","destino" ,originCoordinates2, destinationCoordinates2, ConstraintViolationException.class //fecha pasada. NEGATIVO7
			},{
				"customer2", null, "titulo", "descripcion", new Date("11/11/2020"), "origen","destino" ,originCoordinates2, destinationCoordinates2, ConstraintViolationException.class //tipo null. NEGATIVO8
			},{
				null, Type.OFFER, "titulo", "descripcion", new Date("11/11/2017"), "origen", "destino", originCoordinates1, destinationCoordinates1, IllegalArgumentException.class //usuario sin loguear. NEGATIVO9
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templatePostService((String) testingData[i][0], (Type) testingData[i][1],
				(String) testingData[i][2], (String) testingData[i][3], (Date) testingData[i][4],
				(String) testingData[i][5], (String) testingData[i][6], (Coordinates) testingData[i][7],
				 (Coordinates) testingData[i][8], (Class<?>) testingData[i][9]);
	}

	public void templatePostService(String username, Type type, String title, String description, Date moment,
		String originPlace, String destinationPlace, Coordinates originCoordinates, Coordinates destinationCoordinates, Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			domain.Service service = this.serviceService.create();
			service.setType(type);
			service.setTitle(title);
			service.setDescription(description);
			service.setMoment(moment);
			service.setOriginPlace(originPlace);
			service.setDestinationPlace(destinationPlace);
			service.setOriginCoordinates(originCoordinates);
			service.setDestinationCoordinates(destinationCoordinates);
			this.serviceService.save(service);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	
	
	@Test
	public void BanService() {
		final Object testingData[][] = {
			{
				"admin", Type.OFFER, 106, null
			},{
				"admin", Type.REQUEST, 108, null
			},{
				"customer1", Type.OFFER, 106, IllegalArgumentException.class
			},{
				"customer1", Type.REQUEST, 108, IllegalArgumentException.class
			},{
				null, Type.OFFER, 106, IllegalArgumentException.class
			},{
				null, Type.REQUEST, 108, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateBanService((String) testingData[i][0], (Type) testingData[i][1],
				(int) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	public void templateBanService(final String username, Type type, int serviceId, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
//			this.authenticate("customer1");
//			domain.Service service = this.serviceService.create();
//			service.setType(type);
//			service.setTitle("titulo");
//			service.setDescription("descripcion");
//			service.setMoment(Calendar.getInstance().getTime());
//			service.setOriginPlace("Sevilla");
//			service.setDestinationPlace("Cadiz");
//			service.setOriginCoordinates(new Coordinates());
//			service.setDestinationCoordinates(new Coordinates());
//			service = this.serviceService.save(service);
//			Assert.notNull(service);
//			this.unauthenticate();
			
			this.authenticate(username);
			this.serviceService.banService(serviceId);
			this.unauthenticate();
			Assert.isTrue(serviceService.findOne(serviceId).isBanned());

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverRatioOffers() {
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
			this.templateRatioOffers((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateRatioOffers(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.serviceService.ratioOffers();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverRatioRequests() {
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
			this.templateRatioRequests((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateRatioRequests(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.serviceService.ratioRequests();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverAvgServicesPerCustomer() {
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
			this.templateAvgServicesPerCustomer((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateAvgServicesPerCustomer(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.serviceService.avgServicesPerCustomer();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverAvgApplicationsPerOffer() {
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
			this.templateAvgApplicationsPerOffer((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateAvgApplicationsPerOffer(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.serviceService.avgApplicationsPerOffer();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverAvgApplicationsPerRequest() {
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
			this.templateAvgApplicationsPerRequest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateAvgApplicationsPerRequest(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.serviceService.avgApplicationsPerRequest();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	
	@Test
	public void driverFindAllServicesToDoApplication() {
		final Object testingData[][] = {
		{
			"customer1", null //logueado como un customer. Positivo
		},{
			"admin", IllegalArgumentException.class //logueado como un administrador. Negativo
		},{
			null, IllegalArgumentException.class	//Sin loguear
	}

};

		for (int i = 0; i < testingData.length; i++)
			this.templateFindAllServicesToDoApplication((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateFindAllServicesToDoApplication(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.serviceService.findAllServicesToDoApplication(this.customerService.findByPrincipal());
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	
}
