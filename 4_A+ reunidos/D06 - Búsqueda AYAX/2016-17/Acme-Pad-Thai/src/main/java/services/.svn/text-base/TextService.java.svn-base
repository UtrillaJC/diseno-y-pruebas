package services;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TextRepository;
import domain.Cook;
import domain.MasterClass;
import domain.Text;

@Service
@Transactional
public class TextService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private TextRepository textRepository;
	
	//Supported Services =============================================================================

	@Autowired
	private CookService cookService;
	
	//Constructor methods ============================================================================
	
	public TextService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Text findOne(int textId){
		Text result;
		
		result = textRepository.findOne(textId);
		
		return result;
	}
	
	public Text create(MasterClass masterClass){
		Assert.notNull(masterClass);
		Assert.notNull(masterClass.getCook());
		Text result;
		Cook principal;
		Collection<URL> attachments;
		
		principal = cookService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Cook.class, principal);
		Assert.isTrue(principal.equals(masterClass.getCook()));
		attachments = new HashSet<URL>();

		result = new Text();
		
		result.setMasterClass(masterClass);
		result.setAttachments(attachments);
		result.setType("Text");
		
		return result;	
	}
	
	public Text save(Text text){
		Assert.notNull(text);
		Assert.notNull(text.getMasterClass().getCook());
		Text result;
		Cook principal;
		
		principal = cookService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Cook.class, principal);
		Assert.isTrue(principal.equals(text.getMasterClass().getCook()));
				
		result = textRepository.save(text);
		
		return result;
	}
	
	//Other Business Methods =========================================================================

}
