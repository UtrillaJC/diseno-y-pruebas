
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Configuration;
import repositories.ConfigurationRepository;

@Service
@Transactional
public class ConfigurationService {

	//Managed Repository =============================================================================

	@Autowired
	private ConfigurationRepository	configurationRepository;

	//Supported Services =============================================================================

	@Autowired
	private AdministratorService	administratorService;


	//Constructor methods ============================================================================

	public ConfigurationService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	public Collection<Configuration> findAll() {
		Collection<Configuration> result;

		result = configurationRepository.findAll();

		return result;
	}

	public Configuration findOne(int configurationId) {
		Configuration result;

		result = configurationRepository.findOne(configurationId);

		return result;
	}

	public Configuration findOneToEdit(int configurationId) {
		Configuration result;
		Administrator principal;

		principal = administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		result = configurationRepository.findOne(configurationId);

		return result;
	}

	public Configuration create() {
		Configuration result;
		Administrator principal;

		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);

		result = new Configuration();

		return result;
	}

	public Configuration save(Configuration configuration) {
		Assert.notNull(configuration);
		Configuration result;
		Administrator principal;

		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);

		result = configurationRepository.save(configuration);

		return result;
	}

	public Double findFee() {
		Configuration c = findAll().iterator().next();
		return c.getFee();
	}

	//Other Business Methods =========================================================================

}
