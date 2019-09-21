package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MasterClassRepository;
import domain.Administrator;
import domain.Cook;
import domain.LearningMaterial;
import domain.MasterClass;
import domain.Message;
import domain.User;

@Service
@Transactional
public class MasterClassService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private MasterClassRepository masterClassRepository;
	
	//Supported Services =============================================================================
	
	@Autowired
	private CookService cookService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LearningMaterialService learningMaterialService;
	
	//Constructor methods ============================================================================
	
	//Simple CRUD methods ============================================================================
	
	public MasterClass findOne(int masterClassId){
		MasterClass result;
		
		result = masterClassRepository.findOne(masterClassId);
		
		return result;
	}
	
	public Collection<MasterClass> findAll(){
		Collection<MasterClass> result;
		
		result = masterClassRepository.findAll();
		
		return result;
	}
	
	public MasterClass create(Cook cook){
		Assert.notNull(cook);
		MasterClass result;
		Cook principal;
		Collection<LearningMaterial> learningMaterials;
		Collection<User> users;
		
		principal = cookService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(cook));
		Assert.isInstanceOf(Cook.class, principal);
		learningMaterials = new HashSet<LearningMaterial>();
		users = new HashSet<User>();
		
		result = new MasterClass();
		
		result.setLearningMaterials(learningMaterials);
		result.setIsPromoted(false);
		result.setCook(cook);
		result.setUsers(users);
		cook.getMasterClasses().add(result);
	
		return result;
	}
	
	public MasterClass save(MasterClass masterClass){
		Assert.notNull(masterClass);	
		Assert.notNull(masterClass.getCook());
		Assert.notNull(masterClass.getIsPromoted());	
		MasterClass result;
		Cook principal;
				
		principal = cookService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(masterClass.getCook()));
		Assert.isInstanceOf(Cook.class, principal);

		result = masterClassRepository.save(masterClass);
		
		return result;
	}
	
	public void delete(MasterClass masterClass){
		Assert.notNull(masterClass);
		Cook principal;
		Collection<LearningMaterial> learningMaterials;
		
		principal = cookService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(masterClass.getCook()));
		Assert.isInstanceOf(Cook.class, principal);
		learningMaterials = masterClass.getLearningMaterials();
		
		if(learningMaterials != null){
			for(LearningMaterial learningMaterial: learningMaterials){
				
				learningMaterialService.delete(learningMaterial);
								
			}
		}
		
		if(masterClass.getUsers().isEmpty()){
			masterClassRepository.delete(masterClass);	
		}else{
			sendMessagesUsers(masterClass);
			masterClassRepository.delete(masterClass);	
		}
		
	}
	
	//Other Business Methods =========================================================================
	
	public MasterClass findOneByUserPrincipal(int masterClassId){
		MasterClass result;
		User principal;
		
		principal = userService.findByPrincipal();
		result = masterClassRepository.findOne(masterClassId);
		
		Assert.notNull(principal);
		Assert.notNull(result);
		Assert.isTrue(principal.getMasterClasses().contains(result));
		
		return result;
	}

	public Collection<MasterClass> findAllByUser(User user){
		Assert.notNull(user);
		Collection<MasterClass> result;
		User principal;
		
		principal = userService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(user));
		
		result = masterClassRepository.findAllByUserId(user.getId());
		
		return result;
	}
	
	public Collection<MasterClass> findAllByNotUser(User user){
		Assert.notNull(user);
		Collection<MasterClass> result;
		User principal;
		
		principal = userService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(user));
		
		result = masterClassRepository.findAllByNotUserId(user.getId());
		
		return result;
	}
	
	public Collection<MasterClass> findAllPromoted(){
		Collection<MasterClass> result;
		Administrator principal;
		
		principal = administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		result = masterClassRepository.findAllPromoted();
		
		return result;
	}
	
	public Collection<MasterClass> findAllDemoted(){
		Collection<MasterClass> result;
		Administrator principal;
		
		principal = administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		
		result = masterClassRepository.findAllDemoted();
		
		return result;
	}
	
	public void register(int masterClassId) {
		User user;
		Collection<User> users;
		MasterClass masterClass;
		Collection<MasterClass> masterClasses;
		
		user = userService.findByPrincipal();
		masterClasses = user.getMasterClasses();
		masterClass = masterClassRepository.findOne(masterClassId);
		users = masterClass.getUsers();		
		
		Assert.isTrue(!masterClasses.contains(masterClass));
		
		masterClasses.add(masterClass);
		users.add(user);

		masterClassRepository.save(masterClass);
	}

	public void unregister(int masterClassId) {
		User user;
		Collection<User> users;
		MasterClass masterClass;
		Collection<MasterClass> masterClasses;
		
		user = userService.findByPrincipal();
		masterClasses = user.getMasterClasses();
		masterClass = masterClassRepository.findOne(masterClassId);
		users = masterClass.getUsers();				
		
		Assert.isTrue(masterClasses.contains(masterClass));
		
		masterClasses.remove(masterClass);
		users.remove(user);

		masterClassRepository.save(masterClass);	
	}
	
	public void promote(int masterClassId) {
		Administrator administrator;
		MasterClass masterClass;
		Collection<MasterClass> masterClasses;
		
		administrator = administratorService.findByPrincipal();
		masterClasses = masterClassRepository.findAllPromoted();
		masterClass = masterClassRepository.findOne(masterClassId);
				
		Assert.isInstanceOf(Administrator.class, administrator);
		Assert.isTrue(!masterClasses.contains(masterClass));
		
		masterClass.setIsPromoted(true);
		
		masterClassRepository.save(masterClass);
	}

	public void demote(int masterClassId) {
		Administrator administrator;
		MasterClass masterClass;
		Collection<MasterClass> masterClasses;
		
		administrator = administratorService.findByPrincipal();
		masterClasses = masterClassRepository.findAllDemoted();
		masterClass = masterClassRepository.findOne(masterClassId);
		
		Assert.isInstanceOf(Administrator.class, administrator);
		Assert.isTrue(!masterClasses.contains(masterClass));
		
		masterClass.setIsPromoted(false);

		masterClassRepository.save(masterClass);	
	}
	
	public Collection<MasterClass> findAllByCook(Cook cook){
		Assert.notNull(cook);
		Collection<MasterClass> result;
		Cook principal;
		
		principal = cookService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(cook));
		
		result = masterClassRepository.findAllByCookId(cook.getId());
		
		return result;
	}
	
	public void sendMessagesUsers(MasterClass masterClass){
		Assert.notNull(masterClass);
		Assert.notNull(masterClass.getCook());
		Cook principal;
		Collection<User> users;
		Message message;
		
		principal = cookService.findByPrincipal();
		Assert.isTrue(principal.equals(masterClass.getCook()));
		users = masterClass.getUsers();
				
		for(User user : users){
			message = messageService.create(principal);
			
			message.setSubject("Master class delete/Clase maestra borrada");
			message.setBody("The masterclass where you have registered has been deleted/La clase maestra donde estabas registrado ha sido borrada");
			message.setPriority("HIGH");
			message.setRecipient(user.getUserAccount());
			
			messageService.save(message);		
		}
	}
	
	//Dashboard =============================================================================

	public Double minMasterClassesCook(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = masterClassRepository.minMasterClassesCook();
		
		return result;
	}
	
	public Double maxMasterClassesCook(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = masterClassRepository.maxMasterClassesCook();
		
		return result;
	}
	
	public Double avgMasterClassesCook(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = masterClassRepository.avgMasterClassesCook();
		
		return result;
	}
	
	public Double stddevMasterClassesCook(){
		Administrator principal;
		principal = administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Double result;
		
		result = masterClassRepository.stddevMasterClassesCook();
		
		return result;
	}
}
