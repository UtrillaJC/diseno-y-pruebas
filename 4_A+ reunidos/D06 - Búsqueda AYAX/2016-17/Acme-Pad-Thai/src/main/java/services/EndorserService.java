package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorserRepository;
import domain.Curriculum;
import domain.Endorser;
import domain.Nutritionist;

@Service
@Transactional
public class EndorserService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private EndorserRepository endorserRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private NutritionistService nutritionistService;
	
	//Constructor methods ============================================================================
	
	public EndorserService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Endorser findOne(int endorserId) {
		Endorser result;
		
		result = endorserRepository.findOne(endorserId);
		
		return result;
	}
	
	public Endorser create(){
		Endorser result;
		Nutritionist principal;
		Collection<Curriculum> curricula;

		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Nutritionist.class, principal);
		curricula = new HashSet<Curriculum>();
		
		result = new Endorser();
		
		result.setCurricula(curricula);

		return result;
	}
	
	public Endorser save(Endorser endorser){
		Assert.notNull(endorser);
		Endorser result;
		Nutritionist principal;

		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Nutritionist.class, principal);
		
		result = endorserRepository.save(endorser);
		
		return result;
	}

	public void delete(Endorser endorser) {
		Assert.notNull(endorser);
		Nutritionist principal;
		
		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);	
		
		endorserRepository.delete(endorser);		
	}
	
	//Other Business Methods =========================================================================
	
	public Collection<Endorser> findAllByNotCurriculum(Curriculum curriculum){
		Assert.notNull(curriculum);
		Collection<Endorser> result;

		result = endorserRepository.findAllByNotCurriculumId(curriculum.getId());
		
		return result;
	}
	
	public Collection<Endorser> findAllByCurriculum(int curriculumId){
		Collection<Endorser> result;
		
		result = endorserRepository.findAllByCurriculumId(curriculumId);
				
		return result;
	}
	
}
