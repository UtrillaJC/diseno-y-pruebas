
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MiscellaneousRecordRepository;
import domain.Curriculum;
import domain.MiscellaneousRecord;

@Service
@Transactional
public class MiscellaneousRecordService {

	// Manager Repository

	@Autowired
	private MiscellaneousRecordRepository	miscellaneousRecordRepository;

	// Supported Services

	@Autowired
	private CurriculumService				curriculumService;


	// Simple CRUD methods ----------------------------------------------------

	public MiscellaneousRecord create() {

		MiscellaneousRecord result;
		result = new MiscellaneousRecord();
		result.setComments(new ArrayList<String>());
		return result;
	}

	public MiscellaneousRecord findOne(final int miscellaneousRecordId) {
		MiscellaneousRecord result;

		result = this.miscellaneousRecordRepository.findOne(miscellaneousRecordId);

		return result;
	}

	public Collection<MiscellaneousRecord> findAll() {
		Collection<MiscellaneousRecord> result;
		result = this.miscellaneousRecordRepository.findAll();
		return result;
	}

	public MiscellaneousRecord save(final MiscellaneousRecord miscellaneousRecord) {
		Assert.notNull(miscellaneousRecord);
		MiscellaneousRecord result;
		result = this.miscellaneousRecordRepository.save(miscellaneousRecord);
		return result;
	}

	public void delete(final MiscellaneousRecord miscellaneousRecord, final Curriculum curriculum) {
		Assert.notNull(miscellaneousRecord);
		Assert.notNull(curriculum);
		this.curriculumService.checkRangerByCurriculum(curriculum);

		curriculum.getEducationRecords().remove(miscellaneousRecord);
		this.curriculumService.save(curriculum);
		this.miscellaneousRecordRepository.delete(miscellaneousRecord);
	}

	// other business methods

	public MiscellaneousRecord findOneToEdtiMiscellaneousRecord(final MiscellaneousRecord miscellaneousRecord) {

		return null;
	}

}
