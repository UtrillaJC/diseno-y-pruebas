package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class SpamTerm extends DomainEntity{
	
	//Attributes ====================================================================================
	
	private Collection<String> keywords;
		
	//Constructor ====================================================================================
		
	public SpamTerm(){
		super();
	}

	//Getters & setters ====================================================================================

	@ElementCollection
	public Collection<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(Collection<String> keywords) {
		this.keywords = keywords;
	}

	//Relationships ====================================================================================

}
