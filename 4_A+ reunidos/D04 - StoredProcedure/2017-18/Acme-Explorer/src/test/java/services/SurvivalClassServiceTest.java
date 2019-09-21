
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.GPSCoordinates;
import domain.Manager;
import domain.SurvivalClass;
import domain.Trip;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SurvivalClassServiceTest extends AbstractTest {

	// Service under Test ------------------------------------------
	@Autowired
	private SurvivalClassService	survivalClassService;

	@Autowired
	private ManagerService			managerService;

	
	@Autowired
	private TripService tripService;
	
	


	// Tests -------------------------------------------------------

	@Test
	public void testCreate() {
		SurvivalClass survivalClass = new SurvivalClass();

		super.authenticate("manager1");
		
		survivalClass=survivalClassService.create();
		Assert.isNull(survivalClass.getDescription());
		Assert.isNull(survivalClass.getLocation());
		Assert.notNull(survivalClass.getManager());
		Assert.isNull(survivalClass.getMoment());
		Assert.isNull(survivalClass.getTitle());
		Assert.isNull(survivalClass.getTrip());
		super.unauthenticate();
	}
	
	
	@Test
	public void testSave() {
		SurvivalClass survivalClass,saved;
		super.authenticate("manager1");
		survivalClass=survivalClassService.create();
		survivalClass.setDescription("Description SurvivalClass1");
		GPSCoordinates location= new GPSCoordinates();
		location.setLatitude(58.4);
		location.setLongitude(64.15);
		location.setName("ValenciaCoordinates");
		survivalClass.setLocation(location);
		Manager manager = managerService.findByPrincipal();
		survivalClass.setManager(manager);
		survivalClass.setTitle("SurvivalClass1");
		Trip trip= tripService.findOne(tripService.findAll().iterator().next().getId());
		survivalClass.setTrip(trip);
		saved = survivalClassService.save(survivalClass);
		SurvivalClass s= survivalClassService.findOne(saved.getId());
		Assert.notNull(s);
		super.unauthenticate();

	}
	@Test
	public void testDelete() {
		SurvivalClass survivalClass,saved;
		super.authenticate("manager1");
		survivalClass=survivalClassService.create();
		survivalClass.setDescription("Description SurvivalClass1");
		GPSCoordinates location= new GPSCoordinates();
		location.setLatitude(58.4);
		location.setLongitude(64.15);
		location.setName("ValenciaCoordinates");
		survivalClass.setLocation(location);
		Manager manager = managerService.findByPrincipal();
		survivalClass.setManager(manager);
		survivalClass.setTitle("SurvivalClass1");
		Trip trip= tripService.findOne(tripService.findAll().iterator().next().getId());
        survivalClass.setTrip(trip);
		saved = survivalClassService.save(survivalClass);
		survivalClassService.delete(saved);
		SurvivalClass s= survivalClassService.findOne(saved.getId());
		Assert.isNull(s);
		super.unauthenticate();
	}
	
	@Test
	public void testFindByTrip(){
		super.authenticate("manager1");
		Trip trip= tripService.findOne(tripService.findAll().iterator().next().getId());
		survivalClassService.findByTrip(trip.getId());
		super.unauthenticate();
	}
	
	@Test
	public void testFindByManager(){
		super.authenticate("manager1");
		Manager manager= managerService.findByPrincipal();
		survivalClassService.findByManager(manager);
		super.unauthenticate();
	}
	
	@Test
	public void testDeleteByManager(){
		super.authenticate("manager1");
		Manager manager= managerService.findByPrincipal();
		survivalClassService.deleteByManager(manager);
		super.unauthenticate();
		survivalClassService.deleteByManager(manager);
	}
	
	@Test
	public void testFindOneToEdit(){
		super.authenticate("manager1");
		SurvivalClass survivalClass=survivalClassService.create();
		survivalClass.setDescription("Description SurvivalClass1");
		GPSCoordinates location= new GPSCoordinates();
		location.setLatitude(58.4);
		location.setLongitude(64.15);
		location.setName("ValenciaCoordinates");
		survivalClass.setLocation(location);
		Manager manager = managerService.findByPrincipal();
		survivalClass.setManager(manager);
		survivalClass.setTitle("SurvivalClass1");
		Trip trip= tripService.findOne(tripService.findAll().iterator().next().getId());
		survivalClass.setTrip(trip);
		SurvivalClass saved = survivalClassService.save(survivalClass);
		SurvivalClass sRetrieved = survivalClassService.findOneToEdit(saved.getId());
		Assert.isTrue(saved.equals(sRetrieved));
		super.unauthenticate();
		
	}
	@Test
	public void testAssignSurvivalClassToTrip(){
		super.authenticate("manager1");
		SurvivalClass survClass=survivalClassService.findOne(survivalClassService.findAll().iterator().next().getId());
		Trip trip= tripService.findOne(tripService.findAll().iterator().next().getId());
		survivalClassService.assignSurvivalClassToTrip(trip.getId(), survClass.getId());
		Assert.isTrue(trip.getSurvivalClasses().contains(survClass));
		Assert.isTrue(survClass.getTrip().equals(trip));
		super.unauthenticate();
	}

}
