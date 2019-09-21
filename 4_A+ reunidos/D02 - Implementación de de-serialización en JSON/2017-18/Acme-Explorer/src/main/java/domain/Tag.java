
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Tag extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Tag() {
		super();

		this.registers = new ArrayList<Register>();
	}


	// Attributes -------------------------------------------------------------

	private String	name;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}


	// Relationships ----------------------------------------------------------

	private Collection<Register>	registers;


	@Valid
	//	@ManyToMany
	public Collection<Register> getRegisters() {
		return this.registers;
	}

	public void setRegister(final Collection<Register> registers) {
		this.registers = registers;
	}

}
