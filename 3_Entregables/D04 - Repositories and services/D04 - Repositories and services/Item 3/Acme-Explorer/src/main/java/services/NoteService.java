
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Auditor;
import domain.Manager;
import domain.Note;
import domain.Trip;

@Service
@Transactional
public class NoteService {

	// Managed repository ---------------------------------------

	@Autowired
	private NoteRepository	noteRepository;

	// Supporting services ---------------------------------------

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private AuditorService	auditorService;

	@Autowired
	private TripService		tripService;


	// Simple CRUD methods ---------------------------------------

	public Note create() {
		Auditor auditor;

		final Note note = new Note();

		note.setMoment(new Date(System.currentTimeMillis() - 1000));
		auditor = this.auditorService.findByPrincipal();
		note.setAuditor(auditor);
		auditor.getNotes().add(note);

		return note;
	}

	public Collection<Note> findAll() {
		return this.noteRepository.findAll();
	}

	public Note findOne(final int id) {

		return this.noteRepository.findOne(id);
	}

	public Note save(final Note note) {
		this.checkByPrincipalAuditor(note);
		Assert.notNull(note);
		Assert.notNull(note.getTrip());
		return this.noteRepository.save(note);
	}

	

	// Others business methods ----------------------------------------------------

	public void deleteNotes(final Trip trip) {
		final Collection<Note> notes = trip.getNotes();
		this.noteRepository.delete(notes);

	}

	public void deleteByAuditor(final Auditor auditor) {

		final Collection<Note> notes = this.findByAuditor(auditor);

		this.noteRepository.delete(notes);
	}

	public Collection<Note> findByAuditor(final Auditor auditor) {

		Collection<Note> result = null;
		result = this.noteRepository.findByAuditor(auditor);
		return result;
	}

	public Note replyToNote(final int noteId, final String reply) {
		Assert.notNull(reply);
		final Note note = this.noteRepository.findOne(noteId);
		this.checkByPrincipalManager(note);
		note.setReplyMoment(new Date(System.currentTimeMillis() - 1000));
		this.checkByPrincipalManager(note);
		note.setReply(reply);
		this.save(note);
		return note;
	}

	public void checkByPrincipalAuditor(final Note note) {
		final Auditor principal = this.auditorService.findByPrincipal();
		Assert.isTrue(note.getAuditor().equals(principal));

	}

	public void checkByPrincipalManager(final Note note) {
		final Manager principal = this.managerService.findByPrincipal();
		Assert.isTrue(note.getTrip().getManager().equals(principal));

	}

	public Note assignNoteToTrip(final Note note, final Trip trip) {

		Assert.notNull(trip);
		Assert.notNull(note);

		this.checkByPrincipalAuditor(note);
		note.setTrip(trip);
		trip.getNotes().add(note);
		this.tripService.save(trip);
		this.save(note);

		return note;
	}

}
