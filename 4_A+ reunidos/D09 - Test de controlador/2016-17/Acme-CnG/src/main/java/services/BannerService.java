
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BannerRepository;
import domain.Administrator;
import domain.Banner;

@Service
@Transactional
public class BannerService {

	//Managed Repository =============================================================================

	@Autowired
	private BannerRepository		bannerRepository;

	//Supported Services =============================================================================

	@Autowired
	private AdministratorService	administratorService;


	//Constructor methods ============================================================================

	public BannerService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	public Collection<Banner> findAll() {
		Collection<Banner> result;

		result = this.bannerRepository.findAll();

		return result;
	}

	public Banner save(Banner banner) {
		Assert.notNull(banner);
		Banner result;
		Administrator principal;

		principal = this.administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		Assert.notNull(principal);

		result = this.bannerRepository.saveAndFlush(banner);

		return result;

	}

	//Other Business Methods =========================================================================

	public Banner findOneToEdit(int bannerId) {
		Banner result;
		Administrator principal;

		principal = this.administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		Assert.notNull(principal);

		result = this.bannerRepository.findOne(bannerId);
		Assert.notNull(result);

		return result;
	}
	public Banner findBanner() {
		Banner result;
		Collection<Banner> banners;

		banners = this.findAll();
		result = new Banner();

		for (Banner b : banners)
			result = this.bannerRepository.findOne(b.getId());

		return result;
	}
}
