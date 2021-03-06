
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curriculum;
import domain.MiscellaneousRecord;
import domain.Ranger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MiscellaneousRecordServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;

	@Autowired
	private RangerService				rangerService;

	@Autowired
	private CurriculumService			curriculumService;


	// Tests -------------------------------------------

	

	@Test
	public void testCreate() {
		Ranger ranger = null;
		MiscellaneousRecord miscellaneousRecord = null;

		super.authenticate("ranger1");
		ranger = this.rangerService.findByPrincipal();
		miscellaneousRecord = this.miscellaneousRecordService.create();

		Assert.notNull(miscellaneousRecord.getComments());

	}
	@Test
	public void testSave() {
		Ranger ranger = null;
		MiscellaneousRecord miscellaneousRecord = null;
		MiscellaneousRecord saved = null;

		super.authenticate("ranger1");
		ranger = this.rangerService.findByPrincipal();
		miscellaneousRecord = this.miscellaneousRecordService.create();
		miscellaneousRecord.setTitle("Test title");
		miscellaneousRecord.setAttachment("http://www.attachment.com/test.html");
		saved = this.miscellaneousRecordService.save(miscellaneousRecord);

		Assert.isTrue(saved.getTitle().equals("Test title"));
		Assert.isTrue(this.miscellaneousRecordService.findAll().contains(saved));
	}

	@Test
	public void testDelete() {
		Ranger ranger = null;
		MiscellaneousRecord miscellaneousRecord = null;
		MiscellaneousRecord saved = null;
		Curriculum curriculum = null;

		super.authenticate("ranger1");
		ranger = this.rangerService.findByPrincipal();
		curriculum = this.curriculumService.findOne(ranger.getCurriculum().getId());

		miscellaneousRecord = this.miscellaneousRecordService.create();
		miscellaneousRecord.setTitle("Test title");
		miscellaneousRecord.setAttachment("http://www.attachment.com/test.html");
		saved = this.miscellaneousRecordService.save(miscellaneousRecord);
		Assert.isTrue(this.miscellaneousRecordService.findAll().contains(saved));

		this.miscellaneousRecordService.delete(saved, curriculum);
		Assert.isTrue(!this.miscellaneousRecordService.findAll().contains(saved));

	}
}
