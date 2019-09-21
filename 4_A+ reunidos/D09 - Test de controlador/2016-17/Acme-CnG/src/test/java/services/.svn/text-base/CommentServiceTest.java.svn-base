package services;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Comment;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CommentServiceTest extends AbstractTest{
	
	// The SUT ====================================================================================

		@Autowired
		private CommentService commentService;


		// Tests =======================================================================================
	
	@Test
	public void driverCreate() {
		final Object testingData[][] = {
			{
				"customer1", "titulo", "texto", 0, 106, null //Logueado como customer1, positivo
			},{
				null, "titulo", "texto", 4, 107, IllegalArgumentException.class //No logueado, negativo
			},{
				"admin", "titulo", "", 5, 108, ConstraintViolationException.class //Logueado como admin, texto en blanco,
			},{																	  //negativo
				"customer3", "titulo", "texto", 6, 109, ConstraintViolationException.class //Logueado como customer3,
			},{																			   //stars se pasa del rango, negativo
				"customer3", "", "texto", 2, 92, ConstraintViolationException.class //Logueado como customer3,
			},{																		 //titulo en blanco, negativo
				"customer1", "titulo", "texto", -1, 95, ConstraintViolationException.class //Logueado como customer3,
			},{																				//stars menor que 0, negativo
				"admin", "", "", 4, 93, ConstraintViolationException.class //Logueado como admin, titulo y texto blancos, negativo
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], 
				(int) testingData[i][3], (int) testingData[i][4], (Class<?>) testingData[i][5]);
	}

	public void templateCreate(String username, String title, String text, int stars, int commentClassId, Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			Comment comment = this.commentService.create(commentClassId);
			comment.setTitle(title);
			comment.setText(text);
			comment.setStars(stars);
			this.commentService.save(comment);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverAvgCommentPerActor() {
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
			this.templateAvgCommentPerActor((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateAvgCommentPerActor(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.commentService.avgCommentPerActor();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverAvgCommentPerOffer() {
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
			this.templateAvgCommentPerOffer((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateAvgCommentPerOffer(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.commentService.avgCommentPerOffer();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}


	@Test
	public void driverAvgCommentPerRequest() {
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
			this.templateAvgCommentPerRequest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateAvgCommentPerRequest(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.commentService.avgCommentPerRequest();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	@Test
	public void driverAvgCommentPostedActor() {
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
			this.templateAvgCommentPostedActor((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templateAvgCommentPostedActor(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.commentService.avgCommentPostedActor();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	@Test
	public void driverPostedActor10Avg() {
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
			this.templateAvgCommentPostedActor((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	public void templatePostedActor10Avg(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.commentService.postedActor10Avg();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	@Test
	public void driverBanComment() {
		final Object testingData[][] = {
		{
			"admin", 102, null //Logueado como admin, positivo
		},{
			null, 102, IllegalArgumentException.class //No logueado, negativo
		},{
			"customer1", 102, IllegalArgumentException.class //Logueado como customer, no puede banear comentarios,
		}													 //negativo

};

		for (int i = 0; i < testingData.length; i++)
			this.templateBanComment((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	public void templateBanComment(String username, int commentId, Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.commentService.banComment(commentId);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
