package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Access(AccessType.PROPERTY)
public class Registration extends DomainEntity{
	
	//Attributes ====================================================================================
	
	private Date moment;
	
	//Constructor ====================================================================================
	
	public Registration(){
		super();
	
	}

	//Getters & setters ====================================================================================
	
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	//Relationships ====================================================================================
	
	
	private Contest contest;
	private Recipe recipe;

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}
