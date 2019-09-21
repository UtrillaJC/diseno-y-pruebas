
package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curriculum;
import domain.ProfessionalRecord;
import domain.Ranger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ProfessionalRecordServiceTest extends AbstractTest {

	// Service under test --------------------------------------------------
	@Autowired
	private ProfessionalRecordService	professionalRecordService;

	@Autowired
	private CurriculumService			curriculumService;

	@Autowired
	private RangerService				rangerService;


	// Tests ---------------------------------------------------------------

	@Test
	public void testCreate() {
		Ranger ranger = null;
		ProfessionalRecord professionalRecord = null;

		super.authenticate("ranger1");
		ranger = this.rangerService.findByPrincipal();

		professionalRecord = this.professionalRecordService.create();
		Assert.notNull(professionalRecord.getComments());
	}

	@Test
	public void testSave() {
		Ranger ranger = null;
		ProfessionalRecord professionalRecord = null;
		ProfessionalRecord saved = null;
		Curriculum curriculum = null;

		super.authenticate("ranger1");
		ranger = this.rangerService.findByPrincipal();

		professionalRecord = this.professionalRecordService.create();
		professionalRecord.setRole("Test");
		professionalRecord.setStartDate(new Date(2011, 01, 12, 15, 00));
		professionalRecord.setEndDate(new Date(2010, 01, 12, 15, 00));
		professionalRecord.setCompany("test");
		curriculum = this.curriculumService.findOne(ranger.getCurriculum().getId());

		saved = this.professionalRecordService.save(professionalRecord, curriculum);
		final ProfessionalRecord professionalRecordRetrieved = this.professionalRecordService.findOne(saved.getId());
		Assert.notNull(professionalRecordRetrieved);
		Assert.isTrue(curriculum.getProfessionalRecords().contains(professionalRecordRetrieved));
	}

	@Test
	public void testDelete() {
		Ranger ranger = null;
		ProfessionalRecord professionalRecord = null;
		ProfessionalRecord saved = null;
		Curriculum curriculum = null;

		super.authenticate("ranger1");
		ranger = this.rangerService.findByPrincipal();

		professionalRecord = this.professionalRecordService.create();
		professionalRecord.setRole("Test");
		professionalRecord.setStartDate(new Date(01 / 01 / 2008));
		professionalRecord.setEndDate(new Date(01 / 01 / 2011));
		professionalRecord.setCompany("test");
		curriculum = this.curriculumService.findOne(7213);
		saved = this.professionalRecordService.save(professionalRecord, curriculum);
		this.professionalRecordService.delete(saved, curriculum);

		Assert.isTrue(!this.professionalRecordService.findAll().contains(saved));
	}

}
