
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProfessionalRecordRepository;
import domain.Curriculum;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ProfessionalRecordRepository	professionalRecordRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CurriculumService				curriculumService;


	// Constructors -----------------------------------------------------------

	public ProfessionalRecordService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public ProfessionalRecord create() {
		ProfessionalRecord professionalRecord;

		professionalRecord = new ProfessionalRecord();
		professionalRecord.setComments(new ArrayList<String>());

		return professionalRecord;
	}

	public ProfessionalRecord save(final ProfessionalRecord professionalRecord, final Curriculum curriculum) {
		Assert.notNull(professionalRecord);
		Assert.notNull(curriculum);

		ProfessionalRecord saved = null;

		saved = this.professionalRecordRepository.save(professionalRecord);
		curriculum.getProfessionalRecords().add(saved);
		this.curriculumService.save(curriculum);

		return saved;
	}

	public void delete(final ProfessionalRecord professionalRecord, final Curriculum curriculum) {
		Assert.notNull(professionalRecord);
		Assert.notNull(professionalRecord);
		Assert.notNull(curriculum);

		curriculum.getProfessionalRecords().remove(professionalRecord);
		this.curriculumService.save(curriculum);
		this.professionalRecordRepository.delete(professionalRecord);
	}

	public ProfessionalRecord findOne(final int id) {
		return this.professionalRecordRepository.findOne(id);
	}

	public Collection<ProfessionalRecord> findAll() {
		return this.professionalRecordRepository.findAll();
	}

	// Other business methods -------------------------------------------------
}
