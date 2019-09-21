
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PersonalRecordRepository;
import domain.Curriculum;
import domain.PersonalRecord;
import domain.Ranger;

@Service
@Transactional
public class PersonalRecordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PersonalRecordRepository	personalRecordRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CurriculumService			curriculumService;

	@Autowired
	private RangerService				rangerService;


	// Constructors -----------------------------------------------------------

	public PersonalRecordService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public PersonalRecord create() {

		PersonalRecord result;
		result = new PersonalRecord();

		return result;
	}

	public PersonalRecord findOne(final int personalRecordId) {

		PersonalRecord result = null;
		result = this.personalRecordRepository.findOne(personalRecordId);
		return result;
	}

	public Collection<PersonalRecord> findAll() {

		Collection<PersonalRecord> result = null;
		result = this.personalRecordRepository.findAll();
		return result;
	}

	public PersonalRecord save(final PersonalRecord personalRecord) {
		Assert.notNull(personalRecord);
		Ranger ranger = null;
		Curriculum curriculum = null;
		PersonalRecord saved = null;

		ranger = this.rangerService.findByPrincipal();
		curriculum = ranger.getCurriculum();

		saved = this.personalRecordRepository.save(personalRecord);
		if (personalRecord.getId() == 0) {
			curriculum.setPersonalRecord(saved);
			this.curriculumService.save(curriculum);
		}

		return saved;
	}

	public void delete(final PersonalRecord personalRecord) {

		Assert.notNull(personalRecord);

		Ranger ranger = null;
		Curriculum curriculum = null;

		ranger = this.rangerService.findByPrincipal();
		curriculum = ranger.getCurriculum();

		curriculum.setEndorserRecords(null);
		this.curriculumService.save(curriculum);

		this.personalRecordRepository.delete(personalRecord);
	}

	// Other business methods -------------------------------------------------

}
