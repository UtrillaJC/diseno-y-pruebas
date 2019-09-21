package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity{
	private String banner;
	private String englishWelcome;
	private String spanishWelcome;
	private Collection<String> spamWords;
	private double vat;
	private String countryCode;
	private Collection<Tag> tag;  
	private Collection<Category> categories;
	private Collection<LegalText> legalText;
	
	private int finderCache;
	private int maxTripDisplay;
	
	
	
	public Configuration() {
		super();
		this.spamWords= new ArrayList<String>();
		this.tag= new ArrayList<Tag>();  
		this.categories=new ArrayList<Category>();
		this.legalText=new ArrayList<LegalText>();
	}
	@URL
	public String getBanner() {
		return banner;
	}
	@NotBlank
	public String getEnglishWelcome() {
		return englishWelcome;
	}
	
	@NotBlank
	public String getSpanishWelcome() {
		return spanishWelcome;
	}
	
	@NotEmpty
	public Collection<String> getSpamWords() {
		return spamWords;
	}
	
	@Digits(integer=3,fraction=2)
	@Range(min=0,max=100)
	public double getVat() {
		return vat;
	}
	
	@Pattern(regexp = "\\+\\d(1,3)$")
	public String getCountryCode() {
		return countryCode;
	}

	public Collection<Tag> getTag() {
		return tag;
	}
	@NotEmpty
	public Collection<Category> getCategories() {
		return categories;
	}
	@NotEmpty
	public Collection<LegalText> getLegalText() {
		return legalText;
	}
	
	@Range(min=0,max=100)
	public int getMaxTripDisplay() {
		return maxTripDisplay;
	}
	
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public void setEnglishWelcome(String englishWelcome) {
		this.englishWelcome = englishWelcome;
	}
	public void setSpanishWelcome(String spanishWelcome) {
		this.spanishWelcome = spanishWelcome;
	}
	public void setSpamWords(Collection<String> spamWords) {
		this.spamWords = spamWords;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public void setTag(Collection<Tag> tag) {
		this.tag = tag;
	}
	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}
	public void setLegalText(Collection<LegalText> legalText) {
		this.legalText = legalText;
	}
	public void setMaxTripDisplay(Integer maxTripDisplay) {
		this.maxTripDisplay = maxTripDisplay;
	}
	
	@Range(min=60,max=1440)
	public int getFinderCache() {
		return finderCache;
	}
	public void setFinderCache(int finderCache) {
		this.finderCache = finderCache;
	}
	
	
}
