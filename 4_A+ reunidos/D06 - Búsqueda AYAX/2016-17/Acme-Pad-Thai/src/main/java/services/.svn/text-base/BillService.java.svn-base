package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BillRepository;
import domain.Administrator;
import domain.Banner;
import domain.Bill;
import domain.Campaign;
import domain.Fee;
import domain.Sponsor;

@Service
@Transactional
public class BillService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private BillRepository billRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private FeeService feeService;
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private AdministratorService administratorService;
	
	//Constructor methods ============================================================================
	
	public BillService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Bill findOne(int billId){
		Bill result;
		
		result = billRepository.findOne(billId);
		
		return result;
	}
	
	public Collection<Bill> findAll(){
		Collection<Bill> result;
		
		result = billRepository.findAll();
		
		return result;
	}
	
	public Bill create(Campaign campaign){
		Assert.notNull(campaign);		
		Bill result;
		Date momentCreated;
		Fee fee;
		Integer numDisplayed;
		Double cost;
		String description;

		momentCreated = new Date(System.currentTimeMillis());
		fee = feeService.findOne(56);
		numDisplayed = bannerService.sumNumDisplayedOfBannerForCampaign(campaign);
		cost = fee.getAmount() * numDisplayed;
		description = "Summary of banners ";

		result = new Bill();

		result.setCampaign(campaign);
		result.setMomentCreated(momentCreated);
		result.setMomentPaid(null);
		result.setCost(cost);
		for(Banner banner : campaign.getBanners()){
			description = description + banner.getDescription() + ", ";
		}
		result.setDescription(description);
		
		campaign.getBills().add(result);
	
		return result;
	}
	
	public Bill save(Bill bill){
		Assert.notNull(bill);	
		Assert.notNull(bill.getCampaign());	
		Bill result;
		Date momentCreated;
		
		momentCreated = new Date(System.currentTimeMillis()-1000);
	
		bill.setMomentCreated(momentCreated);
		
		result = billRepository.save(bill);
		
		return result;		
	}
	

	public void delete(Bill bill) {
		Assert.notNull(bill);
		Sponsor principal;
		
		principal = sponsorService.findByPrincipal();
		Assert.notNull(principal);
		
		billRepository.delete(bill);		
	}
		
	//Other Business Methods =========================================================================
	
	public Collection<Bill> findAllBySponsor(Sponsor sponsor){
		Assert.notNull(sponsor);
		Collection<Bill> result;
		Sponsor principal;
		
		principal = sponsorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(sponsor));
		result = billRepository.findAllBySponsorId(sponsor.getId());
		
		return result;
	}
	

	public Collection<Bill> findAllBillsUnpaid(){
		Collection<Bill> result;
		
		result = billRepository.findAllBillsUnpaid();
		
		return result;
	}
	
	public Collection<Bill> findAllBySponsorPaid(Sponsor sponsor){
		Assert.notNull(sponsor);
		Collection<Bill> result;
		Sponsor principal;
		
		principal = sponsorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(sponsor));
		result = billRepository.findAllBySponsorIdPaid(sponsor.getId());
		
		return result;
	}
	
	public Collection<Bill> findAllBySponsorNotPaid(Sponsor sponsor){
		Assert.notNull(sponsor);
		Collection<Bill> result;
		Sponsor principal;
		
		principal = sponsorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(sponsor));
		result = billRepository.findAllBySponsorIdNotPaid(sponsor.getId());
		
		return result;
	}
	
	public Collection<Bill> findAllBySponsorNotPaidToAdministrator(Sponsor sponsor){
		Assert.notNull(sponsor);
		Collection<Bill> result;
		
		result = billRepository.findAllBySponsorIdNotPaid(sponsor.getId());
		
		return result;
	}
	
	public Collection<Bill> findAllByMonth(){
		Collection<Bill> result;
		
		result = billRepository.findAllByMonth();
		
		return result;
		
	}

	public void pay(int billId){
		Bill bill;
		Date momentPaid;
		
		bill = findOne(billId);
		momentPaid = new Date(System.currentTimeMillis()-1000);
	
		bill.setMomentPaid(momentPaid);
		
		billRepository.save(bill);
	}
	
	@SuppressWarnings("deprecation")
	public void generateMonthyBills(Campaign campaign){
		Assert.notNull(campaign);
		Date momentActual;
		int cont;
		
		momentActual = new Date(System.currentTimeMillis()-1000);
		
		Assert.isTrue(momentActual.after(campaign.getDateStart()));
		
		if(momentActual.after(campaign.getDateEnd()) && momentActual.getYear() == campaign.getDateStart().getYear()){
				cont = campaign.getDateEnd().getMonth() - campaign.getDateStart().getMonth();
		}else if(momentActual.getYear() == campaign.getDateStart().getYear()){
			cont = momentActual.getMonth() - campaign.getDateStart().getMonth();
		}else if(momentActual.getYear() == campaign.getDateStart().getYear() - 1){
			cont = 12 - campaign.getDateStart().getMonth() + momentActual.getMonth(); 
		}else{
			cont = 12 *( momentActual.getYear() - campaign.getDateStart().getYear());
		}
		
		for(int i = 1; i <= cont; i++){
			Bill bill;
			
			bill = create(campaign);
			
			save(bill);
		}
	}

	//Dashboard =============================================================================

	public Double avgBillsPaid(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = billRepository.avgBillsPaid();
		
		return result;
	}
	
	public Double avgBillsNotPaid(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = billRepository.avgBillsNotPaid();
		
		return result;
	}
	
	public Double stddevBillsPaid(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = billRepository.stddevBillsPaid();
		
		return result;
	}

	public Double stddevBillsNotPaid(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = billRepository.stddevBillsNotPaid();
		
		return result;
	}
}
