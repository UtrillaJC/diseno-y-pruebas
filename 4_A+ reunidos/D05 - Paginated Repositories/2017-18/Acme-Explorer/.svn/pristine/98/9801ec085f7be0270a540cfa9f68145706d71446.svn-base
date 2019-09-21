
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Auditor;
import domain.Note;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class NoteServiceTest extends AbstractTest {

	// Service under test ---------------------------------------

	@Autowired
	private AuditorService	auditorService;

	@Autowired
	private NoteService		noteService;

	@Autowired
	private TripService		tripService;


	// Tests ----------------------------------------------------

	@Test
	public void TestCreate() {
		super.authenticate("auditor1");
		final Note note = this.noteService.create();
		Assert.notNull(note.getMoment());
		Assert.notNull(note);
		Assert.isNull(note.getRemark());
		Assert.isNull(note.getReply());
		Assert.isNull(note.getTrip());
		Assert.isNull(note.getReplyMoment());
		super.unauthenticate();
	}

	@Test
	public void testSave() {
		Note note, saved;
		Trip trip;

		super.authenticate("auditor1");

		note = this.noteService.create();
		note.setRemark("remark10");
		trip = this.tripService.findOne(this.tripService.findAll().iterator().next().getId());
		note.setTrip(trip);
		note.setReply("replay10");
		saved = this.noteService.save(note);
		trip.getNotes().add(note);
		Assert.isTrue(this.noteService.findAll().contains(saved));
		super.unauthenticate();

	}

	/*
	 * @Test
	 * public void testDelete(){
	 * Note note,saved;
	 * Trip trip;
	 * 
	 * super.authenticate("auditor1");
	 * Auditor a= auditorService.findByPrincipal();
	 * note = noteService.create(a);
	 * note.setRemark("remark10");
	 * trip=tripService.findOne(7151);
	 * note.setTrip(trip);
	 * note.setReply("reply10");
	 * saved=noteService.save(note);
	 * noteService.delete(saved);
	 * Assert.isTrue(!noteService.findAll().contains(note));
	 * super.unauthenticate();
	 * }
	 */

	@Test
	public void testReplyToNote() {
		String reply;
		Note note, saved;
		Trip trip;

		super.authenticate("auditor1");
		final Auditor a = this.auditorService.findByPrincipal();
		note = this.noteService.create();
		note.setRemark("remark10");
		trip = this.tripService.findOne(this.tripService.findAll().iterator().next().getId());
		note.setTrip(trip);
		reply = "reply10";
		note.setReply("reply10");
		saved = this.noteService.save(note);
		Assert.isTrue(note.getReply().equals(reply));
		super.unauthenticate();
	}

	@Test
	public void testAssignNoteToTrip() {
		Note note, saved;
		Trip trip;

		super.authenticate("auditor1");

		trip = this.tripService.findOne(this.tripService.findAll().iterator().next().getId());
		note = this.noteService.create();
		note.setRemark("remark10");
		note.setReply("replay10");
		saved = this.noteService.assignNoteToTrip(note, trip);

		Assert.isTrue(trip.getNotes().contains(saved));
		Assert.isTrue(saved.getTrip().equals(trip));
		super.unauthenticate();

	}

	@Test
	public void testFindByAuditor() {
		super.authenticate("auditor1");
		final Auditor auditor = this.auditorService.findByPrincipal();
		this.noteService.findByAuditor(auditor);
		super.unauthenticate();
	}

}
