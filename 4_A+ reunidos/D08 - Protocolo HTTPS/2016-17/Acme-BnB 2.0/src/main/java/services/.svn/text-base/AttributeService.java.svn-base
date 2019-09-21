
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Attribute;
import domain.ValueAttribute;
import repositories.AttributeRepository;

@Service
@Transactional
public class AttributeService {

	//Managed Repository =============================================================================

	@Autowired
	private AttributeRepository attributeRepository;

	//Supported Services =============================================================================

	//			@Autowired
	//			private AdministratorService administratorService;


	//Constructor methods ============================================================================

	public AttributeService() {
		super();
	}

	//Simple CRUD methods ============================================================================

	public Attribute create() {
		Attribute result;
		//				this.administratorService.checkPrincipal();
		Collection<ValueAttribute> valueAttributes = new ArrayList<ValueAttribute>();

		result = new Attribute();

		result.setValueAttributes(valueAttributes);

		return result;
	}

	public Attribute findOne(int attributeId) {
		Attribute result;

		result = attributeRepository.findOne(attributeId);

		return result;
	}

	public Collection<Attribute> findAll() {
		Collection<Attribute> result;

		result = attributeRepository.findAll();

		return result;
	}

	public Attribute save(Attribute attribute) {
		Assert.notNull(attribute);
		//				this.administratorService.checkPrincipal();
		Attribute result;

		result = attributeRepository.save(attribute);

		return result;
	}

	public void delete(Attribute attribute) {
		Assert.notNull(attribute);
		//				this.administratorService.checkPrincipal();

		attributeRepository.delete(attribute);
	}

	//Other Business Methods =========================================================================
	public Collection<Attribute> AttribueOrderUseId() {
		Collection<Attribute> result;

		result = this.attributeRepository.AttribueOrderUseId();
		Assert.notNull(result);

		return result;
	}
}
