package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Contest extends DomainEntity{

	//Attributes ====================================================================================
	
	private String title;
	private Date momentOpening;
	private Date momentClosing;
	
	//Constructor ====================================================================================
	
	public Contest(){
		super();
	}

	//Getters & setters ====================================================================================
	
	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMomentOpening() {
		return momentOpening;
	}

	public void setMomentOpening(Date momentOpening) {
		this.momentOpening = momentOpening;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMomentClosing() {
		return momentClosing;
	}

	public void setMomentClosing(Date momentClosing) {
		this.momentClosing = momentClosing;
	}
	
	//Relationships ====================================================================================
		
	private Collection<Recipe> winners;
	private Collection<Registration> registrations;

	@Valid
	@ManyToMany
	public Collection<Recipe> getWinners() {
		return winners;
	}

	public void setWinners(Collection<Recipe> winners) {
		this.winners = winners;
	}
	
	@Valid
	@OneToMany(mappedBy="contest")
	public Collection<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Collection<Registration> registrations) {
		this.registrations = registrations;
	}
}
