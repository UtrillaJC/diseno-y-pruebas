
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PersonalRecordRepository;
import domain.Curriculum;
import domain.PersonalRecord;

@Service
@Transactional
public class PersonalRecordService {

	// Manager Repository
	@Autowired
	private PersonalRecordRepository	personalRecordRepository;


	// Supported Services

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

		PersonalRecord result = null;
		result = this.personalRecordRepository.save(personalRecord);
		return result;
	}

	public void delete(final PersonalRecord personalRecord, final Curriculum curriculum) {
		Assert.notNull(curriculum);
		Assert.notNull(personalRecord);

		this.personalRecordRepository.delete(personalRecord);
	}
	// Other Business Methods =========================================================================

}
