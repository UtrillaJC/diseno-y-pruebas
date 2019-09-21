
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EducationRecordRepository;
import domain.Curriculum;
import domain.EducationRecord;

@Service
@Transactional
public class EducationRecordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private EducationRecordRepository	educationalrecordRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CurriculumService			curriculumService;


	// Constructors -----------------------------------------------------------

	public EducationRecordService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public EducationRecord create() {
		EducationRecord educationRecord = null;
		Collection<String> comments = null;

		comments = new ArrayList<String>();
		educationRecord = new EducationRecord();

		educationRecord.setComments(comments);

		return educationRecord;
	}

	public EducationRecord save(final EducationRecord educationRecord) {

		Assert.notNull(educationRecord);
		return this.educationalrecordRepository.save(educationRecord);

	}

	public void delete(final EducationRecord educationRecord, final Curriculum curriculum) {

		Assert.notNull(educationRecord);
		Assert.notNull(curriculum);

		this.curriculumService.checkRangerByCurriculum(curriculum);

		curriculum.getEducationRecords().remove(educationRecord);
		this.curriculumService.save(curriculum);

		this.educationalrecordRepository.delete(educationRecord);
	}

	public EducationRecord findOne(final int id) {
		return this.educationalrecordRepository.findOne(id);
	}

	public Collection<EducationRecord> findAll() {
		return this.educationalrecordRepository.findAll();
	}

	// Other business methods -------------------------------------------------
}
