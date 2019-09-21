package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ValueAttributeRepository;
import domain.Lessor;
import domain.Property;
import domain.ValueAttribute;

@Service
@Transactional
public class ValueAttributeService {
	
	//Managed Repository =============================================================================
	
		@Autowired
		private ValueAttributeRepository valueAttributeRepository;
		
		//Supported Services =============================================================================
		
		@Autowired
		private LessorService lessorService;
		
		//Constructor methods ============================================================================
		
		public ValueAttributeService() {
			super();
		}
		
		//Simple CRUD methods ============================================================================
		
		public ValueAttribute create(Property property) {;
			Assert.notNull(property);
			ValueAttribute result;
			this.lessorService.checkPrincipal();

			result = new ValueAttribute();
			
			result.setProperty(property);
			
			return result;
		}
		
		public ValueAttribute findOne(int valueAttributeId){
			ValueAttribute result;
			
			result = valueAttributeRepository.findOne(valueAttributeId);
			
			return result;
		}
		
		public Collection<ValueAttribute> findAll(){
			Collection<ValueAttribute> result;
			
			result = valueAttributeRepository.findAll();
			
			return result;
		}
		
		
		public ValueAttribute save(ValueAttribute valueAttribute) {
			Assert.notNull(valueAttribute);
			ValueAttribute result;
			Lessor principal = this.lessorService.findByPrincipal();
			
			Assert.isTrue(principal.equals(valueAttribute.getProperty().getLessor()));
			result = valueAttributeRepository.save(valueAttribute);
		
			return result;
		}
		
		public void delete(ValueAttribute valueAttribute) {
			Lessor principal;

			principal = lessorService.findByPrincipal();
			Assert.isTrue(principal.equals(valueAttribute.getProperty().getLessor()));

			valueAttributeRepository.delete(valueAttribute);
		}
		
		//Other Business Methods =========================================================================
		
		public Collection<ValueAttribute> findAllByProperty(Property property){
			Assert.notNull(property);
			Collection<ValueAttribute> result;
						
			result = valueAttributeRepository.findAllByPropertyId(property.getId());
			
			return result;	
		}
		

}
