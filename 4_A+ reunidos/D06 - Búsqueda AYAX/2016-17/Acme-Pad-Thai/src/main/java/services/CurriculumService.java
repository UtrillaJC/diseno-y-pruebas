package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;
import domain.Endorser;
import domain.Nutritionist;

@Service
@Transactional
public class CurriculumService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private CurriculumRepository curriculumRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private NutritionistService nutritionistService;
	
	@Autowired
	private EndorserService endorserService;
	
	//Constructor methods ============================================================================
	
	public CurriculumService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Curriculum findOne(int curriculumId) {
		Curriculum result;
		
		result = curriculumRepository.findOne(curriculumId);
		
		return result;
	}
	
	public Curriculum create(Nutritionist nutritionist){
		Assert.notNull(nutritionist);
		Curriculum result;
		Nutritionist principal;
		Collection<Endorser> endorsers;

		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(nutritionist));
		Assert.isInstanceOf(Nutritionist.class, principal);
		endorsers = new HashSet<Endorser>();
		
		result = new Curriculum();
		
		result.setNutritionist(nutritionist);
		result.setEndorsers(endorsers);
		nutritionist.getCurricula().add(result);

		return result;
	}
	
	public Curriculum save(Curriculum curriculum){
		Assert.notNull(curriculum);
		Assert.notNull(curriculum.getNutritionist());
		Curriculum result;
		Nutritionist principal;
		
		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(curriculum.getNutritionist()));
		Assert.isInstanceOf(Nutritionist.class, principal);
		
		result = curriculumRepository.save(curriculum);
		
		return result;	
	}
	
	public void delete(Curriculum curriculum){
		Assert.notNull(curriculum);		
		Nutritionist principal;
		Collection<Endorser> endorsers;
		
		principal = nutritionistService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(curriculum.getNutritionist()));
		endorsers = curriculum.getEndorsers();

		
		if(endorsers != null){
			for(Endorser endorser: endorsers){
				endorserService.delete(endorser);
			}
		}
		curriculumRepository.delete(curriculum);
	}
	
	//Other Business Methods =========================================================================

	public Collection<Curriculum> findAllByNutritionist(Nutritionist nutritionist){
		Assert.notNull(nutritionist);
		Collection<Curriculum> result;
		
		result = curriculumRepository.findAllByNutritionistId(nutritionist.getId());
		
		return result;
	}
	
	public void addEndorser(Curriculum curriculum, Endorser endorser){
		curriculum.getEndorsers().add(endorser);
		endorser.getCurricula().add(curriculum);
		
		curriculumRepository.save(curriculum);
	}
	
}
