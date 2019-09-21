package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Attribute extends DomainEntity{
	
	//Constructors====================================================================================
			public Attribute() {
				super();
			}


			//Attributes=====================================================================================
			
			private String name;

			@NotBlank
			public String getName() {
				return name;
			}


			public void setName(String name) {
				this.name = name;
			}
			
			//Relationships=====================================================================================
			
			private Collection<ValueAttribute> valueAttributes;

			
			@Valid
			@OneToMany(mappedBy="attribute", cascade=CascadeType.REMOVE)
			public Collection<ValueAttribute> getValueAttributes() {
				return valueAttributes;
			}


			public void setValueAttributes(Collection<ValueAttribute> valueAttributes) {
				this.valueAttributes = valueAttributes;
			}

}
