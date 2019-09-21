/* Nutritionist.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Nutritionist extends Person{
	
	//Constructors ====================================================================================
	
	public Nutritionist() {
		super();
				
	}

	//Relationship ====================================================================================
	
	private Collection<Curriculum> curricula;
	
	@Valid
	@OneToMany(mappedBy="nutritionist")
	public Collection<Curriculum> getCurricula() {
		return curricula;
	}

	public void setCurricula(Collection<Curriculum> curricula) {
		this.curricula = curricula;
	}	
}
	
