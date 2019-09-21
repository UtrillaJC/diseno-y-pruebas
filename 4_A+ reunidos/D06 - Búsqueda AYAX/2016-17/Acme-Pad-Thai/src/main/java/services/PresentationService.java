package services;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PresentationRepository;
import domain.Cook;
import domain.MasterClass;
import domain.Presentation;

@Service
@Transactional
public class PresentationService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private PresentationRepository presentationRepository;
	
	//Supported Services =============================================================================

	@Autowired
	private CookService cookService;
	
	//Constructor methods ============================================================================
	
	public PresentationService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Presentation findOne(int presentationId){
		Presentation result;
		
		result = presentationRepository.findOne(presentationId);
		
		return result;
	}
	
	public Presentation create(MasterClass masterClass){
		Assert.notNull(masterClass);
		Assert.notNull(masterClass.getCook());
		Presentation result;
		Cook principal;
		Collection<URL> attachments;
		
		principal = cookService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Cook.class, principal);
		Assert.isTrue(principal.equals(masterClass.getCook()));
		attachments = new HashSet<URL>();

		result = new Presentation();
		
		result.setMasterClass(masterClass);
		result.setAttachments(attachments);
		result.setType("Presentation");
		
		return result;	
	}
	
	public Presentation save(Presentation presentation){
		Assert.notNull(presentation);
		Assert.notNull(presentation.getMasterClass().getCook());
		Presentation result;
		Cook principal;
		
		principal = cookService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Cook.class, principal);
		Assert.isTrue(principal.equals(presentation.getMasterClass().getCook()));
				
		result = presentationRepository.save(presentation);
		
		return result;
	}
	
	//Other Business Methods =========================================================================

}
