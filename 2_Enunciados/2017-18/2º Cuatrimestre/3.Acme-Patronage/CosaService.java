
package services;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CosaRepository;
import domain.Administrator;
import domain.Cosa;
import domain.Newspaper;

@Service
@Transactional
public class CosaService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private CosaRepository			cosaRepository;

	// Supporting services ----------------------------------------------------

	private AdministratorService	administratorService;
	private ActorService			actorService;
	private NewspaperService		newspaperService;


	// Constructors -----------------------------------------------------------

	public CosaService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Cosa create() {
		Cosa result;
		result = new Cosa();
		result.setIsDraft(true);
		result.setAdministrator(this.isAdministratorAunthenticated());
		result.setDisplayMoment(null);
		result.setGauge(1);
		return result;
	}

	public Cosa save(Cosa cosa) {
		Assert.isTrue(cosa.getIsDraft());
		Assert.isNull(cosa.getNewspaper());

		final Date displayMoment = cosa.getDisplayMoment();
		if (displayMoment != null) {
			final Date now = new Date(System.currentTimeMillis() - 15);
			Assert.isTrue(displayMoment.after(now));
		}
		if (cosa.getId() == 0)
			cosa = this.saveFromCreateChecksAndPreparations(cosa);
		else
			cosa = this.saveFromEditChecksAndPreparations(cosa);
		Cosa savedCosa;
		savedCosa = this.cosaRepository.save(cosa);
		return savedCosa;
	}
	private Cosa saveFromCreateChecksAndPreparations(final Cosa cosa) {
		this.isAdministratorAunthenticated();
		cosa.setTicker(this.generateTicker());
		return cosa;
	}
	private Cosa saveFromEditChecksAndPreparations(final Cosa cosa) {

		final Cosa cosaInDB = this.cosaRepository.findOne(cosa.getId());
		Assert.notNull(cosaInDB);
		this.isCorrectAdministratorAunthenticated(cosa);
		this.isCorrectAdministratorAunthenticated(cosaInDB);
		return cosa;
	}
	public void delete(final Cosa cosa) {

		final Cosa cosaInDB = this.cosaRepository.findOne(cosa.getId());
		Assert.notNull(cosaInDB);
		this.isCorrectAdministratorAunthenticated(cosa);
		this.isCorrectAdministratorAunthenticated(cosaInDB);
		Assert.isTrue(cosaInDB.getIsDraft());

	}

	public void associateCosaToNewspaper(final int cosaId, final int newspaperId) {
		final Cosa cosa = this.cosaRepository.findOne(cosaId);
		final Newspaper newspaper = this.newspaperService.findOne(newspaperId);
		Assert.notNull(cosa);
		Assert.notNull(newspaper);
		Assert.isTrue(!cosa.getIsDraft());
		this.isCorrectAdministratorAunthenticated(cosa);
		cosa.setNewspaper(newspaper);
		this.cosaRepository.save(cosa);

	}
	public Cosa findOne(final int cosaId) {
		Cosa result;
		result = this.findOne(cosaId);
		return result;
	}

	public void flush() {
		this.cosaRepository.flush();
	}

	//Auxiliars method ----------------------------------------------------------

	private Administrator isAdministratorAunthenticated() {
		final Administrator administrator;
		administrator = this.administratorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(administrator, "ADMIN"));
		return administrator;

	}
	private void isCorrectAdministratorAunthenticated(final Cosa cosa) {
		Assert.isTrue(cosa.getAdministrator().getId() == this.isAdministratorAunthenticated().getId());
	}
	private String generateTicker() {
		String result;
		final String year = new Integer(Calendar.getInstance().get(Calendar.YEAR)).toString().substring(2, 4);
		final Integer nMonth = new Integer(Calendar.getInstance().get(Calendar.MONTH) + 1);
		final Integer nDay = new Integer(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String month = nMonth.toString();
		String day = nDay.toString();
		if (nMonth < 10)
			month = "0" + month;
		if (nDay < 10)
			day = "0" + day;
		//DDDlL-yy-mm-dd-\W{3} D es un digito, l una letra minúscula, L una mayúscula y \W{3} 3 caracteres
		//cualquiera entre letras mayúculas, minúsculas, digitos y "_".
		do
			result = this.getRandomString(3, "D") + this.getRandomString(1, "l") + this.getRandomString(1, "L") + "-" + year + "-" + month + "-" + day + "-" + this.getRandomString(3, "lLD_");
		while (this.cosaRepository.findOneByTicker(result) != null);
		return result;
	}

	private String getRandomString(final int lenght, final String characterType) {
		final String uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String lowercaseAlphabet = "abcdefghijklmnopqrstuvwxy";
		final String digits = "0123456789";

		String charactersSet;
		charactersSet = "";
		if (characterType.contains("l"))
			charactersSet = charactersSet + lowercaseAlphabet;
		if (characterType.contains("L"))
			charactersSet = charactersSet + uppercaseAlphabet;
		if (characterType.contains("D"))
			charactersSet = charactersSet + digits;
		if (characterType.contains("_"))
			charactersSet = charactersSet + "_";

		String randomString;

		randomString = "";
		final Random random = new Random();
		if (charactersSet.length() != 0)
			for (int i = 0; i < lenght; i++) {
				final int randomIndex = random.nextInt(charactersSet.length());
				randomString = randomString + charactersSet.charAt(randomIndex);
			}

		return randomString;
	}
}
