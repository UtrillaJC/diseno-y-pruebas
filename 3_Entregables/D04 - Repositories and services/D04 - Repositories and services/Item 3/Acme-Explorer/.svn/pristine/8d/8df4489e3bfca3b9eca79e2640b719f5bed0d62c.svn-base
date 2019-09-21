
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorserRecordRepository;
import domain.Curriculum;
import domain.EndorserRecord;

@Service
@Transactional
public class EndorserRecordService {

	// Managed repository --------------------------------------
	@Autowired
	private EndorserRecordRepository	endorserRecordRepository;

	// Supported services ---------------------------------------
	@Autowired
	private CurriculumService			curriculumService;


	// Simple Crud methods ---------------------------------------
	public EndorserRecord create() {
		EndorserRecord endorserRecord = null;
		final Collection<String> comments = new ArrayList<>();

		endorserRecord = new EndorserRecord();
		endorserRecord.setComments(comments);

		return endorserRecord;
	}

	public EndorserRecord save(final EndorserRecord endorserRecord) {
		Assert.notNull(endorserRecord);
		return this.endorserRecordRepository.save(endorserRecord);
	}
	public void delete(final EndorserRecord endorserRecord, final Curriculum curriculum) {
		Assert.notNull(endorserRecord);
		Assert.notNull(curriculum);
		this.curriculumService.checkRangerByCurriculum(curriculum);
		curriculum.getEducationRecords().remove(endorserRecord);

		this.endorserRecordRepository.delete(endorserRecord);
	}
	public EndorserRecord findOne(final int id) {
		return this.endorserRecordRepository.findOne(id);
	}
	public Collection<EndorserRecord> findAll() {
		return this.endorserRecordRepository.findAll();
	}
	// Other business methods

	// TODO:	findOneToEdit?
}
