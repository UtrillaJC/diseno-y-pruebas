
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import security.LoginService;
import security.UserAccount;
import domain.Explorer;
import domain.Finder;
import domain.Trip;

@Service
@Transactional
public class FinderService {

	// Managed Repository =============================================================================

	@Autowired
	private FinderRepository	finderRepository;

	// Supported Services =============================================================================

	@Autowired
	private ExplorerService		explorerService;


	// Constructor methods ============================================================================

	public FinderService() {
		super();
	}

	// Simple CRUD methods ============================================================================

	public Finder create(final Explorer explorer) {

		Assert.notNull(explorer);

		Finder result = null;
		result = new Finder();
		result.setLastUpdate(new Date(System.currentTimeMillis() - 1000));
		result.setExplorer(explorer);
		explorer.setFinder(result);
		result.setTrips(new ArrayList<Trip>());
		return result;
	}

	public Finder findOne(final int finderId) {

		Finder result = null;
		result = this.finderRepository.findOne(finderId);
		return result;
	}

	public Collection<Finder> findAll() {

		Collection<Finder> result = null;
		result = this.finderRepository.findAll();
		return result;
	}

	public Finder save(final Finder finder) {

		Assert.notNull(finder);

		Finder result = null;
		result = this.finderRepository.save(finder);
		result.setLastUpdate(new Date(System.currentTimeMillis() - 1000));
		return result;
	}

	public void delete(final Finder finder) {

		this.finderRepository.delete(finder);
		finder.getExplorer().setFinder(null);
	}

	// Other Business Methods =========================================================================

	public Finder findByPrincipal() {

		Finder result = null;
		final UserAccount userAccount = LoginService.getPrincipal();
		result = this.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Finder findByUserAccountId(final int userAccountId) {

		Finder result = null;
		result = this.finderRepository.findByUserAccountId(userAccountId);
		return result;
	}

	public void checkPrincipal(final Finder finder) {

		final Explorer explorer = this.explorerService.findByPrincipal();
		Assert.isTrue(explorer.equals(finder.getExplorer()));
	}

	public Finder assignTripsToFinder(final Finder finder, final Collection<Trip> trips) {
		Assert.notNull(finder);

		finder.setTrips(trips);

		return finder;

	}

	public void deleteReferenceTrip(final Trip trip) {
		final Collection<Finder> finders = this.finderRepository.findFinderByTrip(trip);

		for (final Finder f : finders)
			f.getTrips().remove(trip);

	}
}
