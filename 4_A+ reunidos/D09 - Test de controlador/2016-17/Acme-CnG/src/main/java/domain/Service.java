
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"type", "title", "description", 
	"originPlace", "destinationPlace"})})
public class Service extends CommentClass {

	//Attributes=====================================================================================

	private Type		type;
	private String		title;
	private String		description;
	private Date		moment;
	private String		originPlace;
	private String		destinationPlace;
	private Coordinates	originCoordinates;
	private Coordinates	destinationCoordinates;

	private boolean		banned;


	//Constructors====================================================================================

	public Service() {
		super();
	}

	//Getters & setters================================================================================

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getOriginPlace() {
		return this.originPlace;
	}

	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}

	@NotBlank
	public String getDestinationPlace() {
		return this.destinationPlace;
	}

	public void setDestinationPlace(String destinationPlace) {
		this.destinationPlace = destinationPlace;
	}
	
	@Valid
	@AttributeOverrides({@AttributeOverride(name="latitude", column=@Column(name="originLatitude")),
						@AttributeOverride(name="longitude", column=@Column(name="originlongitude"))})
	public Coordinates getOriginCoordinates() {
		return originCoordinates;
	}

	
	public void setOriginCoordinates(Coordinates originCoordinates) {
		this.originCoordinates = originCoordinates;
	}

	@Valid
	@AttributeOverrides({@AttributeOverride(name="latitude", column=@Column(name="destinationLatitude")),
						@AttributeOverride(name="longitude", column=@Column(name="destinationlongitude"))})
	public Coordinates getDestinationCoordinates() {
		return destinationCoordinates;
	}
	
	public void setDestinationCoordinates(Coordinates destinationCoordinates) {
		this.destinationCoordinates = destinationCoordinates;
	}

	public boolean isBanned() {
		return this.banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}


	// Relationships ====================================================================================

	private Collection<Application>	applications;
	private Customer				customer;


	@Valid
	@OneToMany(mappedBy = "service")
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(Collection<Application> applications) {
		this.applications = applications;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
