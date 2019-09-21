
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.HasValueRepository;
import domain.HasValue;
import domain.Trip;

@Service
@Transactional
public class HasValueService {

	// Managed Repository =============================================================================

	@Autowired
	private HasValueRepository	hasValueRepository;


	// Supported Services =============================================================================

	// Constructor methods ============================================================================

	public HasValueService() {
		super();
	}

	// Simple CRUD methods ============================================================================

	public HasValue create(final Trip trip) {

		HasValue result = null;
		result = new HasValue();

		result.setTrip(trip);
		trip.getHasValues().add(result);

		return result;

	}

	public Collection<HasValue> findAll() {

		Collection<HasValue> result = null;

		result = this.hasValueRepository.findAll();

		return result;
	}

	public HasValue findOne(final int hasValueId) {

		HasValue result = null;

		result = this.hasValueRepository.findOne(hasValueId);

		return result;

	}

	public HasValue save(final HasValue hasValue) {

		Assert.notNull(hasValue);

		HasValue result = null;

		result = this.hasValueRepository.save(hasValue);

		return result;

	}

	public void delete(final HasValue hasValue) {

		Assert.notNull(hasValue);

		this.hasValueRepository.delete(hasValue);

	}

	// Other Business Methods =========================================================================

	public void deleteHasValues(final Trip trip) {
		final Collection<HasValue> hasValues = trip.getHasValues();
		this.hasValueRepository.delete(hasValues);

	}
}
