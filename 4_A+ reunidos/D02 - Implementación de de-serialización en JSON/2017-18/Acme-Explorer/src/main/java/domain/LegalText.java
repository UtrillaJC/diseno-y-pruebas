
package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class LegalText extends DomainEntity {

	//Atributes

	private String				title;
	private String				body;
	private Collection<String>	laws;
	private Date				moment;
	private boolean				finalVersion;

	// RelationShips
	private Collection<Trip>	trips;


	public LegalText() {
		super();
		this.trips = new ArrayList<Trip>();
	}

	public boolean getFinalVersion() {
		return this.finalVersion;
	}

	public void setFinalVersion(final boolean finalVersion) {
		this.finalVersion = finalVersion;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml
	public String getBody() {
		return this.body;
	}
	public void setBody(final String body) {
		this.body = body;
	}

	@NotEmpty
	public Collection<String> getLaws() {
		return this.laws;
	}
	public void setLaws(final Collection<String> laws) {
		this.laws = laws;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy:mm")
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public boolean getVersionFinal() {
		return this.finalVersion;
	}
	public void setVersionFinal(final Boolean version) {
		this.finalVersion = version;
	}
	@NotNull
	@Valid
	//@OneToMany
	public Collection<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(final Collection<Trip> trips) {
		this.trips = trips;
	}

}
