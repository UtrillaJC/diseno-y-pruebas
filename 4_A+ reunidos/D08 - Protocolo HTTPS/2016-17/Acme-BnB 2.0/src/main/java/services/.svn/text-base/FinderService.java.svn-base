
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.Tenant;

@Service
@Transactional
public class FinderService {

	//Managed Repository =============================================================================

	@Autowired
	private FinderRepository finderRepository;

	//Supported Services =============================================================================
	
	@Autowired
	private TenantService tenantService;


	//Constructor methods ============================================================================

	public FinderService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	public Finder findOne(int finderId) {
		Finder result;

		result = finderRepository.findOne(finderId);

		return result;
	}

	public Collection<Finder> findAll() {
		Collection<Finder> result;

		result = finderRepository.findAll();

		return result;
	}

	public Finder create() {
		Finder result;

		result = new Finder();

		result.setDestinationCity("destinationCity");
		result.setKeyword("");

		return result;
	}
	public Finder saveEdit(Finder finder) {
		Assert.notNull(finder);
		Date momentActual;
		Finder result;
		Tenant principal;
		
		principal = tenantService.findByPrincipal();
		Assert.isTrue(finder.equals(principal.getFinder()));
		
		momentActual = new Date(System.currentTimeMillis() - 1000);
		finder.setLastSearch(momentActual);
		
		result = finderRepository.save(finder);

		return result;
	}


	public Finder save(Finder finder) {
		Assert.notNull(finder);
		Date momentActual;
		Finder result;

		momentActual = new Date(System.currentTimeMillis() - 1000);
		finder.setLastSearch(momentActual);
		
		result = finderRepository.save(finder);

		return result;
	}
	
	public Finder saveSearch(Finder finder) {
		Assert.notNull(finder);
		Finder result;
		Tenant principal;
				
		principal = tenantService.findByPrincipal();
		Assert.isTrue(finder.equals(principal.getFinder()));
		result = finderRepository.save(finder);

		return result;
	}

	//Other Business Methods =========================================================================
	
	public Finder findByPrincipal() {
		Finder result;
		Tenant tenant;
		
		tenant = this.tenantService.findByPrincipal();
		result = tenant.getFinder();
		
		return result;
	}

}
