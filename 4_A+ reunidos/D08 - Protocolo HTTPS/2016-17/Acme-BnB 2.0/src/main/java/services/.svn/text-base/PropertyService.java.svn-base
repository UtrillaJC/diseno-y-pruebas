
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Finder;
import domain.Lessor;
import domain.Property;
import domain.Tenant;
import repositories.PropertyRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class PropertyService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PropertyRepository	propertyRepository;

	// Supporting services ------------------------------------------------------

	@Autowired
	private LessorService		lessorService;

	@Autowired
	private TenantService		tenantService;

	@Autowired
	private FinderService		finderService;


	// Constructors-------------------------------------------------------

	public PropertyService() {
		super();
	}

	// Simple CRUD Methods --------------------------------------------------------

	public Property create() {
		Property result;
		Lessor principal;

		principal = lessorService.findByPrincipal();
		Assert.notNull(principal);

		result = new Property();

		result.setLessor(principal);

		return result;
	}

	public Property findOne(int propertyID) {
		Property res;
		res = this.propertyRepository.findOne(propertyID);
		Assert.notNull(res);
		return res;
	}

	public Collection<Property> findAll() {

		Collection<Property> res;
		res = this.propertyRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Property save(Property property) {
		Property result;
		Assert.notNull(property);

		result = propertyRepository.save(property);

		return result;
	}

	public void delete(Property property) {
		Lessor principal;

		principal = lessorService.findByPrincipal();
		Assert.isTrue(principal.equals(property.getLessor()));

		propertyRepository.delete(property);
	}

	//Other Business Methods ----------------------------------------------------------------

	public Collection<Property> findAllByLessor(Lessor lessor) {
		Assert.notNull(lessor);
		Collection<Property> result;
		Lessor principal;

		principal = lessorService.findByPrincipal();
		Assert.isTrue(lessor.equals(principal));

		result = propertyRepository.findAllByLessorId(lessor.getId());

		return result;
	}

	public Collection<Property> findAllByLessorOrderAuditsSizeId(int lessorId) {
		Assert.notNull(lessorId);
		Collection<Property> result;

		result = propertyRepository.findAllByLessorOrderAuditsSizeId(lessorId);

		return result;
	}

	public Collection<Property> findAllByLessorOrderRequestsSizeId(int lessorId) {
		Assert.notNull(lessorId);
		Collection<Property> result;

		result = propertyRepository.findAllByLessorOrderRequestsSizeId(lessorId);

		return result;
	}

	public Collection<Property> findAllByLessorOrderRequestsAcceptedSizeId(int lessorId) {
		Assert.notNull(lessorId);
		List<Property> result;

		result = (List<Property>) propertyRepository.findAllByLessorId(lessorId);

		return result;
	}

	public Collection<Property> findAllByLessorOrderRequestsDeniedSizeId(int lessorId) {
		Assert.notNull(lessorId);
		List<Property> result;

		result = (List<Property>) propertyRepository.findAllByLessorId(lessorId);

		return result;
	}

	public Collection<Property> findAllByLessorOrderRequestsPendingSizeId(int lessorId) {
		Assert.notNull(lessorId);
		List<Property> result;

		result = (List<Property>) propertyRepository.findAllByLessorId(lessorId);

		return result;
	}

	public Collection<Property> findAllByFinder(Finder finder) {
		Assert.notNull(finder);
		Collection<Property> result;
		Tenant principal;
		Date momentActual;
		Date lastSearch;

		principal = tenantService.findByPrincipal();
		Assert.isTrue(finder.equals(principal.getFinder()));
		momentActual = new Date(System.currentTimeMillis() - 1000);
		lastSearch = finder.getLastSearch();

		result = finder.getProperties();

		long minutes;
		long miliseconds;

		if (lastSearch == null) {
			minutes = 0;
		} else {
			miliseconds = getDateDiff(lastSearch, momentActual, TimeUnit.MILLISECONDS);
			minutes = miliseconds / 60000;
		}

		if (minutes > 59) {
			finder.getProperties().removeAll(result);
		}

		System.out.println(minutes);

		return result;
	}

	public void search(Finder finder) {
		Assert.notNull(finder);
		Tenant principal;
		Collection<Property> propertiesFinder;
		Collection<Property> propertiesAll;
		Date momentActual;
		Date lastSearch;
		long minutes;
		long miliseconds;

		principal = tenantService.findByPrincipal();
		Assert.isTrue(finder.equals(principal.getFinder()));
		momentActual = new Date(System.currentTimeMillis() - 1000);
		propertiesAll = findAll();
		propertiesFinder = finder.getProperties();
		lastSearch = finder.getLastSearch();

		if (lastSearch == null || finder.getProperties().isEmpty()) {
			minutes = 60;
		} else {
			miliseconds = getDateDiff(lastSearch, momentActual, TimeUnit.MILLISECONDS);
			minutes = miliseconds / 60000;
		}

		if (minutes > 59) {
			finder.getProperties().removeAll(propertiesFinder);
			for (Property p : propertiesAll) {
				if (finder.getMaximumPrice() == null && finder.getMinimumPrice() == null && finder.getKeyword().isEmpty()) {
					if (p.getAddress().contains(finder.getDestinationCity())) {
						finder.getProperties().add(p);
					}
				} else if (finder.getMaximumPrice() == null && finder.getMinimumPrice() == null) {
					if (p.getAddress().contains(finder.getDestinationCity()) && p.getAddress().contains(finder.getKeyword())) {
						finder.getProperties().add(p);
					}
				} else if (finder.getMaximumPrice() == null && finder.getKeyword().isEmpty()) {
					if (p.getAddress().contains(finder.getDestinationCity()) && p.getRatePerDay() >= finder.getMinimumPrice()) {
						finder.getProperties().add(p);
					}
				} else if (finder.getMinimumPrice() == null && finder.getKeyword().isEmpty()) {
					if (p.getAddress().contains(finder.getDestinationCity()) && p.getRatePerDay() <= finder.getMaximumPrice()) {
						finder.getProperties().add(p);
					}
				} else if (finder.getMaximumPrice() == null && finder.getMinimumPrice() != null && !finder.getKeyword().isEmpty()) {
					if (p.getAddress().contains(finder.getDestinationCity()) && p.getRatePerDay() <= finder.getMinimumPrice() && p.getName().contains(finder.getKeyword())
						|| p.getAddress().contains(finder.getDestinationCity()) && p.getRatePerDay() >= finder.getMinimumPrice() && p.getAddress().contains(finder.getKeyword())
						|| p.getAddress().contains(finder.getDestinationCity()) && p.getRatePerDay() >= finder.getMinimumPrice() && p.getDescription().contains(finder.getKeyword())) {
						finder.getProperties().add(p);
					}

				} else if (finder.getMinimumPrice() == null && finder.getMaximumPrice() != null && !finder.getKeyword().isEmpty()) {
					if (p.getAddress().contains(finder.getDestinationCity()) && p.getRatePerDay() <= finder.getMaximumPrice() && p.getName().contains(finder.getKeyword())
						|| p.getAddress().contains(finder.getDestinationCity()) && p.getRatePerDay() <= finder.getMaximumPrice() && p.getAddress().contains(finder.getKeyword())
						|| p.getAddress().contains(finder.getDestinationCity()) && p.getRatePerDay() <= finder.getMaximumPrice() && p.getDescription().contains(finder.getKeyword())) {
						finder.getProperties().add(p);
					}

				} else if (finder.getKeyword().isEmpty() && finder.getMinimumPrice() != null && finder.getMaximumPrice() != null) {
					if (p.getAddress().contains(finder.getDestinationCity()) && p.getRatePerDay() <= finder.getMaximumPrice() && p.getRatePerDay() >= finder.getMinimumPrice()) {
						finder.getProperties().add(p);
					}
				} else if (p.getAddress().contains(finder.getDestinationCity()) && p.getRatePerDay() <= finder.getMaximumPrice() && p.getRatePerDay() >= finder.getMinimumPrice() && p.getName().contains(finder.getKeyword())
					|| p.getAddress().contains(finder.getDestinationCity()) && p.getRatePerDay() <= finder.getMaximumPrice() && p.getRatePerDay() >= finder.getMinimumPrice() && p.getDescription().contains(finder.getKeyword())
					|| p.getAddress().contains(finder.getDestinationCity()) && p.getRatePerDay() <= finder.getMaximumPrice() && p.getRatePerDay() >= finder.getMinimumPrice() && p.getAddress().contains(finder.getKeyword())) {
					finder.getProperties().add(p);
				}
			}
		}
		System.out.println(minutes);
		finderService.saveSearch(finder);
	}

	public void checkPrincipal(Property property) {
		Assert.notNull(property);

		UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		Assert.isTrue(property.getLessor().getUserAccount().equals(userAccount));
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	public Collection<Integer> findByAuditorAudits(int auditorId) {
		// Busca las propediedades en las que un auditor ha hecho una auditoria
		return propertyRepository.findByAuditorAudits(auditorId);
	}

	public Double avgResultPerFinder() {
		Double contador = null;
		Double result = null;
		Collection<Finder> finders = finderService.findAll();

		if (!finders.isEmpty()) {
			for (Finder f : finders) {
				if (contador == null) {
					Integer i = f.getProperties().size();
					contador = i.doubleValue();
				} else {
					contador = contador + f.getProperties().size();
				}
			}
			Integer numFinder = propertyRepository.numFinder();
			Double numFinderDouble = numFinder.doubleValue();
			result = contador / numFinderDouble;
		} else {
			result = 0.0;
		}
		return result;
	}

	public Integer minResultPerFinder() {
		Integer result = null;

		Collection<Finder> finders = finderService.findAll();

		for (Finder f : finders) {
			if (result == null) {
				result = f.getProperties().size();
			} else {
				if (f.getProperties().size() < result) {
					result = f.getProperties().size();
				}
			}
		}
		return result;
	}

	public Integer maxResultPerFinder() {
		Integer result = null;

		Collection<Finder> finders = finderService.findAll();

		for (Finder f : finders) {
			if (result == null) {
				result = f.getProperties().size();
			} else {
				if (f.getProperties().size() > result) {
					result = f.getProperties().size();
				}
			}
		}
		return result;
	}

	public Double avgAuditPerProperty() {
		Double result;

		result = this.propertyRepository.avgAuditPerProperty();

		if (result == null) {
			result = 0.0;
		}

		return result;
	}

	public Integer maxAuditPerProperty() {
		Integer result;

		result = this.propertyRepository.maxAuditPerProperty();

		if (result == null) {
			result = 0;
		}

		return result;
	}

	public Integer minAuditPerProperty() {
		Integer result;

		result = this.propertyRepository.minAuditPerProperty();

		if (result == null) {
			result = 0;
		}

		return result;
	}

	public Double avgRequestPerProperty() {
		Double result;

		result = this.propertyRepository.avgRequestPerProperty();

		if (result == null) {
			result = 0.0;
		}

		return result;
	}
}
