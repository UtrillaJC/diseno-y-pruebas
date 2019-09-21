
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
import domain.EndorserRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EndorserRecordServiceTest extends AbstractTest {

	// Service under Test --------------------------------------------------

	@Autowired
	private EndorserRecordService	endorserRecordService;

	@Autowired
	private CurriculumService		curriculumService;


	// Tests -----------------------------------------------------------------

	@Test
	public void testCreate() {

		EndorserRecord endorserRecord = null;

		super.authenticate("ranger1");
		endorserRecord = this.endorserRecordService.create();
		Assert.notNull(endorserRecord.getComments());
	}

	@Test
	public void testSave() {
		EndorserRecord endorserRecord = null;
		EndorserRecord saved = null;

		super.authenticate("ranger1");
		endorserRecord = this.endorserRecordService.create();
		endorserRecord.setEmail("endorsertest@gmail.com");
		endorserRecord.setFullName("Endorser Test");
		endorserRecord.setLink("http://www.endorser.com/test.html");
		endorserRecord.setPhone("13649578");
		saved = this.endorserRecordService.save(endorserRecord);

		Assert.isTrue(this.endorserRecordService.findAll().contains(saved));
	}

	@Test
	public void testDelete() {
		EndorserRecord endorserRecord = null;
		EndorserRecord saved = null;
		Curriculum curriculum = null;

		super.authenticate("ranger1");
		endorserRecord = this.endorserRecordService.create();
		endorserRecord.setEmail("endorsertest@gmail.com");
		endorserRecord.setFullName("Endorser Test");
		endorserRecord.setLink("http://www.endorser.com/test.html");
		endorserRecord.setPhone("+136 1111");
		saved = this.endorserRecordService.save(endorserRecord);
		Assert.isTrue(this.endorserRecordService.findAll().contains(saved));
		curriculum = this.curriculumService.findOne(7213);

		this.endorserRecordService.delete(saved, curriculum);
		Assert.isTrue(!this.endorserRecordService.findAll().contains(saved));

	}

}
