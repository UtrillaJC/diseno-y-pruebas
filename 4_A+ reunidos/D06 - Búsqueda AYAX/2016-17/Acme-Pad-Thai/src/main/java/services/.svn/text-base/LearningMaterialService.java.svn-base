package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LearningMaterialRepository;
import domain.Administrator;
import domain.Cook;
import domain.LearningMaterial;
import domain.MasterClass;
import domain.User;

@Service
@Transactional
public class LearningMaterialService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private LearningMaterialRepository learningMaterialRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CookService cookService;
	
	//Constructor methods ============================================================================
	
	//Simple CRUD methods ============================================================================
	
	
	public LearningMaterial findOne(int learningMaterialId){
		LearningMaterial result;

		result = learningMaterialRepository.findOne(learningMaterialId);
		
		return result;
	}	
	
	public Collection<LearningMaterial> findAll(){
		Collection<LearningMaterial> result;
		
		result = learningMaterialRepository.findAll();
		
		return result;
	}
	
	public LearningMaterial create(MasterClass masterClass){
		Assert.notNull(masterClass);	
		LearningMaterial result;
		Cook principal;

		principal = cookService.findByPrincipal();
		
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(masterClass.getCook()));
		
		result = new LearningMaterial() {
		};
	
		return result;
	}
	
	public LearningMaterial save(LearningMaterial learningMaterial){
		Assert.notNull(learningMaterial);	
		LearningMaterial result;
		Cook principal;
				
		principal = cookService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(learningMaterial.getMasterClass().getCook()));

		result = learningMaterialRepository.save(learningMaterial);
		
		return result;
	}
	
	public void delete(LearningMaterial learningMaterial) {
		Assert.notNull(learningMaterial);
		Cook principal;
		
		principal = cookService.findByPrincipal();
		Assert.notNull(principal);	
		
		learningMaterialRepository.delete(learningMaterial);
	}
	
	//Other Business Methods =========================================================================
	
	public Collection<LearningMaterial> findAllByMasterClass(MasterClass masterClass){
		Assert.notNull(masterClass);
		Collection<LearningMaterial> result;
		User principal;
		
		principal = userService.findByPrincipal();
		Assert.isTrue(principal.getMasterClasses().contains(masterClass));
		result = learningMaterialRepository.findAllByMasterClassId(masterClass.getId());
		
		
		return result;
	}
	
	//Dashboard =============================================================================

	public Double avgNumberLearningMaterialsGroupedLearningMaterials(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = learningMaterialRepository.avgNumberLearningMaterialsGroupedLearningMaterials();
		
		return result;
	}
	
	public Integer numberOfMasterClassPromoted(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Integer result;
		
		result = learningMaterialRepository.numberOfMasterClassPromoted();
		
		return result;
	}
	
	public 	Collection<Cook> listOfCookOrderByNumMasterClassPromoted(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Collection<Cook> result;
		
		result = learningMaterialRepository.listOfCookOrderByNumMAsterClassPromoted();
		
		return result;
	}
	
	public Double avgNumberPromotedPerCook(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = learningMaterialRepository.avgNumberPromotedPerCook();
		
		return result;
	}
	
	public Double avgNumberDemotedPerCook(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = learningMaterialRepository.avgNumberDemotedPerCook();
		
		return result;
	}
}
