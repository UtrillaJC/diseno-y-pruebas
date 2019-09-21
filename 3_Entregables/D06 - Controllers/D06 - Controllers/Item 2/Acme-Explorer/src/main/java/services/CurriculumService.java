
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.ProfessionalRecord;
import domain.Ranger;

@Service
@Transactional
public class CurriculumService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CurriculumRepository	curriculumRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private RangerService			rangerService;

	@Autowired
	private PersonalRecordService	personalRecordService;


	// Constructors -----------------------------------------------------------

	public CurriculumService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Curriculum create(final Ranger ranger) {

		Assert.notNull(ranger);

		Curriculum result = null;
		result = this.generateTicker(new Curriculum());
		//		result.setRanger(ranger);
		ranger.setCurriculum(result);
		result.setPersonalRecord(this.personalRecordService.create());
		result.setEducationRecords(new ArrayList<EducationRecord>());
		result.setEndorserRecords(new ArrayList<EndorserRecord>());
		result.setMiscellaneousRecords(new ArrayList<MiscellaneousRecord>());
		result.setProfessionalRecords(new ArrayList<ProfessionalRecord>());

		return result;
	}

	public Curriculum findOne(final int curriculumId) {

		Curriculum result = null;
		result = this.curriculumRepository.findOne(curriculumId);
		return result;
	}

	public Collection<Curriculum> findAll() {

		Collection<Curriculum> result = null;
		result = this.curriculumRepository.findAll();
		return result;
	}

	public Curriculum save(final Curriculum curriculum) {
		Assert.notNull(curriculum);

		Curriculum result = null;
		Ranger rangerCurriculum = null;
		Ranger rangerLogin = null;

		rangerLogin = this.rangerService.findByPrincipal();

		if (curriculum.getId() == 0)
			Assert.isNull(rangerLogin.getCurriculum());
		else {
			rangerCurriculum = this.rangerService.findRangerByCurriculumId(curriculum.getId());
			Assert.isTrue(rangerLogin.equals(rangerCurriculum));
		}

		result = this.curriculumRepository.save(curriculum);
		rangerLogin.setCurriculum(result);
		this.rangerService.save(rangerLogin);

		return result;
	}

	public void delete(final Curriculum curriculum) {

		Assert.notNull(curriculum);
		Ranger rangerLogin = null;
		Ranger rangerCurriculum = null;

		rangerCurriculum = this.rangerService.findRangerByCurriculumId(curriculum.getId());
		rangerLogin = this.rangerService.findByPrincipal();
		Assert.isTrue(rangerLogin.equals(rangerCurriculum));

		this.curriculumRepository.delete(curriculum);
		//ranger.setCurriculum(null);
		rangerLogin.setCurriculum(null);
		this.rangerService.save(rangerLogin);
		this.personalRecordService.delete(curriculum.getPersonalRecord());
	}

	// Other business methods -------------------------------------------------

	public void checkRangerByCurriculum(final Curriculum curriculum) {
		Ranger ranger = null;
		Ranger rangerByCurriculum = null;

		ranger = this.rangerService.findByPrincipal();
		rangerByCurriculum = this.rangerService.findRangerByCurriculumId(curriculum.getId());
		//Assert.isTrue(ranger.equals(curriculum.getRanger()));

		// Si son el mismo curriculum, significa que es el mismo ranger
		Assert.isTrue(ranger.equals(rangerByCurriculum));
	}

	public void deleteByRanger(final Ranger ranger) {

		final Curriculum curriculum = this.findByRangerId(ranger.getId());
		this.curriculumRepository.delete(curriculum);
	}

	public Curriculum findByRangerId(final int rangerId) {

		Curriculum result = null;
		//result = this.curriculumRepository.findByRangerId(rangerId);
		result = this.rangerService.findCurriculumByRangerId(rangerId);
		return result;
	}

	public Curriculum findByPrincipal() {

		Curriculum result = null;
		final Ranger ranger = this.rangerService.findByPrincipal();
		result = this.findByRangerId(ranger.getId());
		return result;
	}

	public Curriculum generateTicker(final Curriculum curriculum) {

		String ticker = new String();
		Curriculum newCurriculum = null;
		boolean newTicker = false;

		while (!newTicker) {
			final Integer year = Calendar.getInstance().get(Calendar.YEAR) % 100;
			final Integer month = Calendar.getInstance().get(Calendar.MONTH);
			final Integer day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

			ticker = year.toString() + month.toString() + day.toString() + "-";

			final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			final int N = alphabet.length();

			final Random r = new Random();

			for (int i = 0; i < 4; i++)
				ticker += alphabet.charAt(r.nextInt(N));

			newCurriculum = this.curriculumRepository.findByTicker(ticker);

			if (newCurriculum == null)
				newTicker = true;

			curriculum.setTicker(ticker);
		}
		return curriculum;
	}
}
