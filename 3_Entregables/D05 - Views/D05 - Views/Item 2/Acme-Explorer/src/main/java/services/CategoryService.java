
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import security.Authority;
import domain.Actor;
import domain.Admin;
import domain.Category;
import domain.Trip;

@Service
@Transactional
public class CategoryService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CategoryRepository	categoryRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private AdminService		adminService;

	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------

	public CategoryService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Category create() {

		Collection<Trip> trips = null;
		Collection<Category> children = null;
		Category res = null;

		children = new ArrayList<Category>();
		trips = new ArrayList<Trip>();

		res = new Category();

		res.setTrips(trips);
		res.setChildrenCategories(children);

		return res;
	}

	public Category findOne(final int categoryId) {

		Assert.notNull(categoryId);
		final Category res = this.categoryRepository.findOne(categoryId);
		return res;

	}

	public Collection<Category> findAll() {

		Admin admin = null;
		Collection<Authority> authorities = null;
		String authority = null;
		Collection<Category> result = null;

		admin = this.adminService.findByPrincipal();
		authorities = admin.getUserAccount().getAuthorities();
		authority = authorities.toArray()[0].toString();

		Assert.isTrue((authority.equals("ADMIN")));

		result = this.categoryRepository.findAll();
		return result;

	}
	public Category save(final Category category) {

		Actor actor = null;
		Collection<Authority> test = null;
		String authority = null;
		Category saved = null;
		Integer parentId = null;

		actor = this.actorService.findByPrincipal();
		test = actor.getUserAccount().getAuthorities();
		authority = test.toArray()[0].toString();
		parentId = category.getParentCategory().getId();

		// Comprobamos que los parámetros de entrada son correctos 

		Assert.isTrue(authority.equals("MANAGER") || authority.equals("ADMIN"));
		Assert.isTrue(!this.categoryRepository.existsThisCategoryName(category.getName(), parentId));  	// Comprobamos que el nombre del 
																										// category no coincide con el 
																										// nombre de los hijos del category 
		// Realizamos las modificaciones correspondientes 			                                   	// padre ni con el nombre del padre

		saved = this.categoryRepository.save(category);
		saved.getParentCategory().getChildrenCategories().add(saved);

		return saved;

	}

	public void delete(final Category category) {

		Admin admin = null;
		Collection<Authority> authorities = null;
		String authority = null;

		admin = this.adminService.findByPrincipal();
		authorities = admin.getUserAccount().getAuthorities();
		authority = authorities.toArray()[0].toString();

		// Comprobamos que los parámetros de entrada son correctos 

		Assert.isTrue((authority.equals("ADMIN")));
		Assert.notNull(category);

		if (category.getChildrenCategories().isEmpty())
			this.categoryRepository.delete(category);
		else {
			category.getChildrenCategories().clear();
			this.categoryRepository.delete(category);
		}

	}

	// Other business methods -------------------------------------------------

	public Collection<Trip> findAllTripByCategoryID(final int categoryId) {

		Admin admin = null;
		Collection<Authority> authorities = null;
		String authority = null;
		Collection<Trip> result = null;

		admin = this.adminService.findByPrincipal();
		authorities = admin.getUserAccount().getAuthorities();
		authority = authorities.toArray()[0].toString();

		// Comprobamos que los parámetros de entrada son correctos.

		Assert.isTrue((authority.equals("ADMIN")));
		Assert.notNull(categoryId);

		result = this.categoryRepository.findAllTripByCategoryID(categoryId);

		return result;

	}

	public Collection<Category> findCategoryChildrenID(final int categoryId) {

		Admin admin = null;
		Collection<Authority> authorities = null;
		String authority = null;
		Collection<Category> result = null;

		admin = this.adminService.findByPrincipal();
		authorities = admin.getUserAccount().getAuthorities();
		authority = authorities.toArray()[0].toString();

		// Comprobamos que los parámetros son correctos

		Assert.isTrue((authority.equals("ADMIN")));
		Assert.notNull(categoryId);

		result = this.categoryRepository.findCategoryChildrenID(categoryId);
		return result;
	}

}
