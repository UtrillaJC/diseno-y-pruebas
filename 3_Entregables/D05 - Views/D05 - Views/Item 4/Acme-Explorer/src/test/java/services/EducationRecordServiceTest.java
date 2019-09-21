
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
import domain.EducationRecord;
import domain.Ranger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EducationRecordServiceTest extends AbstractTest {

	// Service under test --------------------------------------------------
	@Autowired
	private EducationRecordService	educationalRecordService;

	@Autowired
	private RangerService			rangerService;


	// Tests ------------------------------------------------------------------

	@Test
	public void testCreate() {

		EducationRecord educationRecord = null;

		super.authenticate("ranger1");
		educationRecord = this.educationalRecordService.create();
		Assert.notNull(educationRecord.getComments());
	}
	@Test
	public void testSave() {

		EducationRecord educationRecord = null;
		EducationRecord saved = null;

		super.authenticate("ranger1");
		educationRecord = this.educationalRecordService.create();
		educationRecord.setTitle("EducationRecord test");
		educationRecord.setStartDate(new Date(2000, 10, 02, 15, 00));
		educationRecord.setEndDate(new Date(2002, 10, 02, 15, 00));
		educationRecord.setInstitution("institution");
		educationRecord.setAttachment("http://www.attachmentTest.com");

		saved = this.educationalRecordService.save(educationRecord);
		Assert.isTrue(saved.getTitle().equals("EducationRecord test"));
	}

	@Test
	public void testDelete() {
		Ranger ranger = null;
		EducationRecord educationRecord = null;
		Curriculum curriculum = null;

		super.authenticate("ranger1");
		ranger = this.rangerService.findByPrincipal();
		curriculum = ranger.getCurriculum();
		educationRecord = curriculum.getEducationRecords().iterator().next();

		this.educationalRecordService.delete(educationRecord, curriculum);

		Assert.isTrue(!curriculum.getEducationRecords().contains(educationRecord));
		Assert.isTrue(!this.educationalRecordService.findAll().contains(educationRecord));
		Assert.isTrue(!ranger.getCurriculum().getEducationRecords().contains(educationRecord));
		super.authenticate(null);
	}

}
