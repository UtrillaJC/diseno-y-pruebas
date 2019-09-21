
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsorship extends DomainEntity {

	private String		banner;
	private String		information;
	private CreditCard	creditCard;

	private Sponsor		sponsor;
	private Trip		tripSponsored;


	public Sponsorship() {
		super();
	}

	@URL
	public String getBanner() {
		return this.banner;
	}
	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@URL
	public String getInformation() {
		return this.information;

	}
	public void setInfo(final String information) {
		this.information = information;
	}

	@CreditCardNumber
	public CreditCard getCreditCard() {
		return this.creditCard;
	}
	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@NotNull
	@Valid
	//@ManyToOne
	public Sponsor getSponsor() {
		return this.sponsor;
	}
	public void setSponsor(final Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	@NotNull
	@Valid
	//@ManyToOne
	public Trip getTripSponsored() {
		return this.tripSponsored;
	}
	public void setTripSponsored(final Trip tripSponsored) {
		this.tripSponsored = tripSponsored;
	}

}
